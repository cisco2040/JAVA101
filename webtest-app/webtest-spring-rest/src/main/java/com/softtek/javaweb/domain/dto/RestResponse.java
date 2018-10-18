package com.softtek.javaweb.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
public class RestResponse {

	@NotNull
	Long statusCode;
	@NotNull
	String message;
	String description;
	List<String> errors;
	List<RestError> fieldErrors;

	public RestResponse(Long statusCode, String message, String description, List<String> errors) {
		this.statusCode = statusCode;
		this.message = message;
		this.description = description;
		this.errors = errors;
	}
	public RestResponse() {}
	
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonInclude(Include.NON_EMPTY)
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	@JsonInclude(Include.NON_EMPTY)
	public List<RestError> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<RestError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	@Override
	public String toString() {
		return "RestResponse [statusCode=" + statusCode + ", message=" + message + ", description=" + description
				+ ", errors=" + errors + "]";
	}	
}
