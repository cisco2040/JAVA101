package com.softtek.javaweb.service.jpa;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.softtek.javaweb.domain.dto.CouponStatus;
import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.Coupon;
import com.softtek.javaweb.domain.model.Order;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.exceptionhandling.MyValidation;
import com.softtek.javaweb.repository.jpa.CartRepository;
import com.softtek.javaweb.repository.jpa.OrderRepository;
import com.softtek.javaweb.service.filter.OrderFilter;
import com.softtek.javaweb.service.types.CouponState;

@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest(MyValidation.class)
public class OrderServiceTest {
		
	@Mock
	OrderRepository orderRepository;
	@Mock
	CartRepository cartRepository;
	@Mock
	CouponService couponService;
	@Mock
	BindingResult results;
	@InjectMocks
	@Spy
	OrderService orderService = new OrderService();
	
//	@Before
//	public void setup() {
//		MockitoAnnotations.initMocks(this);
//	}	
	
	@Test
	public void searchOrder_OrdersFound_OrdersReturned () throws IncorrectParametersException, ResourceNotAvailableException {
		OrderFilter of = new OrderFilter();

		Order order = new Order();
		order.setOrderId(1L);
		List <Order> orders = new ArrayList<>();
		orders.add(order);
		
		when(orderRepository.findAll(any(BooleanExpression.class))).thenReturn(orders);
		when(results.hasErrors()).thenReturn(false);
		
		Assertions.assertThat(orderService.searchOrders(of, results))
			.isNotEmpty()
			.hasSize(1)
			.isEqualTo(orders);
	}

	@Test (expected = IncorrectParametersException.class)
	public void searchOrder_IncorrectParameters_ExceptionThrown () throws IncorrectParametersException, ResourceNotAvailableException {
		OrderFilter of = new OrderFilter();
		List<FieldError> fieldErrors = new ArrayList<>();  
		fieldErrors.add(new FieldError("some object", "some field", "rejectd value", false, null, null, "some error description"));

		when(results.hasErrors()).thenReturn(true);
		when(results.getFieldErrors()).thenReturn(fieldErrors);
		
		orderService.searchOrders(of, results);		
	}
	
	// need tests for getOne and update

	@Test (expected = ResourceNotAvailableException.class)
	public void searchOrder_NoneFound_ExceptionThrown () throws IncorrectParametersException, ResourceNotAvailableException {
		OrderFilter of = new OrderFilter();

		when(orderRepository.findAll(any(BooleanExpression.class))).thenReturn(new ArrayList<Order>());
		when(results.hasErrors()).thenReturn(false);

		orderService.searchOrders(of, results);		
	}

	@Test (expected = ResourceNotAvailableException.class)
	public void getList_NoneFound_ExceptionThrown () throws ResourceNotAvailableException {
		when(orderRepository.findAll()).thenReturn(Collections.emptyList());
		orderService.getList();				
	}

	@Test
	public void getList_OrdersFound_ReturnList () throws ResourceNotAvailableException {
		Order order = new Order();
		order.setOrderId(1L);
		List <Order> orders = new ArrayList<>();
		orders.add(order);

		when(orderRepository.findAll()).thenReturn(orders);
		
		Assertions.assertThat(orderService.getList())
			.isNotEmpty()
			.hasSize(1)
			.isEqualTo(orders);
	}
	
	@Test
	public void updateFull_OrderUpdated_OrderReturned () throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {		
		Order order = new Order();
		Long orderId = 1L;
		doReturn(order).when(orderService).update(any(Order.class));

		Assertions.assertThat(orderService.updateFull(order, orderId))
			.isNotNull()
			.hasFieldOrPropertyWithValue("orderId", orderId);
	}

	@Test (expected = ResourceCouldNotBeFoundException.class)
	public void updatePartial_OrderNonExistant_ExceptionThrown () throws ResourceCouldNotBeFoundException, ResourceNotUpdatedException{		
		when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		orderService.updatePartial(new Order(), 1L);				
	}
	

