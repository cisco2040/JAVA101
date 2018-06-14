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
		final List<Cart> carts = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cart_id, lines_amount, shipping_amount, cart_amount, ship_to_id, status_id, ");
		sql.append("create_user, create_date, update_user, update_date ");
		sql.append("FROM cart ");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
			ResultSet result = ps.executeQuery();
		) {
			while (result.next()) {
				carts.add(this.buildEntity(result));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carts;
	}
	public int add(final Cart cart) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cart ");
		sql.append("(lines_amount, shipping_amount, cart_amount, ship_to_id, status_id, ");
		sql.append("create_user, create_date, update_user, update_date) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setFloat(1, cart.getLinesAmount());
			ps.setFloat(2, cart.getShippingAmount());
			ps.setFloat(3, cart.getCartAmount());
			ps.setLong(4, cart.getShipTo().getShipToId());
			ps.setLong(5, cart.getStatus().getStatusId());
			ps.setString(6, cart.getCreateUser());
			ps.setTimestamp(7, cart.getCreateDate());
			ps.setString(8, cart.getUpdateUser());
			ps.setTimestamp(9, cart.getUpdateDate());

			status = ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return status;	
	}

	public int update(final Cart cart) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cart ");
		sql.append("SET lines_amount = ?, shipping_amount = ?, cart_amount = ?, ship_to_id = ?, status_id = ?, ");
		sql.append("create_user = ?, create_date = ?, update_user = ?, update_date = ? ");
		sql.append("WHERE cart_id = ?");
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setFloat(1, cart.getLinesAmount());
			ps.setFloat(2, cart.getShippingAmount());
			ps.setFloat(3, cart.getCartAmount());
			ps.setLong(4, cart.getShipTo().getShipToId());
			ps.setLong(5, cart.getStatus().getStatusId());
			ps.setString(6, cart.getCreateUser());
			ps.setTimestamp(7, cart.getCreateDate());
			ps.setString(8, cart.getUpdateUser());
			ps.setTimestamp(9, cart.getUpdateDate());
			ps.setLong(10, cart.getCartId());
			
			status = ps.executeUpdate();			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return status;	
	}

	public int delete(final Long id) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM cart ");
		sql.append("WHERE cart_id = ?");
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setLong(1, id);
			status = ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return status;	
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
		cart.setCreateDate(rs.getTimestamp("create_date"));
		cart.setUpdateUser(rs.getString("update_user"));
		cart.setUpdateDate(rs.getTimestamp("update_date"));
		
		return cart;
	}
}
