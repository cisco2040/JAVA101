package com.softtek.javaweb.domain.model;

public class Status {

	private Long statusId;
	private String description;
	private String statusType;
	
	public Status(Long statusId, String description, String statusType) {
		this.statusId = statusId;
		this.description = description;
		this.statusType = statusType;
	}
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", description=" + description + ", statusType=" + statusType + "]";
	}	
}
