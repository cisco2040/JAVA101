package com.softtek.javaweb.domain.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonView;
import com.softtek.javaweb.domain.json.view.OrderView;

@XmlRootElement @Entity @Table(name = "payment_method")
public class PaymentMethod implements Serializable {

	private static final long serialVersionUID = -529659986973532411L;

	@Id
	@Column (name = "payment_method_id")
	@JsonView(OrderView.Public.class)
	private String paymentMethodId;
	@Column (name = "description")
	@Length (min=0, max=100, message = "<paymentMethod.description> {org.hibernate.validator.constraints.Length.message}")
	private String description;
	
	public String getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PaymentMethod(String paymentMethodId, String description) {
		this.paymentMethodId = paymentMethodId;
		this.description = description;
	}
	
	public PaymentMethod() {}
	
	@Override
	public String toString() {
		return "PaymentMethod [paymentMethodId=" + paymentMethodId + ", description=" + description + "]";
	}
}
