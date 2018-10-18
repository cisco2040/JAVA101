package com.softtek.javaweb.repository.jpa;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softtek.javaweb.domain.model.Cart;

public interface CartRepository extends BaseRepository<Cart, Long> {
	List<Cart> findByStatus_StatusId (Long statusId);
	List<Cart> findByCartAmountLessThanEqual (Float cartAmount);
	List<Cart> findByCartAmountGreaterThanEqual (Float cartAmount);
	List<Cart> findByCartAmountGreaterThanEqualAndCartAmountLessThanEqual (Float cartAmountMin, Float cartAmountMax);
	@Query("select c from Cart c where c.createDate between :start and :end")
	List<Cart> findByCreateDateRange (@Param ("start") Timestamp start, @Param ("end") Timestamp end);
}
