package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.CityRowMapper;

@Repository
public class CityRepository implements MyRepository<City,Long>{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private CityRowMapper cityRowMapper;

	@Override
	public City getOne(final Long id) {
		String sql = "SELECT * FROM city WHERE city_id = :id";
		List<City> cities = namedParameterJdbcTemplate.query(sql, Collections.singletonMap("id", id), cityRowMapper); 
		return !cities.isEmpty() ? cities.get(0) : null;
	}
	
	@Override
	public List<City> list() {
		String sql = "SELECT * FROM city ";
		return namedParameterJdbcTemplate.query(sql, cityRowMapper);
	}

	@Override
	public City add(City entity) {
		return null;
	}

	@Override
	public City update(City entity) {
		return null;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}
}
