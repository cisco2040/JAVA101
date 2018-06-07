package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.Cart;

public class CartRepository {

	public Cart getOne(final Long id) {
		Cart cart = new Cart();
		
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT cart_id, lines_amount, shipping_amount, cart_amount, ship_to_id, status_id, ");
		sql.append("create_user, create_date, update_user, update_date ");
		sql.append("FROM cart ");
		sql.append("WHERE cart_id = ?");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				cart = this.buildEntity(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cart;
	}
	
	public List<Cart> list() {
		final List<Cart> carts = new ArrayList<Cart>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cart_id, lines_amount, shipping_amount, cart_amount, ship_to_id, status_id, ");
		sql.append("create_user, create_date, update_user, update_date ");
		sql.append("FROM cart ");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				carts.add(this.buildEntity(result));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carts;
	}

	private Cart buildEntity(ResultSet rs) throws SQLException  {
		Cart cart = new Cart();
		ShipToRepository shipToRepository = new ShipToRepository();
		StatusRepository statusRepository = new StatusRepository();

		cart.setCartId(rs.getLong("cart_id"));
		cart.setLinesAmount(rs.getFloat("lines_amount"));
		cart.setShippingAmount(rs.getFloat("shipping_amount"));
		cart.setCartAmount(rs.getFloat("cart_amount"));
		cart.setShipTo(shipToRepository.getOne(rs.getLong("ship_to_id")));
		cart.setStatus(statusRepository.getOne(rs.getLong("status_id")));
		cart.setCreateUser(rs.getString("create_user"));
		cart.setCreateDate(rs.getDate("create_date"));
		cart.setUpdateUser(rs.getString("update_user"));
		cart.setUpdateDate(rs.getDate("update_date"));
		
		return cart;
	}
}
