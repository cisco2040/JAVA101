package com.softtek.javaweb.domain.dto;

import com.softtek.javaweb.domain.model.Coupon;
import com.softtek.javaweb.service.types.CouponState;

public class CouponStatus {
	CouponState status;
	String message;
	Coupon coupon;
	
	public CouponState getStatus() {
		return status;
	}
	public void setStatus(CouponState status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	
	public CouponStatus(CouponState status, String message, Coupon coupon) {
		this.status = status;
		this.message = message;
		this.coupon = coupon;
	}
	public CouponStatus() {}
	
	@Override
	public String toString() {
		return "CouponStatus [status=" + status + ", message=" + message + ", coupon=" + coupon + "]";
	}
	
	
}
