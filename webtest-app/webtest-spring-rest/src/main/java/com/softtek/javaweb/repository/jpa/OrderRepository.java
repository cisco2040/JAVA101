package com.softtek.javaweb.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.softtek.javaweb.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order>, CustomOrderRepository {
}
