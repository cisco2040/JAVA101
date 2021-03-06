package com.softtek.academy.jsp.service;

import java.util.List;

import com.softtek.academy.jsp.domain.model.City;
import com.softtek.academy.jsp.domain.model.State;
import com.softtek.academy.jsp.repository.CityRepository;
import com.softtek.academy.jsp.repository.StateRepository;

public class CityService {
	
	private CityRepository cityRepository;
	private StateRepository stateRepository;
	
	
	public CityService() {
		cityRepository = new CityRepository();
		stateRepository = new StateRepository();
	}
	
	
	public City getOne(final Long id) {
		return cityRepository.getOne(id);
	}
	
	public List<City> list() {
		return cityRepository.list();
	}
	
	public City save(final Long id, final String description, final Long stateId) {
		final State state = stateRepository.getOne(stateId);
		final City city = new City(id, description, state);
		
		cityRepository.save(city);
		
		return city;
	}
	
	public City update(final Long id, final String description, final Long stateId) {
		final State state = stateRepository.getOne(stateId);
		final City city = new City(id, description, state);
		
		cityRepository.update(city);
		
		return city;
	}
}
