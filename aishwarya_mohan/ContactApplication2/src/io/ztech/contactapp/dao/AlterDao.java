package io.ztech.contactapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.beans.HomeNumber;
import io.ztech.contactapp.beans.MobileNumber;
import io.ztech.contactapp.beans.OfficeNumber;
import io.ztech.contactapp.constants.SQLQueryStringConstants;
import io.ztech.contactapp.dbutils.DBConnectivity;

public class AlterDao {
	private static final DBConnectivity con_obj = new DBConnectivity();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public void alterMobileInDB(MobileNumber m, int con_id, int mob_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.EDIT_MOBILE);
			ps.setString(1, m.getCountryCode());
			ps.setString(2, m.getNumber());
			ps.setInt(3, con_id);
			ps.setInt(4, mob_id);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void alterOfficeInDB(OfficeNumber o, int con_id, int off_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.EDIT_OFFICE);
			ps.setString(1, o.getExtensionNumber());
			ps.setString(2, o.getNumber());
			ps.setInt(3, con_id);
			ps.setInt(4, off_id);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void alterHomeInDB(HomeNumber h, int con_id, int home_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.EDIT_HOME);
			ps.setString(1, h.getCountryCode());
			ps.setString(2, h.getAreaCode());
			ps.setString(3, h.getNumber());
			ps.setInt(4, con_id);
			ps.setInt(5, home_id);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void alterEmailInDB(String mail, int con_id, int mail_id_num) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.EDIT_EMAIL);
			ps.setString(1, mail);
			ps.setInt(2, con_id);
			ps.setInt(3, mail_id_num);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

}
