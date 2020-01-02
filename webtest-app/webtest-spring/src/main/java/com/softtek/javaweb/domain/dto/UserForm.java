package com.softtek.javaweb.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserForm {

	private String username;
	private String password;
	private String passwordConfirm;
	private String name;
	private String userRoleId;
	private String active;

	public UserForm() {}
	public UserForm(String username, String password, String name, String userRoleId, String active) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.userRoleId = userRoleId;
		this.active = active;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "UserForm [username=" + username + ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", name=" + name + ", userRoleId="
				+ userRoleId + ", active=" + active + "]";
	}
}
