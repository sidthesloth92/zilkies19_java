package io.zilker.application.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;

public class ConnectionConfig {
	static Connection con;
	static Logger logger = Logger.getLogger(ConnectionConfig.class.getName());

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.info(DisplayConstants.NO_CLASS_FOUND_ERR);
		}
		String connectionUrl = DisplayConstants.CONNECTION_URL;
		String userName = DisplayConstants.CONNECTION_USERNAME;
		String password = DisplayConstants.CONNECTION_PASSWORD;
		try {
			con = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (Exception e) {
			logger.info(DisplayConstants.SQL_ERR);
		}
		return con;
	}

	public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.info(DisplayConstants.RESULT_SET_CLOSE_ERR);
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				logger.info(DisplayConstants.PREPARED_STMT_CLOSE_ERR);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				logger.info(DisplayConstants.CONNECTION_CLOSE_ERR);
			}
		}
	}
}
