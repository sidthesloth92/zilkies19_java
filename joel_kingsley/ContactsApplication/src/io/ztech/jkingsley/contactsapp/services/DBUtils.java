package io.ztech.jkingsley.contactsapp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
	public static final String DRIVER_URL = "jdbc:mysql://localhost:3306/zilker";
	public static final String DRIVER_USERNAME = "root";
	public static final String DRIVER_PASSWORD = "Ztech@123";	

	public Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection;
			connection = DriverManager.getConnection(DRIVER_URL,DRIVER_USERNAME,DRIVER_PASSWORD);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
