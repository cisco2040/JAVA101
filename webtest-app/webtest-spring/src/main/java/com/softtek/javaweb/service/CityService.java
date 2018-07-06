package com.softtek.javaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getList() {
		return this.cityRepository.list();
	}

	public City getOne(final Long id) {
		return this.cityRepository.getOne(id);
	}
}
