package com.zilker.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.logging.Logger;

public class DatabaseConfig {

	static Connection connection;
	static Logger logger = Logger.getLogger(DatabaseConfig.class.getName());

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.info("mysql class not found");
		}
		
		String connectionUrl = "jdbc:mysql://localhost:3306/EMPLOYEE?autoReconnect=true&useSSL=false";
		String userName = "root";
		String password = "muthu";
		try {
			connection = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("Sql error");
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch(SQLException e) {
				logger.info("result set error");
			}
		}
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch(SQLException e) {
				logger.info("prepared stmt error");
			}
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.info("connection error");
			}
}
	}
}
