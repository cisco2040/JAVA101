package com.softtek.javaweb.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.repository.CartRepository;

public class CartService {
	
	private CartRepository cartRepository = new CartRepository();
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	public static final String NEW = "New Record";
	public static final String MODIFY = "Update Record";
	
	public List<Cart> getList() {
		List<Cart> carts = this.cartRepository.list();
		LOGGER.info("## Cart List Obtained: {}", carts);
		return carts;
	}

	public Cart getOne(final Long id) {
		Cart cart = this.cartRepository.getOne(id);
		LOGGER.info("## Cart Obtained: {}", cart);
		return cart;
	}
	
	public ResponseStatus update(final Cart cart) {
		ResponseStatus validateCart = validate(cart, CartService.MODIFY);		
		if (validateCart.isValid()) {
			Cart calculatedCart = calculateCart (cart, CartService.MODIFY);
			LOGGER.info("## Attempting to update cart: {}", calculatedCart);
			if (this.cartRepository.update(calculatedCart) <= 0) {
				validateCart.setValid(false);
				validateCart.appendServiceMsg("There was an unknown error while attempting to update record. Please contact DBAdmin.");				
			}
		}
		return validateCart;
	}
	public ResponseStatus add(final Cart cart) {
		ResponseStatus validateCart = validate(cart, CartService.NEW);
		if (validateCart.isValid()) {
			Cart calculatedCart = calculateCart (cart, CartService.NEW);
			LOGGER.info("## Attempting to add cart: {}", cart);
			if (this.cartRepository.add(calculatedCart) <= 0) {
				validateCart.setValid(false);
				validateCart.appendServiceMsg("There was an unknown error while attempting to add record. Please contact DBAdmin.");				
			}
		}
		return validateCart;
	}
	public ResponseStatus delete (final Long id) {
		ResponseStatus validateCart = new ResponseStatus();
		validateCart.setValid(true);
		if (this.cartRepository.delete(id) <= 0) {
			validateCart.setValid(false);
			validateCart.appendServiceMsg("There was an unknown error while attempting to delete record, or record does not exist. Please contact DBAdmin.");
		}
				
		return validateCart;
	}
	
	public ResponseStatus validate (final Cart cart, final String action) {
		ResponseStatus validateService = new ResponseStatus();
		validateService.setValid(true);

		LOGGER.info("## Validating cart: {}", cart);

		if (cart.getLinesAmount() == null ) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Line Amount is mandatory.");
		}
		if (cart.getShipTo().getShipToId() == null ) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Ship-To ID is mandatory.");
		}
		if (cart.getStatus().getStatusId() == null ) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Status is mandatory.");
		}
		if (cart.getCreateUser() == null ) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Create User is mandatory.");
		}
		if (cart.getUpdateUser() == null && action == CartService.MODIFY) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Update User is mandatory.");
		}
		
		LOGGER.info("## Validating cart status: {}", validateService.isValid());
		LOGGER.info("## Validating cart status messages: {}", validateService.getServiceMsg());
		
		return validateService;
	}	
	private Cart calculateCart(Cart cart, String action) {
		Cart calculatedCart = cart;
		ShipToService shipToService = new ShipToService();
		LOGGER.info("## Attempting to calculate cart: {}", cart);
		calculatedCart.setShippingAmount(shipToService.getOne(calculatedCart.getShipTo().getShipToId()).getCity().getState().getShippingZone().getShippingCost());
		calculatedCart.setCartAmount(calculatedCart.getLinesAmount() + calculatedCart.getShippingAmount());
		if (action == CartService.NEW) {
			calculatedCart.setCreateDate(new Timestamp(System.currentTimeMillis()));
		}
		if (action == CartService.MODIFY) {
			calculatedCart.setUpdateDate(new Timestamp(System.currentTimeMillis()));			
		}
		LOGGER.info("## Calculated cart: {}", cart);
		
		return calculatedCart;
	}
}