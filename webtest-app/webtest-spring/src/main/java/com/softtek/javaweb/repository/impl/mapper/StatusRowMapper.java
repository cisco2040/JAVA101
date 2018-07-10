package com.softtek.javaweb.repository.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.softtek.javaweb.domain.model.Status;

public class StatusRowMapper implements RowMapper<Status> {

	@Override
	public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Status(
			rs.getLong("status_id"),
			rs.getString("description"),
			rs.getString("status_type")
		);
	}

}
