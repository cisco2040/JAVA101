package com.softtek.javaweb.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseStatus {
	public static final Logger LOGGER = LoggerFactory.getLogger(ResponseStatus.class);

	private List<String> serviceMsg = new ArrayList<>();
	private boolean valid;
	
	public List<String> getServiceMsg() {
		return serviceMsg;
	}
	public void appendServiceMsg(String message) {
		LOGGER.info("Attempting to add new message: {}", message);
		this.serviceMsg.add(message);
		LOGGER.info("New message added: {}", this.serviceMsg);
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
