package com.softtek.javaweb.domain.model;

public class State {
	private Long stateId;
	private String description;
	private ShippingZone shippingZone;
	
	
	public State(Long stateId, String description, ShippingZone shippingZone) {
		this.stateId = stateId;
		this.description = description;
		this.shippingZone = shippingZone;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ShippingZone getShippingZone() {
		return shippingZone;
	}
	public void setShippingZone(ShippingZone shippingZone) {
		this.shippingZone = shippingZone;
	}
	@Override
	public String toString() {
		return "State [stateId=" + stateId + ", description=" + description + ", shippingZone=" + shippingZone.toString() + "]";
	}	
}
