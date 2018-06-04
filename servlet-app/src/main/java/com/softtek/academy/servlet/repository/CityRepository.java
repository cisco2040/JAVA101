package com.softtek.academy.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softtek.academy.servlet.domain.model.City;
import com.softtek.academy.servlet.domain.model.State;

public class CityRepository {

	public static final Logger LOGGER = LoggerFactory.getLogger(CityRepository.class);

	public City getOne(final Long id) {
		City city = null;
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.city_id AS city_id, c.description AS city_name, c.state_id AS state_id, s.description AS state_name ");
		sql.append("FROM city AS c ");
		sql.append("INNER JOIN state AS s ON c.state_id = s.state_id ");
		sql.append("WHERE c.city_id = ?");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, id.toString());			
			result = ps.executeQuery();
			while (result.next()) {
				long city_id = result.getLong("city_id");
				String city_name = result.getString("city_name");
				long state_id = result.getLong("state_id");
				String state_name = result.getString("state_name");
				State state = new State(state_id, state_name);
				city = new City(city_id,city_name,state);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
		
		return city;
	}
	
	public List<City> list() {
			
		List<City> cities = new ArrayList<City>();
		ResultSet result = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.city_id AS city_id, c.description AS city_name, c.state_id AS state_id, s.description AS state_name FROM city AS c ");
		sql.append("INNER JOIN state AS s ON c.state_id=s.state_id");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		CityRepository.LOGGER.info("CitiesRepo SQL Statement:" + sql);
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			result = ps.executeQuery();
			CityRepository.LOGGER.info("CitiesRepo SQL Executed:" + result.toString());
			while (result.next()) {
				CityRepository.LOGGER.info("CitiesRepo Start loop");
				long city_id = result.getLong("city_id");
				String city_name = result.getString("city_name");
				long state_id = result.getLong("state_id");
				String state_name = result.getString("state_name");
				State state = new State(state_id, state_name);
				cities.add(new City(city_id,city_name,state));	
				CityRepository.LOGGER.info("CitiesRepo End loop");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
		return cities;
	}
	
	public void save(final City city) {
		//CityRepository.cities.add(city);
		Long city_id = city.getId();
		String city_name = city.getDescription();
		Long state_id = city.getState().getId();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO city (city_id, description, state_id) ");
		sql.append("VALUES (?,?,?)");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			ps.setString(1, city_id.toString());			
			ps.setString(2, city_name);			
			ps.setString(3, state_id.toString());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}	
	}
	
	public void update(final City city) {

		Long city_id = city.getId();
		String city_name = city.getDescription();
		Long state_id = city.getState().getId();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE city ");
		sql.append("SET description = ?,");
		sql.append("SET state_id = ? ");
		sql.append("WHERE city_id = ? ");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			ps.setString(1, city_name);			
			ps.setString(2, state_id.toString());			
			ps.setString(3, city_id.toString());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
	}	
}