package com.ztech.io.fitnessfirstprototype.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConfiguration {
	private static final String driverString = "com.mysql.cj.jdbc.Driver";
	private static final String dbConnectionString = "jdbc:mysql://localhost:3306/GoFitWebDB?useSSL=false";
	private static final String user = "root";
	private static final String password = "Ztech@123";

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName(driverString);
			Connection connection = DriverManager.getConnection(dbConnectionString, user, password);
			return connection;
		} finally {
			
		}
	}

	public void closeConnection(Connection conn, ResultSet rs, PreparedStatement ps) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}