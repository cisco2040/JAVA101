package com.softtek.javaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.State;
import com.softtek.javaweb.repository.StateRepository;

@Service
public class StateService {
	@Autowired
	private StateRepository stateRepository;
	
	public List<State> getList() {
		return this.stateRepository.list();
	}

	public State getOne(final Long id) {
		return this.stateRepository.getOne(id);
	}
}
