package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.exception.JavawebException;

public class DatabaseOperationException extends JavawebException {

	private static final long serialVersionUID = -210229139577707261L;

	public DatabaseOperationException() {
		super();
	}
	
	public DatabaseOperationException(final String message) {
		super(message);
	}
	
	public DatabaseOperationException(final String message, List<RestError> violations) {
		super(message, violations);
	}
}
