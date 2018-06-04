package com.softtek.academy.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtek.academy.servlet.domain.model.State;

public class StateRepository {

	public static final Logger LOGGER = LoggerFactory.getLogger(StateRepository.class);

	public State getOne(final Long id) {
		State state = null;
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT state_id, description AS state_name, shipping_zone_id ");
		sql.append("FROM state ");
		sql.append("WHERE state_id = ?");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id.toString());			
			result = ps.executeQuery();
			while (result.next()) {
				long stateId = result.getLong("state_id");
				String stateName = result.getString("state_name");				
				String shippingZoneId = result.getString("shipping_zone_id");
				state = new State(stateId,stateName,shippingZoneId);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
		
		return state;
	}

	public List<State> list() {
		List<State> states = new ArrayList<State>();
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT state_id, description AS state_name, shipping_zone_id ");
		sql.append("FROM state");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		StateRepository.LOGGER.info("StatesRepo SQL Statement:" + sql);
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			result = ps.executeQuery();
			StateRepository.LOGGER.info("StatesRepo SQL Executed:" + result.toString());
			while (result.next()) {
				StateRepository.LOGGER.info("StatesRepo Start loop");
				long stateId = result.getLong("state_id");
				String stateName = result.getString("state_name");
				String shippingZoneId = result.getString("shipping_zone_id");
				states.add(new State(stateId,stateName,shippingZoneId));	
				StateRepository.LOGGER.info("StatesRepo End loop");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
		return states;

	}
	
	public void save(final State state) {

		Long stateId = state.getId();
		String stateName = state.getDescription();
		String shippingZoneId = state.getShippingZoneId();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO state (state_id, description, shipping_zone_id) ");
		sql.append("VALUES (?,?,?)");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			ps.setLong(1, stateId.longValue());			
			ps.setString(2, stateName);			
			ps.setString(3, shippingZoneId);			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}	
	}
}