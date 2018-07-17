package com.softtek.javaweb.web;

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
import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.service.CityService;
import com.softtek.javaweb.service.ShipToService;
import com.softtek.javaweb.service.UserService;

@Controller
@RequestMapping("/shipTo")
public class ShipToController {

	@Autowired
	ShipToService shipToService;
	@Autowired
	CityService cityService;
	@Autowired
	UserService userService;
	
	private ResponseStatus responseStatus;

	static final String HEADER = "headerTitle";
	static final String SUBMIT_BTN = "frmLblSubmitBtn";
	static final String EDIT_FORM = "shipTo/edit";
	static final String LIST_FORM = "shipTo/list";
	static final String SHIP_TO_BEAN = "shipTo";
	static final String SHIP_TO_LIST_BEAN = "shipTos";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String shipToListController (Model model) {
		model.addAttribute(ShipToController.SHIP_TO_LIST_BEAN, shipToService.getList());	
		return ShipToController.LIST_FORM;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "home")
	public String shipToHomeController () {
		return "redirect:/";
	}	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String shipToDefaultEditController () {
		return null;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancel")
	public String shipToCancelController (Model model) {
		model.addAttribute(ShipToController.SHIP_TO_LIST_BEAN, shipToService.getList());	
		return ShipToController.LIST_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"showDetail","id"})
	public String shipToEditController (@RequestParam("id") String id, Model model) {
		model.addAllAttributes(this.initializeEditForm("Edit Ship-To Address", "Update"));
		model.addAttribute(ShipToController.SHIP_TO_BEAN, shipToService.getOne(Long.valueOf(id)));	
		return ShipToController.EDIT_FORM;				
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Update")
	public String shipToUpdateController (HttpServletRequest httpRequest, Model model) {
		ShipTo shipTo = this.makeShipTo(httpRequest);
		model.addAttribute(ShipToController.SHIP_TO_BEAN, shipTo);
		responseStatus = shipToService.update(shipTo);
		
		if (responseStatus.isValid()) {
			model.addAttribute(ShipToController.SHIP_TO_LIST_BEAN, shipToService.getList());	
			return ShipToController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit Ship-To Address", "Update"));
			return ShipToController.EDIT_FORM;				
	   	}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addNew")
	public String shipToNewController (Model model) {
   		model.addAllAttributes(this.initializeEditForm("Add New Ship-To Address", "Save"));
		return ShipToController.EDIT_FORM;				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Save")
	public String shipToNewController (HttpServletRequest httpRequest, Model model) {
		ShipTo shipTo = this.makeShipTo(httpRequest);
		model.addAttribute(ShipToController.SHIP_TO_BEAN, shipTo);
		responseStatus = shipToService.add(shipTo);
		
		if (responseStatus.isValid()) {
			model.addAttribute(ShipToController.SHIP_TO_LIST_BEAN, shipToService.getList());	
			return ShipToController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Add New Ship-To Address", "Save"));
			return ShipToController.EDIT_FORM;				
	   	}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public String shipToDeleteController (HttpServletRequest httpRequest, Model model) {
		ShipTo shipTo = this.makeShipTo(httpRequest);
		model.addAttribute(ShipToController.SHIP_TO_BEAN, shipTo);
		responseStatus = shipToService.delete(shipTo.getShipToId());
		
		if (responseStatus.isValid()) {
			model.addAttribute(ShipToController.SHIP_TO_LIST_BEAN, shipToService.getList());	
			return ShipToController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit Ship-To Address", "Update"));
			return ShipToController.EDIT_FORM;				
	   	}
	}

	private Map<String,Object> initializeEditForm (final String header, final String submitAction) {
		Map<String,Object> map = new HashMap<>();
		
		map.put(ShipToController.HEADER, header);
		map.put(ShipToController.SUBMIT_BTN, submitAction);
		map.put("cities", cityService.getList());	
		map.put("users", userService.getList());
		if (this.responseStatus != null) {
			map.put("frmValMsgs", this.responseStatus.getServiceMsg());
		}

		return map;
	}
	private ShipTo makeShipTo(HttpServletRequest request) {
		ShipTo shipTo = new ShipTo();
		
		Long shipToId = StringUtils.isNotEmpty(request.getParameter("frmShipToId")) ? new Long(request.getParameter("frmShipToId")) : null;
		String user = StringUtils.isNotEmpty(request.getParameter("frmUserId")) ? request.getParameter("frmUserId") : null;
		String name = StringUtils.isNotEmpty(request.getParameter("frmName")) ? request.getParameter("frmName") : null;
		String address = StringUtils.isNotEmpty(request.getParameter("frmAddress")) ? request.getParameter("frmAddress") : null;
		Long cityId = StringUtils.isNotEmpty(request.getParameter("frmCityId")) ? new Long(request.getParameter("frmCityId")) : null;
		Long zipcode = StringUtils.isNotEmpty(request.getParameter("frmZipcode")) ? new Long(request.getParameter("frmZipcode")) : null;
		String phone = StringUtils.isNotEmpty(request.getParameter("frmPhone")) ? request.getParameter("frmPhone") : null;
		String email = StringUtils.isNotEmpty(request.getParameter("frmEmail")) ? request.getParameter("frmEmail") : null;

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
}
