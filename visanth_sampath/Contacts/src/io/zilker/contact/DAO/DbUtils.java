package io.zilker.contact.DAO;

import java.sql.*;

import io.zilker.contact.service.AddPerson;
import io.zilker.contact.service.DeleteMain;
import io.zilker.contact.service.DisplayRecords;
import io.zilker.contact.service.UpdatePerson;

public class DbUtils {
	private static String url = "jdbc:mysql://localhost:3306/leaveManagement";
	private static String userName = "root";
	private static String password = "password";
	public static Connection connection;
	
	//get Connection
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		}
		catch(Exception e){
			e.getStackTrace();
		}
		return connection;
	}
	
	//close Connection
	public static void closeConnection() {
		try {
			if(connection!=null) {
				connection.close();
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	//connect DB and call add method  
	public static void addPersonConnection() {
		try{
			connection = createConnection();
			AddPerson.addMain(connection);
		}
		catch(Exception e){
			e.getStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	//connect DB and call Remove method
	public static void removePersonConnection() {
		try{
			connection = createConnection();
			DeleteMain.deleteMainOptions(connection);
		}
		catch(Exception e){
			e.getStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	//connect DB and call list method
	public static void listPeronsConnection() {
		try {
			connection = createConnection();
			DisplayRecords.displayAllRecordsMain(connection);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		finally {
			closeConnection();
		}
	}
	
	//connect DB and call Modify Method
		public static void updatePersonConnection() {
			try{
				connection = createConnection();
				UpdatePerson.updateMain(connection);
			}
			catch(Exception e){
				e.getStackTrace();
			}
			finally {
				closeConnection();
			}
		}

}
