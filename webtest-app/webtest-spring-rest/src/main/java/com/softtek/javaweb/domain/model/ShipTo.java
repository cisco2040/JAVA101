package com.softtek.javaweb.domain.model;

import javax.validation.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import javax.xml.bind.annotation.*;

import org.hibernate.validator.constraints.*;

@XmlRootElement
public class ShipTo {
	
	private Long shipToId;
	@NotNull (message = "<username> {javax.validation.constraints.NotNull.message}")
	private User user;
	@NotNull (message = "<name> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=100, message = "<name> {org.hibernate.validator.constraints.Length.message}")
	private String name;
	@NotNull (message = "<address> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=250, message = "<name> {org.hibernate.validator.constraints.Length.message}")
	private String address;
	@NotNull (message = "<city> {javax.validation.constraints.NotNull.message}")
	private City city;
	@NotNull (message = "<zipcode> {javax.validation.constraints.NotNull.message}")
	private Long zipcode;
	@NotNull (message = "<phone> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=20, message = "<phone> {org.hibernate.validator.constraints.Length.message}")
	private String phone;
	@NotNull (message = "<email> {javax.validation.constraints.NotNull.message}")
	@Length (min=1, max=100, message = "<email> {org.hibernate.validator.constraints.Length.message}")
	@Email
	private String email;

	public ShipTo(Long shipToId, User user, String name, String address, City city, Long zipcode, String phone,
			String email) {
		this.shipToId = shipToId;
		this.user = user;
		this.name = name;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
	}
	public ShipTo() {}
	
	public Long getShipToId() {
		return shipToId;
	}
	public void setShipToId(Long shipToId) {
		this.shipToId = shipToId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Long getZipcode() {
		return zipcode;
	}
	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "shipTo [shipToId=" + shipToId + ", user=" + (user != null ? user.toString() : null) + ", name=" + name + ", address=" + address + ", city="
				+ (city != null ? city.toString() : null) + ", zipcode=" + zipcode + ", phone=" + phone + ", email=" + email + "]";
	}
}
