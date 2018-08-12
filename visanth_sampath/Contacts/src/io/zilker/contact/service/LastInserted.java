package io.zilker.contact.service;

import java.sql.*;

import io.zilker.contact.constants.SqlConstants;

public class LastInserted {
	//public static Connection con;
	
	public static PreparedStatement preparedStatement;
	private static int id;
	
	//gets the last inserted record
	public static int lastInsertedId(Connection con) {
		try {
			ResultSet resultSet;
			preparedStatement = con.prepareStatement(SqlConstants.LAST_INSERTED);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				id = resultSet.getInt("id");
			}
		}
		catch(Exception e) {
			
		}
		finally {
		}
		return id;
	}

}
