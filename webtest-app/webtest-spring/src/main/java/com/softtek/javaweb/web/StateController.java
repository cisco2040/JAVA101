package com.softtek.javaweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtek.javaweb.service.StateService;

@Controller
@RequestMapping("/state")
public class StateController {

	@Autowired
	StateService stateService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String stateListController (Model model) {
		model.addAttribute("states", stateService.getList());	
		return "state/list";
	}
	
}
