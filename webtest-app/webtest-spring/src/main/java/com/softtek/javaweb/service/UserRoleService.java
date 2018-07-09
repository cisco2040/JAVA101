package com.softtek.javaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.repository.UserRoleRepository;

@Service
public class UserRoleService {
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	public List<UserRole> getList() {
		return this.userRoleRepository.list();
	}

	public UserRole getOne(final String id) {
		return this.userRoleRepository.getOne(id);
	}
}
