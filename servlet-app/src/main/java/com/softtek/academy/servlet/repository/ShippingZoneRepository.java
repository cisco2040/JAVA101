package com.softtek.academy.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtek.academy.servlet.domain.model.ShippingZone;

public class ShippingZoneRepository {

	public static final Logger LOGGER = LoggerFactory.getLogger(ShippingZoneRepository.class);

	public ShippingZone getOne(final Long id) {
		ShippingZone shippingZone = null;
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT shipping_zone_id, description, delivery_time, shipping_cost ");
		sql.append("FROM shipping_zone ");
		sql.append("WHERE shipping_zone_id = ?");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id.toString());
			result = ps.executeQuery();
			while (result.next()) {
				String shippingZoneId = result.getString("shipping_zone_id");
				String description = result.getString("description");
				Long deliveryTime = result.getLong("delivery_time");
				Float shippingCost= result.getFloat("shipping_cost");
				shippingZone = new ShippingZone(shippingZoneId,description,deliveryTime,shippingCost);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
		
		return shippingZone;
	}

	public List<ShippingZone> list() {
		List<ShippingZone> shippingZones = new ArrayList<ShippingZone>();
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT shipping_zone_id, description, delivery_time, shipping_cost ");
		sql.append("FROM shipping_zone");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		ShippingZoneRepository.LOGGER.info("ShippingZonesRepo SQL ShippingZonement:" + sql);
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			result = ps.executeQuery();
			ShippingZoneRepository.LOGGER.info("ShippingZonesRepo SQL Executed:" + result.toString());
			while (result.next()) {
				String shippingZoneId = result.getString("shipping_zone_id");
				String description = result.getString("description");
				Long deliveryTime = result.getLong("delivery_time");
				Float shippingCost= result.getFloat("shipping_cost");
				shippingZones.add(new ShippingZone(shippingZoneId,description,deliveryTime,shippingCost));	
				ShippingZoneRepository.LOGGER.info("ShippingZonesRepo End loop");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
		return shippingZones;

	}
	
	public void save(final ShippingZone shippingZone) {

		String shippingZoneId = shippingZone.getShippingZoneId();
		String description = shippingZone.getDescription();
		Long deliveryTime = shippingZone.getDeliveryTime();
		Float shippingCost= shippingZone.getShippingCost();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO shippingZone (shipping_zone_id, description, delivery_time, shipping_cost) ");
		sql.append("VALUES (?,?,?,?)");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			ps.setString(1, shippingZoneId.toString());			
			ps.setString(2, description.toString());			
			ps.setInt(3, deliveryTime.intValue());			
			ps.setFloat(4, shippingCost.floatValue());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}	
	}
}