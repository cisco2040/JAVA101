package com.softtek.javaweb.service.filter;

import java.sql.Date;

public class OrderFilter {
	
	Long cartId;
	Date createDateStart;
	Date createDateEnd;
	Float orderAmountMin;
	Float orderAmountMax;
	String paymentMethodId;
	Long statusId;
	String updateUser;	
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Date getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}
	public Date getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public Float getOrderAmountMin() {
		return orderAmountMin;
	}
	public void setOrderAmountMin(Float orderAmountMin) {
		this.orderAmountMin = orderAmountMin;
	}
	public Float getOrderAmountMax() {
		return orderAmountMax;
	}
	public void setOrderAmountMax(Float orderAmountMax) {
		this.orderAmountMax = orderAmountMax;
	}
	public String getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public OrderFilter(Long cartId, String createDateRange, Date createDateStart, Date createDateEnd,
			String orderAmountRange, Float orderAmountMin, Float orderAmountMax, String paymentMethodId, Long statusId,
			String updateUser) {
		this.cartId = cartId;
		this.createDateStart = createDateStart;
		this.createDateEnd = createDateEnd;
		this.orderAmountMin = orderAmountMin;
		this.orderAmountMax = orderAmountMax;
		this.paymentMethodId = paymentMethodId;
		this.statusId = statusId;
		this.updateUser = updateUser;
	}
	public OrderFilter() {}
	
	@Override
	public String toString() {
		return "OrderFilter [cartId=" + cartId + ", createDateStart=" + createDateStart + ", createDateEnd=" + createDateEnd 
				+ ", orderAmountMin=" + orderAmountMin + ", orderAmountMax=" + orderAmountMax + ", paymentMethodId="
				+ paymentMethodId + ", statusId=" + statusId + ", updateUser=" + updateUser + "]";
	}
	
}
