package com.zilker.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionConfig {
	static Connection con;
	public static Connection getConnection() {
		String connectionUrl = "jdbc:mysql://localhost:3306/leave_management";
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
	public static void closeConnection(Connection con, PreparedStatement pst) {
		if(con != null) {
			try {
				con.close();
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


