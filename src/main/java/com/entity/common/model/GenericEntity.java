package com.entity.common.model;

public class GenericEntity {
	String entity_name;
	String json_data;
	
	public GenericEntity(String entity_name, String json_data) {
		this.entity_name = entity_name;
		this.json_data = json_data;
	}
	
	public GenericEntity() {
		
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public String getJson_data() {
		return json_data;
	}

	public void setJson_data(String json_data) {
		this.json_data = json_data;
	}	
	
	
}
