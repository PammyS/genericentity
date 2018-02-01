package com.entity.common.model;

public class CustomResponse {
	boolean status;
	String responseData;
	
	
	public CustomResponse() {
		
	}
	
	public CustomResponse(boolean status, String responseData) {
		this.status = status;
		this.responseData = responseData;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	
}
