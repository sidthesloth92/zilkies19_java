package io.ztech.jkingsley.hrmanagement.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.ztech.jkingsley.hrmanagement.constants.Constants;
import io.ztech.jkingsley.hrmanagement.constants.Titles;


public class DBUtils {
		

	public static Connection getConnection() throws PersistenceException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection;
			connection = DriverManager.getConnection(Constants.DRIVER_URL,Constants.DRIVER_USERNAME,Constants.DRIVER_PASSWORD);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistenceException(Titles.ERROR_CONNECTION_OPENING,e);
		}
	}
	
	public static void closeConnection(Connection connection) throws PersistenceException {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_CONNECTION_CLOSING,e);
		}
	}

	public static void closeConnection(Connection connection, ResultSet rs) throws PersistenceException {
		try {
			connection.close();
			rs.close();
		} catch (SQLException e) {
			throw new PersistenceException(Titles.ERROR_CONNECTION_CLOSING,e);
		}
	}
}
