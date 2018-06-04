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
		sql.append("SELECT c.city_id AS city_id, c.description AS city_name, c.state_id AS state_id, s.description AS state_name, s.shipping_zone_id as shipping_zone_id ");
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
				long cityId = result.getLong("city_id");
				String cityName = result.getString("city_name");
				long stateId = result.getLong("state_id");
				String stateName = result.getString("state_name");
				String shippingZoneId = result.getString("shipping_zone_id");
				State state = new State(stateId, stateName, shippingZoneId);
				city = new City(cityId,cityName,state);	
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
		sql.append("SELECT c.city_id AS city_id, c.description AS city_name, c.state_id AS state_id, s.description AS state_name, s.shipping_zone_id as shipping_zone_id ");
		sql.append("FROM city AS c ");
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
				long cityId = result.getLong("city_id");
				String cityName = result.getString("city_name");
				long stateId = result.getLong("state_id");
				String stateName = result.getString("state_name");
				String shippingZoneId = result.getString("shipping_zone_id");
				State state = new State(stateId, stateName, shippingZoneId);
				cities.add(new City(cityId,cityName,state));	
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
		Long cityId = city.getId();
		String cityName = city.getDescription();
		Long stateId = city.getState().getId();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO city (city_id, description, state_id) ");
		sql.append("VALUES (?,?,?)");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			ps.setString(1, cityId.toString());			
			ps.setString(2, cityName);			
			ps.setString(3, stateId.toString());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}	
	}
	
	public void update(final City city) {

		Long cityId = city.getId();
		String cityName = city.getDescription();
		Long stateId = city.getState().getId();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE city ");
		sql.append("SET description = ?,");
		sql.append("SET state_id = ? ");
		sql.append("WHERE city_id = ? ");
		
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql.toString());			
			ps.setString(1, cityName);			
			ps.setString(2, stateId.toString());			
			ps.setString(3, cityId.toString());			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseConnection.close(connection, ps);
		}
	}	
}