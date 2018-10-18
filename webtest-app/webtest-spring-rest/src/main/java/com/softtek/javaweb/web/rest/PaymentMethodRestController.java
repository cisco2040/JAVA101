package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.PaymentMethod;
import com.softtek.javaweb.exception.impl.OperationNotSupportedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.service.jpa.PaymentMethodService;

@RestController
@RequestMapping("/paymentMethods")
public class PaymentMethodRestController {
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PaymentMethod getOne(@PathVariable String id) throws ResourceNotAvailableException {
		return paymentMethodService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<PaymentMethod> getList() throws ResourceNotAvailableException {
		return paymentMethodService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethod add(@RequestBody PaymentMethod paymentMethod) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("POST operation not supported for State resource");
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePut(@RequestBody PaymentMethod paymentMethod, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PUT operation not supported for State resource");
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePatch(@RequestBody PaymentMethod paymentMethod, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PATCH operation not supported for State resource");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("DELETE operation not supported for State resource");
	}
}