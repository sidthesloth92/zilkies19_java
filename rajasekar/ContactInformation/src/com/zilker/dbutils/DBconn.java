package com.zilker.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import java.sql.*;

public class DBconn {
	private static final Logger logger = Logger.getLogger(DBconn.class.getName());
	private static final String cString = "jdbc:mysql://localhost:3306/contact?useSSL=false";
	private static final String user = "root";
	private static final String pass = "boopalan";

	public static Connection getConn() {
		Connection myconn = null;
		try {
			myconn = DriverManager.getConnection(cString, user, pass);
		} catch (Exception e) {
			logger.warning("Error while connecting to DB");
		}
		return myconn;
	}

	public static void closeConn(Connection myconn, PreparedStatement ps, ResultSet rs) {
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
