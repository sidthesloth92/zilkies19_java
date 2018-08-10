package io.zilker.contact.service;

import java.sql.*;

import io.zilker.contact.constants.SqlVariables;

public class LastInserted {
	//public static Connection con;
	public static ResultSet rs;
	public static PreparedStatement pst;
	private static int id;
	
	//gets the last inserted record
	public static int lastInsertedId(Connection con) {
		try {
			pst = con.prepareStatement(SqlVariables.LAST_INSERTED);
			rs = pst.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
		}
		catch(Exception e) {
			
		}
		finally {
		}
		return id;
	}

}
