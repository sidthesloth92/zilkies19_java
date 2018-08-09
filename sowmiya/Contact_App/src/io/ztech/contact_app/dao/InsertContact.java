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

public class InsertContact {
	Logger log = Logger.getLogger("InsertContact.class");
	public static Connection conn = DbConnection.getConnection();

	public boolean insertContact(ContactDetail contact) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String mail = "";
		String id = "";
		int mobile_count, office_count, home_count, email_count;

		try {

			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SqlConstants.INSERT_NAME);
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());
			ps.executeUpdate();
			ps = conn.prepareStatement(SqlConstants.GET_RECENT_CONTACTID);

			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString(1);
			}
			ps = conn.prepareStatement(SqlConstants.INSERT_MOBILE_NUMBER);
			mobile_count = contact.getMobileNumber().size();
			while (mobile_count > 0) {
				String input[] = contact.getMobileNumber().get(mobile_count - 1).split("-");
				ps.setString(1, id);
				ps.setString(2, input[0]);
				ps.setString(3, input[1]);
				ps.executeUpdate();
				mobile_count--;
			}
			ps = conn.prepareStatement(SqlConstants.INSERT_HOME_NUMBER);
			home_count = contact.getHomeNumber().size();
			while (home_count > 0) {
				String input[] = contact.getHomeNumber().get(home_count - 1).split("-");
				ps.setString(1, id);
				ps.setString(2, input[0]);
				ps.setString(3, input[1]);
				ps.executeUpdate();
				home_count--;
			}
			ps = conn.prepareStatement(SqlConstants.INSERT_OFFICE_NUMBER);
			office_count = contact.getOfficeNumber().size();
			while (office_count > 0) {
				String input[] = contact.getHomeNumber().get(office_count - 1).split("-");
				ps.setString(1, id);
				ps.setString(2, input[0]);
				ps.setString(3, input[1]);
				ps.executeUpdate();
				office_count--;
			}
			ps = conn.prepareStatement(SqlConstants.INSERT_EMAIL);
			email_count = contact.getEmail().size();
			while (email_count > 0) {
				mail = contact.getEmail().get(email_count - 1);
				ps.setString(1, id);
				ps.setString(2, mail);
				ps.executeUpdate();
				email_count--;
			}

			conn.commit();
		} catch (Exception e) {
			log.info(e.toString());
			conn.rollback();
		} finally {
			DbConnection.closeConnection(rs, ps, conn);

		}

		return false;

	}

	public boolean isExists(ContactDetail contact) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		int numberOfrows = 0;
		try {
			ps = conn.prepareStatement(SqlConstants.VIEW_SPECIFIC_CONTACT);
			ps.setString(1, contact.getFirstName());
			ps.setString(2, contact.getLastName());

			rs = ps.executeQuery();
			while (rs.next()) {
				numberOfrows++;
			}
			if (numberOfrows > 0) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
