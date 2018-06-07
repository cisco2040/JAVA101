package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.State;
import com.softtek.javaweb.repository.StateRepository;

public class StateService {
	
	private StateRepository stateRepository = new StateRepository();
	
	public List<State> getList() {
		return this.stateRepository.list();
	}

	public State getOne(final Long id) {
		return this.stateRepository.getOne(id);
	}
}
