package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.UserRoleRowMapper;

@Repository
public class UserRoleRepository implements MyRepository<UserRole,String>{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public UserRole getOne(String id) {
		String sql = "SELECT * FROM user_role WHERE user_role_id = :id";		
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new UserRoleRowMapper());
	}

	@Override
	public List<UserRole> list() {
		String sql = "SELECT * FROM user_role";		
		return namedParameterJdbcTemplate.query(sql, new UserRoleRowMapper());		
	}

	@Override
	public int add(UserRole entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(UserRole entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
