package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.exception.JavawebException;

public class ResourceNotAddedException extends JavawebException {

	private static final long serialVersionUID = -4505697194281438347L;

	public ResourceNotAddedException() {
		super();
	}
	
	public ResourceNotAddedException(final String message) {
		super(message);
	}
	
	public ResourceNotAddedException(final String message, List<RestError> violations) {
		super(message, violations);
	}

}
