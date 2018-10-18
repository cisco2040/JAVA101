package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.exception.JavawebException;

public class ResourceNotDeletedException extends JavawebException {

	private static final long serialVersionUID = 261307127328567541L;
	
	public ResourceNotDeletedException() {
		super();
	}
	
	public ResourceNotDeletedException(final String message) {
		super(message);
	}
	
	public ResourceNotDeletedException(final String message, List<RestError> violations) {
		super(message, violations);
	}

}
