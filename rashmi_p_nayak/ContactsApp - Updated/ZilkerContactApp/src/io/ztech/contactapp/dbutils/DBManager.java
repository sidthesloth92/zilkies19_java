package io.ztech.contactapp.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//=========================================================================================================================================
//DB MANAGER CLASS - DATABASE UTILITIES
//=========================================================================================================================================
public class DBManager {
	private String url = "jdbc:mysql://localhost:3306/contactsApp?autoReconnect=true&useSSL=false";
	private String username = "root";
	private String password = "Ztech@123";
	Connection con;

	// creates and returns a connection
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

	// closes the sent connection
	public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
