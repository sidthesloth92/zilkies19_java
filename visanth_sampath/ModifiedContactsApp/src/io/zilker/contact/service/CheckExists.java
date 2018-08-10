package io.zilker.contact.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.zilker.contact.constants.SqlVariables;

public class CheckExists {
	private static PreparedStatement stmt;
	private static ResultSet rs;
	//check if the contact exists
	public static boolean checkExists(String firstName , String lastName,Connection con) {
		try {
			stmt = con.prepareStatement(SqlVariables.SELECT_WITH_NAMES);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			rs = stmt.executeQuery();
			if(rs.next()){
				return true;
			}
		}
		catch(Exception e) {
			
		}
		finally {
		}
		return false;
	}

}
