package com.softtek.javaweb.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.softtek.javaweb.domain.json.view.OrderView;

@XmlRootElement @Entity @Table (name = "cart")
public class Cart extends Auditable implements Serializable {

	private static final long serialVersionUID = -3448409870911306718L;

	@Id @Column (name = "cart_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonView(OrderView.Public.class)
	private Long cartId;

	@NotNull @Column (name = "lines_amount")
	private Float linesAmount;
	
	@Column (name = "shipping_amount")
	private Float shippingAmount;
	
	@Column (name = "cart_amount")
	private Float cartAmount;
	
	@NotNull @Valid @ManyToOne @JoinColumn (name = "ship_to_id")
	private ShipTo shipTo;
	
	@NotNull @Valid @ManyToOne @JoinColumn (name = "status_id")
	private Status status;
	
	public Cart(Long cartId, Float linesAmount, Float shippingAmount, Float cartAmount, ShipTo shipTo, Status status,
			String createUser, Timestamp createDate, String updateUser, Timestamp updateDate) {
		super(createUser, createDate, updateUser, updateDate);
		this.cartId = cartId;
		this.linesAmount = linesAmount;
		this.shippingAmount = shippingAmount;
		this.cartAmount = cartAmount;
		this.shipTo = shipTo;
		this.status = status;
	}
	public Cart() {}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Float getLinesAmount() {
		return linesAmount;
	}

	public void setLinesAmount(Float linesAmount) {
		this.linesAmount = linesAmount;
	}

	public Float getShippingAmount() {
		return shippingAmount;
	}

	public void setShippingAmount(Float shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public Float getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(Float cartAmount) {
		this.cartAmount = cartAmount;
	}

	public ShipTo getShipTo() {
		return shipTo;
	}

	public void setShipTo(ShipTo shipTo) {
		this.shipTo = shipTo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", linesAmount=" + linesAmount + ", shippingAmount=" + shippingAmount
				+ ", cartAmount=" + cartAmount + ", shipTo=" + shipTo + ", status=" + status + ", getCreateUser()="
				+ getCreateUser() + ", getCreateDate()=" + getCreateDate() + ", getUpdateUser()=" + getUpdateUser()
				+ ", getUpdateDate()=" + getUpdateDate() + "]";
	}	
}
