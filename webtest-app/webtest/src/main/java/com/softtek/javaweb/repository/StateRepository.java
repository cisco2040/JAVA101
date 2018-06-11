package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.State;

public class StateRepository {

	public State getOne(final Long id) {
		State state = new State();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT state_id, description, shipping_zone_id ");
		sql.append("FROM state ");
		sql.append("WHERE state_id = ?");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				state = this.buildEntity(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return state;
	}
	
	public List<State> list() {
		final List<State> states = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT state_id, description, shipping_zone_id ");
		sql.append("FROM state ");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
			ResultSet result = ps.executeQuery();
		) {
			while (result.next()) {
				states.add(this.buildEntity(result));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return states;
	}

	private State buildEntity(ResultSet rs) throws SQLException  {
		State state = new State();
		ShippingZoneRepository shippingZoneRepository = new ShippingZoneRepository();

		state.setStateId(rs.getLong("state_id"));
		state.setDescription(rs.getString("description"));
		state.setShippingZone(shippingZoneRepository.getOne(rs.getString("shipping_zone_id")));
	
		return state;
	}
}
