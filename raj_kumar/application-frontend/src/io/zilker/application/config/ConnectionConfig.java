package io.zilker.application.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.zilker.application.constants.DisplayConstants;

public class ConnectionConfig {
	static Connection con;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();
		}
		String connectionUrl = DisplayConstants.CONNECTION_URL;
		String userName = DisplayConstants.CONNECTION_USERNAME;
		String password = DisplayConstants.CONNECTION_PASSWORD;
		try {
			con = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (Exception e) {
			System.out.println("fghjksdsd");
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
