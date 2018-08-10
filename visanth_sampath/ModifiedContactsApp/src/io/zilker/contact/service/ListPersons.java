package io.zilker.contact.service;

import java.sql.*;
import java.util.logging.Logger;

import io.zilker.contact.constants.SqlVariables;

public class ListPersons {
	public static Logger log = Logger.getLogger(ListPersons.class.getName());
	 
	
	//list the available users
	public static void displayUsers(String firstName, String lastName,Connection con) {
		try {
			PreparedStatement pst;
			ResultSet rs;
			pst = con.prepareStatement(SqlVariables.SELECT_WITH_NAMES);
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			rs = pst.executeQuery();
			while(rs.next()) {
				log.info(rs.getString("id")+" " +rs.getString("first_name") +" "+ rs.getString("last_name"));
			}
		}
		catch (Exception e) {
			
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
