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
import com.softtek.javaweb.exception.impl.IncorrectParametersException;
import com.softtek.javaweb.exception.impl.ResourceCouldNotBeFoundException;
import com.softtek.javaweb.exception.impl.ResourceNotUpdatedException;
import com.softtek.javaweb.service.jpa.CartService;

@RestController
@RequestMapping("/carts")
public class CartRestController {
	
	@Autowired
	CartService cartService;

	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, params = {"status"})
	@ResponseStatus(HttpStatus.OK)
	public List<Cart> getByStatus(@RequestParam ("status") Long status) throws ResourceNotAvailableException {
		return cartService.getByStatus(status);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, params = {"maxCartAmount"})
	@ResponseStatus(HttpStatus.OK)
	public List<Cart> getByMaxCartAmount(@RequestParam ("maxCartAmount") Float cartAmount) throws ResourceNotAvailableException {
		return cartService.getByMaxCartAmount(cartAmount);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, params = {"minCartAmount"})
	@ResponseStatus(HttpStatus.OK)
	public List<Cart> getByMinCartAmount(@RequestParam ("minCartAmount") Float cartAmount) throws ResourceNotAvailableException {
		return cartService.getByMinCartAmount(cartAmount);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, params = {"minCartAmount", "maxCartAmount"})
	@ResponseStatus(HttpStatus.OK)
	public List<Cart> getByMinCartAmount(@RequestParam("minCartAmount") Float min, @RequestParam("maxCartAmount") Float max) throws ResourceNotAvailableException, IncorrectParametersException {
		return cartService.getByRangeCartAmount(min, max);
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, params = {"createStart", "createEnd"})
	@ResponseStatus(HttpStatus.OK)
	public List<Cart> getByCreateDateRange(@RequestParam("createStart") String start, @RequestParam("createEnd") String end) throws ResourceNotAvailableException, IncorrectParametersException {
		return cartService.getByCreateDateRange(start, end);
	}	
	
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
	public Cart add(@RequestBody Cart cart, Principal principal) throws ResourceNotAddedException {
		cart.setCreateUser(principal != null ? principal.getName() : "anonymous");
		return cartService.add(cart);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Cart updatePut(@RequestBody Cart cart, @PathVariable Long id, Principal principal) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		cart.setUpdateUser(principal != null ? principal.getName() : "anonymous");
		return cartService.updateFull(cart, id);
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Cart updatePatch(@RequestBody Cart cart, @PathVariable Long id, Principal principal) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		cart.setUpdateUser(principal != null ? principal.getName() : "anonymous");		
		return cartService.updatePartial(cart, id);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Cart delete(@PathVariable Long id) throws ResourceNotDeletedException {
		cartService.delete(id);
		return null;
	}
}