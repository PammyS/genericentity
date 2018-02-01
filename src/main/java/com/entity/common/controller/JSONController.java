package com.entity.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.common.dao.EntitySchemaDAO;
import com.entity.common.dao.impl.JDBCEntitySchemaDAO;
import com.entity.common.model.CustomResponse;
import com.entity.common.model.EntitySchema;

@Controller
@RequestMapping("/entity")
public class JSONController {

//	@RequestMapping(value = "{name}", method = RequestMethod.GET)
//	public @ResponseBody
//	Shop getShopInJSON(@PathVariable String name) {
//
//		Shop shop = new Shop();
//		shop.setName(name);
//		shop.setStaffName(new String[] { "mkyong1", "mkyong2" });
//
//		return shop;
//
//	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public @ResponseBody Hello getHello() {
		Hello hello = new Hello("hiiiii");
		return hello;
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public @ResponseBody Hello setHello(@RequestBody String str) {
		Hello hello = new Hello(str);
		return hello;
	}
	
	@RequestMapping(value = "/register-entity-schema", method = RequestMethod.POST)
	public @ResponseBody CustomResponse setHello(@RequestBody EntitySchema schema) {
		EntitySchemaDAO entitySchemaDAO = new JDBCEntitySchemaDAO();
		String schemaName = entitySchemaDAO.insert(schema);
		CustomResponse resp = new CustomResponse(true, schemaName)  ;
		return resp;
	}
	
}




class Hello {
String str;

Hello(String str) {
	this.str = str;
}

public String getStr() {
	return str;
}

public void setStr(String str) {
	this.str = str;
}
}