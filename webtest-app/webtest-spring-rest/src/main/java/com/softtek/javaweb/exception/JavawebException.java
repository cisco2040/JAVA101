package com.softtek.javaweb.exception;

import java.util.List;

import com.softtek.javaweb.domain.dto.RestError;

public class JavawebException extends Exception {
	
	private static final long serialVersionUID = -1245496375701440441L;
	
	private List<RestError> restErrors;

	public List<RestError> getRestErrors() {
		return restErrors;
	}

	public void setRestErrors(List<RestError> restErrors) {
		this.restErrors = restErrors;
	}
	
	public JavawebException() {
		super();
	}
	
	public JavawebException(final String message) {
		super(message);
	}
	
	public JavawebException(final String message, final List<RestError> restErrors) {
		super(message);
		this.restErrors = restErrors;
	}
}
