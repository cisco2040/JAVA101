package com.softtek.javaweb.domain.model;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.softtek.javaweb.domain.json.view.OrderView;
/**
 * Represents an order entity.
 * @author victor.cortes
 *
 */
@Entity @Table (name = "orders")
public class Order extends Auditable {

	private static final long serialVersionUID = 1798127777172953073L;

	@Id @Column (name = "order_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonView(OrderView.Public.class)
	private Long orderId;
	
	@NotNull @OneToOne @JoinColumn (name = "cart_id")
	@JsonView(OrderView.Public.class)
	private Cart cart;
	
	@Column (name = "order_date")
	@JsonView(OrderView.Public.class)
	@JsonFormat(pattern = "yyyy-MM-dd hh:MM:SS")
	private Timestamp orderDate;
	
	@Column (name = "schedule_date")	
	@JsonView(OrderView.Public.class)
	@JsonFormat(pattern = "yyyy-MM-dd hh:MM:SS")
	private Timestamp scheduleDate;
	
	@Column (name = "delivery_date")
	@JsonView(OrderView.Public.class)
	@JsonFormat(pattern = "yyyy-MM-dd hh:MM:SS")
	private Timestamp deliveryDate;
	
	@Column (name = "cancellation_date")
	@JsonView(OrderView.Public.class)
	@JsonFormat(pattern = "yyyy-MM-dd hh:MM:SS")
	private Timestamp cancellationDate;
	
	@NotNull @Column (name = "lines_amount")
	@JsonView(OrderView.Public.class)
	private Float linesAmount;
	
	@Column (name = "shipping_amount")
	@JsonView(OrderView.Public.class)
	private Float shippingAmount;
	
	@Column (name = "order_amount")
	@JsonView(OrderView.Public.class)
	private Float orderAmount;
	
	@ManyToOne @JoinColumn (name = "payment_method_id")
	@JsonView(OrderView.Public.class)
	private PaymentMethod paymentMethod;
	
	@Column (name = "payment_reference")
	@JsonView(OrderView.Public.class)
	private String paymentReference;
	
	@Column (name = "notes")
	@JsonView(OrderView.Public.class)
	private String notes;
	
	@ManyToOne @JoinColumn (name = "status_id")
	@NotNull @JsonView(OrderView.Public.class)
	private Status status;
	
	@ManyToOne @JoinColumn (name = "coupon_id")
	@JsonView(OrderView.Public.class)
	private Coupon coupon;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Timestamp getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(Timestamp scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Timestamp getCancellationDate() {
		return cancellationDate;
	}
	public void setCancellationDate(Timestamp cancellationDate) {
		this.cancellationDate = cancellationDate;
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
	public Float getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Float orderAmount) {
		this.orderAmount = orderAmount;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getPaymentReference() {
		return paymentReference;
	}
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	
	public Order(String createUser, Timestamp createDate, String updateUser, Timestamp updateDate, Long orderId,
			@NotNull Cart cart, Timestamp orderDate, Timestamp scheduleDate, Timestamp deliveryDate,
			Timestamp cancellationDate, Float linesAmount, Float shippingAmount, Float orderAmount,
			PaymentMethod paymentMethod, String paymentReference, String notes, @NotNull Status status, Coupon coupon) {
		super(createUser, createDate, updateUser, updateDate);
		this.orderId = orderId;
		this.cart = cart;
		this.orderDate = orderDate;
		this.scheduleDate = scheduleDate;
		this.deliveryDate = deliveryDate;
		this.cancellationDate = cancellationDate;
		this.linesAmount = linesAmount;
		this.shippingAmount = shippingAmount;
		this.orderAmount = orderAmount;
		this.paymentMethod = paymentMethod;
		this.paymentReference = paymentReference;
		this.notes = notes;
		this.status = status;
		this.coupon = coupon;
	}
	
	public Order() {}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", cart=" + cart + ", orderDate=" + orderDate + ", scheduleDate="
				+ scheduleDate + ", deliveryDate=" + deliveryDate + ", cancellationDate=" + cancellationDate
				+ ", linesAmount=" + linesAmount + ", shippingAmount=" + shippingAmount + ", orderAmount=" + orderAmount
				+ ", paymentMethod=" + paymentMethod + ", paymentReference=" + paymentReference + ", notes=" + notes
				+ ", status=" + status + ", coupon=" + coupon + ", toString()=" + super.toString() + "]";
	}

	
}
