package com.softtek.javaweb.domain.model;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import org.hibernate.validator.constraints.*;

@XmlRootElement
public class Status {

	@NotNull (message = "<statusId> {javax.validation.constraints.NotNull.message}")
	private Long statusId;
	@NotNull (message = "<description> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=100, message = "<description> {org.hibernate.validator.constraints.Length.message}")
	private String description;
	@NotNull (message = "<statusType> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=20, message = "<statusType> {org.hibernate.validator.constraints.Length.message}")
	private String statusType;
	
	public Status(Long statusId, String description, String statusType) {
		this.statusId = statusId;
		this.description = description;
		this.statusType = statusType;
	}
	public Status() {}
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
