package com.softtek.javaweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtek.javaweb.service.CityService;

@Controller
@RequestMapping("/city")
public class CityController {

	@Autowired
	CityService cityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String cityListController (Model model) {
		model.addAttribute("cities", cityService.getList());	
		return "city/list";
	}

}
