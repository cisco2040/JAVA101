package com.softtek.javaweb.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.mapper.StatusRowMapper;

@Repository
public class StatusRepository implements MyRepository<Status,Long>{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private StatusRowMapper statusRowMapper;
	
	@Override
	public Status getOne(final Long id) {
		String sql = "SELECT * FROM status WHERE status_id = :id";
		List<Status> statuses = namedParameterJdbcTemplate.query(sql, Collections.singletonMap("id", id), statusRowMapper); 
		return !statuses.isEmpty() ? statuses.get(0) : null;
	}

	@Override
	public List<Status> list() {
		String sql = "SELECT * FROM status";
		return namedParameterJdbcTemplate.query(sql, new StatusRowMapper());
	}

	@Override
	public Status add(Status entity) {
		return null;
	}

	@Override
	public Status update(Status entity) {
		return null;
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

}
