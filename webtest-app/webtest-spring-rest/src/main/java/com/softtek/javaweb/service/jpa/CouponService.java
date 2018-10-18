package com.softtek.javaweb.service.jpa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.CouponStatus;
import com.softtek.javaweb.domain.model.Coupon;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.repository.jpa.CouponRepository;
import com.softtek.javaweb.service.types.CouponState;
/**
 * Provides methods for Coupon entity operations and DB access.
 * @author victor.cortes
 *
 */
@Service
public class CouponService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CouponRepository couponRepository;

	/**
	 * Obtain list of all coupons from DB.
	 * @return List&lt;Coupon&gt; - list of all coupons
	 * @throws ResourceNotAvailableException - thrown if no entities found.
	 */
	public List<Coupon> getList() throws ResourceNotAvailableException {
		List<Coupon> coupons = this.couponRepository.findAll();
		LOGGER.info("## Coupon List Obtained: {}", coupons);
		if (coupons.isEmpty()) {
			throw new ResourceNotAvailableException("No coupons were found.");
		}
		return coupons;
	}
/**
 * Gets a single Coupon entity from DB based on provided key
 * @param id - the Coupon code being requested
 * @return Coupon entity, if found
 * @throws ResourceNotAvailableException - thrown if entity is not found.
 */
	public Coupon getOne(final String id) throws ResourceNotAvailableException {
		Coupon coupon = this.couponRepository.findById(id).orElse(null);
		LOGGER.info("## Coupon Obtained: {}", coupon);
		if (coupon == null) {
			throw new ResourceNotAvailableException("Coupon id <" + id + "> not found.");
		}
		return coupon;
	}
/**
 * Validates coupon code. Checks existence, valid from and expire dates against current date, and redeem status. 
 * @param code - the coupon code being validated
 * @return a CouponStatus object
 */
	public CouponStatus validate(final String code) {
		Optional<Coupon> coupon = couponRepository.findById(code);		
		Timestamp currentDate = new Timestamp (new Date().getTime());
		CouponStatus couponStatus = new CouponStatus(CouponState.VALID, "", null);

		if (!coupon.isPresent()) {
			couponStatus.setStatus(CouponState.NON_EXISTENT);
			couponStatus.setMessage("Coupon code " + code + " is not valid.");
		}
		if (coupon.get().isRedeemed()) {
			couponStatus.setStatus(CouponState.REDEEMED);
			couponStatus.setMessage("Coupon " + code + " has already been redeemed.");
		} else if (currentDate.compareTo(coupon.get().getValidFrom()) < 0) {
			couponStatus.setStatus(CouponState.NOT_ACTIVE);
			couponStatus.setMessage("Coupon " + code + " is not active.");
		} else if (currentDate.compareTo(coupon.get().getExpiresOn()) > 0) {
			couponStatus.setStatus(CouponState.EXPIRED);
			couponStatus.setMessage("Coupon " + code + " is expired.");
		} else {
			couponStatus.setStatus(CouponState.VALID);
			couponStatus.setCoupon(coupon.get());
		}	
		
		return couponStatus;
	}
/**
 * Returns whether coupon is valid or not based no valid from and expire dates, and redeem status
 * @param code - coupon to be updated
 * @return boolean
 */
	public boolean isValid(final String code) {
		return validate(code).getStatus() == CouponState.VALID ? true : false;
	}
/**
 * Set status of coupon to redeemed=true
 * @param code - coupon code to be updated
 * @return updated Coupon entity
 */
	public Coupon redeem(String code) {
		CouponStatus couponStatus = validate(code);		
		
		if (couponStatus.getStatus() == CouponState.VALID) {
			couponStatus.getCoupon().setRedeemed(true);
			couponRepository.save(couponStatus.getCoupon());
			return couponStatus.getCoupon();
		}
		
		return null;
	}
}
