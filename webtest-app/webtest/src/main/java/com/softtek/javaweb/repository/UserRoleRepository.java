package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.UserRole;

public class UserRoleRepository {

	public UserRole getOne(final String id) {
		UserRole userRole = new UserRole();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user_role_id, description ");
		sql.append("FROM user_role ");
		sql.append("WHERE user_role_id = ?");
		
		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setString(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				userRole = this.buildEntity(result);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userRole;
	}
	public List<UserRole> list() {
		final List<UserRole> userRoles = new ArrayList<UserRole>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user_role_id, description ");
		sql.append("FROM user_role");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				userRoles.add(this.buildEntity(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userRoles;
	}

	private UserRole buildEntity(ResultSet rs) throws SQLException  {
		UserRole userRole = new UserRole();
		
		userRole.setUserRoleId(rs.getString("user_role_id"));
		userRole.setDescription(rs.getString("description"));
		
		return userRole;
	}

}
