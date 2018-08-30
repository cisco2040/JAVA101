package com.softtek.javaweb.exception;

import java.util.List;

public class JavawebException extends Exception {
	
	private static final long serialVersionUID = -1245496375701440441L;
	private List<String> violations;
	
	public JavawebException() {
		super();
	}
	
	public JavawebException(final String message) {
		super(message);
	}
	
	public JavawebException(final String message, List<String> violations) {
		super(message);
		this.violations = violations;
	}

	public void setViolations(List<String> violations) {
		this.violations = violations;
	}

	public List<String> getViolations() {
		return violations;
	}
}
