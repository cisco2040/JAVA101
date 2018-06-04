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
		sql.append("SELECT state_id, description AS state_name ");
		sql.append("FROM state ");
		sql.append("WHERE state_id = ?");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id.toString());			
			result = ps.executeQuery();
			while (result.next()) {
				long state_id = result.getLong("state_id");
				String state_name = result.getString("state_name");
				state = new State(state_id,state_name);	
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
		sql.append("SELECT state_id , description AS state_name ");
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
				long state_id = result.getLong("state_id");
				String state_name = result.getString("state_name");
				states.add(new State(state_id,state_name));	
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

		Long state_id = state.getId();
		String state_name = state.getDescription();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO state (state_id, description) ");
		sql.append("VALUES (?,?)");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			ps.setString(1, state_id.toString());			
			ps.setString(2, state_name);			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}	
	}
}