package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.repository.impl.UserRoleRepository;

@Repository
public class UserRowMapper implements RowMapper<User> {

	@Autowired
    UserRoleRepository userRoleRepository;

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new User(
			rs.getString("username"),
			rs.getString("password"),
			rs.getString("name"),
			userRoleRepository.getOne(rs.getString("user_role_id")),
			rs.getString("active")		
		);
	}
}
