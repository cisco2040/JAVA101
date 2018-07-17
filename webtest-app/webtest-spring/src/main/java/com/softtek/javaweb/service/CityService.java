package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class CityService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private MyRepository<City,Long> cityRepository;
	
	public List<City> getList() {
		List<City> cities = this.cityRepository.list();
		LOGGER.info("## City List Obtained: {}", cities);
		return cities;
	}

	public City getOne(final Long id) {
		City city = this.cityRepository.getOne(id);
		LOGGER.info("## City Obtained: {}", city);
		return this.cityRepository.getOne(id);
	}
}
