package com.softtek.javaweb.repository;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.State;

@Repository
public class StateRepository {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public StateRepository (final DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public State getOne(final Long id) {
		String sql = "SELECT * FROM state WHERE state_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new StateRowMapper());
	}
	
	public List<State> list() {
		String sql = "SELECT * FROM state ";
		return namedParameterJdbcTemplate.query(sql, new StateRowMapper());
	}

}
