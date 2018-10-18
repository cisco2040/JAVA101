package com.softtek.javaweb.service.jpa;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.exceptionhandling.MyValidation;
import com.softtek.javaweb.repository.jpa.CartRepository;
import com.softtek.javaweb.repository.jpa.ShipToRepository;
import com.softtek.javaweb.service.types.UpdateType;

@Service
public class CartService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ShipToRepository shipToRepository;

	public List<Cart> getByStatus(Long statusId) throws ResourceNotAvailableException {
		List<Cart> carts = cartRepository.findByStatus_StatusId(statusId);
		LOGGER.info("## Cart List Obtained (by status): {}", carts);
		if (carts.isEmpty()) {
			throw new ResourceNotAvailableException("No matching carts were found.");
		}
		return carts;
	}

	public List<Cart> getByMaxCartAmount (Float cartAmount) throws ResourceNotAvailableException {
		List<Cart> carts = cartRepository.findByCartAmountLessThanEqual(cartAmount);
		LOGGER.info("## Cart List Obtained (by amount max {}): {}", cartAmount, carts);
		if (carts.isEmpty()) {
			throw new ResourceNotAvailableException("No matching carts were found.");
		}
		return carts;
	}

	public List<Cart> getByMinCartAmount (Float cartAmount) throws ResourceNotAvailableException {
		List<Cart> carts = cartRepository.findByCartAmountGreaterThanEqual(cartAmount);
		LOGGER.info("## Cart List Obtained (by amount min {}): {}", cartAmount, carts);
		if (carts.isEmpty()) {
			throw new ResourceNotAvailableException("No matching carts were found.");
		}
		return carts;
	}

	public List<Cart> getByRangeCartAmount (Float cartAmountMin, Float cartAmountMax) throws ResourceNotAvailableException, IncorrectParametersException {
		List<Cart> carts = cartRepository.findByCartAmountGreaterThanEqualAndCartAmountLessThanEqual(cartAmountMin, cartAmountMax);
		if (cartAmountMin > cartAmountMax) {
			throw new IncorrectParametersException("First cart amount must be less or equal than second amount.");
		}
		LOGGER.info("## Cart List Obtained (by amount range between {} and {}): {}", cartAmountMin, cartAmountMax, carts);
		if (carts.isEmpty()) {
			throw new ResourceNotAvailableException("No matching carts were found.");
		}
		return carts;
	}

	public List<Cart> getByCreateDateRange (String start, String end) throws ResourceNotAvailableException, IncorrectParametersException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp startTs = null;
		Timestamp endTs = null;
		
		try {
			startTs = new Timestamp (df.parse(start).getTime());
		} catch (ParseException e) {
			throw new IncorrectParametersException("Start date format must be YYYY-MM-DD.");
		}
		try {
			endTs = new Timestamp (df.parse(end).getTime());
		} catch (ParseException e) {
			throw new IncorrectParametersException("End date format must be YYYY-MM-DD.");
		}
		
		List<Cart> carts = cartRepository.findByCreateDateRange(startTs, endTs);
		
		if (startTs.after(endTs)) {
			throw new IncorrectParametersException("First date amount must be earlier or equal than second date.");
		}
		LOGGER.info("## Cart List Obtained (by create date range between {} and {}): {}", start, end, carts);
		if (carts.isEmpty()) {
			throw new ResourceNotAvailableException("No matching carts were found.");
		}
		
		return carts;
	}

	public List<Cart> getList() throws ResourceNotAvailableException {
		List<Cart> carts = cartRepository.findAll();
		LOGGER.info("## Cart List Obtained: {}", carts);
		if (carts.isEmpty()) {
			throw new ResourceNotAvailableException("No carts were found.");
		}
		return carts;
	}

	public Cart getOne(final Long id) throws ResourceNotAvailableException {
		Cart cart = this.cartRepository.findById(id).orElse(null);
		LOGGER.info("## Cart Obtained: {}", cart);
		if (cart == null ) {
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
		Cart fullCart = this.cartRepository.findById(id).orElse(null);
		
		if (fullCart == null) {
			throw new ResourceCouldNotBeFoundException("Cart id <" + id + "> not found.");
		}
		
		Cart newCart = mergeBeans(cart, fullCart);
		List<RestError> restErrors = MyValidation.validateBean(newCart);

		if (restErrors.isEmpty()) {
			LOGGER.info("## Attempting to update (PATCH) cart: {}, with elements: {}", id, cart);
			Cart calculatedCart = calculateCart (newCart, UpdateType.MODIFY);
			if (this.cartRepository.save(calculatedCart) == null) {
				throw new ResourceNotUpdatedException("Could not update cart due to unknown problems during persist.");
			}
		} else {
			throw new ResourceNotUpdatedException("Could not update cart due to missing/incorrect data.", restErrors);
		}
		
		return this.cartRepository.findById(id).get();
	}

	public Cart update(final Cart cart) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		List<RestError> restErrors = MyValidation.validateBean(cart);
		
		if (!isUnique(cart)) {
			if (restErrors.isEmpty()) {
				LOGGER.info("## Attempting to update cart: {}", cart);
				Cart calculatedCart = calculateCart (cart, UpdateType.MODIFY);
				if (this.cartRepository.save(calculatedCart) == null) {
					throw new ResourceNotUpdatedException("Could not update cart due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotUpdatedException("Could not update cart due to missing/incorrect data.", restErrors);
			}
		} else {
			throw new ResourceCouldNotBeFoundException("Cart id <" + cart.getCartId() + "> not found.");
		}

		return this.cartRepository.findById(cart.getCartId()).get();
	}

	public Cart add(final Cart cart) throws ResourceNotAddedException {
		List<RestError> restErrors = MyValidation.validateBean(cart);
		Cart newCart;
		
		if (restErrors.isEmpty()) {
			Cart calculatedCart = calculateCart (cart, UpdateType.NEW);
			LOGGER.info("## Attempting to add cart: {}", calculatedCart);
			newCart = this.cartRepository.save(calculatedCart);
			if (newCart == null) {
				throw new ResourceNotAddedException("Could not add cart due to unknown problems during persist.");
			}
		} else {
			throw new ResourceNotAddedException("Could not add cart due to missing/incorrect data.", restErrors);			
		}

		return newCart;
	}

	public void delete (final Long id) throws ResourceNotDeletedException {
		if (!this.cartRepository.findById(id).isPresent()) {
			throw new ResourceNotDeletedException("Cart id <" + id + "> not found.");			
		}
		this.cartRepository.deleteById(id);
	}
	
	private Cart calculateCart(Cart cart, UpdateType action) {
		Cart calculatedCart = cart;
		LOGGER.info("## Attempting to calculate cart: {}", cart);
		calculatedCart.setShippingAmount(shipToRepository.findById(cart.getShipTo().getShipToId()).get().getCity().getState().getShippingZone().getShippingCost());
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
		return this.cartRepository.findById(cart.getCartId()).isPresent() ? false : true;
	}
}