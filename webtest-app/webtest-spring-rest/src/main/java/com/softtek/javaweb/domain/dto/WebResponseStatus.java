package com.softtek.javaweb.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebResponseStatus {
	public static final Logger LOGGER = LoggerFactory.getLogger(WebResponseStatus.class);

	private List<String> serviceMsg = new ArrayList<>();
	private boolean valid;
	private boolean present;
	
	public List<String> getServiceMsg() {
		return serviceMsg;
	}
	public void setServiceMsg(List<String> serviceMsg) {
		this.serviceMsg = serviceMsg;
	}
	public void appendServiceMsg(String message) {
		this.serviceMsg.add(message);
		LOGGER.info("New message added: {}", this.serviceMsg);
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	
}
