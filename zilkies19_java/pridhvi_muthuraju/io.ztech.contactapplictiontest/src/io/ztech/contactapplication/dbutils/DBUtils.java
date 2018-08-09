package io.ztech.contactapplication.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.contactapplication.constants.AppConstants;

public class DBUtils {

	private static final Logger logger = Logger.getLogger(DBUtils.class.getName());

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(AppConstants.CONNECTION, AppConstants.USERNAME, AppConstants.PASSWORD);
		} catch (SQLException e) {
			logger.warning(AppConstants.SQL_ERROR);
		}
		return conn;
	}

	public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.warning(AppConstants.SQL_ERROR);
		}
	}
}
