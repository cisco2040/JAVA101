package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softtek.javaweb.domain.model.ShippingZone;

public class ShippingZoneRowMapper implements RowMapper<ShippingZone> {

	@Override
	public ShippingZone mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new ShippingZone(
			rs.getString("shipping_zone_id"),
			rs.getString("description"),
			rs.getLong("delivery_time"),
			rs.getFloat("shipping_cost")
		);
	}

}
