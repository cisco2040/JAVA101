package com.softtek.javaweb.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.softtek.javaweb.domain.dto.CartForm;
import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.mapper.EntityMapper;
import com.softtek.javaweb.service.CartService;
import com.softtek.javaweb.service.ShipToService;
import com.softtek.javaweb.service.StatusService;
import com.softtek.javaweb.service.UserService;

@EnableWebMvc
@Controller
@RequestMapping("/cart")
public class CartController {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

	@Autowired
	CartService cartService;
	@Autowired
	ShipToService shipToService;
	@Autowired
	StatusService statusService;
	@Autowired
	UserService userService;
	
	static final String HEADER = "headerTitle";
	static final String SUBMIT_BTN = "frmLblSubmitBtn";
	
	static final String EDIT_FORM = "cart/edit";
	static final String LIST_FORM = "cart/list";

	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String cartListController (Model model) {
		model.addAttribute("carts", cartService.getList());	
		return CartController.LIST_FORM;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "home")
	public String cartHomeController () {
		return "redirect:/";
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancel")
	public String cartCancelController (Model model) {
		model.addAttribute("carts", cartService.getList());	
		return CartController.LIST_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"showDetail","id"})
	public String cartEditController (@RequestParam("id") String id, Model model) {
		model.addAllAttributes(this.initializeEditForm("Edit Cart", "Update", null));
		model.addAttribute("cart", cartService.getOne(Long.valueOf(id)));	
		return CartController.EDIT_FORM;				
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Update")
	public String cartUpdateController (@ModelAttribute final CartForm cartForm, BindingResult bindingResult, Model model) {
		ResponseStatus responseStatus = cartService.update(cartForm);

		if (responseStatus.isValid()) {
			model.addAttribute("carts", cartService.getList());	
			return CartController.LIST_FORM;				
	   	} else {
			model.addAttribute("cart", EntityMapper.makeCartFromForm(cartForm));
	   		model.addAllAttributes(this.initializeEditForm("Edit Cart", "Update", responseStatus));
			return CartController.EDIT_FORM;				
	   	}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addNew")
	public String cartNewController (Model model) {
   		model.addAllAttributes(this.initializeEditForm("Add New Cart", "Save", null));
		return CartController.EDIT_FORM;				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Save")
	public String cartSaveController (@ModelAttribute final CartForm cartForm, BindingResult bindingResult, Model model) {
		ResponseStatus responseStatus = cartService.add(cartForm);

		if (responseStatus.isValid()) {
			model.addAttribute("carts", cartService.getList());	
			return CartController.LIST_FORM;				
	   	} else {
			model.addAttribute("cart", EntityMapper.makeCartFromForm(cartForm));
	   		model.addAllAttributes(this.initializeEditForm("Add New Cart", "Save", responseStatus));
			return CartController.EDIT_FORM;				
	   	}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public String cartDeleteController (@ModelAttribute final CartForm cartForm, BindingResult bindingResult, Model model) {
		ResponseStatus responseStatus = cartService.delete(cartForm.getCartId());
		
		if (responseStatus.isValid()) {
			model.addAttribute("carts", cartService.getList());	
			return CartController.LIST_FORM;				
	   	} else {
			model.addAttribute("cart", EntityMapper.makeCartFromForm(cartForm));
	   		model.addAllAttributes(this.initializeEditForm("Edit Cart", "Update", responseStatus));
			return CartController.EDIT_FORM;				
	   	}
	}

	private Map<String,Object> initializeEditForm (final String header, final String submitAction, ResponseStatus responseStatus) {
		Map<String,Object> map = new HashMap<>();
		
		map.put(CartController.HEADER, header);
		map.put(CartController.SUBMIT_BTN, submitAction);
		map.put("shipTos", shipToService.getList());	
		map.put("statuses", statusService.getList());	
		map.put("users", userService.getList());
		if (responseStatus != null) {
			map.put("frmValMsgs", responseStatus.getServiceMsg());
		}

		return map;
	}
}
