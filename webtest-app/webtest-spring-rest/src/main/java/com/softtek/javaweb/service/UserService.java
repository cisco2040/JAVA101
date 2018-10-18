package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.domain.dto.WebResponseStatus;
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.exceptionhandling.MyValidation;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class UserService {
	
	@Autowired
	private MyRepository<User,String> userRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	public List<User> getList() throws ResourceNotAvailableException {
		List<User> users = this.userRepository.list();
		LOGGER.info("## User List Obtained: {}", users);
		if (users.isEmpty()) {
			throw new ResourceNotAvailableException("No Users were found.");
		}
		return users;
	}

	public User getOne(final String id) throws ResourceNotAvailableException {
		User user = this.userRepository.getOne(id);
		LOGGER.info("## User Obtained: {}", user);
		if (user == null) {
			throw new ResourceNotAvailableException("User <" + id + "> not found.");
		}
		return user;
	}	
	public User updateFull(final User user, final String id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		User fullUser = user;
		fullUser.setUsername(id);
		return this.update(fullUser);
	}
	
	public User updatePartial(final User user, final String id) throws ResourceCouldNotBeFoundException, ResourceNotUpdatedException {
		User fullUser = this.userRepository.getOne(id);
		
		if (fullUser == null) {
			throw new ResourceCouldNotBeFoundException("User <" + id + "> not found.");
		}
		
		User newUser = mergeBeans(user, fullUser);
		WebResponseStatus validateUser = validate(newUser, false);

		if (validateUser.isValid()) {
			LOGGER.info("## Attempting to update (PATCH) user: {}, with elements: {}", id, user);
			if (this.userRepository.update(newUser) == null) {
				throw new ResourceNotUpdatedException("Could not update user due to unknown problems during persist.");
			}
		} else {
			throw new ResourceNotUpdatedException("Could not update user due to missing/incorrect data.", MyValidation.validateBean(fullUser));
		}
		
		return userRepository.getOne(id);
	}

	public User update(final User user) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		WebResponseStatus validateUser = validate(user, false);
		
		if (!isUnique(user)) {
			if (validateUser.isValid()) {
				LOGGER.info("## Attempting to update user: {}", user);
				if (this.userRepository.update(user) == null) {
					throw new ResourceNotUpdatedException("Could not update user due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotUpdatedException("Could not update user due to missing/incorrect data.", MyValidation.validateBean(user));
			}
		} else {
			throw new ResourceCouldNotBeFoundException("User <" + user.getUsername() + "> not found.");
		}
		
		return userRepository.getOne(user.getUsername());
	}
	
	public User add(final User user) throws ResourceNotAddedException {
		WebResponseStatus validateUser = validate(user, true);
		User newUser;
		
		if (isUnique(user)) {
			if (validateUser.isValid()) {
				LOGGER.info("## Attempting to add user: {}", user);
				newUser = this.userRepository.add(user);
				if (newUser == null) {
					throw new ResourceNotAddedException("Could not add user due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotAddedException("Could not add user due to missing/incorrect data.", MyValidation.validateBean(user));			
			}
		} else {			
			throw new ResourceNotAddedException("User <" + user.getUsername() + "> already exists. Please post a PUT or PATCH request if you mean to udpate this resource.");
		}
		
		return newUser;
	}

	public void delete (final String id) throws ResourceNotDeletedException {
		if (this.userRepository.getOne(id) == null ) {
			throw new ResourceNotDeletedException("User <" + id + "> not found.");			
		} else if (this.userRepository.delete(id) <= 0) {
			throw new ResourceNotDeletedException("Could not delete user due to unknwon problems during delete process.");			
		}
	}
	
	public WebResponseStatus validate (final User user, final boolean checkUsername) {
		WebResponseStatus validateService = new WebResponseStatus();
		LOGGER.info("## Validating user: {}", user);
		validateService.setValid(true);

		List<RestError> validateBeanErrors = MyValidation.validateBean(user);
		if (!validateBeanErrors.isEmpty()) {
			validateService.setValid(false);
		}
		
		if (!isUnique(user) && checkUsername) { 
			validateService.setValid(false);
		}

		LOGGER.info("## Validating user status: {}", validateService.isValid());
		LOGGER.info("## Validating user status messages: {}", validateService.getServiceMsg());
		
		return validateService;
	}
	
	private User mergeBeans(final User partialUser, final User fullUser) {
		User newUser = fullUser;
		
		if (partialUser.getName() != null ) {
			newUser.setName(partialUser.getName());
		}
		if (partialUser.getPassword() != null ) {
			newUser.setPassword(partialUser.getPassword());
		}
		if (partialUser.getUserRole() != null && partialUser.getUserRole().getUserRoleId() != null ) {
			newUser.setUserRole(new UserRole(partialUser.getUserRole().getUserRoleId(),null));
		}
		if (partialUser.getActive() != null ) {
			newUser.setActive(partialUser.getActive());
		}
		
		return newUser;
	}
	private boolean isUnique(final User user) {
		LOGGER.info("## Validating user (unique): {}", user);
		return this.userRepository.getOne(user.getUsername()) == null ? true : false;
	}
}