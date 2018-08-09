package io.ztech.contact_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.constants.SqlConstants;
import io.ztech.contact_app.dbutil.DbConnection;

public class UpdateContactDao {
	Connection conn = DbConnection.getConnection();
	Logger log = Logger.getLogger("UpdateContactDao.class");

	public void updateContactDetails(String id, ContactDetail contact) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String mail = "";

		int success, mobile_count, office_count, home_count, email_count;

		try {
			conn.setAutoCommit(false);
			if (contact.getMobileNumber().size() > 0) {
				ps = conn.prepareStatement(SqlConstants.INSERT_MOBILE_NUMBER);
				mobile_count = contact.getMobileNumber().size();
				while (mobile_count > 0) {
					String input[] = contact.getMobileNumber().get(mobile_count - 1).split("-");
					ps.setString(1, id);
					ps.setString(2, input[0]);
					ps.setString(3, input[1]);
					success = ps.executeUpdate();
					mobile_count--;
				}

			}
			if (contact.getHomeNumber().size() > 0) {
				ps = conn.prepareStatement(SqlConstants.INSERT_HOME_NUMBER);
				home_count = contact.getHomeNumber().size();
				while (home_count > 0) {
					String input[] = contact.getHomeNumber().get(home_count - 1).split("-");
					ps.setString(1, id);
					ps.setString(2, input[0]);
					ps.setString(3, input[1]);
					success = ps.executeUpdate();
					home_count--;
				}
			}
			if (contact.getOfficeNumber().size() > 0) {
				ps = conn.prepareStatement(SqlConstants.INSERT_OFFICE_NUMBER);
				office_count = contact.getOfficeNumber().size();
				while (office_count > 0) {
					String input[] = contact.getHomeNumber().get(office_count - 1).split("-");
					ps.setString(1, id);
					ps.setString(2, input[0]);
					ps.setString(3, input[1]);
					success = ps.executeUpdate();
					office_count--;
				}
			}
			if (contact.getEmail().size() > 0) {
				ps = conn.prepareStatement(SqlConstants.INSERT_EMAIL);
				email_count = contact.getEmail().size();
				while (email_count > 0) {
					mail = contact.getEmail().get(email_count - 1);
					ps.setString(1, id);
					ps.setString(2, mail);
					ps.executeUpdate();
					email_count--;
				}
			}
			conn.commit();
			log.info(ApplicationConstants.UPDATED);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
