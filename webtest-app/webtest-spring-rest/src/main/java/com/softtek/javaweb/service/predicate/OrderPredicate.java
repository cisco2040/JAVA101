package com.softtek.javaweb.service.predicate;

import java.sql.Timestamp;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.softtek.javaweb.domain.model.QOrder;
import com.softtek.javaweb.service.filter.OrderFilter;

public class OrderPredicate {
	
	private OrderPredicate () {}
	
	public static Predicate buildSearchPredicate (OrderFilter of) {
		QOrder order = QOrder.order;
		BooleanExpression expression = order.isNotNull();
		
		if (of.getCartId() != null) {
			expression = expression.and(order.cart.cartId.eq(of.getCartId()));
		}
		if (of.getCreateDateStart() != null ) {
			expression = expression.and(order.createDate.goe(new Timestamp(of.getCreateDateStart().getTime())));						
		}
		if (of.getCreateDateEnd() != null ) {
			expression = expression.and(order.createDate.goe(new Timestamp(of.getCreateDateEnd().getTime())));						
		}
		if (of.getOrderAmountMin() != null ) {
			expression = expression.and(order.orderAmount.goe(of.getOrderAmountMin()));
		}
		if (of.getOrderAmountMax() != null ) {
			expression = expression.and(order.orderAmount.loe(of.getOrderAmountMax()));
		}
		if (of.getStatusId() != null ) {
			expression = expression.and(order.status.statusId.eq(of.getStatusId()));
		}
		if (of.getUpdateUser() != null) {
			expression = expression.and(order.updateUser.contains(of.getUpdateUser()));
		}
		if (of.getPaymentMethodId() != null) {
			expression = expression.and(order.paymentMethod.paymentMethodId.eq(of.getPaymentMethodId()));
		}
		
		return expression;
	}
}
