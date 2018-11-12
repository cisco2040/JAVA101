package com.softtek.javaweb.service.jpa;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.softtek.javaweb.domain.dto.CouponStatus;
import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.Coupon;
import com.softtek.javaweb.domain.model.Order;
import com.softtek.javaweb.domain.model.PaymentMethod;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.exceptionhandling.MyValidation;
import com.softtek.javaweb.repository.jpa.CartRepository;
import com.softtek.javaweb.repository.jpa.OrderRepository;
import com.softtek.javaweb.service.filter.OrderFilter;
import com.softtek.javaweb.service.predicate.OrderPredicate;
import com.softtek.javaweb.service.types.CouponState;
import com.softtek.javaweb.service.types.UpdateType;
/**
 * Service class for Order entity.
 * @author victor.cortes
 *
 */
@Service
public class OrderService {

	public static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CouponService couponService;

	public List<Order> getOrderByNameAndRandomStatus (String createUser) {
		Long[] statusIds = {1100L,1200L,1300L, 1400L, 
							2100L, 2300L, 2350L, 2400L, 
							3100L, 3300L, 3400L};
		Integer index = (int) (Math.random() * 11);
		List<Order> orders = orderRepository.findByNameStatusSorted(createUser,statusIds[index]);
			
		return orders;
	}
	
	
/**
 * Search orders bases on a provided custom filter and return a list as a result.  
 * @param of - {@link OrderFilter} filter object. Results of submitted form bind to this object.
 * @param result - {@link BindingResult} validation object. Will store binding errors if any.
 * @return a {@link List} of {@link Order} entities that match the provided filter object.
 * @throws IncorrectParametersException
 * @throws ResourceNotAvailableException
 */
	public List<Order> searchOrders(OrderFilter of, BindingResult result) throws IncorrectParametersException, ResourceNotAvailableException {
		// validation strings or use biding results
		if (result.hasErrors()) {
			throw new IncorrectParametersException("Request parameters are incorrect. Please check API documentation.", MyValidation.getErrorsFromBindingResults(result));
		}	
		List<Order> orders = (List<Order>) orderRepository.findAll(OrderPredicate.buildSearchPredicate(of));
		if (orders.isEmpty()) {
			throw new ResourceNotAvailableException("No orders were found.");
		}		
		return orders;
	}

	/**
	 * Obtains list of all available orders in DB.
	 * @return a {@link List} of {@link Order} entities.
	 * @throws ResourceNotAvailableException
	 */
	public List<Order> getList() throws ResourceNotAvailableException {
		List<Order> orders = orderRepository.findAll();
		LOGGER.info("## Order List Obtained: {}", orders);
		if (orders.isEmpty()) {
			throw new ResourceNotAvailableException("No orders were found.");
		}
		return orders;
	}

	/**
	 * Returns an order matching the provided order number. If none found, an exception is thrown.
	 * @param id - order number being requested
	 * @return an {@link Order} entity matching the provided order number.
	 * @throws ResourceNotAvailableException
	 * @throws DatabaseOperationException 
	 */
	public Order getOne(final Long id) throws ResourceNotAvailableException, DatabaseOperationException {
		Optional<Order> order = Optional.of(new Order());
		
		try {
			order = this.orderRepository.findById(id);
		} catch (IllegalArgumentException e) {			
			throw new DatabaseOperationException("Database lookup operation failed with stacktrace: " + e.getStackTrace());
		}
		if (order.isPresent()) {
			LOGGER.info("## Order Obtained: {}", order.get());
		}
		if (!order.isPresent()) {
			throw new ResourceNotAvailableException("Order id <" + id + "> not found.");
		}
		return order.get();
	}
	
	/**
	 * Updates an existing order with the data provided in a fully constructed valid bean.
	 * Returned order will contain all values from passed bean.
	 * @param order - {@link Order} entity bean
	 * @param id - order number to be updated
	 * @return - updated order
	 * @throws ResourceNotUpdatedException
	 * @throws ResourceCouldNotBeFoundException
	 */
	
	public Order updateFull(final Order order, final Long id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		Order fullOrder = order;
		fullOrder.setOrderId(id); 
		return this.update(fullOrder);
	}

