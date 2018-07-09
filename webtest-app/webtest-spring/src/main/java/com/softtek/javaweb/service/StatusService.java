package com.softtek.javaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> getList() {
		return this.statusRepository.list();
	}

	public Status getOne(final Long id) {
		return this.statusRepository.getOne(id);
	}
}
