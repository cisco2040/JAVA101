package com.softtek.javaweb.web.rest;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.softtek.javaweb.domain.json.view.OrderView;
import com.softtek.javaweb.domain.model.Order;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.service.filter.OrderFilter;
import com.softtek.javaweb.service.jpa.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);

	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, params = "couponId")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(OrderView.Public.class)
	public Order applyCoupon(@RequestBody Order order, @RequestParam ("couponId") String code) throws ResourceNotAvailableException, ResourceNotUpdatedException {
		return orderService.previewCoupon(order, code);
	}
	
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@JsonView(OrderView.Public.class)
	public List<Order> search(@ModelAttribute OrderFilter of, BindingResult results) throws IncorrectParametersException, ParseException, ResourceNotAvailableException {
		return orderService.searchOrders(of, results);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@JsonView(OrderView.Public.class)
	public Order getOne(@PathVariable Long id) throws ResourceNotAvailableException, DatabaseOperationException {
		return orderService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@JsonView(OrderView.Public.class)
	public List<Order> getList() throws ResourceNotAvailableException {
		return orderService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(OrderView.Public.class)
	public Order add(@RequestBody Order order, Principal principal) throws ResourceNotAddedException, ResourceNotUpdatedException {
		order.setCreateUser(principal != null ? principal.getName() : "anonymous");
		return orderService.add(order);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@JsonView(OrderView.Public.class)
	public Order updatePut(@RequestBody Order order, @PathVariable Long id, Principal principal) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		order.setUpdateUser(principal != null ? principal.getName() : "anonymous");
		return orderService.updateFull(order, id);
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@JsonView(OrderView.Public.class)
	public Order updatePatch(@RequestBody Order order, @PathVariable Long id, Principal principal) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException, IncorrectParametersException {
		order.setUpdateUser(principal != null ? principal.getName() : "anonymous");		
		return orderService.updatePartial(order, id);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Order delete(@PathVariable Long id) throws ResourceNotDeletedException, DatabaseOperationException {
		orderService.delete(id);
		return null;
	}
}