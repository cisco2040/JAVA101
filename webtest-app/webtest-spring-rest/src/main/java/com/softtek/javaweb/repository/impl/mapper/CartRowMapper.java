package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.repository.impl.ShipToRepository;
import com.softtek.javaweb.repository.impl.StatusRepository;

@Repository
public class CartRowMapper implements RowMapper<Cart> {
	
	@Autowired
	ShipToRepository shipToRepository;
	@Autowired
	StatusRepository statusRepository;
	@Override
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new Cart (
			rs.getLong("cart_id"),
			rs.getFloat("lines_amount"),
			rs.getFloat("shipping_amount"),
			rs.getFloat("cart_amount"),
			shipToRepository.getOne(rs.getLong("ship_to_id")),
			statusRepository.getOne(rs.getLong("status_id")),
			rs.getString("create_user"),
			rs.getTimestamp("create_date"),
			rs.getString("update_user"),
			rs.getTimestamp("update_date")
		);
	}
}
