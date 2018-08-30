package com.softtek.javaweb.domain.model;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

@XmlRootElement
public class UserRole {
	
	@NotNull (message = "<userRoleId> must be present.")
	@Length (min=1, max=5, message = "<userRoleId> length must be between {min} and {max}.")
	private String userRoleId;
	@Length (min=1, max=20, message = "<description> length must be between {min} and {max}.")
	private String description;
	
	public UserRole(String userRoleId, String description) {
		this.userRoleId = userRoleId;
		this.description = description;
	}
	public UserRole() {}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", description=" + description + "]";
	}	
}
