package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.ShippingZone;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.ShippingZoneRowMapper;

@Repository
public class ShippingZoneRepository implements MyRepository<ShippingZone,String>{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private ShippingZoneRowMapper shippingZoneRowMapper;
	
	@Override
	public ShippingZone getOne(final String id) {
		String sql = "SELECT * FROM shipping_zone WHERE shipping_zone_id = :id";
		List<ShippingZone> shippingZones = namedParameterJdbcTemplate.query(sql, Collections.singletonMap("id", id), shippingZoneRowMapper); 
		return !shippingZones.isEmpty() ? shippingZones.get(0) : null;
	}
	
	@Override
	public List<ShippingZone> list() {
		String sql = "SELECT * FROM shipping_zone ";
		return namedParameterJdbcTemplate.query(sql, new ShippingZoneRowMapper());
	}

	@Override
	public ShippingZone add(ShippingZone entity) {
		return null;
	}

	@Override
	public ShippingZone update(ShippingZone entity) {
		return null;
	}

	@Override
	public int delete(String id) {
		return 0;
	}

}
