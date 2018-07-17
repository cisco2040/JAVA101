package com.softtek.javaweb.web;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.service.CartService;
import com.softtek.javaweb.service.ShipToService;
import com.softtek.javaweb.service.StatusService;
import com.softtek.javaweb.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	ShipToService shipToService;
	@Autowired
	StatusService statusService;
	@Autowired
	UserService userService;
	
	private ResponseStatus responseStatus;

	static final String HEADER = "headerTitle";
	static final String SUBMIT_BTN = "frmLblSubmitBtn";
	static final String EDIT_FORM = "cart/edit";
	static final String LIST_FORM = "cart/list";
	static final String CART_BEAN = "cart";
	static final String CART_LIST_BEAN = "carts";

	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String cartListController (Model model) {
		model.addAttribute(CartController.CART_LIST_BEAN, cartService.getList());	
		return CartController.LIST_FORM;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "home")
	public String cartHomeController () {
		return "redirect:/";
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String cartDefaultEditController () {
		return null;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancel")
	public String cartCancelController (Model model) {
		model.addAttribute(CartController.CART_LIST_BEAN, cartService.getList());	
		return CartController.LIST_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"showDetail","id"})
	public String cartEditController (@RequestParam("id") String id, Model model) {
		model.addAllAttributes(this.initializeEditForm("Edit Cart", "Update"));
		model.addAttribute(CartController.CART_BEAN, cartService.getOne(Long.valueOf(id)));	
		return CartController.EDIT_FORM;				
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Update")
	public String cartUpdateController (HttpServletRequest httpRequest, Model model) {
		Cart cart = this.makeCart(httpRequest);
		model.addAttribute(CartController.CART_BEAN, cart);
		responseStatus = cartService.update(cart);
		
		if (responseStatus.isValid()) {
			model.addAttribute(CartController.CART_LIST_BEAN, cartService.getList());	
			return CartController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit Cart", "Update"));
			return CartController.EDIT_FORM;				
	   	}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addNew")
	public String cartNewController (Model model) {
   		model.addAllAttributes(this.initializeEditForm("Add New Cart", "Save"));
		return CartController.EDIT_FORM;				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Save")
	public String cartNewController (HttpServletRequest httpRequest, Model model) {
		Cart cart = this.makeCart(httpRequest);
		model.addAttribute(CartController.CART_BEAN, cart);
		responseStatus = cartService.add(cart);
		
		if (responseStatus.isValid()) {
			model.addAttribute(CartController.CART_LIST_BEAN, cartService.getList());	
			return CartController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Add New Cart", "Save"));
			return CartController.EDIT_FORM;				
	   	}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public String cartDeleteController (HttpServletRequest httpRequest, Model model) {
		Cart cart = this.makeCart(httpRequest);
		model.addAttribute(CartController.CART_BEAN, cart);
		responseStatus = cartService.delete(cart.getCartId());
		
		if (responseStatus.isValid()) {
			model.addAttribute(CartController.CART_LIST_BEAN, cartService.getList());	
			return CartController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit Cart", "Update"));
			return CartController.EDIT_FORM;				
	   	}
	}

	private Map<String,Object> initializeEditForm (final String header, final String submitAction) {
		Map<String,Object> map = new HashMap<>();
		
		map.put(CartController.HEADER, header);
		map.put(CartController.SUBMIT_BTN, submitAction);
		map.put("shipTos", shipToService.getList());	
		map.put("statuses", statusService.getList());	
		map.put("users", userService.getList());
		if (this.responseStatus != null) {
			map.put("frmValMsgs", this.responseStatus.getServiceMsg());
		}

		return map;
	}
	
	private Cart makeCart(HttpServletRequest request) {
		Cart cart = new Cart();
	
		Long cartId = StringUtils.isNotEmpty(request.getParameter("frmCartId")) ? new Long(request.getParameter("frmCartId")) : null;
		Float linesAmount = StringUtils.isNotEmpty(request.getParameter("frmLinesAmount")) ? new Float(request.getParameter("frmLinesAmount")) : null;
		Float shippingAmount = StringUtils.isNotEmpty(request.getParameter("frmShippingAmount")) ? new Float(request.getParameter("frmShippingAmount")) : null;
		Float cartAmount = StringUtils.isNotEmpty(request.getParameter("frmCartAmount")) ? new Float(request.getParameter("frmCartAmount")) : null;
		Long shipToId = StringUtils.isNotEmpty(request.getParameter("frmShipToId")) ? new Long(request.getParameter("frmShipToId")) : null;
		Long statusId = StringUtils.isNotEmpty(request.getParameter("frmStatusId")) ? new Long(request.getParameter("frmStatusId")) : null;
		String createUser = StringUtils.isNotEmpty(request.getParameter("frmCreateUser")) ? request.getParameter("frmCreateUser") : null;
		String createDateString = StringUtils.isNotEmpty(request.getParameter("frmCreateDate")) ? request.getParameter("frmCreateDate") : null;
		Timestamp createDate = null;
		try {
			createDate = createDateString != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDateString).getTime()) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String updateUser = StringUtils.isNotEmpty(request.getParameter("frmUpdateUser")) ? request.getParameter("frmUpdateUser") : null;
		String updateDateString = StringUtils.isNotEmpty(request.getParameter("frmUpdateDate")) ? request.getParameter("frmUpdateDate") : null;
		Timestamp updateDate = null;
		try {
			updateDate = updateDateString != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateDateString).getTime()) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		cart.setCartId(cartId);
		cart.setLinesAmount(linesAmount);
		cart.setShippingAmount(shippingAmount);
		cart.setCartAmount(cartAmount);
		cart.setShipTo(new ShipTo(shipToId, null, StringUtils.EMPTY, StringUtils.EMPTY, null, Long.MIN_VALUE, StringUtils.EMPTY, StringUtils.EMPTY));
		cart.setStatus(new Status(statusId, StringUtils.EMPTY, StringUtils.EMPTY));
		cart.setCreateUser(createUser);
		cart.setCreateDate(createDate);
		cart.setUpdateUser(updateUser);
		cart.setUpdateDate(updateDate);
		
		return cart;
	}
}
