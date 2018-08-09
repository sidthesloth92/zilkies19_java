package io.zilker.contacts.dao;

import java.sql.*;
import io.zilker.contacts.dbutil.*;
import io.zilker.contacts.constants.*;

public class Updation {

	private static Connection conn = null;

	public static boolean UpdateFirstName(int contactID, String firstName) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.updateFirstName);
			query.setString(1, firstName);
			query.setInt(2, contactID);
			int status = query.executeUpdate();
			if(status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.dbErr);
		return false;
	}
	
	public static boolean UpdateLastName(int contactID, String lastName) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.updateLastName);
			query.setString(1, lastName);
			query.setInt(2, contactID);
			int status = query.executeUpdate();
			if(status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.dbErr);
		return false;
	}
	
}
