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

}
