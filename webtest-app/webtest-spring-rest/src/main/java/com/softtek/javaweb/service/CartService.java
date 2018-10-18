package com.softtek.javaweb.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.domain.dto.WebResponseStatus;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.exceptionhandling.MyValidation;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.ShipToRepository;
import com.softtek.javaweb.service.types.UpdateType;

@Service
public class CartService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	MyRepository<Cart,Long> cartRepository;
	@Autowired
	ShipToRepository shipToRepository;

	public List<Cart> getList() throws ResourceNotAvailableException {
		List<Cart> carts = cartRepository.list();
		LOGGER.info("## Cart List Obtained: {}", carts);
		if (carts.isEmpty()) {
			throw new ResourceNotAvailableException("No carts were found.");
		}
		return carts;
	}

	public Cart getOne(final Long id) throws ResourceNotAvailableException {
		Cart cart = cartRepository.getOne(id);
		LOGGER.info("## Cart Obtained: {}", cart);
		if (cart == null) {
			throw new ResourceNotAvailableException("Cart id <" + id + "> not found.");
		}
		return cart;
	}
	
	public Cart updateFull(final Cart cart, final Long id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		Cart fullCart = cart;
		fullCart.setCartId(id);
		return this.update(fullCart);
	}

	public Cart updatePartial(final Cart cart, final Long id) throws ResourceCouldNotBeFoundException, ResourceNotUpdatedException {
		Cart fullCart = this.cartRepository.getOne(id);
		
		if (fullCart == null) {
			throw new ResourceCouldNotBeFoundException("Cart id <" + id + "> not found.");
		}
		
		Cart newCart = mergeBeans(cart, fullCart);
		WebResponseStatus validateCart = validate(newCart, UpdateType.MODIFY);

		if (validateCart.isValid()) {
			LOGGER.info("## Attempting to update (PATCH) cart: {}, with elements: {}", id, cart);
			Cart calculatedCart = calculateCart (newCart, UpdateType.MODIFY);
			if (this.cartRepository.update(calculatedCart) == null) {
				throw new ResourceNotUpdatedException("Could not update cart due to unknown problems during persist.");
			}
		} else {
			throw new ResourceNotUpdatedException("Could not update cart due to missing/incorrect data.", MyValidation.validateBean(newCart));
		}
		
		return cartRepository.getOne(id);
	}

	public Cart update(final Cart cart) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		WebResponseStatus validateCart = validate(cart, UpdateType.MODIFY);
		
		if (!isUnique(cart)) {
			if (validateCart.isValid()) {
				LOGGER.info("## Attempting to update cart: {}", cart);
				Cart calculatedCart = calculateCart (cart, UpdateType.MODIFY);
				if (this.cartRepository.update(calculatedCart) == null) {
					throw new ResourceNotUpdatedException("Could not update cart due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotUpdatedException("Could not update cart due to missing/incorrect data.", MyValidation.validateBean(cart));
			}
		} else {
			throw new ResourceCouldNotBeFoundException("Cart id <" + cart.getCartId() + "> not found.");
		}

		return cartRepository.getOne(cart.getCartId());
	}

	public Cart add(final Cart cart) throws ResourceNotAddedException {
		WebResponseStatus validateCart = validate(cart, UpdateType.NEW);
		Cart newCart;
		
		if (validateCart.isValid()) {
			Cart calculatedCart = calculateCart (cart, UpdateType.NEW);
			LOGGER.info("## Attempting to add cart: {}", calculatedCart);
			newCart = this.cartRepository.add(calculatedCart);
			if ( newCart == null) {
				throw new ResourceNotAddedException("Could not add cart due to unknown problems during persist.");
			}
		} else {
			throw new ResourceNotAddedException("Could not add cart due to missing/incorrect data.", MyValidation.validateBean(cart));			
		}

		return newCart;
	}

	public WebResponseStatus delete (final Long id) throws ResourceNotDeletedException {
		WebResponseStatus validateCart = new WebResponseStatus();

		if (this.cartRepository.getOne(id) == null ) {
			throw new ResourceNotDeletedException("Cart id <" + id + "> not found.");			
		} else if (this.cartRepository.delete(id) <= 0) {
			throw new ResourceNotDeletedException("Could not delete cart due to unknwon problems during delete process.");			
		}
				
		return validateCart;
	}
	
	public WebResponseStatus validate (final Cart cart, final UpdateType action) {
		WebResponseStatus validateService = new WebResponseStatus();
		validateService.setValid(true);

		LOGGER.info("## Validating cart: {}", cart);
		
		List<RestError> validateBeanErrors = MyValidation.validateBean(cart);
		if (!validateBeanErrors.isEmpty()) {
			validateService.setValid(false);
		}

		LOGGER.info("## Validating cart status: {}", validateService.isValid());
		LOGGER.info("## Validating cart status messages: {}", validateService.getServiceMsg());
		
		return validateService;
	}	

	private Cart calculateCart(Cart cart, UpdateType action) {
		Cart calculatedCart = cart;
		LOGGER.info("## Attempting to calculate cart: {}", cart);
		calculatedCart.setShippingAmount(shipToRepository.getOne(calculatedCart.getShipTo().getShipToId()).getCity().getState().getShippingZone().getShippingCost());
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
	
	private Cart mergeBeans(final Cart partialCart, final Cart fullCart) {
		Cart newCart = fullCart;
		
		if (partialCart.getLinesAmount() != null ) {
			newCart.setLinesAmount(partialCart.getLinesAmount());
		}
		if (partialCart.getShipTo() != null && partialCart.getShipTo().getShipToId() != null ) {
			newCart.setShipTo(new ShipTo(partialCart.getShipTo().getShipToId(), null, null, null, null, null, null, null));
		}
		if (partialCart.getStatus() != null && partialCart.getStatus().getStatusId() != null ) {
			newCart.setStatus(new Status(partialCart.getStatus().getStatusId(), null, null));
		}
		
		return newCart;
	}

	private boolean isUnique(final Cart cart) {
		LOGGER.info("## Validating cart (unique): {}", cart);
		return this.cartRepository.getOne(cart.getCartId()) == null ? true : false;
	}
}