package com.softtek.javaweb.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.dto.UserForm;
import com.softtek.javaweb.domain.mapper.EntityMapper;
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class UserService {
	
	@Autowired
	private MyRepository<User,String> userRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	public List<User> getList() {
		List<User> users = this.userRepository.list();
		LOGGER.info("## User List Obtained: {}", users);
		return users;
	}

	public User getOne(final String id) {
		User user = this.userRepository.getOne(id);
		LOGGER.info("## User Obtained: {}", user);
		return user;
	}	
	public ResponseStatus update(final User user, final String confirmPassword) {
		ResponseStatus validateUser = validate(user, confirmPassword, false);
		if (validateUser.isValid()) {
			LOGGER.info("## Attempting to update user: {}", user);
			if (this.userRepository.update(user) <= 0) {
				validateUser.setValid(false);
				validateUser.appendServiceMsg("There was an unknown error while attempting to update record. Please contact DBAdmin.");				
			}
		}
		return validateUser;
	}
	public ResponseStatus update(final UserForm userForm) {
		return this.update(EntityMapper.makeUserFromForm(userForm), userForm.getPasswordConfirm());
	}
	
	public ResponseStatus add(final User user, final String confirmPassword) {
		ResponseStatus validateUser = validate(user, confirmPassword, true);
		if (validateUser.isValid()) {
			LOGGER.info("## Attempting to add user: {}", user);
			if (this.userRepository.add(user) < 0) {
				validateUser.setValid(false);
				validateUser.appendServiceMsg("There was an unknown error while attempting to add record. Please contact DBAdmin.");				
			}
		}
		return validateUser;
	}
	public ResponseStatus add(final UserForm userForm) {
		return this.add(EntityMapper.makeUserFromForm(userForm), userForm.getPasswordConfirm());
	}
	public ResponseStatus delete (final String id) {
		ResponseStatus validateUser = new ResponseStatus();
		validateUser.setValid(true);
		if (this.userRepository.delete(id) <= 0) {
			validateUser.setValid(false);
			validateUser.appendServiceMsg("There was an unknown error while attempting to delete record, or record does not exist. Please contact DBAdmin.");
		}
				
		return validateUser;
	}
	
	public ResponseStatus validate (final User user, final String confirmPassword, final boolean checkUsername) {
		ResponseStatus validateService = new ResponseStatus();
		validateService.setValid(true);

		LOGGER.info("## Validating user: {}", user);

		if (user.getUsername() == null || user.getUsername().equals(StringUtils.EMPTY)) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Username is mandatory.");
		}
		if (checkUsername && !isUnique(user)) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Username already exists in DB. It must be unique.");
		}
		if (user.getPassword() == null || user.getPassword().equals(StringUtils.EMPTY)) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Password is mandatory.");
		} else	{			
			if (!user.getPassword().equals(confirmPassword)) {
				validateService.setValid(false);
				validateService.appendServiceMsg("Password fields must match.");
			}
		}
		if (user.getName() == null || user.getName().equals(StringUtils.EMPTY)) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Name is mandatory.");
		}
		if (user.getUserRole().getUserRoleId() == null || user.getUserRole().getUserRoleId().equals(StringUtils.EMPTY)) {
			validateService.setValid(false);
			validateService.appendServiceMsg("User Role is mandatory.");
		}
		if (user.getActive() == null || user.getActive().equals(StringUtils.EMPTY)) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Active Flag is mandatory.");
		}
		LOGGER.info("## Validating user status: {}", validateService.isValid());
		LOGGER.info("## Validating user status messages: {}", validateService.getServiceMsg());
		
		return validateService;
	}
	
	private boolean isUnique(final User user) {
		User chkUser = this.getOne(user.getUsername());
		LOGGER.info("## Validating user (unique): {}", user);
		if (chkUser != null) {
			return !chkUser.getUsername().equals(user.getUsername());
		}
		return true;
	}

}