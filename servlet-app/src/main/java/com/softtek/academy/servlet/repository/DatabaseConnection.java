package com.softtek.academy.servlet.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DatabaseConnection {
	
	private static BasicDataSource ds;
	
	static {
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUsername("myuser");
		ds.setPassword("mypassword");
		ds.setUrl("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&useSSL=false");
	}
	
	private DatabaseConnection () {}
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			connection = ds.getConnection();			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return connection;
	}

	public static void close(Connection c, PreparedStatement ps) {
		if (ps != null && c != null) {
			try {
				if (!c.isClosed()) {
					c.close();
				}
				if (!ps.isClosed()) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
