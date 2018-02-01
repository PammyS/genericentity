package com.entity.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.common.dao.EntitySchemaDAO;
import com.entity.common.dao.GenericEntityDAO;
import com.entity.common.dao.impl.JDBCEntitySchemaDAO;
import com.entity.common.dao.impl.JDBCGenericEntityDAO;
import com.entity.common.model.CustomResponse;
import com.entity.common.model.EntitySchema;
import com.entity.common.model.GenericEntity;

@Controller
@RequestMapping("/entity")
public class JSONController {

	@RequestMapping(value = "/entity-schema/register", method = RequestMethod.POST)
	public @ResponseBody CustomResponse putSchema(@RequestBody EntitySchema schema) {
		EntitySchemaDAO entitySchemaDAO = new JDBCEntitySchemaDAO();
		String schemaName = entitySchemaDAO.insert(schema);
		CustomResponse resp = new CustomResponse(true, schemaName)  ;
		return resp;
	}
	
	@RequestMapping(value = "/entity-schema/get", method = RequestMethod.POST)
	public @ResponseBody EntitySchema getSchema(@RequestBody String name) {
		EntitySchemaDAO entitySchemaDAO = new JDBCEntitySchemaDAO();
		EntitySchema schema = entitySchemaDAO.get(name);		
		return schema;
	}

	@RequestMapping(value = "/entity-schema/delete", method = RequestMethod.POST)
	public @ResponseBody EntitySchema deleteSchema(@RequestBody String name) {
		EntitySchemaDAO entitySchemaDAO = new JDBCEntitySchemaDAO();
		EntitySchema schema = entitySchemaDAO.delete(name);		
		return schema;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody CustomResponse createEntity(@RequestBody GenericEntity entity) {
		GenericEntityDAO entityDAO = new JDBCGenericEntityDAO();
		String id = entityDAO.insert(entity);
		CustomResponse resp = new CustomResponse(true, id)  ;
		return resp;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody GenericEntity getEntity(@RequestBody String id) {
		GenericEntityDAO entityDAO = new JDBCGenericEntityDAO();
		GenericEntity entity = entityDAO.get(id);		
		return entity;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody GenericEntity deleteEntity(@RequestBody String id) {
		GenericEntityDAO entityDAO = new JDBCGenericEntityDAO();
		GenericEntity entity = entityDAO.get(id);		
		return entity;
	}
	
}