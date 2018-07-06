package com.softtek.javaweb.domain.model;

public class UserRole {
	
	private String userRoleId;
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
