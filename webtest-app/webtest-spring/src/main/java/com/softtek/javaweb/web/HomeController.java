package com.softtek.javaweb.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softtek.javaweb.service.CartService;

@Controller
public class HomeController {

	public static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String welcomePage() {
		return "index";
	}

	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET} )
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		LOGGER.info("## login Controller. error param: {}, logout param: {}", error, logout);

		if (error != null) {
			model.addAttribute("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
	
		return "login";
	}
}
