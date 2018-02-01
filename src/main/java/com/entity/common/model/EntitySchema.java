package com.entity.common.model;
import org.apache.avro.Schema;

public class EntitySchema {
	
	String name;
	Schema json_schema;
	
	public EntitySchema()
	{
		
	}
	
	public EntitySchema(String name, String json_schema) {
		Schema schema = new Schema.Parser().parse(json_schema);
		this.name = name;
		this.json_schema = schema;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJson_schema() {
		return json_schema.toString();
	}
	public void setJson_schema(String json_schema) {
		Schema schema = new Schema.Parser().parse(json_schema);
		this.json_schema = schema;
	}
	
}
