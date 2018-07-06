package com.softtek.javaweb.repository;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.City;

@Repository
public class CityRepository {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public CityRepository (final DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public City getOne(final Long id) {
		String sql = "SELECT * FROM city WHERE city_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new CityRowMapper());
	}
	
	public List<City> list() {
		String sql = "SELECT * FROM city ";
		return namedParameterJdbcTemplate.query(sql, new CityRowMapper());
	}
}
