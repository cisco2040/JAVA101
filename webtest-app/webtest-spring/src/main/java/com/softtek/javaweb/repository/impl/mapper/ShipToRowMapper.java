package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.repository.impl.CityRepository;
import com.softtek.javaweb.repository.jpa.UserRepository;

@Repository
public class ShipToRowMapper implements RowMapper<ShipTo> {

	@Autowired
	UserRepository userRepository;
	@Autowired
	CityRepository cityRepository;
	
	@Override
	public ShipTo mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ShipTo(
			rs.getLong("ship_to_id"),
			userRepository.findById(rs.getString("user")).orElse(null),
			rs.getString("name"),
			rs.getString("address"),
			cityRepository.getOne(rs.getLong("city_id")),
			rs.getLong("zip_code"),
			rs.getString("phone"),
			rs.getString("email")
		);
	}

}
