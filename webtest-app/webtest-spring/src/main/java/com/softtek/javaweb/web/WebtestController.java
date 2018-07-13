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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.domain.model.UserRole;
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

	static final String HEADER = "headerTitle";
	static final String SUBMIT_BTN = "frmLblSubmitBtn";

	@RequestMapping(value = "/cart/edit", method = { RequestMethod.GET,RequestMethod.POST })	
	public ModelAndView cartEditController (
			@RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "showDetail", required = false) String showDetail, 
			@RequestParam(value = "addNew", required = false) String addNew, 
			@RequestParam(value = "Update", required = false) String update, 
			@RequestParam(value = "Save", required = false) String save, 
			@RequestParam(value = "delete", required = false) String delete, 
			@RequestParam(value = "cancel", required = false) String cancel, 
			@RequestParam(value = "home", required = false) String home, 
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
	    ResponseStatus responseStatus = new ResponseStatus();
	    Cart cart = new Cart();
	    
	    // set default title and update action
		modelAndView.addObject(WebtestController.HEADER, "Edit Cart");
		modelAndView.addObject(WebtestController.SUBMIT_BTN, "Update");
		
		// populate these only if we're not doing any back out operations
		if (home == null && cancel == null) { 
			modelAndView.addObject("shipTos", shipToService.getList());	
			modelAndView.addObject("statuses", statusService.getList());	
			modelAndView.addObject("users", userService.getList());	
		}		
		if (home != null) {
			modelAndView.clear();
			modelAndView.setViewName("redirect:/");
		}
		if (cancel != null) {
			modelAndView.clear();
			modelAndView.addObject("carts", cartService.getList());	
			modelAndView.setViewName("cart/list");				
		}
		if (showDetail != null) {
			modelAndView.addObject("cart", cartService.getOne(Long.valueOf(id)));	
			modelAndView.setViewName("cart/edit");				
		}
		if (addNew != null) {
			modelAndView.addObject(WebtestController.HEADER, "Add New Cart");
			modelAndView.addObject(WebtestController.SUBMIT_BTN, "Save");
			modelAndView.setViewName("cart/edit");
		}
		// before any SQL update operation, build cart to refresh form in case of validation or SQL error
		if (update != null || save != null || delete != null) {
			cart = this.makeCart(request);
			modelAndView.addObject("cart", cart);				
		}
		if (update != null) {
			responseStatus = cartService.update(cart);
		}
		if (save != null) {
			responseStatus = cartService.add(cart);
			modelAndView.addObject(WebtestController.HEADER, "Add New Cart");
			modelAndView.addObject(WebtestController.SUBMIT_BTN, "Save");
		}
		if (delete != null) {
			responseStatus = cartService.delete(cart.getCartId());
		}
		
		// after any SQL update operation, check status and redirect to list if successful, otherwise repaint form
		if (update != null || save != null || delete != null) {  
			if (responseStatus.isValid()) {
				modelAndView.clear();
				modelAndView.addObject("carts", cartService.getList());	
				modelAndView.setViewName("cart/list");				
		   	} else {
		   		modelAndView.setViewName("cart/edit");
		   	}
		}
		
		// populate error message list if any
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

	@RequestMapping(value = "/shipTo/edit", method = { RequestMethod.GET,RequestMethod.POST })	
	public ModelAndView shipToEditController (
			@RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "showDetail", required = false) String showDetail, 
			@RequestParam(value = "addNew", required = false) String addNew, 
			@RequestParam(value = "Update", required = false) String update, 
			@RequestParam(value = "Save", required = false) String save, 
			@RequestParam(value = "delete", required = false) String delete, 
			@RequestParam(value = "cancel", required = false) String cancel, 
			@RequestParam(value = "home", required = false) String home, 
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
	    ResponseStatus responseStatus = new ResponseStatus();
	    ShipTo shipTo = new ShipTo();
	    
	    // set default title and update action
		modelAndView.addObject(WebtestController.HEADER, "Edit Ship-To Address");
		modelAndView.addObject(WebtestController.SUBMIT_BTN, "Update");
		
		// populate these only if we're not doing any back out operations
		if (home == null && cancel == null) { 
			modelAndView.addObject("cities", cityService.getList());	
			modelAndView.addObject("users", userService.getList());	
		}		
		if (home != null) {
			modelAndView.clear();
			modelAndView.setViewName("redirect:/");
		}
		if (cancel != null) {
			modelAndView.clear();
			modelAndView.addObject("shipTos", shipToService.getList());	
			modelAndView.setViewName("shipTo/list");				
		}
		if (showDetail != null) {
			modelAndView.addObject("shipTo", shipToService.getOne(Long.valueOf(id)));	
			modelAndView.setViewName("shipTo/edit");				
		}
		if (addNew != null) {
			modelAndView.addObject(WebtestController.HEADER, "Add New Ship-To Address");
			modelAndView.addObject(WebtestController.SUBMIT_BTN, "Save");
			modelAndView.setViewName("shipTo/edit");
		}
		// before any SQL update operation, build shipTo to refresh form in case of validation or SQL error
		if (update != null || save != null || delete != null) {
			shipTo = this.makeShipTo(request);
			modelAndView.addObject("shipTo", shipTo);				
		}
		if (update != null) {
			responseStatus = shipToService.update(shipTo);
		}
		if (save != null) {
			responseStatus = shipToService.add(shipTo);
			modelAndView.addObject(WebtestController.HEADER, "Add New Ship-To Address");
			modelAndView.addObject(WebtestController.SUBMIT_BTN, "Save");
		}
		if (delete != null) {
			responseStatus = shipToService.delete(shipTo.getShipToId());
		}
		
		// after any SQL update operation, check status and redirect to list if successful, otherwise repaint form
		if (update != null || save != null || delete != null) {  
			if (responseStatus.isValid()) {
				modelAndView.clear();
				modelAndView.addObject("shipTos", shipToService.getList());	
				modelAndView.setViewName("shipTo/list");				
		   	} else {
		   		modelAndView.setViewName("shipTo/edit");
		   	}
		}
		
		// populate error message list if any
		modelAndView.addObject("frmValMsgs", responseStatus.getServiceMsg());
		
		return modelAndView;
	}
	private ShipTo makeShipTo(HttpServletRequest request) {
		ShipTo shipTo = new ShipTo();
		
		Long shipToId = StringUtils.isNotEmpty(request.getParameter("frmShipToId")) ? new Long(request.getParameter("frmShipToId")) : null;
		String user = StringUtils.isNotEmpty(request.getParameter("frmUserId")) ? new String(request.getParameter("frmUserId")) : null;
		String name = StringUtils.isNotEmpty(request.getParameter("frmName")) ? new String(request.getParameter("frmName")) : null;
		String address = StringUtils.isNotEmpty(request.getParameter("frmAddress")) ? new String(request.getParameter("frmAddress")) : null;
		Long cityId = StringUtils.isNotEmpty(request.getParameter("frmCityId")) ? new Long(request.getParameter("frmCityId")) : null;
		Long zipcode = StringUtils.isNotEmpty(request.getParameter("frmZipcode")) ? new Long(request.getParameter("frmZipcode")) : null;
		String phone = StringUtils.isNotEmpty(request.getParameter("frmPhone")) ? new String(request.getParameter("frmPhone")) : null;
		String email = StringUtils.isNotEmpty(request.getParameter("frmEmail")) ? new String(request.getParameter("frmEmail")) : null;

		shipTo.setShipToId(shipToId);
		shipTo.setUser(new User(user, StringUtils.EMPTY, StringUtils.EMPTY, null, StringUtils.EMPTY));
		shipTo.setName(name);
		shipTo.setAddress(address);
		shipTo.setCity(new City(cityId, StringUtils.EMPTY, null));
		shipTo.setZipcode(zipcode);
		shipTo.setPhone(phone);
		shipTo.setEmail(email);

		return shipTo;
	}

	@RequestMapping(value = "/user/edit", method = { RequestMethod.GET,RequestMethod.POST })	
	public ModelAndView userEditController (
			@RequestParam(value = "id", required = false) String id, 
			@RequestParam(value = "showDetail", required = false) String showDetail, 
			@RequestParam(value = "addNew", required = false) String addNew, 
			@RequestParam(value = "Update", required = false) String update, 
			@RequestParam(value = "Save", required = false) String save, 
			@RequestParam(value = "delete", required = false) String delete, 
			@RequestParam(value = "cancel", required = false) String cancel, 
			@RequestParam(value = "home", required = false) String home, 
			HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
	    ResponseStatus responseStatus = new ResponseStatus();
	    User user = new User();
	    String passwordConfirm = StringUtils.isNotEmpty(request.getParameter("frmPasswordConfirm")) ?
			       new String(request.getParameter("frmPasswordConfirm")) : StringUtils.EMPTY;
		modelAndView.addObject("frmValPasswordConfirm", passwordConfirm);				
	    
	    // set default title and update action
		modelAndView.addObject(WebtestController.HEADER, "Edit User");
		modelAndView.addObject(WebtestController.SUBMIT_BTN, "Update");
		
		if (showDetail != null) {
			user = userService.getOne(id);
			modelAndView.addObject("user", user);	
			modelAndView.addObject("frmValPasswordConfirm", user.getPassword());				
			modelAndView.setViewName("user/edit");				
		}
		// populate these only if we're not doing any back out operations
		if (home == null && cancel == null) { 
			modelAndView.addObject("userRoles", userRoleService.getList());	
		}		
		if (home != null) {
			modelAndView.clear();
			modelAndView.setViewName("redirect:/");
		}
		if (cancel != null) {
			modelAndView.clear();
			modelAndView.addObject("users", userService.getList());	
			modelAndView.setViewName("user/list");				
		}
		if (addNew != null) {
			modelAndView.addObject(WebtestController.HEADER, "Add New User");
			modelAndView.addObject(WebtestController.SUBMIT_BTN, "Save");
			modelAndView.setViewName("user/edit");
		}
		// before any SQL update operation, build user to refresh form in case of validation or SQL error
		if (update != null || save != null || delete != null) {
			user = this.makeUser(request);
			modelAndView.addObject("user", user);				
		}
		if (update != null) {
			responseStatus = userService.update(user, passwordConfirm);
		}
		if (save != null) {
			responseStatus = userService.add(user, passwordConfirm);
			modelAndView.addObject(WebtestController.HEADER, "Add New User");
			modelAndView.addObject(WebtestController.SUBMIT_BTN, "Save");
		}
		if (delete != null) {
			responseStatus = userService.delete(user.getUsername());
		}
		
		// after any SQL update operation, check status and redirect to list if successful, otherwise repaint form
		if (update != null || save != null || delete != null) {  
			if (responseStatus.isValid()) {
				modelAndView.clear();
				modelAndView.addObject("users", userService.getList());	
				modelAndView.setViewName("user/list");				
		   	} else {
		   		modelAndView.setViewName("user/edit");
		   	}
		}
		
		// populate error message list if any
		modelAndView.addObject("frmValMsgs", responseStatus.getServiceMsg());
		
		return modelAndView;
	}

	private User makeUser(HttpServletRequest request) {
		User user = new User();
		
		String username = StringUtils.isNotEmpty(request.getParameter("frmUsername")) ? new String(request.getParameter("frmUsername")) : null;
		String password = StringUtils.isNotEmpty(request.getParameter("frmPassword")) ? new String(request.getParameter("frmPassword")) : null;
		String name = StringUtils.isNotEmpty(request.getParameter("frmName")) ? new String(request.getParameter("frmName")) : null;
		String userRoleId = StringUtils.isNotEmpty(request.getParameter("frmUserRoleId")) ? new String(request.getParameter("frmUserRoleId")) : null;
		String active = StringUtils.isNotEmpty(request.getParameter("frmActive")) ? new String(request.getParameter("frmActive")) : null;
		
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setUserRole(new UserRole(userRoleId, StringUtils.EMPTY));
		user.setActive(active);
		
		return user;
	}

	@RequestMapping(value = "/{entity}/list", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listsController (
			@PathVariable("entity") String entity, 
			@RequestParam(value = "id", required = false) String id) {
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

}
