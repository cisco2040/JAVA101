package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class UserRoleService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	MyRepository<UserRole,String> userRoleRepository;
	
	public List<UserRole> getList() {
		List<UserRole> userRoles = this.userRoleRepository.list(); 
		LOGGER.info("## User Role List Obtained: {}", userRoles);
		return userRoles;
	}

	public UserRole getOne(final String id) {
		UserRole userRole = this.userRoleRepository.getOne(id);
		LOGGER.info("## User Role Obtained: {}", userRole);
		return userRole;
	}
}
