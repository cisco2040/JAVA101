package com.softtek.javaweb.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
import com.softtek.javaweb.domain.json.view.OrderView;

@Entity
@Table (name = "coupon")
public class Coupon implements Serializable {
	
	private static final long serialVersionUID = 108094995578831312L;

	@Id @Column (name = "coupon_id", length = 8, unique = true)
	@JsonView(OrderView.Public.class)
	String couponId;
	@Column (name = "valid_from")
	Timestamp validFrom;
	@Column (name = "expires_on")
	Timestamp expiresOn;
	@Column (name = "discount_percent")
	@JsonView(OrderView.Public.class)
	@JsonInclude(Include.NON_NULL)
	Float discountPercent;
	@Column (name = "discount_amount")
	@JsonView(OrderView.Public.class)
	@JsonInclude(Include.NON_NULL)
	Float discountAmount;
	@Column (name = "redeemed")
	Boolean redeemed;
	
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public Timestamp getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}
	public Timestamp getExpiresOn() {
		return expiresOn;
	}
	public void setExpiresOn(Timestamp expiresOn) {
		this.expiresOn = expiresOn;
	}
	public Float getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Float discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Float getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Float discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Boolean isRedeemed() {
		return redeemed;
	}
	public void setRedeemed(Boolean redeemed) {
		this.redeemed = redeemed;
	}
	public Coupon(String couponId, Timestamp validFrom, Timestamp expiresOn, Float discountPercent,
			Float discountAmount, Boolean redeemed) {
		this.couponId = couponId;
		this.validFrom = validFrom;
		this.expiresOn = expiresOn;
		this.discountPercent = discountPercent;
		this.discountAmount = discountAmount;
		this.redeemed = redeemed;
	}
	public Coupon() {}
	
	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", validFrom=" + validFrom + ", expiresOn=" + expiresOn
				+ ", discountPercent=" + discountPercent + ", discountAmount=" + discountAmount + ", redeemed="
				+ redeemed + "]";
	}	
}

