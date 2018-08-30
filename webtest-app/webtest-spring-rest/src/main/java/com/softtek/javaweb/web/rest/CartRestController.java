package com.softtek.javaweb.web.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.exception.impl.ResourceNotAddedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.exception.impl.ResourceNotDeletedException;
import com.softtek.javaweb.exception.impl.ResourceCouldNotBeFoundException;
import com.softtek.javaweb.exception.impl.ResourceNotUpdatedException;
import com.softtek.javaweb.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartRestController {
	
	@Autowired
	CartService cartService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Cart getOne(@PathVariable Long id) throws ResourceNotAvailableException {
		return cartService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Cart> getList() throws ResourceNotAvailableException {
		return cartService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public Cart add(@RequestBody Cart cart, Principal principal) throws ResourceNotAddedException, ResourceNotAvailableException  {
		cart.setCreateUser(principal != null ? principal.getName() : "anonymous");		
		return cartService.add(cart);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Cart updatePut(@RequestBody Cart cart, @PathVariable Long id, Principal principal) throws ResourceNotUpdatedException, ResourceNotAvailableException, ResourceCouldNotBeFoundException {
		cart.setUpdateUser(principal.getName());
		cartService.updateFull(cart, id);
		return cartService.getOne(cart.getCartId());
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Cart updatePatch(@RequestBody Cart cart, @PathVariable Long id, Principal principal) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException, ResourceNotAvailableException {
		cart.setUpdateUser(principal.getName());
		cartService.updatePartial(cart, id);
		return cartService.getOne(id);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Cart delete(@PathVariable Long id) throws ResourceNotDeletedException {
		cartService.delete(id);
		return null;
	}
}