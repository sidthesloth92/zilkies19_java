package io.zilker.contacts.dao;

import java.sql.*;

import io.zilker.contacts.constants.DatabaseQueries;
import io.zilker.contacts.constants.ErrorCodes;
import io.zilker.contacts.dbutil.*;

public class Check {

	static Connection conn = null;

	public static boolean hasContactID(int contactID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.checkContactID);
			query.setInt(1, contactID);
			ResultSet result = query.executeQuery();
			if (result.next())
				return true;
			else
				System.out.println(ErrorCodes.notExistsErr);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		return false;
	}

	public static boolean hasMobileIDForContactID(int contactID, int mobileID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchMobileIDAndContactID);
			query.setInt(1, contactID);
			query.setInt(2, mobileID);
			ResultSet result = query.executeQuery();
			if (result.next())
				return true;
			else
				System.out.println(ErrorCodes.notExistsErr);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		return false;
	}
	
	public static boolean hasHomeIDForContactID(int contactID, int homeID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchHomeIDAndContactID);
			query.setInt(1, contactID);
			query.setInt(2, homeID);
			ResultSet result = query.executeQuery();
			if (result.next())
				return true;
			else
				System.out.println(ErrorCodes.notExistsErr);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		return false;
	}
	
	public static boolean hasOfficeIDForContactID(int contactID, int officeID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchOfficeIDAndContactID);
			query.setInt(1, contactID);
			query.setInt(2, officeID);
			ResultSet result = query.executeQuery();
			if (result.next())
				return true;
			else
				System.out.println(ErrorCodes.notExistsErr);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		return false;
	}
	
	public static boolean hasEmailIDForContactID(int contactID, int emailID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchEmailIDAndContactID);
			query.setInt(1, contactID);
			query.setInt(2, emailID);
			ResultSet result = query.executeQuery();
			if (result.next())
				return true;
			else
				System.out.println(ErrorCodes.notExistsErr);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		return false;
	}
	
}
