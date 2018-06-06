package com.softtek.javaweb.domain.model;

import java.util.Date;

public class Cart {

	private Long cartId;
	private Float linesAmount;
	private Float shippingAmount;
	private Float cartAmount;
	private ShipTo shipTo;
	private Status status;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	
	public Cart(Long cartId, Float linesAmount, Float shippingAmount, Float cartAmount, ShipTo shipTo, Status status,
			String createUser, Date createDate, String updateUser, Date updateDate) {
		this.cartId = cartId;
		this.linesAmount = linesAmount;
		this.shippingAmount = shippingAmount;
		this.cartAmount = cartAmount;
		this.shipTo = shipTo;
		this.status = status;
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
	}

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", linesAmount=" + linesAmount + ", shippingAmount=" + shippingAmount
				+ ", cartAmount=" + cartAmount + ", shipTo=" + shipTo.toString() + ", status=" + status.toString() + ", createUser="
				+ createUser + ", createDate=" + createDate + ", updateUser=" + updateUser + ", updateDate="
				+ updateDate + "]";
	}	
}
