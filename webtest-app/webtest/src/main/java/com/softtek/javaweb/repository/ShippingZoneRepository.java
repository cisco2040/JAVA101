package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.ShippingZone;

public class ShippingZoneRepository {

	public ShippingZone getOne(final String id) {
		ShippingZone shippingZone = new ShippingZone();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT shipping_zone_id, description, delivery_time, shipping_cost ");
		sql.append("FROM shipping_zone ");
		sql.append("WHERE shipping_zone_id = ?");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setString(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				shippingZone = this.buildEntity(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shippingZone;
	}
	
	public List<ShippingZone> list() {
		final List<ShippingZone> shippingZones = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT shipping_zone_id, description, delivery_time, shipping_cost ");
		sql.append("FROM shipping_zone ");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
			ResultSet result = ps.executeQuery();
		) {
			while (result.next()) {
				shippingZones.add(this.buildEntity(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return shippingZones;
	}

	private ShippingZone buildEntity(ResultSet rs) throws SQLException  {
		ShippingZone shippingZone = new ShippingZone();
		
		shippingZone.setShippingZoneId(rs.getString("shipping_zone_id"));
		shippingZone.setDescription(rs.getString("description"));
		shippingZone.setDeliveryTime(rs.getLong("delivery_time"));
		shippingZone.setShippingCost(rs.getFloat("shipping_cost"));
		
		return shippingZone;
	}
}
