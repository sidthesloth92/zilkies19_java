package io.zilker.appstore.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import io.zilker.appstore.constants.*;

public class Connections {

	private Connection conn;
	
	public Connection open() {
		try {
			conn = DriverManager.getConnection(ConnectionDetails.url, ConnectionDetails.user, ConnectionDetails.pass);
		} catch (Exception e) {
			System.out.println(Errors.CONNECTION_ERR + e);
		} 
		return conn;
	}

	public void close(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(Errors.CONNECTION_ERR + e);
		}
	}
	
}
