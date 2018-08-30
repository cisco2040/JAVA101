package com.softtek.javaweb.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class User {
	
	@NotNull (message = "<username> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=20, message = "<username> {org.hibernate.validator.constraints.Length.message}")
	private String username;
	@JsonIgnore
	@NotNull (message = "<password> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=20, message = "<password> {org.hibernate.validator.constraints.Length.message}")
	private String password;
	@NotNull (message = "<name> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=100, message = "<name> {org.hibernate.validator.constraints.Length.message}")
	private String name;
	@NotNull (message = "<userRole> {javax.validation.constraints.NotNull.message}")
	@Valid
	private UserRole userRole;
	@NotNull (message = "<active> {javax.validation.constraints.NotNull.message}")
	@Pattern (regexp = "[SN]", message = "<active> flag must be either 'S' or 'N'.")
	private String active;

	public User(String username, String password, String name, UserRole userRole, String active) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.userRole = userRole;
		this.active = active;
	}
	public User() {}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
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
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", userRole=" + (userRole != null ? userRole.toString() : null) + ", active=" + active + "]";
	}
}
