package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository = new UserRepository();
	public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	public List<User> getList() {
		List<User> users = this.userRepository.list();
		LOGGER.info("## User List Obtained: {}", users);
		return users;
	}

	public User getOne(final String id) {
		User user = this.userRepository.getOne(id);
		LOGGER.info("## User Obtained: {}", user.toString());
		return user;
	}	
	public ValidateService update(final User user, final String confirmPassword) {
		ValidateService validateUser = validate(user, confirmPassword, false);
		if (validateUser.isValid()) {
			LOGGER.info("## Attempting to update user: {}", user);
			if (!(this.userRepository.update(user) > 0)) {
				validateUser.setValid(false);
				validateUser.appendServiceMsg("There was an unknown error while attempting to update record. Please contact DBAdmin.");				
			}
		}
		return validateUser;
	}
	
	public ValidateService add(final User user, final String confirmPassword) {
		ValidateService validateUser = validate(user, confirmPassword, true);
		if (validateUser.isValid()) {
			LOGGER.info("## Attempting to add user: {}", user);
			if (!(this.userRepository.add(user) > 0)) {
				validateUser.setValid(false);
				validateUser.appendServiceMsg("There was an unknown error while attempting to add record. Please contact DBAdmin.");				
			}
		}
		return validateUser;
	}
	public ValidateService delete (final String id) {
		ValidateService validateUser = new ValidateService();
		validateUser.setValid(true);
		if (!(this.userRepository.delete(id) > 0)) {
			validateUser.setValid(false);
			validateUser.appendServiceMsg("There was an unknown error while attempting to delete record, or record does not exist. Please contact DBAdmin.");
		}
				
		return validateUser;
	}
	
	public ValidateService validate (final User user, final String confirmPassword, final boolean checkUsername) {
		ValidateService validateService = new ValidateService();
		validateService.setValid(true);

		LOGGER.info("## Validating user: {}", user);

		if (user.getUsername() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Username is mandatory.");
		}
		if (checkUsername) {
			if (!isUnique(user)) { 
				validateService.setValid(false);
				validateService.appendServiceMsg("Username already exists in DB. It must be unique.");
			}
		}
		if (user.getPassword() == null) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Password is mandatory.");
		} else	{			
			if (!user.getPassword().equals(confirmPassword)) {
				validateService.setValid(false);
				validateService.appendServiceMsg("Password fields must match.");
			}
		}
		if (user.getName() == null) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Name is mandatory.");
		}
		if (user.getUserRole().getUserRoleId() == null) {
			validateService.setValid(false);
			validateService.appendServiceMsg("User Role is mandatory.");
		}
		if (user.getActive() == null) {
			validateService.setValid(false);
			validateService.appendServiceMsg("Active Flag is mandatory.");
		}
		LOGGER.info("## Validating user status: {}", validateService.isValid());
		LOGGER.info("## Validating user status messages: {}", validateService.getServiceMsg());
		
		return validateService;
	}
	
	public boolean isUnique(final User user) {
		User chkUser = this.getOne(user.getUsername());
		LOGGER.info("## Validating user (unique): {}", user);
		if (chkUser.getUsername() != null ) {
			return chkUser.getUsername().equals(user.getUsername()) ? false : true;
		}
		return true;
	}
}