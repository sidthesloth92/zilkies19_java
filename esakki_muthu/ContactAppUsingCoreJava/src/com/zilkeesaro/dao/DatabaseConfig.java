package com.zilkeesaro.dao;

import java.sql.*;

public class DatabaseConfig {
	
	static Connection conn=null;
	
	public static Connection getConnection() {
		
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost/ContactDetails", "root", "root");
			
		}catch(Exception e) {
			
		}
		
		return conn;
	}
	
	public static void CloseConnection(ResultSet set,PreparedStatement prepareStmt,Connection conn)
	{
		
		if(set==null) {
			
		}else {
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(prepareStmt==null) {
			try {
				prepareStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn==null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
