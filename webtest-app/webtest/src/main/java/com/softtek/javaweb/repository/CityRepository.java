package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.City;

public class CityRepository {

	public City getOne(final Long id) {
		City city = new City();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT city_id, description, state_id ");
		sql.append("FROM city ");
		sql.append("WHERE city_id = ?");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				city = this.buildEntity(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return city;
	}
	
	public List<City> list() {
		final List<City> cities = new ArrayList<City>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT city_id, description, state_id ");
		sql.append("FROM city ");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				cities.add(this.buildEntity(result));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cities;
	}

	private City buildEntity(ResultSet rs) throws SQLException  {
		City city = new City();
		StateRepository stateRepository = new StateRepository();
		
		city.setCityId(rs.getLong("city_id"));
		city.setDescription(rs.getString("description"));
		city.setState(stateRepository.getOne(rs.getLong("state_id")));
		return city;
	}
}
