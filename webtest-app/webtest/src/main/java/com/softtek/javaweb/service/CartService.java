package com.softtek.javaweb.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.repository.CartRepository;

public class CartService {
	
	private CartRepository cartRepository = new CartRepository();
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	public static final String NEW = "New Record";
	public static final String UPDATE = "Update Record";
	
	public List<Cart> getList() {
		List<Cart> carts = this.cartRepository.list();
		LOGGER.info("## Cart List Obtained: {}", carts);
		return carts;
	}

	public Cart getOne(final Long id) {
		Cart cart = this.cartRepository.getOne(id);
		LOGGER.info("## Cart Obtained: {}", cart.toString());
		return cart;
	}
	
	public ValidateService update(final Cart cart) {
		ValidateService validateCart = validate(cart, CartService.UPDATE);		
		if (validateCart.isValid()) {
			Cart calculatedCart = calculateCart (cart, CartService.UPDATE);
			LOGGER.info("## Attempting to update cart: {}", calculatedCart);
			if (this.cartRepository.update(calculatedCart) <= 0) {
				validateCart.setValid(false);
				validateCart.appendServiceMsg("There was an unknown error while attempting to update record. Please contact DBAdmin.");				
			}
		}
		return validateCart;
	}
	public ValidateService add(final Cart cart) {
		ValidateService validateCart = validate(cart, CartService.NEW);
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
	public ValidateService delete (final Long id) {
		ValidateService validateCart = new ValidateService();
		validateCart.setValid(true);
		if (this.cartRepository.delete(id) <= 0) {
			validateCart.setValid(false);
			validateCart.appendServiceMsg("There was an unknown error while attempting to delete record, or record does not exist. Please contact DBAdmin.");
		}
				
		return validateCart;
	}
	
	public ValidateService validate (final Cart cart, final String action) {
		ValidateService validateService = new ValidateService();
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
		if (cart.getUpdateUser() == null && action == CartService.UPDATE) {
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
		if (action == CartService.UPDATE) {
			calculatedCart.setUpdateDate(new Timestamp(System.currentTimeMillis()));			
		}
		LOGGER.info("## Calculated cart: {}", cart);
		
		return calculatedCart;
	}
}