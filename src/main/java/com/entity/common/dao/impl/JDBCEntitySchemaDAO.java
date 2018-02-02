package com.entity.common.dao.impl;


import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.entity.common.dao.EntitySchemaDAO;
import com.entity.common.model.EntitySchema;


public class JDBCEntitySchemaDAO implements EntitySchemaDAO{

	private DataSource dataSource;
	
	public JDBCEntitySchemaDAO() {
		setDataSource();
	}

	public void setDataSource() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		this.dataSource = dataSource;
		
	}

	@Override
	public String insert(EntitySchema entitySchema) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO entity_schema " +
				"(name, json_schema) VALUES (?, ?)";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entitySchema.getName());
			ps.setString(2, entitySchema.getJson_schema());			
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return entitySchema.getName();
	}

	@Override
	public EntitySchema get(String name) {
		String sql = "SELECT * FROM entity_schema WHERE name = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			EntitySchema entitySchema = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				entitySchema = new EntitySchema(					
					rs.getString("name"),
					rs.getString("json_schema")
				);
			}
			rs.close();
			ps.close();
			return entitySchema;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	

	@Override
	public EntitySchema delete(String name) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM entity_schema WHERE name = ?";

		Connection conn = null;

		try {
			EntitySchema entitySchema = get(name);
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.execute();
			ps.close();
			return entitySchema;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public String update(EntitySchema entitySchema) {
		String name = entitySchema.getName();
		delete(name);
		return insert(entitySchema);	
	}
	

}
