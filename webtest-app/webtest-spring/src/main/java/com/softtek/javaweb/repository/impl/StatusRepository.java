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

	@Override
	public Status getOne(final Long id) {
		String sql = "SELECT * FROM status WHERE status_id = :id";
		return namedParameterJdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), new StatusRowMapper());
	}

	@Override
	public List<Status> list() {
		String sql = "SELECT * FROM status";
		return namedParameterJdbcTemplate.query(sql, new StatusRowMapper());
	}

	@Override
	public int add(Status entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Status entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
