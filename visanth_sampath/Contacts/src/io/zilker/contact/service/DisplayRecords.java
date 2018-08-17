package io.zilker.contact.service;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.zilker.contact.constants.Applicationconstants;
import io.zilker.contact.constants.SqlConstants;

public class DisplayRecords {

	// private static Connection con;
	public static Logger log = Logger.getLogger(DisplayRecords.class.getName());

	// displays mobile records
	public static void displayMobileRecords(int userId, Connection con) {
		try {
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			preparedStatement = con.prepareStatement(SqlConstants.SELECT_FROM_MOBILE);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			// 1.mobile no 2.country code 3.table_id
			while (resultSet.next()) {
				log.info(resultSet.getString("mobile_no") + " " + resultSet.getString("country_code") + " "
						+ resultSet.getInt("table_id"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// displays mail records
	public static void displayMailRecords(int userId, Connection con) {
		try {
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			preparedStatement = con.prepareStatement(SqlConstants.SELECT_FROM_EMAIL);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			// 1.mail id 2.table_id
			while (resultSet.next()) {
				log.info(resultSet.getString("mail") + " " + resultSet.getInt("table_id"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// displays home reocrds
	public static void displayHomeRecords(int userId, Connection con) {
		try {
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			preparedStatement = con.prepareStatement(SqlConstants.SELECT_FROM_HOME);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			// 1.home no 2.country code 3.area code 4.table_id
			while (resultSet.next()) {
				log.info(resultSet.getString("home_no") + " " + resultSet.getString("country_code") + " "
						+ resultSet.getString("area_code") + " " + resultSet.getInt("table_id"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// displays office records
	public static void displayOfficeRecords(int userId, Connection con) {
		try {
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			preparedStatement = con.prepareStatement(SqlConstants.SELECT_FROM_OFFICE);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			// 1.mobile no 2.extension 3.table_id
			while (resultSet.next()) {
				log.info(resultSet.getString("off_number") + " " + resultSet.getString("extension") + " "
						+ resultSet.getInt("table_id"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// display records
	public static void displayAllRecordsMain(Connection con) {
		try {
			PreparedStatement preparedStatement;
			ResultSet resultSet;
			preparedStatement = con.prepareStatement(SqlConstants.SELECT_ALL_ROWS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int tmpid = resultSet.getInt("id");
				log.info(tmpid + " " + resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
				displayMobileRecords(tmpid, con);
				displayMailRecords(tmpid, con);
				displayHomeRecords(tmpid, con);
				displayOfficeRecords(tmpid, con);
				log.info(Applicationconstants.PATTERN);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// test main function
	/*
	 * public static void main(String [] args) { try {
	 * Class.forName("com.mysql.jdbc.Driver"); Connection con =
	 * DbUtils.getConnection(); displayAllRecordsMain(con); } catch(Exception e) {
	 * log.info(e.toString()); } }
	 */

}
