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
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.service.UserService;
import com.softtek.javaweb.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	
	private ResponseStatus responseStatus;

	static final String HEADER = "headerTitle";
	static final String SUBMIT_BTN = "frmLblSubmitBtn";
	static final String EDIT_FORM = "user/edit";
	static final String LIST_FORM = "user/list";
	static final String USER_BEAN = "user";
	static final String USER_LIST_BEAN = "users";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userListController (Model model) {
		model.addAttribute(UserController.USER_LIST_BEAN, userService.getList());	
		return UserController.LIST_FORM;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "home")
	public String userHomeController () {
		return "redirect:/";
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String userDefaultEditController () {
		return null;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "cancel")
	public String userCancelController (Model model) {
		model.addAttribute(UserController.USER_LIST_BEAN, userService.getList());	
		return UserController.LIST_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"showDetail","id"})
	public String userEditController (@RequestParam("id") String id, Model model) {
		User user = userService.getOne(id);
		model.addAllAttributes(this.initializeEditForm("Edit User", user.getPassword(), "Update"));
		model.addAttribute(UserController.USER_BEAN, user);	
		return UserController.EDIT_FORM;				
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = { "Update", "frmPasswordConfirm" })
	public String userUpdateController (@RequestParam("frmPasswordConfirm") String passwordConfirm, HttpServletRequest httpRequest, Model model) {
		User user = this.makeUser(httpRequest);
		model.addAttribute(UserController.USER_BEAN, user);
		responseStatus = userService.update(user, passwordConfirm);
		
		if (responseStatus.isValid()) {
			model.addAttribute(UserController.USER_LIST_BEAN, userService.getList());	
			return UserController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit User", passwordConfirm, "Update"));
			return UserController.EDIT_FORM;				
	   	}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addNew")
	public String userNewController (Model model) {
   		model.addAllAttributes(this.initializeEditForm("Add New User", StringUtils.EMPTY, "Save"));
		return UserController.EDIT_FORM;				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = { "Save", "frmPasswordConfirm" })
	public String userSaveController (@RequestParam("frmPasswordConfirm") String passwordConfirm, HttpServletRequest httpRequest, Model model) {
		User user = this.makeUser(httpRequest);
		model.addAttribute(UserController.USER_BEAN, user);
		responseStatus = userService.add(user, passwordConfirm);
		
		if (responseStatus.isValid()) {
			model.addAttribute(UserController.USER_LIST_BEAN, userService.getList());	
			return UserController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Add New User", passwordConfirm, "Save"));
			return UserController.EDIT_FORM;				
	   	}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public String userDeleteController (@RequestParam("frmPasswordConfirm") String passwordConfirm, HttpServletRequest httpRequest, Model model) {
		User user = this.makeUser(httpRequest);
		model.addAttribute(UserController.USER_BEAN, user);
		responseStatus = userService.delete(user.getUsername());
		
		if (responseStatus.isValid()) {
			model.addAttribute(UserController.USER_LIST_BEAN, userService.getList());	
			return UserController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit User", passwordConfirm, "Update"));
			return UserController.EDIT_FORM;				
	   	}
	}

	private Map<String,Object> initializeEditForm (final String header, final String passwordConfirm, final String submitAction) {
		Map<String,Object> map = new HashMap<>();
		
		map.put(UserController.HEADER, header);
		map.put(UserController.SUBMIT_BTN, submitAction);
		map.put("userRoles", userRoleService.getList());
		map.put("frmValPasswordConfirm", passwordConfirm);
		if (this.responseStatus != null) {
			map.put("frmValMsgs", this.responseStatus.getServiceMsg());
		}

		return map;
	}

	private User makeUser(HttpServletRequest request) {
		User user = new User();
		
		String username = StringUtils.isNotEmpty(request.getParameter("frmUsername")) ? request.getParameter("frmUsername") : null;
		String password = StringUtils.isNotEmpty(request.getParameter("frmPassword")) ? request.getParameter("frmPassword") : null;
		String name = StringUtils.isNotEmpty(request.getParameter("frmName")) ? request.getParameter("frmName") : null;
		String userRoleId = StringUtils.isNotEmpty(request.getParameter("frmUserRoleId")) ? request.getParameter("frmUserRoleId") : null;
		String active = StringUtils.isNotEmpty(request.getParameter("frmActive")) ? request.getParameter("frmActive") : null;
		
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setUserRole(new UserRole(userRoleId, StringUtils.EMPTY));
		user.setActive(active);
		
		return user;
	}
}
