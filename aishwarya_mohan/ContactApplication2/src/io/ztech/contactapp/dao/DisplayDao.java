package io.ztech.contactapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.constants.SQLQueryStringConstants;
import io.ztech.contactapp.dbutils.DBConnectivity;

public class DisplayDao {

	private static final DBConnectivity con_obj = new DBConnectivity();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public void showContacts(String firstName) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_CONTACTS_WITH_FIRSTNAME);

			ps.setString(1, firstName);
			rs = ps.executeQuery();

			while (rs.next()) {
				logger.info(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void displayContact(int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.DISPLAY_CONTACT);
			ps.setInt(1, con_id);
			rs = ps.executeQuery();
			logger.info(ApplicationStringConstants.CONTACT_ID_NAME);
			while (rs.next()) {
				logger.info(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

			ps = conn.prepareStatement(SQLQueryStringConstants.DISPLAY_MOBILE);
			ps.setInt(1, con_id);
			rs = ps.executeQuery();
			logger.info(ApplicationStringConstants.CONTACT_MOBILE);
			while (rs.next()) {
				logger.info("\n" + rs.getString(1) + "\t" + rs.getString(2) + " " + rs.getString(3));
			}

			ps = conn.prepareStatement(SQLQueryStringConstants.DISPLAY_OFFICE);
			ps.setInt(1, con_id);
			rs = ps.executeQuery();
			logger.info(ApplicationStringConstants.CONTACT_OFFICE);
			while (rs.next()) {
				logger.info("\n" + rs.getString(1) + "\t" + rs.getString(2) + " " + rs.getString(3));
			}

			ps = conn.prepareStatement(SQLQueryStringConstants.DISPLAY_HOME);
			ps.setInt(1, con_id);
			rs = ps.executeQuery();
			logger.info(ApplicationStringConstants.CONTACT_HOME);
			while (rs.next()) {
				logger.info("\n" + rs.getString(1) + "\t" + rs.getString(2) + " " + rs.getString(3) + " "
						+ rs.getString(4));
			}

			ps = conn.prepareStatement(SQLQueryStringConstants.DISPLAY_EMAIL);
			ps.setInt(1, con_id);
			rs = ps.executeQuery();
			logger.info(ApplicationStringConstants.CONTACT_EMAIL);
			while (rs.next()) {
				logger.info("\n" + rs.getString(1) + "\t" + rs.getString(2));
			}

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

}
