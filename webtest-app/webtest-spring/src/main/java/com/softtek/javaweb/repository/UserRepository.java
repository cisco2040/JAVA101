package com.softtek.javaweb.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.User;

@Repository
public class UserRepository {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public UserRepository (final DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate (dataSource);
	}
	
	public User getOne(final String id) {
		String sql = "SELECT * FROM user WHERE username = :id";
		System.out.println("##### This is the query ");
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new UserRowMapper());
	}
	
	public List<User> list() {
		String sql = "SELECT * FROM user";		
		return namedParameterJdbcTemplate.query(sql, new UserRowMapper());
	}
	
	public int add(final User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO user ");
		sql.append("(username, password, name, user_role_id, active) ");
		sql.append("VALUES (:username, :pass, :name, :userrole, :active)");
		
		return namedParameterJdbcTemplate.update(sql.toString(), buildItemArgs(user));	
	}

	public int update(final User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE user ");
		sql.append("SET password = :pass, name = :name, user_role_id = :userrole, active = :active ");
		sql.append("WHERE username = :username");

		return namedParameterJdbcTemplate.update(sql.toString(), buildItemArgs(user));
	}

	public int delete(final String id) {
		String sql = "DELETE FROM user WHERE_username = :id";		
		return namedParameterJdbcTemplate.update(sql, Collections.singletonMap("id", id));	
	}
	
	private Map<String, Object> buildItemArgs (User user) {
		Map<String, Object> itemArgs = new HashMap<>();
		
		itemArgs.put("username",user.getUsername());
		itemArgs.put("pass",user.getPassword());
		itemArgs.put("name",user.getName());
		itemArgs.put("userrole",user.getUserRole().getUserRoleId());
		itemArgs.put("active",user.getActive());
		
		return itemArgs;
	}
}
