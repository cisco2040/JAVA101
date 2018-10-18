package com.softtek.javaweb.repository.jpa.dto;

public class RestSearchCriteria {
	
	private String key;
	private String operation;
	private Object value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	public RestSearchCriteria(String key, String operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "RestSearchCriteria [key=" + key + ", operation=" + operation + ", value=" + value.toString() + "]";
	}	
}
