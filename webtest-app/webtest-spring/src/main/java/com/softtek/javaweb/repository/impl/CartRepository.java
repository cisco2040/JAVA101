package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.CartRowMapper;

@Repository
public class CartRepository implements MyRepository<Cart,Long> {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private CartRowMapper cartRowMapper;

	@Override
	public Cart getOne(final Long id) {
		String sql = "SELECT * FROM cart WHERE cart_id = :id";
		List<Cart> carts = namedParameterJdbcTemplate.query(sql, Collections.singletonMap("id", id), cartRowMapper); 
		return !carts.isEmpty() ? carts.get(0) : null;
	}
	
	@Override
	public List<Cart> list() {
		String sql = "SELECT * FROM cart";
		return namedParameterJdbcTemplate.query(sql, cartRowMapper);
	}

	@Override
	public int add(final Cart cart) {
		StringBuilder sql = new StringBuilder();
		int rowsUpdated = 0;
		
		sql.append("INSERT INTO cart ");
		sql.append("(lines_amount, shipping_amount, cart_amount, ship_to_id, status_id, ");
		sql.append("create_user, create_date, update_user, update_date) ");
		sql.append("VALUES (:lnamt, :shpamt, :crtamt, :ship_to_id, :status_id, :crtuser, :crtdate, :upduser, :upddate)");
		
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql.toString(),buildCartArgs(cart));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated; 	
	}

	@Override
	public int update(final Cart cart) {
		StringBuilder sql = new StringBuilder();
		int rowsUpdated = 0;

		sql.append("UPDATE cart ");
		sql.append("SET lines_amount = :lnamt, shipping_amount = :shpamt, cart_amount = :crtamt, ship_to_id = :ship_to_id, status_id = :status_id, ");
		sql.append("create_user = :crtuser, create_date = :crtdate, update_user = :upduser, update_date = :upddate ");
		sql.append("WHERE cart_id = :cart_id");
			
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql.toString(), buildCartArgs(cart));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated; 	
	}

	@Override
	public int delete(final Long id) {
		String sql = "DELETE FROM cart WHERE cart_id = :id";
		int rowsUpdated = 0;
	
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql, Collections.singletonMap("id", id));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated; 	
	}	

	private Map<String, Object> buildCartArgs(Cart cart) {
		Map<String, Object> cartArgs = new HashMap<>();
		
		cartArgs.put("cart_id", cart.getCartId());
		cartArgs.put("lnamt", cart.getLinesAmount());
		cartArgs.put("shpamt", cart.getShippingAmount());
		cartArgs.put("crtamt", cart.getCartAmount());
		cartArgs.put("ship_to_id", cart.getShipTo().getShipToId());
		cartArgs.put("status_id", cart.getStatus().getStatusId());
		cartArgs.put("crtuser", cart.getCreateUser());
		cartArgs.put("crtdate", cart.getCreateDate());
		cartArgs.put("upduser", cart.getUpdateUser());
		cartArgs.put("upddate", cart.getUpdateDate());
		
		return cartArgs;
	}
}
