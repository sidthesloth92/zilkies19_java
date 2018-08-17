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

public class DBUsage {
	private static final DBConnectivity con_obj = new DBConnectivity();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());

	public void uploadMobileToDB(MobileNumber m, int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_MOBILE);

			ps.setString(1, m.getCountryCode());
			ps.setString(2, m.getNumber());
			ps.setInt(3, con_id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void uploadMobileToDB(String countryCodeMobile, String mobileNumber, int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_MOBILE);

			ps.setString(1, countryCodeMobile);
			ps.setString(2, mobileNumber);
			ps.setInt(3, con_id);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void uploadHomeToDB(HomeNumber h, int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_HOME);

			ps.setString(1, h.getCountryCode());
			ps.setString(2, h.getAreaCode());
			ps.setString(3, h.getNumber());
			ps.setInt(4, con_id);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void uploadHomeToDB(String countryCode, String areaCode, String number, int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_HOME);

			ps.setString(1, countryCode);
			ps.setString(2, areaCode);
			ps.setString(3, number);
			ps.setInt(4, con_id);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void uploadOfficeToDB(OfficeNumber o, int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_MOBILE);

			ps.setString(1, o.getExtensionNumber());
			ps.setString(2, o.getNumber());
			ps.setInt(3, con_id);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void uploadOfficeToDB(String ext, String number, int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_MOBILE);

			ps.setString(1, ext);
			ps.setString(2, number);
			ps.setInt(3, con_id);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void uploadEmailToDB(String email, int con_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_EMAIL);

			ps.setString(1, email);
			ps.setInt(2, con_id);
			ps.executeUpdate();
		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void uploadContactToDB(Contact c) {

		int con_id = 0;

		try {
			conn.setAutoCommit(false);
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
				uploadMobileToDB(m, con_id);
			}
			for (HomeNumber h : home) {
				uploadHomeToDB(h, con_id);
			}
			for (OfficeNumber o : office) {
				uploadOfficeToDB(o, con_id);
			}
			for (String s : c.getEmailAddress()) {
				uploadEmailToDB(s, con_id);
			}
			conn.commit();

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}

	}

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

	public void alterMobileInDB(String countryCodeMobile, String mobileNumber, int con_id, int mob_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.EDIT_MOBILE);
			ps.setString(1, countryCodeMobile);
			ps.setString(2, mobileNumber);
			ps.setInt(3, con_id);
			ps.setInt(4, mob_id);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void alterOfficeInDB(String ext, String officeNumber, int con_id, int off_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.EDIT_OFFICE);
			ps.setString(1, ext);
			ps.setString(2, officeNumber);
			ps.setInt(3, con_id);
			ps.setInt(4, off_id);
			ps.executeUpdate();

		} catch (Exception e) {
			logger.info("" + e);
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
	}

	public void alterHomeInDB(String countryCodeHome, String areaCode, String homeNumber, int con_id, int home_id) {
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.EDIT_HOME);
			ps.setString(1, countryCodeHome);
			ps.setString(2, areaCode);
			ps.setString(3, homeNumber);
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
