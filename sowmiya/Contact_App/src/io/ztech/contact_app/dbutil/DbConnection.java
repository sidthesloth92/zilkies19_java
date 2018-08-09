package io.ztech.contact_app.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DbConnection {
	static Logger log = Logger.getLogger("DbConnection.class");

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			String url = "jdbc:mysql://localhost:3306/assignment?useSSL=false";
			String uName = "root";
			String pass = "Ztech@123";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, uName, pass);
			return conn;
		} catch (Exception e) {
			log.info(e.toString());
		}
		return null;

	}

	public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
				;
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			log.info("Error in closing connection");
		}
	}
}
