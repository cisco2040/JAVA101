package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.UserRoleRowMapper;

//@Repository
//@Transactional
public class UserRoleRepository implements MyRepository<UserRole,String> {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private RowMapper<UserRole> userRoleRowMapper;

	@Override
	public UserRole getOne(String id) {
		String sql = "SELECT * FROM user_role WHERE user_role_id = :id";
		List<UserRole> userRoles = namedParameterJdbcTemplate.query(sql, Collections.singletonMap("id", id), userRoleRowMapper); 
		return !userRoles.isEmpty() ? userRoles.get(0) : null;
	}

	@Override
	public List<UserRole> list() {
		String sql = "SELECT * FROM user_role";		
		return namedParameterJdbcTemplate.query(sql, new UserRoleRowMapper());		
	}

	@Override
	public UserRole add(UserRole entity) {
		return null;
	}

	@Override
	public UserRole update(UserRole entity) {
		return null;
	}

	@Override
	public int delete(String id) {
		return 0;
	}

}
