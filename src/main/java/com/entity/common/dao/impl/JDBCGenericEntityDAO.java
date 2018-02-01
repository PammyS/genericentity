package com.entity.common.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.sql.DataSource;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.common.dao.EntitySchemaDAO;
import com.entity.common.dao.GenericEntityDAO;
import com.entity.common.model.EntitySchema;
import com.entity.common.model.GenericEntity;

public class JDBCGenericEntityDAO implements GenericEntityDAO {

	private DataSource dataSource;

	public JDBCGenericEntityDAO() {
		setDataSource();
	}

	public void setDataSource() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		this.dataSource = dataSource;

	}

	@Override
	public String insert(GenericEntity entity) {
		// TODO Auto-generated method stub
		String entityName = entity.getEntity_name();
		EntitySchemaDAO entitySchemaDAO = new JDBCEntitySchemaDAO();
		EntitySchema schema = entitySchemaDAO.get(entityName);
		String jsonData = null;
		if (schema == null)
			throw new RuntimeException("Given Schema type not registered");
		try {
			jsonData = parseJson(entity.getJson_data(), schema.getJson_schema()).toString();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		catch (Exception e1) {			
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		String sql = "INSERT INTO entity " + "(id, entity_name, json_data) VALUES (?, ?, ?)";
		Connection conn = null;
		UUID id = UUID.randomUUID();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, id.toString());
			ps.setString(2, entityName);
			ps.setString(3, jsonData);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return id.toString();
	}

	@Override
	public GenericEntity get(String id) {
		String sql = "SELECT * FROM entity WHERE id = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			GenericEntity entity = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				entity = new GenericEntity(rs.getString("entity_name"), rs.getString("json_data"));
			}
			rs.close();
			ps.close();
			return entity;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public GenericEntity delete(String id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM entity WHERE id = ?";

		Connection conn = null;

		try {
			GenericEntity entity = get(id);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.execute();
			ps.close();
			return entity;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public GenericEntity update(GenericEntity genericEntity, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	GenericData.Record parseJson(String json, String schema) throws IOException, Exception {
		Schema avroSchema = new Schema.Parser().parse(schema);
		InputStream input = new ByteArrayInputStream(json.getBytes());
		DataInputStream din = new DataInputStream(input);
		Decoder decoder = DecoderFactory.get().jsonDecoder(avroSchema, din);
		DatumReader<GenericData.Record> reader = new GenericDatumReader<GenericData.Record>(avroSchema);
		return reader.read(null, decoder);
	}

}
