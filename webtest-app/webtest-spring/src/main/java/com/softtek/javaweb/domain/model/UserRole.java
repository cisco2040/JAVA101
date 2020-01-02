package com.softtek.javaweb.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Proxy;

@XmlRootElement
@Entity
@Proxy(lazy = false)
@Table(name = "user_role")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = -3751881669422373644L;

	@NotNull (message = "<userRoleId> must be present.")
	@Id @Column(name = "user_role_id")
	private String userRoleId;
	
	@Column(name = "description")
	private String description;

	@OneToMany (mappedBy = "userRole")
	private Set<User> users = new HashSet<>();
	
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
