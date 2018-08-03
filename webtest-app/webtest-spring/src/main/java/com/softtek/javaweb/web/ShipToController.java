package com.softtek.javaweb.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.dto.ShipToForm;
import com.softtek.javaweb.domain.mapper.EntityMapper;
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
	
	static final String HEADER = "headerTitle";
	static final String SUBMIT_BTN = "frmLblSubmitBtn";

	static final String EDIT_FORM = "shipTo/edit";
	static final String LIST_FORM = "shipTo/list";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String shipToListController (Model model) {
		model.addAttribute("shipTos", shipToService.getList());	
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
		model.addAttribute("shipTos", shipToService.getList());	
		return ShipToController.LIST_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"showDetail","id"})
	public String shipToEditController (@RequestParam("id") String id, Model model) {
		model.addAllAttributes(this.initializeEditForm("Edit Ship-To Address", "Update", null));
		model.addAttribute("shipTo", EntityMapper.makeFormFromShipTo(shipToService.getOne(Long.valueOf(id))));	
		return ShipToController.EDIT_FORM;				
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Update")
	public String shipToUpdateController (@ModelAttribute ShipToForm shipToForm, BindingResult result, Model model) {
		ResponseStatus responseStatus = shipToService.update(shipToForm);
		
		if (responseStatus.isValid()) {
			model.addAttribute("shipTos", shipToService.getList());	
			return ShipToController.LIST_FORM;				
	   	} else {
			model.addAttribute("shipTo", shipToForm);
	   		model.addAllAttributes(this.initializeEditForm("Edit Ship-To Address", "Update", responseStatus));
			return ShipToController.EDIT_FORM;				
	   	}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addNew")
	public String shipToNewController (Model model) {
   		model.addAllAttributes(this.initializeEditForm("Add New Ship-To Address", "Save", null));
		return ShipToController.EDIT_FORM;				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Save")
	public String shipToNewController (@ModelAttribute ShipToForm shipToForm, BindingResult result, Model model) {
		ResponseStatus responseStatus = shipToService.add(shipToForm);
		
		if (responseStatus.isValid()) {
			model.addAttribute("shipTos", shipToService.getList());	
			return ShipToController.LIST_FORM;				
	   	} else {
			model.addAttribute("shipTo", shipToForm);
	   		model.addAllAttributes(this.initializeEditForm("Add New Ship-To Address", "Save", responseStatus));
			return ShipToController.EDIT_FORM;				
	   	}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public String shipToDeleteController (@ModelAttribute ShipToForm shipToForm, BindingResult result, Model model) {
		ResponseStatus responseStatus = shipToService.delete(shipToForm.getShipToId());
		
		if (responseStatus.isValid()) {
			model.addAttribute("shipTos", shipToService.getList());	
			return ShipToController.LIST_FORM;				
	   	} else {
			model.addAttribute("shipTo", shipToForm);
	   		model.addAllAttributes(this.initializeEditForm("Edit Ship-To Address", "Update", responseStatus));
			return ShipToController.EDIT_FORM;				
	   	}
	}

	private Map<String,Object> initializeEditForm (final String header, final String submitAction, ResponseStatus responseStatus) {
		Map<String,Object> map = new HashMap<>();
		
		map.put(ShipToController.HEADER, header);
		map.put(ShipToController.SUBMIT_BTN, submitAction);
		map.put("cities", cityService.getList());	
		map.put("users", userService.getList());
		if (responseStatus != null) {
			map.put("frmValMsgs", responseStatus.getServiceMsg());
		}

		return map;
	}
}