	@Test (expected = ResourceNotUpdatedException.class)
	public void updatePartial_OrderNotValid_ExceptionThrown () throws ResourceCouldNotBeFoundException, ResourceNotUpdatedException {
		
		Optional<Order> order = Optional.of(new Order());
		order.get().setOrderId(1L);
		order.get().setCart(new Cart(1L,null, null, null, null, null, null, null, null, null));
		order.get().setStatus(new Status(1100L, null, null));

		when(orderRepository.findById(anyLong())).thenReturn(order);

		orderService.updatePartial(order.get(), 1L);				
	}

	@Test
	public void updatePartial_OrderValid_OrderReturned () throws ResourceCouldNotBeFoundException, ResourceNotUpdatedException {
		
		Optional<Cart> cart = Optional.of(new Cart());
		cart.get().setCartId(1L);
		cart.get().setShippingAmount(10F);
		Optional<Order> order = Optional.of(new Order());
		order.get().setOrderId(1L);
		order.get().setLinesAmount(1000F);
		order.get().setCart(cart.get());
		order.get().setStatus(new Status(1100L, null, null));

		// Mock static method with PowerMockito
		PowerMockito.mockStatic(MyValidation.class);
		BDDMockito.given(MyValidation.validateBean(any(Order.class))).willReturn(new ArrayList<RestError>());

		when(orderRepository.findById(anyLong())).thenReturn(order);
		when(orderRepository.save(any(Order.class))).thenReturn(order.get());
		when(cartRepository.findById(anyLong())).thenReturn(cart);

		assertEquals(order.get(), orderService.updatePartial(order.get(), 1L));		
	}

	@Test (expected = ResourceNotAddedException.class)
	public void add_OrderInvalid_ExceptionThrown() throws ResourceNotAddedException, ResourceNotUpdatedException {
		Order order = new Order();
		orderService.add(order);		
	}

	@Test (expected = ResourceNotUpdatedException.class)
	public void add_OrderWithCouponInvalid_ExceptionThrown() throws ResourceNotAddedException, ResourceNotUpdatedException {
		Coupon coupon = new Coupon();
		coupon.setCouponId("ABCD");

		CouponStatus couponStatus = new CouponStatus();
		couponStatus.setCoupon(coupon);
		couponStatus.setMessage("Coupon " + couponStatus.getCoupon().getCouponId() + " is expired.");
		couponStatus.setStatus(CouponState.EXPIRED);

		Cart cart = new Cart();
		cart.setCartId(1L);

		Order order = new Order();
		order.setCart(cart);
		order.setLinesAmount(1000F);
		order.setCart(cart);
		order.setStatus(new Status(1100L, null, null));
		order.setCoupon(coupon);
		
		when(couponService.validate(anyString())).thenReturn(couponStatus);
		
		orderService.add(order);
	}

