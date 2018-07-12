package com.softtek.javaweb.web;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.service.CartService;
import com.softtek.javaweb.service.CityService;
import com.softtek.javaweb.service.ShipToService;
import com.softtek.javaweb.service.ShippingZoneService;
import com.softtek.javaweb.service.StateService;
import com.softtek.javaweb.service.StatusService;
import com.softtek.javaweb.service.UserRoleService;
import com.softtek.javaweb.service.UserService;

@Controller
public class WebtestController {
	public static final Logger LOGGER = LoggerFactory.getLogger(WebtestController.class);
	
	@Autowired
	CartService cartService;
	@Autowired
	ShipToService shipToService;
	@Autowired
	UserService userService;
	@Autowired
	CityService cityService;
	@Autowired
	ShippingZoneService shippingZoneService;
	@Autowired
	StateService stateService;
	@Autowired
	StatusService statusService;
	@Autowired
	UserRoleService userRoleService;


	@RequestMapping(value = "/{entity}/edit", method = RequestMethod.GET)	
	public ModelAndView editsController (@PathVariable("entity") String entity, @RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "showDetail", required = false) String showDetail, @RequestParam(value = "Update", required = false) String update, 
			HttpServletRequest request, Model model) {
		ModelAndView modelAndView = new ModelAndView();
	    ResponseStatus responseStatus = new ResponseStatus();

		modelAndView.addObject("headerTitle", "Edit Cart");
		modelAndView.addObject("frmLblSubmitBtn", "Update");
		modelAndView.addObject("shipTos", shipToService.getList());	
		modelAndView.addObject("statuses", statusService.getList());	
		modelAndView.addObject("users", userService.getList());	
		
		if (showDetail != null ) {
			modelAndView.addObject("headerTitle", "Edit Cart");
			modelAndView.addObject("frmLblSubmitBtn", "Update");
			modelAndView.addObject("cart", cartService.getOne(Long.valueOf(id)));	
				
			modelAndView.setViewName("cart/edit");
				
		}
		if (update != null) {
			Cart cart = this.makeCart(request);
			System.out.println("### This is the cart from request: " + cart);
			modelAndView.addObject("cart", cart);				
			responseStatus = cartService.update(cart);
			if (responseStatus.isValid()) { // if succesful, redirect to list, otherwise repaint form with error messages
				modelAndView.setViewName("cart/list");
		   	} else {
		   		modelAndView.setViewName("cart/edit");
		   	}
		}
		
		modelAndView.addObject("frmValMsgs", responseStatus.getServiceMsg());		
		return modelAndView;
	}

	private Cart makeCart(HttpServletRequest request) {
		Cart cart = new Cart();
	
		Long cartId = StringUtils.isNotEmpty(request.getParameter("frmCartId")) ? new Long(request.getParameter("frmCartId")) : null;
		Float linesAmount = StringUtils.isNotEmpty(request.getParameter("frmLinesAmount")) ? new Float(request.getParameter("frmLinesAmount")) : null;
		Float shippingAmount = StringUtils.isNotEmpty(request.getParameter("frmShippingAmount")) ? new Float(request.getParameter("frmShippingAmount")) : null;
		Float cartAmount = StringUtils.isNotEmpty(request.getParameter("frmCartAmount")) ? new Float(request.getParameter("frmCartAmount")) : null;
		Long shipToId = StringUtils.isNotEmpty(request.getParameter("frmShipToId")) ? new Long(request.getParameter("frmShipToId")) : null;
		Long statusId = StringUtils.isNotEmpty(request.getParameter("frmStatusId")) ? new Long(request.getParameter("frmStatusId")) : null;
		String createUser = StringUtils.isNotEmpty(request.getParameter("frmCreateUser")) ? new String(request.getParameter("frmCreateUser")) : null;
		String createDateString = StringUtils.isNotEmpty(request.getParameter("frmCreateDate")) ? new String(request.getParameter("frmCreateDate")) : null;
		Timestamp createDate = null;
		try {
			createDate = createDateString != null ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createDateString).getTime()) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String updateUser = StringUtils.isNotEmpty(request.getParameter("frmUpdateUser")) ? new String(request.getParameter("frmUpdateUser")) : null;
		String updateDateString = StringUtils.isNotEmpty(request.getParameter("frmUpdateDate")) ? new String(request.getParameter("frmUpdateDate")) : null;
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

	@RequestMapping(value = "/{entity}/list", method = RequestMethod.GET)
	public ModelAndView listsController (@PathVariable("entity") String entity) {
		ModelAndView modelAndView = new ModelAndView();

		switch (entity) {
		case "cart":
			modelAndView.setViewName("cart/list");
			modelAndView.addObject("carts", cartService.getList());	
			break;
		case "shipTo":
			modelAndView.setViewName("shipTo/list");
			modelAndView.addObject("shipTos", shipToService.getList());	
			break;
		case "user":
			modelAndView.setViewName("user/list");
			modelAndView.addObject("users", userService.getList());	
			break;
		case "city":
			modelAndView.setViewName("city/list");
			modelAndView.addObject("cities", cityService.getList());	
			break;
		case "shippingZone":
			modelAndView.setViewName("shippingZone/list");
			modelAndView.addObject("shippingZones", shippingZoneService.getList());	
			break;
		case "state":
			modelAndView.setViewName("state/list");
			modelAndView.addObject("states", stateService.getList());	
			break;
		case "status":
			modelAndView.setViewName("status/list");
			modelAndView.addObject("statuses", statusService.getList());	
			break;
		case "userRole":
			modelAndView.setViewName("userRole/list");
			modelAndView.addObject("userRoles", userRoleService.getList());	
			break;
		default:
			break;
		}
		
		return modelAndView;
	}

//	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
//	public ModelAndView hello(@PathVariable("name") String name) {
//
//		ModelAndView model = new ModelAndView();
//		model.setViewName("hello");
//		model.addObject("msg", name);
//
//		return model;
//
//	}
}
