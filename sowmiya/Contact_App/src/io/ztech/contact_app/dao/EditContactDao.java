package io.ztech.contact_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.constants.SqlConstants;
import io.ztech.contact_app.dbutil.DbConnection;

public class EditContactDao {
	Connection conn = DbConnection.getConnection();
	Logger log = Logger.getLogger("UpdateContactDao.class");
	PreparedStatement ps = null;
	ResultSet rs = null;

	public void editLastName(ContactDetail contact, String contact_id) {
		try {
			ps = conn.prepareStatement(SqlConstants.EDIT_LAST_NAME);
			ps.setString(1, contact.getLastName());
			ps.setString(2, contact_id);
			ps.executeUpdate();
			log.info(ApplicationConstants.EDIT_SUCCESS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void editMobileNumber(ContactDetail contact, String required_Id) {
		try {
			String input[] = contact.getMobileNumber().get(0).split("-");
			ps = conn.prepareStatement(SqlConstants.EDIT_MOBILE_NUMBER);
			ps.setString(1, input[1]);
			ps.setString(2, input[0]);
			ps.setString(3, required_Id);
			ps.executeUpdate();
			log.info(ApplicationConstants.EDIT_SUCCESS);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void editOfficeNumber(ContactDetail contact, String required_Id) {
		try {
			String input[] = contact.getOfficeNumber().get(0).split("-");
			ps = conn.prepareStatement(SqlConstants.EDIT_OFFICE_NUMBER);
			ps.setString(1, input[1]);
			ps.setString(2, input[0]);
			ps.setString(3, required_Id);
			ps.executeUpdate();
			log.info(ApplicationConstants.EDIT_SUCCESS);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// DbConnection.closeConnection(rs, ps, conn);
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void editHomeNumber(ContactDetail contact, String required_Id) {
		// TODO Auto-generated method stub
		try {
			String input[] = contact.getHomeNumber().get(0).split("-");
			ps = conn.prepareStatement(SqlConstants.EDIT_HOME_NUMBER);
			ps.setString(1, input[1]);
			ps.setString(2, input[0]);
			ps.setString(3, required_Id);
			ps.executeUpdate();
			log.info(ApplicationConstants.EDIT_SUCCESS);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void editMail(ContactDetail contact, String required_Id) {
		// TODO Auto-generated method stub
		try {
			ps = conn.prepareStatement(SqlConstants.EDIT_EMAIL);
			ps.setString(1, contact.getEmail().get(0));
			ps.setString(2, required_Id);
			ps.executeUpdate();
			log.info(ApplicationConstants.EDIT_SUCCESS);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
