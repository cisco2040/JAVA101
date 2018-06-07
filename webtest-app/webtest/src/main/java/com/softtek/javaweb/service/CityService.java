package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.repository.CityRepository;

public class CityService {
	
	private CityRepository cityRepository = new CityRepository();
	
	public List<City> getList() {
		return this.cityRepository.list();
	}

	public City getOne(final Long id) {
		return this.cityRepository.getOne(id);
	}
}
