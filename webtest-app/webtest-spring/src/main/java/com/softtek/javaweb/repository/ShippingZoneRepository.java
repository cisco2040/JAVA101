package com.softtek.javaweb.repository;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.ShippingZone;

@Repository
public class ShippingZoneRepository {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public ShippingZoneRepository (final DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ShippingZone getOne(final String id) {
		String sql = "SELECT * FROM shipping_zone WHERE shipping_zone_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new ShippingZoneRowMapper());
	}
	
	public List<ShippingZone> list() {
		String sql = "SELECT * FROM shipping_zone ";
		return namedParameterJdbcTemplate.query(sql, new ShippingZoneRowMapper());
	}

}
