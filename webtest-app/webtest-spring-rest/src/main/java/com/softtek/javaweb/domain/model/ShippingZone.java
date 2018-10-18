package com.softtek.javaweb.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "shipping_zone")
public class ShippingZone implements Serializable {
	private static final long serialVersionUID = -6890251647588312394L;
	
	@Id @Column (name = "shipping_zone_id")
	private String shippingZoneId;
	
	@Column (name = "description")
	private String description;
	
	@Column (name = "delivery_time")
	private Long deliveryTime;
	
	@Column (name = "shipping_cost")
	private Float shippingCost;
	
	@OneToMany (mappedBy = "shippingZone")
	private Set<State> states = new HashSet<>();
	
	public ShippingZone(String shippingZoneId, String description, Long deliveryTime, Float shippingCost) {
		this.shippingZoneId = shippingZoneId;
		this.description = description;
		this.deliveryTime = deliveryTime;
		this.shippingCost = shippingCost;
	}
	public ShippingZone() {}
	
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
