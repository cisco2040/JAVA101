package com.softtek.javaweb.domain.dto;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CartForm {

	private Long cartId;
	private Float linesAmount;
	private Float shippingAmount;
	private Float cartAmount;
	private Long shipToId;
	private Long statusId;
	private String createUser;
	private Timestamp createDate;
	private String updateUser;

	public CartForm() {}	
	public CartForm(Long cartId, Float linesAmount, Float shippingAmount, Float cartAmount, Long shipToId,
			Long statusId, String createUser, Timestamp createDate, String updateUser) {
		super();
		this.cartId = cartId;
		this.linesAmount = linesAmount;
		this.shippingAmount = shippingAmount;
		this.cartAmount = cartAmount;
		this.shipToId = shipToId;
		this.statusId = statusId;
		this.createUser = createUser;
		this.setCreateDate(createDate);
		this.updateUser = updateUser;
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
	public Long getShipToId() {
		return shipToId;
	}
	public void setShipToId(Long shipToId) {
		this.shipToId = shipToId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	@Override
	public String toString() {
		return "CartForm [cartId=" + cartId + ", linesAmount=" + linesAmount + ", shippingAmount=" + shippingAmount
				+ ", cartAmount=" + cartAmount + ", shipToId=" + shipToId + ", statusId=" + statusId + ", createUser="
				+ createUser + "createDate =" + createDate + "updateUser=" + updateUser + "]";
	}
}
