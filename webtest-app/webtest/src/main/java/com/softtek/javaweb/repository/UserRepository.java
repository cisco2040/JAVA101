package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.User;

public class UserRepository {

	public User getOne(final String id) {
		User user = new User();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT username, password, name, user_role_id, active ");
		sql.append("FROM user ");
		sql.append("WHERE username = ?");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setString(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				user = this.buildEntity(result);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List<User> list() {
		final List<User> users = new ArrayList<User>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT username, password, name, user_role_id, active ");
		sql.append("FROM user");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				users.add(this.buildEntity(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	private User buildEntity(ResultSet rs) throws SQLException  {
		User user = new User();
		UserRoleRepository userRoleRepository = new UserRoleRepository();
		
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setUserRole(userRoleRepository.getOne(rs.getString("user_role_id")));
		user.setActive(rs.getString("active"));
		
		return user;
	}
}
