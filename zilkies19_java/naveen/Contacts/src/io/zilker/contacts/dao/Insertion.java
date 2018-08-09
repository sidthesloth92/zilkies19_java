package io.zilker.contacts.dao;

import java.sql.*;
import io.zilker.contacts.dbutil.*;
import io.zilker.contacts.constants.*;

public class Insertion {

	private static Connection conn = null;

	public static int addName(String firstName, String lastName) {
		try {
			conn = DatabaseConnection.open();
			if (conn != null) {
				PreparedStatement insert = conn.prepareStatement(DatabaseQueries.insertName);
				insert.setString(1, firstName);
				insert.setString(2, lastName);
				int status = insert.executeUpdate();
				if (status != 1) {
					System.out.println(ErrorCodes.dbErr);
					return -1;
				}
				PreparedStatement query = conn.prepareStatement(DatabaseQueries.getContactFromName);
				query.setString(1, firstName);
				query.setString(2, lastName);
				ResultSet id = query.executeQuery();
				id.next();
				return id.getInt("Contact_ID");
			}
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		return -1;
	}

	public static int addCountryCode(String countryCode) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchCountryCode);
			query.setString(1, countryCode);
			ResultSet country = query.executeQuery();
			if (country.next()) {
				return country.getInt(1);
			} else {
				PreparedStatement insert = conn.prepareStatement(DatabaseQueries.insertCountryCode);
				insert.setString(1, countryCode);
				int status = insert.executeUpdate();
				if (status != 1) {
					System.out.println(ErrorCodes.dbErr);
					return -1;
				}
				country = query.executeQuery();
				country.next();
				return country.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.appErr);
		return -1;
	}

	public static int addAreaCode(String areaCode) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchAreaCode);
			query.setString(1, areaCode);
			ResultSet area = query.executeQuery();
			if (area.next()) {
				return area.getInt(1);
			} else {
				PreparedStatement insert = conn.prepareStatement(DatabaseQueries.insertAreaCode);
				insert.setString(1, areaCode);
				int status = insert.executeUpdate();
				if (status != 1) {
					System.out.println(ErrorCodes.appErr);
					return -1;
				}
				area = query.executeQuery();
				area.next();
				return area.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.appErr);
		return -1;
	}

	public static boolean addMobile(int contactID, String mobileNumber, int countryID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement insert = conn.prepareStatement(DatabaseQueries.insertMobileNumber);
			insert.setInt(1, contactID);
			insert.setString(2, mobileNumber);
			insert.setInt(3, countryID);
			int status = insert.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.appErr);
		return false;
	}

	public static boolean addHome(int contactID, String homeNumber, int areaID) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement insert = conn.prepareStatement(DatabaseQueries.insertHomeNumber);
			insert.setInt(1, contactID);
			insert.setString(2, homeNumber);
			insert.setInt(3, areaID);
			int status = insert.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.appErr);
		return false;
	}

	public static boolean addOffice(int contactID, String Number, int ID, String extNumber, char type) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement insert;
			if (type == 'm') {
				insert = conn.prepareStatement(DatabaseQueries.insertOfficeMobile);
				insert.setInt(1, contactID);
				insert.setString(2, Number);
				insert.setInt(3, ID);
				insert.setString(4, extNumber);
			} else {
				insert = conn.prepareStatement(DatabaseQueries.insertOfficeLandline);
				insert.setInt(1, contactID);
				insert.setString(2, Number);
				insert.setInt(3, ID);
				insert.setString(4, extNumber);
			}
			int status = insert.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.appErr);
		return false;
	}

	public static boolean addEmail(int contactID, String email) {
		try {
			conn = DatabaseConnection.open();
			PreparedStatement insert = conn.prepareStatement(DatabaseQueries.insertemail);
			insert.setInt(1, contactID);
			insert.setString(2, email);
			int status = insert.executeUpdate();
			if (status == 1)
				return true;
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
		System.out.println(ErrorCodes.appErr);
		return false;
	}

}
