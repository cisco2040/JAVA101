package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.ShipToRowMapper;

@Repository
public class ShipToRepository implements MyRepository<ShipTo, Long> {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private ShipToRowMapper shipToRowMapper;

	@Override
	public ShipTo getOne(final Long id) {
		String sql = "SELECT * FROM ship_to WHERE ship_to_id = :id";
		List<ShipTo> shipTos = namedParameterJdbcTemplate.query(sql, Collections.singletonMap("id", id), shipToRowMapper); 
		return !shipTos.isEmpty() ? shipTos.get(0) : null;
	}
	
	@Override
	public List<ShipTo> list() {
		String sql = "SELECT * FROM ship_to";
		return namedParameterJdbcTemplate.query(sql, shipToRowMapper);
	}

	@Override
	public int add(final ShipTo shipTo) {
		StringBuilder sql = new StringBuilder();
		int rowsUpdated = 0;
		
		sql.append("INSERT INTO ship_to ");
		sql.append("(user, name, address, city_id, zip_code, phone, email) ");
		sql.append("VALUES (:user, :name, :address, :city_id, :zip, :phone, :email)");
		
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql.toString(), buildShipToArgs(shipTo));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated;
	}

	@Override
	public int update(final ShipTo shipTo) {
		StringBuilder sql = new StringBuilder();
		int rowsUpdated = 0;

		sql.append("UPDATE ship_to ");
		sql.append("SET user = :user, name = :name, address = :address, city_id = :city_id, zip_code = :zip, phone = :phone, email = :email ");
		sql.append("WHERE ship_to_id = :ship_to_id");
		
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql.toString(), buildShipToArgs(shipTo));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated;
	}

	@Override
	public int delete(final Long id) {
		String sql = "DELETE FROM ship_to WHERE ship_to_id = :id";
		int rowsUpdated = 0;
	
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql, Collections.singletonMap("id", id));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated;
	}	
	
	private Map<String, Object> buildShipToArgs(ShipTo shipTo) {
		Map<String, Object> shipToArgs = new HashMap<>();
		
		shipToArgs.put("ship_to_id", shipTo.getShipToId());
		shipToArgs.put("user", shipTo.getUser().getUsername());
		shipToArgs.put("name", shipTo.getName());
		shipToArgs.put("address", shipTo.getAddress());
		shipToArgs.put("city_id", shipTo.getCity().getCityId());
		shipToArgs.put("zip", shipTo.getZipcode());
		shipToArgs.put("phone", shipTo.getPhone());
		shipToArgs.put("email", shipTo.getEmail());
		
		return shipToArgs;
	}
}
