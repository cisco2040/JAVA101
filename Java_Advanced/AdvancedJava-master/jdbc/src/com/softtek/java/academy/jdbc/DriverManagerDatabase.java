package com.softtek.java.academy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerDatabase {

    static {

        try {

            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private DriverManagerDatabase() {

    }

    public static Connection getConnection() {

        Connection connection = null;
        try {

            connection =
                    DriverManager.getConnection(
                    	//	"jdbc:mysql://localhost:3306/omsdb", "omsdb", "omsdb");
            				"jdbc:mysql://address=(host=localhost)(port=3306)/omsdb?user=omsdb&password=omsdb&serverTimezone=UTC&useSSL=false");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
