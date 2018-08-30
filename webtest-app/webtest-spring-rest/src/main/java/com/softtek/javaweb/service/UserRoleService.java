package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class UserRoleService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	MyRepository<UserRole,String> userRoleRepository;
	
	public List<UserRole> getList() throws ResourceNotAvailableException {
		List<UserRole> userRoles = this.userRoleRepository.list(); 
		LOGGER.info("## User Role List Obtained: {}", userRoles);
		if (userRoles.isEmpty()) {
			throw new ResourceNotAvailableException("No user roles were found.");
		}
		return userRoles;
	}

	public UserRole getOne(final String id) throws ResourceNotAvailableException {
		UserRole userRole = this.userRoleRepository.getOne(id);
		LOGGER.info("## User Role Obtained: {}", userRole);
		if (userRole == null) {
			throw new ResourceNotAvailableException("User role id <" + id + "> not found.");
		}
		return userRole;
	}
}
