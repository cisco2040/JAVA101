package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softtek.javaweb.domain.model.UserRole;

public class UserRoleRowMapper implements RowMapper<UserRole>{

	@Override
	public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new UserRole(
			rs.getString("user_role_id"),
			rs.getString("description")
		);
	}

}
