package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.exception.JavawebException;

public class ResourceNotUpdatedException extends JavawebException {

	private static final long serialVersionUID = 8237927878856570043L;

	public ResourceNotUpdatedException() {
		super();
	}
	
	public ResourceNotUpdatedException(final String message) {
		super(message);
	}
	
	public ResourceNotUpdatedException(final String message, final List<RestError> violations) {
		super(message, violations);
	}
}
