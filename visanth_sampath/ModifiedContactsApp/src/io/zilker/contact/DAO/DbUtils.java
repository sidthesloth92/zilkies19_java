package io.zilker.contact.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbUtils {
	private static String url = "jdbc:mysql://localhost:3306/leaveManagement";
	private static String userName = "root";
	private static String password = "password";
	public static Connection con;
	
	//get Connection
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		}
		catch(Exception e){
			
		}
		return con;
	}
	
	//close Connection
	public static void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if(rs!= null) {
				rs.close();
			}
			if(pst!=null) {
				pst.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}
		catch(Exception e) {
			
		}
	}

}
