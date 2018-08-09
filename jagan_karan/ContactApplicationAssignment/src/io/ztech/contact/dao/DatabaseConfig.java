package io.ztech.contact.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import io.ztech.contact.constants.ConstantDisplayStatements;

public class DatabaseConfig {
	public static final Logger logger=Logger.getLogger("Queries");
	public final static String URL = "jdbc:mysql://localhost:3306/contact";
	public final static String USERNAME = "root";
	public final static String PASSWORD = "root";
	public static Connection CON=null;
	public static Connection getConnection() {
			try {
				Properties properties = new Properties();
				properties.setProperty("user", USERNAME);
				properties.setProperty("password", PASSWORD);
				properties.setProperty("useSSL", "false");
				properties.setProperty("autoReconnect", "true");
				CON = DriverManager.getConnection(URL, properties);
			} catch (SQLException ex) {
				logger.info(ConstantDisplayStatements.DATABASEERRORLOG);
			}
	return CON;
	}
	public static void closeConnection(Connection con) {
		try {
			con.close();	
		}
		catch(Exception e) { 
			logger.info(ConstantDisplayStatements.CLOSEERRORLOG);
		}
	}
}