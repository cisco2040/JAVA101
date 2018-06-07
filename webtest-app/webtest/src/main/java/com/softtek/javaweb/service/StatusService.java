package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.repository.StatusRepository;

public class StatusService {
	
	private StatusRepository statusRepository = new StatusRepository();
	
	public List<Status> getList() {
		return this.statusRepository.list();
	}

	public Status getOne(final Long id) {
		return this.statusRepository.getOne(id);
	}
}
