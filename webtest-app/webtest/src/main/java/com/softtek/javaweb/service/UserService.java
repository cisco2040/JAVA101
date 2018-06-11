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
			this.userRepository.update(user);
			LOGGER.info("## User udpated: {}", user);
		}
		return validateUser;
	}
	
	public ValidateService add(final User user, final String confirmPassword) {
		ValidateService validateUser = validate(user, confirmPassword, true);
		if (validateUser.isValid()) {
			LOGGER.info("## Attempting to add user: {}", user);
			this.userRepository.add(user);
			LOGGER.info("## User added: {}", user);
		}
		return validateUser;
	}
	public boolean delete (final String id) {		
		return this.userRepository.delete(id) > 0;
	}
	
	public ValidateService validate (final User user, final String confirmPassword, final boolean checkUsername) {
		ValidateService validateService = new ValidateService();
		validateService.setValid(true);

		LOGGER.info("## Validating user: {}", user);

		if (user.getUsername() == null) { 
			LOGGER.info("## Checked username (invalid): {}", user);
			validateService.setValid(false);
			validateService.appendServiceMsg("Username is mandatory.");
		}
		LOGGER.info("## Checking username: {}", user);
		if (checkUsername) {
			if (!isUnique(user)) { 
				LOGGER.info("## Checked username unique (invalid): {}", user);
				validateService.setValid(false);
				validateService.appendServiceMsg("Username already exists in DB. It must be unique.");
			}
		}
		LOGGER.info("## Checking password: {}", user);
		if (user.getPassword() == null) {
			LOGGER.info("## Checked password (invalid): {}", user);
			validateService.setValid(false);
			validateService.appendServiceMsg("Password is mandatory.");
		} else	{
			LOGGER.info("## Checking password equals: {}", user);
			if (!user.getPassword().equals(confirmPassword)) {
				LOGGER.info("## Checked password equals (invalid): {}", user);
				validateService.setValid(false);
				validateService.appendServiceMsg("Password fields must match.");
			}
		}
		LOGGER.info("## Checking name: {}", user);
		if (user.getName() == null) {
			LOGGER.info("## Checked name (invalid): {}", user);
			validateService.setValid(false);
			validateService.appendServiceMsg("Name is mandatory.");
		}
		LOGGER.info("## Checking user role: {}", user);
		if (user.getUserRole().getUserRoleId() == null) {
			LOGGER.info("## Checked user role (invalid): {}", user);
			validateService.setValid(false);
			validateService.appendServiceMsg("User Role is mandatory.");
		}
		LOGGER.info("## Checking user active: {}", user);
		if (user.getActive() == null) {
			LOGGER.info("## Checked user active (invalid): {}", user);
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
			LOGGER.info("## Validating user (unique): {}", chkUser.getUsername());
			return chkUser.getUsername().equals(user.getUsername()) ? false : true;
		}
		return true;
	}
}