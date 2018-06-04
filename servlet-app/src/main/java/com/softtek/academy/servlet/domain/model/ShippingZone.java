package com.softtek.academy.servlet.domain.model;

public class ShippingZone {
	private String shippingZoneId;
	private String description;
	private Long deliveryTime;
	private Float shippingCost;
	
	public ShippingZone(String shippingZoneId, String description, Long deliveryTime, Float shippingCost) {
		this.shippingZoneId = shippingZoneId;
		this.description = description;
		this.deliveryTime = deliveryTime;
		this.shippingCost = shippingCost;
	}
	
	public String getShippingZoneId() {
		return shippingZoneId;
	}
	public void setShippingZoneId(String shippingZoneId) {
		this.shippingZoneId = shippingZoneId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Float getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(Float shippingCost) {
		this.shippingCost = shippingCost;
	}

	@Override
	public String toString() {
		return "ShippingZone [shippingZoneId=" + shippingZoneId + ", description=" + description + ", deliveryTime="
				+ deliveryTime + ", shippingCost=" + shippingCost + "]";
	}

}
