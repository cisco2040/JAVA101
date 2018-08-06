package com.softtek.javaweb.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.dto.UserForm;
import com.softtek.javaweb.domain.mapper.EntityMapper;
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
	
	static final String HEADER = "headerTitle";
	static final String SUBMIT_BTN = "frmLblSubmitBtn";

	static final String EDIT_FORM = "user/edit";
	static final String LIST_FORM = "user/list";

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String userListController (Model model) {
		model.addAttribute("users", userService.getList());	
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
		model.addAttribute("users", userService.getList());	
		return UserController.LIST_FORM;
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = {"showDetail","id"})
	public String userEditController (@RequestParam("id") String id, Model model) {
		model.addAllAttributes(this.initializeEditForm("Edit User", "Update", EntityMapper.makeFormFromUser(userService.getOne(id)), null));
		return UserController.EDIT_FORM;				
	}	

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Update")
	public String userUpdateController (@ModelAttribute UserForm userForm, Model model) {
		ResponseStatus responseStatus = userService.update(userForm);
		
		if (responseStatus.isValid()) {
			model.addAttribute("users", userService.getList());	
			return UserController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit User", "Update", userForm, responseStatus));
			return UserController.EDIT_FORM;				
	   	}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addNew")
	public String userNewController (Model model) {
   		model.addAllAttributes(this.initializeEditForm("Add New User", "Save", null, null));
		return UserController.EDIT_FORM;				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "Save")
	public String userSaveController (@ModelAttribute UserForm userForm, Model model) {
		ResponseStatus responseStatus = userService.add(userForm);
		
		if (responseStatus.isValid()) {
			model.addAttribute("users", userService.getList());	
			return UserController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Add New User", "Save", userForm, responseStatus));
			return UserController.EDIT_FORM;				
	   	}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public String userDeleteController (@ModelAttribute UserForm userForm, Model model) {
		ResponseStatus responseStatus = userService.delete(userForm.getUsername());
		
		if (responseStatus.isValid()) {
			model.addAttribute("users", userService.getList());	
			return UserController.LIST_FORM;				
	   	} else {
	   		model.addAllAttributes(this.initializeEditForm("Edit User", "Update", userForm, responseStatus));
			return UserController.EDIT_FORM;				
	   	}
	}

	private Map<String,Object> initializeEditForm (final String header, final String submitAction, UserForm userForm, ResponseStatus responseStatus) {
		Map<String,Object> map = new HashMap<>();
		
		map.put(UserController.HEADER, header);
		map.put(UserController.SUBMIT_BTN, submitAction);
		if (userForm != null ) {
			map.put("user", EntityMapper.makeUserFromForm(userForm));
			map.put("passwordConfirm", userForm.getPasswordConfirm());
		}		
		map.put("userRoles", userRoleService.getList());
		if (responseStatus != null) {
			map.put("frmValMsgs", responseStatus.getServiceMsg());
		}

		return map;
	}

}
