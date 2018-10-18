package com.softtek.javaweb.domain.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.validation.constraints.Email;
import javax.xml.bind.annotation.*;

import org.hibernate.validator.constraints.*;

@XmlRootElement
@Entity
@Table (name = "ship_to")
public class ShipTo implements Serializable{
	
	private static final long serialVersionUID = -2947717631120715353L;

	@Id @Column (name = "ship_to_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long shipToId;

	@NotNull @ManyToOne @JoinColumn (name = "user")
	private User user;
	
	@NotNull @Length (min=1, max=100)
	@Column (name = "name")
	private String name;
	
	@NotNull @Length (min=1, max=250)
	@Column (name = "address")
	private String address;
	
	@NotNull @ManyToOne @JoinColumn (name = "city_id")
	private City city;
	
	@NotNull @Column (name = "zip_code")
	private Long zipcode;
	
	@NotNull @Length (min=1, max=20)
	@Column (name = "phone")
	private String phone;
	
	@NotNull @Length (min=1, max=100)
	@Email @Column (name = "email")
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
