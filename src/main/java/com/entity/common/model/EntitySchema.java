package com.entity.common.model;

public class EntitySchema {
	
	String name;
	String json_schema;
	
	public EntitySchema()
	{
		
	}
	
	public EntitySchema(String name, String json_schema) {
		this.name = name;
		this.json_schema = json_schema;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJson_schema() {
		return json_schema;
	}
	public void setJson_schema(String json_schema) {
		this.json_schema = json_schema;
	}
	
}
