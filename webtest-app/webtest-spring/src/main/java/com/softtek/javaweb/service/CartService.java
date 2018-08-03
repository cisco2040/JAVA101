package com.softtek.javaweb.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.CartForm;
import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.mapper.EntityMapper;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.service.types.UpdateType;

@Service
public class CartService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	MyRepository<Cart,Long> cartRepository;
	@Autowired
	ShipToService shipToService;

	public List<Cart> getList() {
		List<Cart> carts = cartRepository.list();
		LOGGER.info("## Cart List Obtained: {}", carts);
		return carts;
	}

	public Cart getOne(final Long id) {
		Cart cart = cartRepository.getOne(id);
		LOGGER.info("## Cart Obtained: {}", cart);
		return cart;
	}
	
	public ResponseStatus update(final Cart cart) {
		ResponseStatus validateCart = validate(cart, UpdateType.MODIFY);		
		if (validateCart.isValid()) {
			Cart calculatedCart = calculateCart (cart, UpdateType.MODIFY);
			LOGGER.info("## Attempting to update cart: {}", calculatedCart);
			if (cartRepository.update(calculatedCart) <= 0) {
				validateCart.setValid(false);
				validateCart.appendServiceMsg("There was an unknown error while attempting to update record. Please contact DBAdmin.");				
			}
		}
		return validateCart;
	}

	public ResponseStatus update(final CartForm cartForm) {
		return this.update(EntityMapper.makeCartFromForm(cartForm));
	}
	
	public ResponseStatus add(final Cart cart) {
		ResponseStatus validateCart = validate(cart, UpdateType.NEW);
		if (validateCart.isValid()) {
			Cart calculatedCart = calculateCart (cart, UpdateType.NEW);
			LOGGER.info("## Attempting to add cart: {}", cart);
			if (cartRepository.add(calculatedCart) <= 0) {
				validateCart.setValid(false);
				validateCart.appendServiceMsg("There was an unknown error while attempting to add record. Please contact DBAdmin.");				
			}
		}
		return validateCart;
	}

	public ResponseStatus add(final CartForm cartForm) {
		return this.add(EntityMapper.makeCartFromForm(cartForm));
	}
	
	public ResponseStatus delete (final Long id) {
		ResponseStatus validateCart = new ResponseStatus();
		validateCart.setValid(true);
		if (cartRepository.delete(id) <= 0) {
			validateCart.setValid(false);
			validateCart.appendServiceMsg("There was an unknown error while attempting to delete record, or record does not exist. Please contact DBAdmin.");
		}
				
		return validateCart;
	}
	
	public ResponseStatus validate (final Cart cart, final UpdateType action) {
		ResponseStatus validateService = new ResponseStatus();
		validateService.setValid(true);

		LOGGER.info("## Validating cart: {}", cart);

		if (cart.getLinesAmount() == null || cart.getLinesAmount().toString().equals(StringUtils.EMPTY) ) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Line Amount is mandatory.");
		}
		if (cart.getShipTo().getShipToId() == null || cart.getShipTo().getShipToId().toString().equals(StringUtils.EMPTY) ) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Ship-To ID is mandatory.");
		}
		if (cart.getStatus().getStatusId() == null || cart.getStatus().getStatusId().toString().equals(StringUtils.EMPTY)) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Status is mandatory.");
		}
		if (cart.getCreateUser() == null || cart.getCreateUser().equals(StringUtils.EMPTY)) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Create User is mandatory.");
		}
		if ((cart.getUpdateUser() == null || cart.getUpdateUser().equals(StringUtils.EMPTY)) && action == UpdateType.MODIFY) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Update User is mandatory.");
		}
		
		LOGGER.info("## Validating cart status: {}", validateService.isValid());
		LOGGER.info("## Validating cart status messages: {}", validateService.getServiceMsg());
		
		return validateService;
	}	

	private Cart calculateCart(Cart cart, UpdateType action) {
		Cart calculatedCart = cart;
		LOGGER.info("## Attempting to calculate cart: {}", cart);
		calculatedCart.setShippingAmount(shipToService.getOne(calculatedCart.getShipTo().getShipToId()).getCity().getState().getShippingZone().getShippingCost());
		calculatedCart.setCartAmount(calculatedCart.getLinesAmount() + calculatedCart.getShippingAmount());
		if (action == UpdateType.NEW) {
			calculatedCart.setCreateDate(new Timestamp(System.currentTimeMillis()));
		}
		if (action == UpdateType.MODIFY) {
			calculatedCart.setUpdateDate(new Timestamp(System.currentTimeMillis()));			
		}
		LOGGER.info("## Calculated cart: {}", cart);
		
		return calculatedCart;
	}
}