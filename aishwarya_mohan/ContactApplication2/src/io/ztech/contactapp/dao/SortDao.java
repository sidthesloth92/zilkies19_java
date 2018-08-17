package io.ztech.contactapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.constants.SQLQueryStringConstants;
import io.ztech.contactapp.dbutils.DBConnectivity;

public class SortDao {
	private static final DBConnectivity con_obj = new DBConnectivity();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public void sort(int num) {
		try {
			conn = con_obj.getConnection();

			if (num == 1) {
				ps = conn.prepareStatement(SQLQueryStringConstants.SORT_CONTACTS_WITH_FIRST_NAME);
				rs = ps.executeQuery();
				while (rs.next()) {
					logger.info("\n" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				}

			} else if (num == 2) {
				ps = conn.prepareStatement(SQLQueryStringConstants.SORT_CONTACTS_WITH_LAST_NAME);
				rs = ps.executeQuery();
				while (rs.next()) {
					logger.info("\n" + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				}
			} else {
				logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("");
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}

	}
}
