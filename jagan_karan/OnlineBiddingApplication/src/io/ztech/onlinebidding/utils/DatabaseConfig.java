package io.ztech.onlinebidding.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import io.ztech.onlinebidding.constant.ConstantDisplayStatement;

public class DatabaseConfig {

	public static final Logger logger = Logger.getLogger("DatabaseConfig");
	public final String URL = "jdbc:mysql://localhost:3306/onlinebidderapplication";
	public final String USERNAME = "root";
	public final String PASSWORD = "root";
	public Connection CON = null;

	public Connection getConnection() {
		try {
			Properties properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("useSSL", "true");
			properties.setProperty("autoReconnect", "true");
			CON = DriverManager.getConnection(URL, properties);
		} catch (SQLException ex) {
			logger.info(ConstantDisplayStatement.DATABASE_ERROR_LOG);
		}
		return CON;
	}

	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			logger.info(ConstantDisplayStatement.CLOSE_ERROR_LOG);
		}
	}

}
