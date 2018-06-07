package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.repository.UserRepository;

public class UserService {
	
	private UserRepository userRepository = new UserRepository();
	
	public List<User> getList() {
		return this.userRepository.list();
	}

	public User getOne(final String id) {
		return this.userRepository.getOne(id);
	}
}
