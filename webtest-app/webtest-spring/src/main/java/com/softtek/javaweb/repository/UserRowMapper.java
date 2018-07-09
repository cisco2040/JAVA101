package com.softtek.javaweb.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.User;

@Repository
public class UserRowMapper implements RowMapper<User> {

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("### Is this on? " + userRoleRepository.isThisOn());
		return new User(
			rs.getString("username"),
			rs.getString("password"),
			rs.getString("name"),
			userRoleRepository.getOne(rs.getString("user_role_id")),
			rs.getString("active")		
		);
	}

}
