package com.softtek.javaweb.repository;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.Status;

@Repository
public class StatusRepository {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public StatusRepository (final DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public Status getOne(final Long id) {
		String sql = "SELECT * FROM status WHERE status_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new StatusRowMapper());
	}

	public List<Status> list() {
		String sql = "SELECT * FROM status";
		return namedParameterJdbcTemplate.query(sql, new StatusRowMapper());
	}

}