	/**
	 * Updates an existing order with the data provided in a order bean.
	 * The provided bean may contain 1 or more fields. Only those will be updated in order.
	 * @param order - {@link Order} entity bean
	 * @param id - order number to be updated
	 * @return - updated order
	 * @throws ResourceCouldNotBeFoundException
	 * @throws ResourceNotUpdatedException
	 */
	public Order updatePartial(final Order order, final Long id) throws ResourceCouldNotBeFoundException, ResourceNotUpdatedException {
		Optional<Order> originalOrder = this.orderRepository.findById(id);

		if (!originalOrder.isPresent()) {
			throw new ResourceCouldNotBeFoundException("Order id <" + id + "> not found.");
		}

		Order newOrder = mergeBeans(order, originalOrder.get());

		LOGGER.info("## Attempting to update (PATCH) order: {}, with elements: {}", id, order);
		return this.updateOrder (newOrder, originalOrder.get());
	}
/**
 * Updates an existing Order in DB. Order must be a fully constructed valid bean. Otherwise, an exception will be thrown.
 * @param Order order
 * @return updated Order entity
 * @throws ResourceNotUpdatedException
 * @throws ResourceCouldNotBeFoundException
 */
	public Order update(final Order order) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		Optional<Order> originalOrder = this.orderRepository.findById(order.getOrderId());

		if (!originalOrder.isPresent()) {
			throw new ResourceCouldNotBeFoundException("Order id <" + order.getOrderId() + "> not found.");
		}

		return updateOrder(order, originalOrder.get());
	}
	
	private Order updateOrder (Order order, Order originalOrder) throws ResourceNotUpdatedException {
		CouponStatus couponStatus = new CouponStatus();
		List<RestError> restError = MyValidation.validateBean(order);
		Order newOrder = order;
		boolean isCouponUpdated = true;
		if (!restError.isEmpty()) {
			throw new ResourceNotUpdatedException("Could not update order due to missing/incorrect data.", restError);
		}

		if (order.getCoupon() != null && originalOrder.getCoupon() != null) {
			isCouponUpdated = !originalOrder.getCoupon().getCouponId().equals(order.getCoupon().getCouponId());
		}	
		
		LOGGER.info("## Attempting to update order: {}", order);

		if (order.getCoupon() != null ) {
			couponStatus = couponService.validate(order.getCoupon().getCouponId());
			if (couponStatus.getStatus() != CouponState.VALID && isCouponUpdated) {
				throw new ResourceNotUpdatedException(couponStatus.getMessage());
			}
		}		
		//set full coupon info in order entity
		order.setCoupon(couponStatus.getCoupon());
		//if linesAmount or couponId is updated from original order, re-apply existing coupon if any
		if (!originalOrder.getLinesAmount().equals(order.getLinesAmount()) || isCouponUpdated) {
			newOrder = updateLineAmount(newOrder);	
		}		
		
		Order savedOrder = this.orderRepository.save(calculateOrder (newOrder, UpdateType.MODIFY));
		
		if (savedOrder == null) {
			throw new ResourceNotUpdatedException("Could not update order due to unknown problems during persist.");
		}
		
		if (savedOrder.getCoupon() != null && isCouponUpdated ) {
			couponService.redeem(savedOrder.getCoupon().getCouponId());
		}		
		
		return savedOrder;
		
	}
