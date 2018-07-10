package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.State;
import com.softtek.javaweb.repository.impl.ShippingZoneRepository;

@Repository
public class StateRowMapper implements RowMapper<State> {

	@Autowired
	ShippingZoneRepository shippingZoneRepository;
	
	@Override
	public State mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new State (
			rs.getLong("state_id"),
			rs.getString("description"),
			shippingZoneRepository.getOne(rs.getString("shipping_zone_id"))
		);
	}

}
