package com.softtek.javaweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtek.javaweb.service.ShippingZoneService;

@Controller
@RequestMapping("/shippingZone")
public class ShippingZoneController {

	@Autowired
	ShippingZoneService shippingZoneService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String shippingZoneListController (Model model) {
		model.addAttribute("shippingZones", shippingZoneService.getList());	
		return "shippingZone/list";
	}
	
}