/**
 * Persist a new order entity in DB. 
 * @param order - {@link Order} entity to persist. Must be a fully constructed valid bean. Otherwise, an exception will be thrown.
 * @return {@link Order} entity
 * @throws ResourceNotAddedException
 * @throws ResourceNotUpdatedException
 */
	@Transactional
	public Order add(final Order order) throws ResourceNotAddedException, ResourceNotUpdatedException {
		List<RestError> restError = MyValidation.validateBean(order);
		Order newOrder = order;
		
		if (!restError.isEmpty()) {
			throw new ResourceNotAddedException("Could not add order due to missing/incorrect data.", restError);			
		}

		if (newOrder.getCoupon() != null ) {
			CouponStatus couponStatus = couponService.validate(newOrder.getCoupon().getCouponId());
			if (couponStatus.getStatus() != CouponState.VALID) {
				throw new ResourceNotUpdatedException(couponStatus.getMessage());		
			}
		}

		Order calculatedOrder = calculateOrder (order, UpdateType.NEW);
		LOGGER.info("## Attempting to add order: {}", calculatedOrder);
		newOrder = this.orderRepository.save(calculatedOrder);
	
		if (newOrder.getCoupon() != null ) {
			couponService.redeem(calculatedOrder.getCoupon().getCouponId());
		}		

		return newOrder;
	}

	public void delete (final Long id) throws ResourceNotDeletedException, DatabaseOperationException {
		if (!this.orderRepository.findById(id).isPresent()) {
			throw new ResourceNotDeletedException("Order id <" + id + "> not found.");			
		}
		
		this.orderRepository.deleteById(id);
		
		if (this.orderRepository.findById(id).isPresent()) {
			throw new DatabaseOperationException("Order id <" + id + "> could not be deleted.");			
		}		
	}
	
	public Order previewCoupon (Order order, String code) throws ResourceNotUpdatedException {		
		CouponStatus couponStatus = couponService.validate(code);

		if (couponStatus.getStatus() != CouponState.VALID) {
			throw new ResourceNotUpdatedException(couponStatus.getMessage());		
		}
	
		return updateOrderWithCoupon(order, couponStatus.getCoupon());
	}

	private Order updateOrderWithCoupon(final Order order, final Coupon coupon) {
		order.setCoupon(coupon);		
		Order newOrder = updateLineAmount(order);
		return calculateOrder(newOrder, UpdateType.NEW);
	}	

	private Order updateLineAmount(final Order order) {
		if (order.getCoupon() != null ) {
			if (order.getCoupon().getDiscountAmount() != null ) {
				order.setLinesAmount(order.getLinesAmount() - order.getCoupon().getDiscountAmount());
			} else if (order.getCoupon().getDiscountPercent() != null ) {
				order.setLinesAmount(order.getLinesAmount() * (1 - (order.getCoupon().getDiscountPercent() / 100)));			
			}
		}
		return order;
	}

	private Order calculateOrder(Order order, UpdateType action) {
		Order calculatedOrder = order;
		LOGGER.info("## Attempting to calculate order: {}", order);

		calculatedOrder.setShippingAmount(cartRepository.findById(order.getCart().getCartId()).get().getShippingAmount());
		calculatedOrder.setOrderAmount(order.getLinesAmount() + calculatedOrder.getShippingAmount());
		
		if (action == UpdateType.NEW) {
			calculatedOrder.setCreateDate(new Timestamp(System.currentTimeMillis()));
		}
		if (action == UpdateType.MODIFY) {
			calculatedOrder.setUpdateDate(new Timestamp(System.currentTimeMillis()));			
		}
		LOGGER.info("## Calculated order: {}", order);
		
		return calculatedOrder;
	}
	
	private Order mergeBeans(final Order partialOrder, final Order fullOrder) {
		Order newOrder = fullOrder;

		if (partialOrder.getCart() != null && partialOrder.getCart().getCartId() != null ) {
			newOrder.setCart(new Cart(partialOrder.getCart().getCartId(), null, null, null, null, null, null, null, null, null));
		}
		if (partialOrder.getOrderDate() != null) {
			newOrder.setOrderDate(partialOrder.getOrderDate());
		}
		if (partialOrder.getLinesAmount() != null) {
			newOrder.setLinesAmount(partialOrder.getLinesAmount());
		}
		if (partialOrder.getScheduleDate() != null) {
			newOrder.setScheduleDate(partialOrder.getScheduleDate());
		}
		if (partialOrder.getDeliveryDate() != null) {
			newOrder.setDeliveryDate(partialOrder.getDeliveryDate());
		}
		if (partialOrder.getCancellationDate() != null) {
			newOrder.setCancellationDate(partialOrder.getCancellationDate());
		}
		if (partialOrder.getPaymentMethod() != null && partialOrder.getPaymentMethod().getPaymentMethodId() != null ) {
			newOrder.setPaymentMethod(new PaymentMethod(partialOrder.getPaymentMethod().getPaymentMethodId(), null));
		}
		if (partialOrder.getPaymentReference() != null) {
			newOrder.setPaymentReference(partialOrder.getPaymentReference());
		}
		if (partialOrder.getNotes() != null) {
			newOrder.setNotes(partialOrder.getNotes());
		}
		if (partialOrder.getStatus() != null && partialOrder.getStatus().getStatusId() != null ) {
			newOrder.setStatus(new Status(partialOrder.getStatus().getStatusId(), null, null));
		}
		
		return newOrder;
	}
}