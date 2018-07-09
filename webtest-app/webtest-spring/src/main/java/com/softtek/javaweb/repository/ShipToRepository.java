package com.softtek.javaweb.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.ShipTo;

@Repository
public class ShipToRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ShipTo getOne(final Long id) {
		String sql = "SELECT * FROM ship_to WHERE ship_to_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new ShipToRowMapper());
	}
	
	public List<ShipTo> list() {
		String sql = "SELECT * FROM ship_to";
		return namedParameterJdbcTemplate.query(sql, new ShipToRowMapper());
	}

	public int add(final ShipTo shipTo) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ship_to ");
		sql.append("(user, name, address, city_id, zip_code, phone, email) ");
		sql.append("VALUES (:user, :name, :address, :city_id, :zip, :phone, :email)");
		
		return namedParameterJdbcTemplate.update(sql.toString(), buildShipToArgs(shipTo));	
	}

	public int update(final ShipTo shipTo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ship_to ");
		sql.append("SET user = :user, name = :name, address = :address, city_id = :city_id, zip_code = :zip, phone = :phone, email = :email ");
		sql.append("WHERE ship_to_id = :ship_to_id");
		
		return namedParameterJdbcTemplate.update(sql.toString(), buildShipToArgs(shipTo));	
	}

	public int delete(final Long id) {
		String sql = "DELETE FROM ship_to WHERE ship_to_id = :id";
		return namedParameterJdbcTemplate.update(sql, Collections.singletonMap("id", id));	
	}	
	
	private Map<String, Object> buildShipToArgs(ShipTo shipTo) {
		Map<String, Object> shipToArgs = new HashMap<>();
		
		shipToArgs.put("ship_to_id", shipTo.getShipToId());
		shipToArgs.put("user", shipTo.getUser());
		shipToArgs.put("name", shipTo.getName());
		shipToArgs.put("address", shipTo.getAddress());
		shipToArgs.put("city_id", shipTo.getCity().getCityId());
		shipToArgs.put("zip", shipTo.getZipcode());
		shipToArgs.put("phone", shipTo.getPhone());
		shipToArgs.put("email", shipTo.getEmail());
		
		return shipToArgs;
	}
}
