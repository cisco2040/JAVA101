package com.softtek.javaweb.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtek.javaweb.domain.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, String> {
}
