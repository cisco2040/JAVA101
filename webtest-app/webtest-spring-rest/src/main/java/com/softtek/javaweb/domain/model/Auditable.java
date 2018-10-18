package com.softtek.javaweb.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Auditable implements Serializable {

	private static final long serialVersionUID = -6563517948766761635L;

	@Column (name = "create_user")
	private String createUser;
	
	@Column (name = "create_date")
	private Timestamp createDate;
	
	@Column (name = "update_user")
	private String updateUser;
	
	@Column (name = "update_date")
	private Timestamp updateDate;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Auditable(String createUser, Timestamp createDate, String updateUser, Timestamp updateDate) {
		super();
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
	}

	public Auditable() {
		super();
	}	
}
