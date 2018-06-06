package com.softtek.javaweb.domain.model;

public class User {
	
	private String username;
	private String password;
	private String name;
	private UserRole userRole;
	private String active;

	public User(String username, String password, String name, UserRole userRole, String active) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.userRole = userRole;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", userRole=" + userRole.toString()
				+ ", active=" + active + "]";
	}
}
