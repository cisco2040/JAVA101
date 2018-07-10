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
	
	@Override
	public ShippingZone getOne(final String id) {
		String sql = "SELECT * FROM shipping_zone WHERE shipping_zone_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new ShippingZoneRowMapper());
	}
	
	@Override
	public List<ShippingZone> list() {
		String sql = "SELECT * FROM shipping_zone ";
		return namedParameterJdbcTemplate.query(sql, new ShippingZoneRowMapper());
	}

	@Override
	public int add(ShippingZone entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ShippingZone entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
