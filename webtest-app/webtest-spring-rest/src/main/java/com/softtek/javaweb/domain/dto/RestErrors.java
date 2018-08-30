package com.softtek.javaweb.domain.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestErrors {
	@NotNull
	private Long statusCode;
	@NotNull
	private String field;
	@NotNull
	private String message;
	
	public RestErrors(@NotNull Long statusCode, @NotNull String field, @NotNull String message) {
		super();
		this.statusCode = statusCode;
		this.field = field;
		this.message = message;
	}
	
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}		
}
