package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.ShippingZone;
import com.softtek.javaweb.exception.impl.OperationNotSupportedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.service.jpa.ShippingZoneService;

@RestController
@RequestMapping("/shippingZones")
public class ShippingZoneRestController {
	
	@Autowired
	ShippingZoneService shippingZoneService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ShippingZone getOne(@PathVariable String id) throws ResourceNotAvailableException {
		return shippingZoneService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<ShippingZone> getList() throws ResourceNotAvailableException {
		return shippingZoneService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public ShippingZone add(@RequestBody ShippingZone shippingZone) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("POST operation not supported for Shipping Zone resource");
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePut(@RequestBody ShippingZone shippingZone, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PUT operation not supported for Shipping Zone resource");
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePatch(@RequestBody ShippingZone shippingZone, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PATCH operation not supported for Shipping Zone resource");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("DELETE operation not supported for Shipping Zone resource");
	}
}