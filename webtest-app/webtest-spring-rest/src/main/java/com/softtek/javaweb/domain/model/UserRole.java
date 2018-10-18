package com.softtek.javaweb.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

@XmlRootElement
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = -3751881669422373644L;

	@NotNull (message = "<userRoleId> must be present.")
	@Length (min=1, max=5, message = "<userRoleId> length must be between {min} and {max}.")
	@Id @Column(name = "user_role_id")
	private String userRoleId;
	
	@Length (min=1, max=20, message = "<description> length must be between {min} and {max}.")
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
