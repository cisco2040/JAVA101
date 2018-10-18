package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.Coupon;
import com.softtek.javaweb.exception.impl.OperationNotSupportedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.service.jpa.CouponService;

@RestController
@RequestMapping("/coupons")
public class CouponRestController {
	
	@Autowired
	CouponService couponService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Coupon getOne(@PathVariable String id) throws ResourceNotAvailableException {
		return couponService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> getList() throws ResourceNotAvailableException {
		return couponService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public Coupon add(@RequestBody Coupon coupon) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("POST operation not supported for Coupon resource");
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePut(@RequestBody Coupon coupon, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PUT operation not supported for Coupon resource");
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePatch(@RequestBody Coupon coupon, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PATCH operation not supported for Coupon resource");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("DELETE operation not supported for Coupon resource");
	}
}