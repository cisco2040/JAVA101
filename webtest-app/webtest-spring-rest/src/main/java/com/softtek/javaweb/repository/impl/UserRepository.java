package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.UserRowMapper;

@Repository
public class UserRepository implements MyRepository<User,String>{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private UserRowMapper userRowMapper;
	
	@Override
	public User getOne(final String id) {
		String sql = "SELECT * FROM user WHERE username = :id";
		List<User> users = namedParameterJdbcTemplate.query(sql, Collections.singletonMap("id", id), userRowMapper); 
		return !users.isEmpty() ? users.get(0) : null;
	}
	
	@Override
	public List<User> list() {
		String sql = "SELECT * FROM user";
		return namedParameterJdbcTemplate.query(sql, userRowMapper);
	}
	
	@Override
	public User add(final User user) {
		StringBuilder sql = new StringBuilder();
		int rowsUpdated = 0;
		
		sql.append("INSERT INTO user ");
		sql.append("(username, password, name, user_role_id, active) ");
		sql.append("VALUES (:username, :pass, :name, :userrole, :active)");
		
		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql.toString(), buildItemArgs(user));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated > 0 ? getOne(user.getUsername()) : null;
	}

	@Override
	public User update(final User user) {
		StringBuilder sql = new StringBuilder();
		int rowsUpdated = 0;

		sql.append("UPDATE user ");
		sql.append("SET password = :pass, name = :name, user_role_id = :userrole, active = :active ");
		sql.append("WHERE username = :username");

		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql.toString(), buildItemArgs(user));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated > 0 ? getOne(user.getUsername()) : null;
	}

	@Override
	public int delete(final String id) {
		String sql = "DELETE FROM user WHERE username = :id";		
		int rowsUpdated = 0;

		try {
			rowsUpdated = namedParameterJdbcTemplate.update(sql, Collections.singletonMap("id", id));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return rowsUpdated;	
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
