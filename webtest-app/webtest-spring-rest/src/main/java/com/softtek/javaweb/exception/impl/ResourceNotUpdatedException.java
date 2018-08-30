package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.exception.JavawebException;

public class ResourceNotUpdatedException extends JavawebException {

	private static final long serialVersionUID = 8237927878856570043L;

	public ResourceNotUpdatedException() {
		super();
	}
	
	public ResourceNotUpdatedException(final String message) {
		super(message);
	}
	
	public ResourceNotUpdatedException(final String message, List<String> violations) {
		super(message, violations);
	}

}
