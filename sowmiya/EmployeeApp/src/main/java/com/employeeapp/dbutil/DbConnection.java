package com.employeeapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.logging.Logger;

import com.employeeapp.constants.SqlConstants;

public class DbConnection {
	private static Logger log = Logger.getLogger(DbConnection.class);

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(SqlConstants.URL, SqlConstants.USERNAME, SqlConstants.PASSWORD);
			return connection;
		} catch (Exception e) {
			log.info(e.toString());
		}
		return null;

	}

	public static void closeConnection(ResultSet resultSet, PreparedStatement preparedStatement,
			Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();

			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			log.info("Error in closing connection");
		}
	}
}
