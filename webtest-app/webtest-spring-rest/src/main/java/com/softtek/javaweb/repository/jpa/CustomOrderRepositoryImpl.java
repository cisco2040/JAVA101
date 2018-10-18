package com.softtek.javaweb.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.softtek.javaweb.domain.model.Order;
import com.softtek.javaweb.domain.model.QOrder;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Order> findByNameStatusSorted(String createUser, Long statusId) {
		QOrder order = QOrder.order;
		JPAQuery<Order> q = new JPAQueryFactory(em).selectFrom(order);
		return q.where(order.createUser.contains(createUser)
				.and(order.status.statusId.goe(statusId)))
				.orderBy(order.status.statusId.asc()).fetch();
	}
}
