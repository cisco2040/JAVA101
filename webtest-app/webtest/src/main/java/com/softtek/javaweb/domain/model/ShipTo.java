package com.softtek.javaweb.domain.model;

public class ShipTo {
	
	private Long shipToId;
	private User user;
	private String name;
	private String address;
	private City city;
	private Long zipcode;
	private String phone;
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
		return "shipTo [shipToId=" + shipToId + ", user=" + user.toString() + ", name=" + name + ", address=" + address + ", city="
				+ city.toString() + ", zipcode=" + zipcode + ", phone=" + phone + ", email=" + email + "]";
	}
}
