package com.zilker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zilker.constants.DBConstants;

public class DatabaseConfig {

	static Connection connection = null;

	public Connection getConnection() {

		try {

			Class.forName(DBConstants.CLASS_NAME);

			connection = DriverManager.getConnection(DBConstants.DB_NAME, DBConstants.DB_USER, DBConstants.DB_PASSWORD);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public void closeConnection(Connection connection, ResultSet resultSet, PreparedStatement prepareStmt) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (prepareStmt != null) {
			try {
				prepareStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
