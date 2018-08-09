package io.zilker.contacts.dbutil;

import io.zilker.contacts.constants.*;
import java.sql.*;

public class DatabaseConnection {

	private static final String url = "jdbc:mysql://localhost:3306/Contacts?autoReconnect=true&useSSL=false";
	private static final String user = "root";
	private static final String pass = "root";
	static Connection conn = null;

	public static Connection open() {
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.out.println(ErrorCodes.connectionErr + e);
		} 
		return conn;
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(ErrorCodes.connectionErr + e);
		}
	}

}
