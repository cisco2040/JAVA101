package com.softtek.javaweb.service.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.repository.jpa.CityRepository;

@Service
public class CityService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private CityRepository cityRepository;
	
	public List<City> getList() throws ResourceNotAvailableException {
		List<City> cities = this.cityRepository.findAll();
		LOGGER.info("## City List Obtained: {}", cities);
		if (cities.isEmpty()) {
			throw new ResourceNotAvailableException("No cities were found.");
		}
		return cities;
	}

	public City getOne(final Long id) throws ResourceNotAvailableException {
		City city = this.cityRepository.findById(id).orElse(null);
		LOGGER.info("## City Obtained: {}", city);
		if (city == null) {
			throw new ResourceNotAvailableException("City id <" + id + "> not found.");
		}
		return this.cityRepository.findOne(Example.of(new City(id,null,null))).get();
	}
}
