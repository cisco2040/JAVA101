package com.softtek.javaweb.web;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	public static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String welcomePage( Principal principal, Model model ) {
		model.addAttribute("username", principal != null ? principal.getName() : "Anonymous");
		return "index";
	}	
	@RequestMapping(value = "/login", method = RequestMethod.GET, params = "error" )
	public String loginError(@RequestParam("error") String error, Model model) {
		LOGGER.info("## login Controller. error param: {}", error);
		model.addAttribute("error", "Invalid username and password!");
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET, params = "logout" )
	public String logout(@RequestParam("logout") String logout, Model model) {
		LOGGER.info("## login Controller. logout param: {}", logout);
		model.addAttribute("msg", "You've been logged out successfully.");
		return "login";
	}
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST } )
	public String loginOnly() {
		return "login";
	}
	@RequestMapping(value = "/403", method = RequestMethod.GET )
	public String authError() {
		return "403";
	}
	
}
