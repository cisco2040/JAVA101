package com.softtek.javaweb.domain.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShipToForm {

	private Long shipToId;
	private String username;
	private String name;
	private String address;
	private Long cityId;
	private Long zipcode;
	private String phone;
	private String email;
	
	public ShipToForm(Long shipToId, String username, String name, String address, Long cityId, Long zipcode,
			String phone, String email) {
		super();
		this.shipToId = shipToId;
		this.username = username;
		this.name = name;
		this.address = address;
		this.cityId = cityId;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
	}
	public ShipToForm() {}
	public Long getShipToId() {
		return shipToId;
	}
	public void setShipToId(Long shipToId) {
		this.shipToId = shipToId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
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
		return "ShipToForm [shipToId=" + shipToId + ", username=" + username + ", name=" + name + ", address=" + address
				+ ", cityId=" + cityId + ", zipcode=" + zipcode + ", phone=" + phone + ", email=" + email + "]";
	}


}
