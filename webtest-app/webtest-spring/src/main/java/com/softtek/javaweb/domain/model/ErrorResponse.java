package com.softtek.javaweb.domain.model;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Integer errorCode;
	String errorMessage;
	
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ErrorResponse(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public ErrorResponse() {
	}
	
	@Override
	public String toString() {
		return "ErrorResponse [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
}
