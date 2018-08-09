package io.zilker.contacts.dao;

import java.sql.*;

import io.zilker.contacts.constants.DatabaseQueries;
import io.zilker.contacts.constants.ErrorCodes;
import io.zilker.contacts.constants.TemplateStrings;
import io.zilker.contacts.dbutil.*;
import io.zilker.contacts.services.PrintContact;
import io.zilker.contacts.beans.*;

public class View {

	static Connection conn = null;

	public static void fillViewDetails(char sortByChoice) {
		Contact contact;
		PreparedStatement query;
		try {
			ResultSet result = null, contacts = null;
			int contactID = -1;
			if (sortByChoice == 'f')
				result = sortByFirstName();
			else
				result = sortByLastName();
			while (result.next()) {
				contact = new Contact();
				contactID = result.getInt("Contact_ID");
				contact.id = contactID;
				contact.name.firstName = result.getString("First_Name");
				contact.name.lastName = result.getString("Last_Name");
				query = conn.prepareStatement(DatabaseQueries.getMobileByContactID);
				query.setInt(1, contactID);
				contacts = query.executeQuery();
				while (contacts.next()) {
					contact.mobile.mobile.add(contacts.getString("M_Number"));
					int countryID = contacts.getInt("Country_ID");
					query = conn.prepareStatement(DatabaseQueries.getCountryCodeWithCountryID);
					query.setInt(1, countryID);
					ResultSet country = query.executeQuery();
					if (country.next())
						contact.mobile.countryCode.add(country.getString("Country_Code"));
					else
						contact.mobile.countryCode.add(null);
				}
				query = conn.prepareStatement(DatabaseQueries.getHomeByContactID);
				query.setInt(1, contactID);
				contacts = query.executeQuery();
				while (contacts.next()) {
					contact.home.landline.add(contacts.getString("H_Number"));
					int areaID = contacts.getInt("Area_ID");
					query = conn.prepareStatement(DatabaseQueries.getAreaCodeWithAreaID);
					query.setInt(1, areaID);
					ResultSet area = query.executeQuery();
					if (area.next())
						contact.home.areaCode.add(area.getString(2));
					else
						contact.home.areaCode.add(null);
				}
				query = conn.prepareStatement(DatabaseQueries.getOfficeByContactID);
				query.setInt(1, contactID);
				contacts = query.executeQuery();
				while (contacts.next()) {
					if (contacts.getString("Off_L_Number") != null) {
						contact.office.officeLandline.landline.add(contacts.getString("Off_L_Number"));
						int areaID = contacts.getInt("Area_ID");
						query = conn.prepareStatement(DatabaseQueries.getAreaCodeWithAreaID);
						query.setInt(1, areaID);
						ResultSet area = query.executeQuery();
						if (area.next())
							contact.office.officeLandline.areaCode.add(area.getString("Area_Code"));
						else
							contact.office.officeLandline.areaCode.add(null);
						contact.office.officeExt_L.add(contacts.getString("Extension_Number"));
					} else {
						contact.office.officeMobile.mobile.add(contacts.getString("Off_M_Number"));
						int country = contacts.getInt("Country_ID");
						query = conn.prepareStatement(DatabaseQueries.getCountryCodeWithCountryID);
						query.setInt(1, country);
						ResultSet countryID = query.executeQuery();
						if (countryID.next())
							contact.office.officeMobile.countryCode.add(countryID.getString("Country_Code"));
						else
							contact.office.officeMobile.countryCode.add(null);
						contact.office.officeExt_M.add(contacts.getString("Extension_Number"));
					}
				}
				query = conn.prepareStatement(DatabaseQueries.getEmailByContactID);
				query.setInt(1, contactID);
				contacts = query.executeQuery();
				while (contacts.next()) {
					contact.email.email.add(contacts.getString(3));
				}
				PrintContact.printFullContact(contact);
			}
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
	}

	private static ResultSet sortByFirstName() {
		ResultSet result = null;
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.sortByFirstName);
			result = query.executeQuery();
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		}
		return result;
	}

	private static ResultSet sortByLastName() {
		ResultSet result = null;
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.sortByLastName);
			result = query.executeQuery();
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		}
		return result;
	}

	public static void ShowMobileNumberWithContactID(int contactID) {
		ResultSet result = null;
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchMobileWithContactID);
			query.setInt(1, contactID);
			result = query.executeQuery();
			printNumberAndId(result);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
	}

	public static void ShowHomeNumberWithContactID(int contactID) {
		ResultSet result = null;
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchHomeWithContactID);
			query.setInt(1, contactID);
			result = query.executeQuery();
			printNumberAndId(result);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
	}

	public static void ShowOfficeNumberWithContactID(int contactID) {
		ResultSet result = null;
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchOfficeWithContactID);
			query.setInt(1, contactID);
			result = query.executeQuery();
			printOfficeNumberAndId(result);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
	}

	public static void ShowEmailWithContactID(int contactID) {
		ResultSet result = null;
		try {
			conn = DatabaseConnection.open();
			PreparedStatement query = conn.prepareStatement(DatabaseQueries.searchEmailWithContactID);
			query.setInt(1, contactID);
			result = query.executeQuery();
			printNumberAndId(result);
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		} finally {
			DatabaseConnection.close(conn);
		}
	}
	
	public static void printNumberAndId(ResultSet result) {
		try {
			while (result.next()) {
				System.out.println(TemplateStrings.number + result.getString(3));
				System.out.println(TemplateStrings.id + result.getInt(1));
			}
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		}
	}

	public static void printOfficeNumberAndId(ResultSet result) {
		try {
			while (result.next()) {
				if (result.getString(3) != null) {
					System.out.println(TemplateStrings.number + result.getString(3));
					System.out.println(TemplateStrings.id + result.getInt(1));
				} else {
					System.out.println(TemplateStrings.number + result.getString(5));
					System.out.println(TemplateStrings.id + result.getInt(1));
				}
			}
		} catch (Exception e) {
			System.out.println(ErrorCodes.dbErr + e);
		}
	}

}
