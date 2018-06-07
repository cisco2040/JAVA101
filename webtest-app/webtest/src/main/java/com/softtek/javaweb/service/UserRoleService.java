package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.repository.UserRoleRepository;

public class UserRoleService {
	
	private UserRoleRepository userRoleRepository = new UserRoleRepository();
	
	public List<UserRole> getList() {
		return this.userRoleRepository.list();
	}

	public UserRole getOne(final String id) {
		return this.userRoleRepository.getOne(id);
	}
}
