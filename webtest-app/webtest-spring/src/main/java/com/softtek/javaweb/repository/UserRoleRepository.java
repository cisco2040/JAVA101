package com.softtek.javaweb.repository;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.UserRole;

@Repository
public class UserRoleRepository {

	private NamedParameterJdbcTemplate nameParameterJdbcTemplate;
	
	@Autowired
	public UserRoleRepository (DataSource dataSource) {
		this.nameParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public UserRole getOne(final String id) {
		String sql = "SELECT * FROM user_role WHERE user_role_id = :id";		
		return nameParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new UserRoleRowMapper());
	}
	public List<UserRole> list() {
		String sql = "SELECT * FROM user_role";		
		return nameParameterJdbcTemplate.query(sql, new UserRoleRowMapper());		
	}
}
