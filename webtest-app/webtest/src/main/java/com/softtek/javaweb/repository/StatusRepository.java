package com.softtek.javaweb.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softtek.javaweb.connection.DriverManagerDatabase;
import com.softtek.javaweb.domain.model.Status;

public class StatusRepository {

	public Status getOne(final Long id) {
		Status status = new Status();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT status_id, description,status_type ");
		sql.append("FROM status ");
		sql.append("WHERE status_id = ?");
		
		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
		) {
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				status = this.buildEntity(result);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public List<Status> list() {
		final List<Status> statuses = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT status_id, description, status_type ");
		sql.append("FROM status ");

		try 
		( 
			Connection connection = DriverManagerDatabase.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql.toString());				
			ResultSet result = ps.executeQuery();
		) {
			while (result.next()) {
				statuses.add(this.buildEntity(result));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statuses;
	}

	private Status buildEntity(ResultSet rs) throws SQLException  {
		Status status = new Status();
		
		status.setStatusId(rs.getLong("status_id"));
		status.setDescription(rs.getString("description"));
		status.setStatusType(rs.getString("status_type"));
		
		return status;
	}
}
