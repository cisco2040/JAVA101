package com.softtek.javaweb.service.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

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
import com.softtek.javaweb.repository.jpa.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	public List<User> getList() throws ResourceNotAvailableException {
		List<User> users = this.userRepository.findAll();
		LOGGER.info("## User List Obtained: {}", users);
		if (users.isEmpty()) {
			throw new ResourceNotAvailableException("No Users were found.");
		}
		return users;
	}

	public User getOne(final String id) throws ResourceNotAvailableException {
		User user = this.userRepository.findById(id).orElse(null);
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
		User fullUser = this.userRepository.findById(id).orElse(null);
		
		if (fullUser == null) {
			throw new ResourceCouldNotBeFoundException("User <" + id + "> not found.");
		}
		
		User newUser = mergeBeans(user, fullUser);
		WebResponseStatus validateUser = validate(newUser, false);

		if (validateUser.isValid()) {
			LOGGER.info("## Attempting to update (PATCH) user: {}, with elements: {}", id, user);
			if (this.userRepository.save(newUser) == null) {
				throw new ResourceNotUpdatedException("Could not update user due to unknown problems during persist.");
			}
		} else {
			throw new ResourceNotUpdatedException("Could not update user due to missing/incorrect data.", validateBean(fullUser));
		}
		
		return this.userRepository.findById(id).get();
	}

	public User update(final User user) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		WebResponseStatus validateUser = validate(user, false);
		
		if (!isUnique(user)) {
			if (validateUser.isValid()) {
				LOGGER.info("## Attempting to update user: {}", user);
				if (this.userRepository.save(user) == null) {
					throw new ResourceNotUpdatedException("Could not update user due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotUpdatedException("Could not update user due to missing/incorrect data.", validateBean(user));
			}
		} else {
			throw new ResourceCouldNotBeFoundException("User <" + user.getUsername() + "> not found.");
		}
		
		return this.userRepository.findById(user.getUsername()).get();
	}
	
	public User add(final User user) throws ResourceNotAddedException {
		WebResponseStatus validateUser = validate(user, true);
		User newUser;
		
		if (isUnique(user)) {
			if (validateUser.isValid()) {
				LOGGER.info("## Attempting to add user: {}", user);
				newUser = this.userRepository.save(user);
				if (newUser == null) {
					throw new ResourceNotAddedException("Could not add user due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotAddedException("Could not add user due to missing/incorrect data.", validateBean(user));			
			}
		} else {			
			throw new ResourceNotAddedException("User <" + user.getUsername() + "> already exists. Please post a PUT or PATCH request if you mean to udpate this resource.");
		}
		
		return newUser;
	}

	public void delete (final String id) throws ResourceNotDeletedException {
		if (this.userRepository.findById(id).orElse(null) == null) {
			throw new ResourceNotDeletedException("User <" + id + "> not found.");			
		}
		this.userRepository.deleteById(id);		
	}
	
	public WebResponseStatus validate (final User user, final boolean checkUsername) {
		WebResponseStatus validateService = new WebResponseStatus();
		LOGGER.info("## Validating user: {}", user);
		validateService.setValid(true);

		List<RestError> validateBeanErrors = validateBean(user);
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
	
	private List<RestError> validateBean(final User user) {
		List<RestError> validationMsgs = new ArrayList<>();
		Set<ConstraintViolation<User>> violations = MyValidation.getBeanValidator().validate(user);

		if (!violations.isEmpty()) {
			violations.forEach(v -> validationMsgs.add(new RestError(v.getPropertyPath().toString(), v.getInvalidValue() != null ? v.getInvalidValue().toString() : null, v.getMessage())));
		}
		
		return validationMsgs;
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
		return this.userRepository.findById(user.getUsername()).isPresent() ? false : true;
	}
}