package io.ztech.contactapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.constants.SQLQueryStringConstants;
import io.ztech.contactapp.dbutils.DBConnectivity;

public class CheckDao {
	private static final DBConnectivity con_obj = new DBConnectivity();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public boolean checkIfExists(String firstname) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_CONTACTS_WITH_FIRSTNAME);

			ps.setString(1, firstname);
			rs = ps.executeQuery();

			if (rs.next() && rs.getString(1) != null) {
				return true;
			}

		} catch (Exception e) {
			logger.info("" + e);
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return false;
	}
}
