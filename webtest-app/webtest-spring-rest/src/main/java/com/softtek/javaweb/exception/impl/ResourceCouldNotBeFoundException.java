package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.exception.JavawebException;

public class ResourceCouldNotBeFoundException extends JavawebException {

	private static final long serialVersionUID = 261307127328567541L;
	
	public ResourceCouldNotBeFoundException() {
		super();
	}
	
	public ResourceCouldNotBeFoundException(final String message) {
		super(message);
	}
	
	public ResourceCouldNotBeFoundException(final String message, List<String> violations) {
		super(message, violations);
	}

}
