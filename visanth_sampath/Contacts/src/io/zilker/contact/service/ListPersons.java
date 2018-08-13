package io.zilker.contact.service;

import java.sql.*;
import java.util.logging.Logger;

import io.zilker.contact.constants.SqlConstants;

public class ListPersons {
	public static Logger log = Logger.getLogger(ListPersons.class.getName());
	 
	
	//list the available users
	public static void displayUsers(String firstName, String lastName,Connection con) {
		try {
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			preparedStatement = con.prepareStatement(SqlConstants.SELECT_WITH_NAMES);
			preparedStatement.setString(1,firstName);
			preparedStatement.setString(2,lastName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				log.info(resultSet.getString("id")+" " +resultSet.getString("first_name") +" "+ resultSet.getString("last_name"));
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		finally {
		}
	}
	//main method
	/*public static void main(String [] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DbUtils.getConnection();
			displayUsers("ajay","diwakar",con);
		}
		catch(Exception e) {
			log.info(e.tostring());
		}
	}*/

}
