package com.softtek.javaweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtek.javaweb.service.StatusService;

@Controller
@RequestMapping("/status")
public class StatusController {

	@Autowired
	StatusService statusService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String statusListController (Model model) {
		model.addAttribute("statuses", statusService.getList());	
		return "status/list";
	}
	
}
