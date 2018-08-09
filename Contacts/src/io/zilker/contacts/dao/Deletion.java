package io.zilker.contacts.dao;

import java.sql.*;

import io.zilker.contacts.constants.DatabaseQueries;
import io.zilker.contacts.constants.ErrorCodes;
import io.zilker.contacts.dbutil.*;

public class Deletion {

	static Connection conn;

	public static boolean deleteContact(int contactID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.deleteContact);
			query.setInt(1, contactID);
			int status = query.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.dbErr);
		return false;
	}

	public static boolean deleteMobile(int ID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.deleteMobileWithMobileID);
			query.setInt(1, ID);
			int status = query.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.dbErr);
		return false;
	}

	public static boolean deleteHome(int ID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.deleteHomeWithHomeID);
			query.setInt(1, ID);
			int status = query.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.dbErr);
		return false;
	}

	public static boolean deleteOffice(int ID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.deleteOfficeWithOfficeID);
			query.setInt(1, ID);
			int status = query.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.dbErr);
		return false;
	}

	public static boolean deleteEmail(int ID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.deleteEmailWithEmailID);
			query.setInt(1, ID);
			int status = query.executeUpdate();
			if (status == 1)
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
