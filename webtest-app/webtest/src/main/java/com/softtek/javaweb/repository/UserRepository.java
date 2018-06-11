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
		final List<User> users = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT username, password, name, user_role_id, active ");
		sql.append("FROM user");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
			ResultSet result = ps.executeQuery();
		) {
			while (result.next()) {
				users.add(this.buildEntity(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	public int add(final User user) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO user ");
		sql.append("(username, password, name, user_role_id, active) ");
		sql.append("VALUES (?, ?, ?, ?, ?)");
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getUserRole().getUserRoleId());
			ps.setString(5, user.getActive());
			status = ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return status;	
	}

	public int update(final User user) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE user ");
		sql.append("SET password = ?, name = ?, user_role_id = ?, active = ? ");
		sql.append("WHERE username = ?");
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setString(3, user.getUserRole().getUserRoleId());
			ps.setString(4, user.getActive());
			ps.setString(5, user.getUsername());
			status = ps.executeUpdate();			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return status;	
	}

	public int delete(final String id) {
		int status = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM user ");
		sql.append("WHERE username = ?");
		
		try (
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());
		) {
			ps.setString(1, id);
			status = ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return status;	
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
