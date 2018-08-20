package com.zilker.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import com.zilker.constant.ConsoleStrings;

import java.sql.*;

public class DbConnection {
	private static final Logger logger = Logger.getLogger(DbConnection.class.getName());

	public static Connection getConnection() {
		Connection myconn = null;
		try {
			myconn = DriverManager.getConnection(ConsoleStrings.CONNECTION_STRING, ConsoleStrings.USER,
					ConsoleStrings.DB_PASSWORD);
		} catch (Exception e) {
			logger.warning("Error while connecting to DB");
		}
		return myconn;
	}

	public static void closeConnection(Connection myconn, PreparedStatement ps, ResultSet rs) {
		try {
			if (myconn != null) {
				myconn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			logger.warning("Error while Closing Connection");
		}
	}
}
