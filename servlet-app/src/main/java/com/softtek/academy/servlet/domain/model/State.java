package com.softtek.academy.servlet.domain.model;

import com.google.common.base.Objects;

public class State {
	
	private Long id;
	private String description; 
	private String shippingZoneId;
	private ShippingZone shippingZone;
		
	public State(Long id, String description, String shippingZoneId) {
		this.id = id;
		this.description = description;
		this.shippingZoneId = shippingZoneId;
	}	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getShippingZoneId() {
		return shippingZoneId;
	}

	public void setShippingZoneId(String shippingZoneId) {
		this.shippingZoneId = shippingZoneId;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", description=" + description + ", shippingZoneId=" + shippingZoneId + "]";
	}
	
}
