package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.repository.impl.StateRepository;

@Repository
public class CityRowMapper implements RowMapper<City> {
	@Autowired
	private StateRepository stateRepository;
	
	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new City(
			rs.getLong("city_id"),
			rs.getString("description"),
			stateRepository.getOne(rs.getLong("state_id"))
		);
	}

}
