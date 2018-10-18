package com.softtek.javaweb.domain.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 9141276168135546112L;
	
	@NotNull @Length (min=1, max=20)
	@Id @Column (name = "username")
	private String username;
	
	@NotNull @Length (min=1, max=20)
	@Column (name = "password")
	private String password;
	
	@NotNull @Length (min=1, max=100)
	@Column (name = "name")
	private String name;
	
	@NotNull @Valid	@ManyToOne
	@JoinColumn (name = "user_role_id")
	private UserRole userRole;
	
	@NotNull @Pattern (regexp = "[SN]", message = "must be either 'S' or 'N'.")
	@Column (name = "active")
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
