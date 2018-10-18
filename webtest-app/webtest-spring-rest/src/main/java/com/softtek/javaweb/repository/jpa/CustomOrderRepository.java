package com.softtek.javaweb.repository.jpa;

import java.util.List;

import com.softtek.javaweb.domain.model.Order;

public interface CustomOrderRepository {
	List<Order> findByNameStatusSorted (String createUser, Long statusId);
}
