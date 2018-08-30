package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.exception.JavawebException;

public class ResourceNotAvailableException extends JavawebException {

	private static final long serialVersionUID = 432858082705481542L;

	public ResourceNotAvailableException() {
		super();
	}
	
	public ResourceNotAvailableException(final String message) {
		super(message);
	}
	
	public ResourceNotAvailableException(final String message, List<String> violations) {
		super(message, violations);
	}

}
