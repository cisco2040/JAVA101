package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class StatusService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);
	
	@Autowired
	private MyRepository<Status,Long> statusRepository;
	
	public List<Status> getList() {
		List<Status> statuses = this.statusRepository.list();
		LOGGER.info("## Status List Obtained: {}", statuses);
		return statuses;
	}

	public Status getOne(final Long id) {
		Status status = this.statusRepository.getOne(id); 
		LOGGER.info("## Status Obtained: {}", status);
		return status;
	}
}
