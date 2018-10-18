package com.softtek.javaweb.service.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.State;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.repository.jpa.StateRepository;

@Service
public class StateService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private StateRepository stateRepository;
	
	public List<State> getList() throws ResourceNotAvailableException {
		List<State> states = this.stateRepository.findAll();
		LOGGER.info("## State List Obtained: {}", states);
		if (states.isEmpty()) {
			throw new ResourceNotAvailableException("No states were found.");
		}
		return states;
	}

	public State getOne(final Long id) throws ResourceNotAvailableException {
		State state = this.stateRepository.findById(id).orElse(null);
		LOGGER.info("## State Obtained: {}", state);
		if (state == null) {
			throw new ResourceNotAvailableException("State id <" + id + "> not found.");
		}
		return state;
	}
}
