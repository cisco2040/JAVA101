package com.softtek.javaweb.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestError implements Serializable {
	
	private static final long serialVersionUID = 3637632373972559935L;
	
	@NotNull
	private String field;
	@NotNull
	private String value;
	@NotNull
	private String message;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public RestError(@NotNull String field, @NotNull String value, @NotNull String message) {
		this.field = field;
		this.value = value;
		this.message = message;
	}
	public RestError() {}
	
	@Override
	public String toString() {
		return "RestErrors [field=" + field + ", value=" + value + ", message=" + message + "]";
	}
	
}
