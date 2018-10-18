package com.softtek.javaweb.domain.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "state")
public class State implements Serializable{

	private static final long serialVersionUID = -6811116062954706319L;
	
	@Id @Column (name = "state_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long stateId;
	
	@Column (name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn (name = "shipping_zone_id")
	private ShippingZone shippingZone;
	
	public State(Long stateId, String description, ShippingZone shippingZone) {
		this.stateId = stateId;
		this.description = description;
		this.shippingZone = shippingZone;
	}
	
	public State() {}
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
		return "State [stateId=" + stateId + ", description=" + description + ", shippingZone=" + (shippingZone != null ? shippingZone.toString() : null) + "]";
	}	
}