	@Test
	public void add_OrderWithCouponValid_OrderReturned() throws ResourceNotAddedException, ResourceNotUpdatedException {
		Coupon coupon = new Coupon();
		coupon.setCouponId("ABCD");

		CouponStatus couponStatus = new CouponStatus();
		couponStatus.setCoupon(coupon);
		couponStatus.setStatus(CouponState.VALID);
		
		Cart cart = new Cart();
		cart.setCartId(1L);
		cart.setShippingAmount(10F);

		Order order = new Order();
		order.setOrderId(1L);
		order.setLinesAmount(1000F);
		order.setCart(cart);
		order.setStatus(new Status(1100L, null, null));
		order.setCoupon(coupon);

		when(couponService.validate(anyString())).thenReturn(couponStatus);
		when(couponService.redeem(anyString())).thenReturn(new Coupon());
		when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));
		when(orderRepository.save(any(Order.class))).then(returnsFirstArg());

		Float expectedOrderAmount = order.getLinesAmount() + order.getCart().getShippingAmount(); 
		Order newOrder = orderService.add(order);

		Assertions.assertThat(newOrder)
			.isNotNull()
			.hasFieldOrPropertyWithValue("orderAmount", expectedOrderAmount);
		Assertions.assertThat(newOrder.getCreateDate()).isNotNull();
	}

	@Test (expected = ResourceNotDeletedException.class)
	public void delete_OrderNonExistent_ExceptionThrown () throws ResourceNotDeletedException {
		when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
		orderService.delete(1L);
	}

	@Test
	public void delete_OrderExists_OrderDeleted () throws ResourceNotDeletedException {
		when(orderRepository.findById(anyLong())).thenReturn(Optional.of(new Order()));
		orderService.delete(1L);
		verify(orderRepository).deleteById(anyLong());;
	}
	
	@Test (expected = ResourceNotUpdatedException.class)
	public void previewCoupon_CouponInvalid_ExceptionThrown () throws ResourceNotUpdatedException {
		String couponCode = "ABCD";
		CouponStatus couponStatus = new CouponStatus();
		couponStatus.setCoupon(new Coupon(couponCode, null, null, null, null, null));
		couponStatus.setStatus(CouponState.EXPIRED);
		couponStatus.setMessage("Coupon is expired");
		
		when(couponService.validate(anyString())).thenReturn(couponStatus);
		orderService.previewCoupon(new Order(), couponCode);
	}

	@Test
	public void previewCoupon_CouponValidWithDiscountAmount_OrderReturned () throws ResourceNotUpdatedException {
		String couponCode = "ABCD";
		CouponStatus couponStatus = new CouponStatus();
		couponStatus.setCoupon(new Coupon(couponCode, null, null, null, 100F, false));
		couponStatus.setStatus(CouponState.VALID);
		
		Cart cart = new Cart();
		cart.setCartId(1L);
		cart.setShippingAmount(10F);

		Order order = new Order();
		order.setOrderId(1L);
		order.setLinesAmount(1000F);
		order.setCart(cart);
		order.setStatus(new Status(1100L, null, null));
		
		Float expectedLinesAmount = order.getLinesAmount() - couponStatus.getCoupon().getDiscountAmount();
		Float expectedOrderAmount = expectedLinesAmount + cart.getShippingAmount(); 
		
		when(couponService.validate(anyString())).thenReturn(couponStatus);
		when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));

		Order updatedOrder = orderService.previewCoupon(order, couponCode);

		Assertions.assertThat(updatedOrder)
			.hasFieldOrPropertyWithValue("coupon.couponId", couponStatus.getCoupon().getCouponId())
			.hasFieldOrPropertyWithValue("linesAmount", expectedLinesAmount)
			.hasFieldOrPropertyWithValue("orderAmount", expectedOrderAmount);
		Assertions.assertThat(updatedOrder.getCreateDate())
			.isNotNull();
	}

	@Test
	public void previewCoupon_CouponValidWithDiscountPercent_OrderReturned () throws ResourceNotUpdatedException {
		String couponCode = "ABCD";
		CouponStatus couponStatus = new CouponStatus();
		couponStatus.setCoupon(new Coupon(couponCode, null, null, 20F, null, false));
		couponStatus.setStatus(CouponState.VALID);
		
		Cart cart = new Cart();
		cart.setCartId(1L);
		cart.setShippingAmount(10F);

		Order order = new Order();
		order.setOrderId(1L);
		order.setLinesAmount(1000F);
		order.setCart(cart);
		order.setStatus(new Status(1100L, null, null));
		
		Float expectedLinesAmount = order.getLinesAmount() * (1 - (couponStatus.getCoupon().getDiscountPercent()/100));
		Float expectedOrderAmount = expectedLinesAmount + cart.getShippingAmount(); 
		
		when(couponService.validate(anyString())).thenReturn(couponStatus);
		when(cartRepository.findById(anyLong())).thenReturn(Optional.of(cart));

		Order updatedOrder = orderService.previewCoupon(order, couponCode);

		Assertions.assertThat(updatedOrder)
			.hasFieldOrPropertyWithValue("coupon.couponId", couponStatus.getCoupon().getCouponId())
			.hasFieldOrPropertyWithValue("linesAmount", expectedLinesAmount)
			.hasFieldOrPropertyWithValue("orderAmount", expectedOrderAmount);
		Assertions.assertThat(updatedOrder.getCreateDate())
			.isNotNull();
	}
}
