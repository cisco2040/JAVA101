package com.softtek.javaweb.exception.impl;

import java.util.List;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.exception.JavawebException;

public class IncorrectParametersException extends JavawebException {

	private static final long serialVersionUID = 6641859077651997091L;

	public IncorrectParametersException() {
		super();
	}
	
	public IncorrectParametersException(final String message) {
		super(message);
	}
	
	public IncorrectParametersException(final String message, List<RestError> violations) {
		super(message, violations);
	}
}
