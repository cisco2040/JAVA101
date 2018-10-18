package com.softtek.javaweb.exceptionhandling.types;

public enum RestStatusCodes {
	C101_NOT_FOUND(101L, "Resource not found."),
	C102_NOT_AVAILABLE(102L, "Resource not available."),
	C201_NOT_ADDED(201L, "Resource could not be added."),
	C202_NOT_UPDATED(202L, "Resource could not be updated."),
	C301_NOT_DELETED(301L, "Resource could not be deleted."),
	C401_NOT_ALLOWED(401L, "Operation not allowed."),
	C501_DATA_INTEGRITY_ERROR(601L, "Database integrity violation."),
	C601_INCORRECT_PARAMS(501L, "Incorrect parameters.");
	
	Long statusCode;
	String statusMessage;
	
	RestStatusCodes (Long code, String msg) {
		this.statusCode = code;
		this.statusMessage = msg;
	}

	public Long getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}    
}
