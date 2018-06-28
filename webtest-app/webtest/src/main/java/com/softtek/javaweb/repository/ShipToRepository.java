package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.ShipTo;

public class ShipToRepository {
	private static final String WHERE_SHIP_TO_ID = "WHERE ship_to_id = ?";
	public ShipTo getOne(final Long id) {
		ShipTo shipTo = new ShipTo();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ship_to_id, user, name, address, city_id, zip_code, phone, email ");
		sql.append("FROM ship_to ");
		sql.append(WHERE_SHIP_TO_ID);

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				shipTo = this.buildEntity(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shipTo;
	}
	
	public List<ShipTo> list() {
		final List<ShipTo> shipTos = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ship_to_id, user, name, address, city_id, zip_code, phone, email ");
		sql.append("FROM ship_to");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				shipTos.add(this.buildEntity(result));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shipTos;
	}

	public int add(final ShipTo shipTo) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ship_to ");
		sql.append("(user, name, address, city_id, zip_code, phone, email) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setString(1, shipTo.getUser().getUsername());
			ps.setString(2, shipTo.getName());
			ps.setString(3, shipTo.getAddress());
			ps.setLong(4, shipTo.getCity().getCityId());
			ps.setLong(5, shipTo.getZipcode());
			ps.setString(6, shipTo.getPhone());
			ps.setString(7, shipTo.getEmail());
			
			status = ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return status;	
	}

	public int update(final ShipTo shipTo) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ship_to ");
		sql.append("SET user = ?, name = ?, address = ?, city_id = ?, zip_code = ?, phone = ?, email = ? ");
		sql.append(WHERE_SHIP_TO_ID);
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setString(1, shipTo.getUser().getUsername());
			ps.setString(2, shipTo.getName());
			ps.setString(3, shipTo.getAddress());
			ps.setLong(4, shipTo.getCity().getCityId());
			ps.setLong(5, shipTo.getZipcode());
			ps.setString(6, shipTo.getPhone());
			ps.setString(7, shipTo.getEmail());
			ps.setLong(8, shipTo.getShipToId());
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
		sql.append("DELETE FROM ship_to ");
		sql.append(WHERE_SHIP_TO_ID);
		
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
	
	private ShipTo buildEntity(ResultSet rs) throws SQLException  {
		ShipTo shipTo = new ShipTo();
		UserRepository userRepository = new UserRepository();
		CityRepository cityRepository = new CityRepository();
		
		shipTo.setShipToId(rs.getLong("ship_to_id"));
		shipTo.setUser(userRepository.getOne(rs.getString("user")));
		shipTo.setName(rs.getString("name"));
		shipTo.setAddress(rs.getString("address"));
		shipTo.setCity(cityRepository.getOne(rs.getLong("city_id")));
		shipTo.setZipcode(rs.getLong("zip_code"));
		shipTo.setPhone(rs.getString("phone"));
		shipTo.setEmail(rs.getString("email"));

		return shipTo;
	}
}
