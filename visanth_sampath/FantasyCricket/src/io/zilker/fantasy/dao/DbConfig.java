package io.zilker.fantasy.dao;

import java.sql.*;
import java.util.logging.Logger;

import io.zilker.fantasy.constants.DbConstants;
import io.zilker.fantasy.constants.DisplayConstants;

public class DbConfig {
	public Connection connection;
	private Logger logger = Logger.getLogger(DbConfig.class.getName());

	public Connection getConnection() {
		try {
			Class.forName(DbConstants.DRIVER_LOADER);
			connection = DriverManager.getConnection(DbConstants.CONNECTION_URL, DbConstants.DATABASE_USER_NAME,
					DbConstants.DATABASE_PASSWORD);
		} catch (Exception e) {
			logger.info(DisplayConstants.CONNECTION_ERROR);
			e.printStackTrace();
		}
		return connection;
	}

	public void closeConnection(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
		try {
			if(preparedStatement!=null) {
				preparedStatement =null;
			}
			if(resultSet != null) {
				resultSet.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			logger.info(DisplayConstants.CONNECTION_CLOSE_ERROR);
			e.printStackTrace();
		}
	}

}
