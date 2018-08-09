package io.ztech.contactapplication.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import io.ztech.contactapplication.constants.SQLConstants;
import io.ztech.contactapplication.dbutils.DBUtils;

public class Validator {

	private static final Logger logger = Logger.getLogger(Validator.class.getName());
	private static Connection con = null;
	private static PreparedStatement pst = null;
	// private ResultSet rs = null;

	public static boolean validateName(String firstName, String lastName) {
		for (int i = 0; i < firstName.length(); i++)
			if (!Character.isAlphabetic(firstName.charAt(i)))
				return false;
		for (int i = 0; i < lastName.length(); i++)
			if (!Character.isAlphabetic(lastName.charAt(i)))
				return false;
		return true;
	}

	public static boolean validateCountryCode(String extn) {
		if (extn.length() > 3)
			return false;
		int i = 0;
		if (extn.charAt(0) == '+') {
			i = 1;
		}
		for (; i < extn.length(); i++) {
			if (!Character.isDigit(extn.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean validateOfficeNumber(String offxtn, String num) {
		return validateNumber(offxtn) && validateNumber(num);
	}

	public static boolean validateAreaCode(String aCode) {
		if (aCode.length() > 3)
			return false;
		return validateNumber(aCode);
	}

	public static boolean validateNumber(String num) {
		for (int i = 0; i < num.length(); i++) {
			if (!Character.isDigit(num.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean validateEmail(String email) {
		return Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
				email);
	}

	public static boolean isNamePresent(String fname, String lname) throws SQLException {

		ResultSet res = null;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement("select first_name,last_name from contact");
			res = pst.executeQuery();

		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			while (res.next()) {
				if (res.getString(1).equals(fname) && res.getString(2).equals(lname)) {
					return true;
				}
			}
			DBUtils.closeConnection(con, pst, null);
		}

		return false;
	}

	public boolean isDuplicate(String fname, String lname, String email, Connection con) throws SQLException {
		ResultSet em = null;
		ResultSet res = null;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.SELECT_CONTACTID_CONTACT);
			pst.setString(1, fname);
			pst.setString(2, lname);
			res = pst.executeQuery();

		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}

		if (res.next()) {
			em = con.createStatement()
					.executeQuery("select email_id from email where " + "contact_id=" + res.getInt(1));
		} else {
			return false;
		}

		while (res.next()) {
			if (res.getString(2).equals(fname) && res.getString(3).equals(lname)) {
				while (em.next()) {
					if (em.getString(1).equals(email))
						return true;
				}
			}
		}
		return false;
	}

	public static boolean isEmailPresent(String emailId) throws SQLException {
		ResultSet res = null;
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement("select email_id from email");
			res = pst.executeQuery();

		} catch (SQLException e) {
			logger.info("Error connecting it with MySQL");
		} finally {
			while (res.next()) {
				if (res.getString("email_id").equals(emailId)) {
					return true;
				}
			}
			DBUtils.closeConnection(con, pst, null);
		}

		return false;
	}
}
