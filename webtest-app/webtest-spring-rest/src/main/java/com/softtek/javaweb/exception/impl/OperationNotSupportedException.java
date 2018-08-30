package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.exception.JavawebException;

public class OperationNotSupportedException extends JavawebException {

	private static final long serialVersionUID = 5710643751583344032L;

	public OperationNotSupportedException() {
		super();
	}
	
	public OperationNotSupportedException(final String message) {
		super(message);
	}
	
	public OperationNotSupportedException(final String message, List<String> violations) {
		super(message, violations);
	}

}
