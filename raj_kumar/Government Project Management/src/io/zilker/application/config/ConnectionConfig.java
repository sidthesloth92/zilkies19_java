package io.zilker.application.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionConfig {
	static Connection con;
	public static Connection getConnection() {
		String connectionUrl = "jdbc:mysql://localhost:3306/government_project?autoReconnect=true&useSSL=false";
		String userName = "root";
		String password = "Ztech@123";
		try {
			con = DriverManager.getConnection(connectionUrl, userName, password);
			
		}catch (Exception e){
			System.out.println("A Error Occured During Connection");
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
