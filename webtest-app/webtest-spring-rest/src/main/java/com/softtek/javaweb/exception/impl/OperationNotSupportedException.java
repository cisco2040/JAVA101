package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.exception.JavawebException;

public class OperationNotSupportedException extends JavawebException {

	private static final long serialVersionUID = 5710643751583344032L;

	public OperationNotSupportedException() {
		super();
	}
	
	public OperationNotSupportedException(final String message) {
		super(message);
	}
	
	public OperationNotSupportedException(final String message, List<RestError> violations) {
		super(message, violations);
	}

}
