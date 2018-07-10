package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.State;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.StateRowMapper;

@Repository
public class StateRepository implements MyRepository<State, Long> {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private StateRowMapper stateRowMapper;
	
	@Override
	public State getOne(final Long id) {
		String sql = "SELECT * FROM state WHERE state_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), stateRowMapper);
	}
	
	@Override
	public List<State> list() {
		String sql = "SELECT * FROM state ";
		return namedParameterJdbcTemplate.query(sql, stateRowMapper);
	}

	@Override
	public int add(State entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(State entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
