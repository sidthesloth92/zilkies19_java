package io.zilker.contact.service;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

import io.zilker.contact.constants.Applicationconstants;
import io.zilker.contact.constants.SqlConstants;

public class ModifyValues {
	public static Scanner scanner = new Scanner(System.in);
	private static int option, rowId;
	private static PreparedStatement pst;
	private static String firstName, lastName, mobileNumber, mobileCountryCode, homeNumber, email, officeNumber,
			extension, homeCountryCode, areaCode;
	public static Logger log = Logger.getLogger(ModifyValues.class.getName());

	// modify Names
	public static void nameModify(int rowId, Connection con) {
		try {
			log.info(Applicationconstants.MODIFIED_FIRST_NAME);
			firstName = scanner.next();
			log.info(Applicationconstants.MODIFIED_FIRST_NAME);
			lastName = scanner.next();
			pst = con.prepareStatement(SqlConstants.UPDATE_NAME);
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setInt(3, rowId);
			pst.executeUpdate();
			con.commit();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// get and update mobile values
	public static void modifyMobileRow(int rowId, Connection con) {
		try {
			log.info(Applicationconstants.MODIFIED_MOBILE);
			mobileNumber = scanner.next();
			log.info(Applicationconstants.ENTER_COUNTRY_CODE);
			mobileCountryCode = scanner.next();
			pst = con.prepareStatement(SqlConstants.UPDATE_MOBILE);
			pst.setString(1, mobileNumber);
			pst.setString(2, mobileCountryCode);
			pst.setInt(3, rowId);
			pst.executeUpdate();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// modify a specific mail record
	public static void modifyMailRow(int rowId, Connection con) {
		try {
			log.info(Applicationconstants.MODIFIED_MAIL);
			email = scanner.next();
			pst = con.prepareStatement(SqlConstants.UPDATE_MAIL);
			pst.setString(1, email);
			pst.setInt(2, rowId);
			pst.executeUpdate();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// modify a specific office record
	public static void modifyOfficeRow(int rowId, Connection con) {
		try {
			log.info(Applicationconstants.MODIFIED_OFFICE);
			officeNumber = scanner.next();
			log.info(Applicationconstants.ENTER_EXTENSION);
			extension = scanner.next();
			pst = con.prepareStatement(SqlConstants.UPDATE_OFFICE);
			pst.setString(1, officeNumber);
			pst.setString(2, extension);
			pst.setInt(3, rowId);
			pst.executeUpdate();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// modify office
	public static void officeModify(int userId, Connection con) {
		try {
			log.info(Applicationconstants.ENTER_ID);
			DisplayRecords.displayOfficeRecords(userId, con);
			rowId = scanner.nextInt();
			modifyOfficeRow(rowId, con);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// modify a specific home record
	public static void modifyHomeRow(int rowId, Connection con) {
		try {
			log.info(Applicationconstants.MODIFIED_HOME);
			homeNumber = scanner.next();
			log.info(Applicationconstants.ENTER_COUNTRY_CODE);
			homeCountryCode = scanner.next();
			log.info(Applicationconstants.ENTER_AREA_CODE);
			areaCode = scanner.next();
			pst = con.prepareStatement(SqlConstants.UPDATE_HOME);
			pst.setString(1, homeNumber);
			pst.setString(2, homeCountryCode);
			pst.setString(3, areaCode);
			pst.setInt(4, rowId);
			pst.executeUpdate();
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// modify home
	public static void homeModify(int userId, Connection con) {
		try {
			log.info(Applicationconstants.ENTER_ID);
			DisplayRecords.displayHomeRecords(userId, con);
			rowId = scanner.nextInt();
			modifyHomeRow(rowId, con);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// modify mail
	public static void mailModify(int userId, Connection con) {
		try {
			log.info(Applicationconstants.ENTER_ID);
			DisplayRecords.displayMailRecords(userId, con);
			rowId = scanner.nextInt();
			modifyMailRow(rowId, con);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// modify mobile
	public static void mobileModify(int userId, Connection con) {
		try {
			log.info(Applicationconstants.ENTER_ID);
			DisplayRecords.displayMobileRecords(userId, con);
			rowId = scanner.nextInt();
			modifyMobileRow(rowId, con);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// display main options
	public static void modifyMainOptions(int userId, Connection con, boolean flag) {
		log.info(Applicationconstants.MODIFY_MAIN_OPTIONS);
		option = scanner.nextInt();
		option = 1;
		switch (option) {
		case 1:
			nameModify(userId, con);
			break;
		case 2:
			mobileModify(userId, con);
			break;
		case 3:
			mailModify(userId, con);
			break;
		case 4:
			homeModify(userId, con);
			break;
		case 5:
			officeModify(userId, con);
			break;
		default:
			break;

		}

	}

	// get id of a particular user
	public static void modifyGetId(Connection con) {
		try {
			log.info(Applicationconstants.FIRST_NAME_ENTER);
			firstName = scanner.next();
			log.info(Applicationconstants.LAST_NAME_ENTER);
			lastName = scanner.next();
			ListPersons.displayUsers(firstName, lastName, con);
			log.info(Applicationconstants.ENTER_ID);
			// prompt to enter id
			option = scanner.nextInt();
			modifyMainOptions(option, con, false);
		} catch (Exception e) {

		}
	}
	/*
	 * public static void main(String [] args) { try {
	 * Class.forName("com.mysql.jdbc.Driver"); Connection con =
	 * DbUtils.getConnection(); con.setAutoCommit(false); modifyGetId(con);
	 * con.commit(); } catch(Exception e) { log.info(e.toString()); } }
	 */

}
