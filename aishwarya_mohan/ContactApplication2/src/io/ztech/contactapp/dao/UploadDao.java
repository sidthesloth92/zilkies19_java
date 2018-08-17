package io.ztech.contactapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.beans.Contact;
import io.ztech.contactapp.beans.HomeNumber;
import io.ztech.contactapp.beans.MobileNumber;
import io.ztech.contactapp.beans.OfficeNumber;
import io.ztech.contactapp.beans.PhoneNumber;
import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.constants.SQLQueryStringConstants;
import io.ztech.contactapp.dbutils.DBConnectivity;

public class UploadDao {
	private static final DBConnectivity con_obj = new DBConnectivity();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public boolean uploadToDB(Object o, int con_id) {
		try {
			conn = con_obj.getConnection();

			if (o instanceof MobileNumber) {
				MobileNumber m = (MobileNumber) o;
				ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_MOBILE);

				ps.setString(1, m.getCountryCode());
				ps.setString(2, m.getNumber());
				ps.setInt(3, con_id);
			} else if (o instanceof OfficeNumber) {
				OfficeNumber of = (OfficeNumber) o;
				ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_OFFICE);

				ps.setString(1, of.getExtensionNumber());
				ps.setString(2, of.getNumber());
				ps.setInt(3, con_id);
			} else if (o instanceof HomeNumber) {
				HomeNumber h = (HomeNumber) o;
				ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_HOME);

				ps.setString(1, h.getCountryCode());
				ps.setString(2, h.getAreaCode());
				ps.setString(3, h.getNumber());
				ps.setInt(4, con_id);
			} else if (o instanceof String) {
				ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_EMAIL);
				String email = (String) o;
				ps.setString(1, email);
				ps.setInt(2, con_id);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			logger.info(ApplicationStringConstants.NOTDONE_MSG);
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public boolean uploadContactToDB(Contact c) {

		int con_id = 0;

		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_CONTACT);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.executeUpdate();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_CONTACT_ID_WITH_NAME);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				con_id = rs.getInt(1);
				c.setContact_id(con_id);
			}

			PhoneNumber phone = c.getPhone();
			ArrayList<MobileNumber> mobile = phone.getMobileNumber();
			ArrayList<HomeNumber> home = phone.getHomeNumber();
			ArrayList<OfficeNumber> office = phone.getOfficeNumber();

			for (MobileNumber m : mobile) {
				uploadToDB(m, con_id);
			}
			for (HomeNumber h : home) {
				uploadToDB(h, con_id);
			}
			for (OfficeNumber o : office) {
				uploadToDB(o, con_id);
			}
			for (String s : c.getEmailAddress()) {
				uploadToDB(s, con_id);
			}

		} catch (Exception e) {
			logger.info(ApplicationStringConstants.NOTDONE_MSG);
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;

	}

}
