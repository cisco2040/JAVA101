package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.State;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class StateService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private MyRepository<State,Long> stateRepository;
	
	public List<State> getList() {
		List<State> states = this.stateRepository.list();
		LOGGER.info("## State List Obtained: {}", states);
		return states;
	}

	public State getOne(final Long id) {
		State state = this.stateRepository.getOne(id);
		LOGGER.info("## State Obtained: {}", state);
		return state;
	}
}
