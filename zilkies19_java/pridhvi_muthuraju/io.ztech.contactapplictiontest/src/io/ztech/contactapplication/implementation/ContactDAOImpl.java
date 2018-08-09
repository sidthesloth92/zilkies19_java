package io.ztech.contactapplication.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import com.sun.xml.internal.bind.v2.runtime.output.Pcdata;

import io.ztech.contactapplication.beans.EmailDetails;
import io.ztech.contactapplication.beans.HomeDetails;
import io.ztech.contactapplication.beans.MobileDetails;
import io.ztech.contactapplication.beans.NameDetails;
import io.ztech.contactapplication.beans.OfficeDetails;
import io.ztech.contactapplication.constants.AppConstants;
import io.ztech.contactapplication.constants.SQLConstants;
import io.ztech.contactapplication.dbutils.DBUtils;

public class ContactDAOImpl implements ContactDAO {

	private Scanner sc = new Scanner(System.in);
	private final Logger logger = Logger.getLogger(ContactDAOImpl.class.getName());
	private Connection con = null;
	private PreparedStatement pst = null;

	@Override
	public void addNewName(NameDetails nameDetails) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_CONTACT);
			pst.setString(1, nameDetails.getFirstName());
			pst.setString(2, nameDetails.getLastName());
			pst.executeUpdate();

		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}
	}

	@Override
	public void addNewOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException {

		logger.info(AppConstants.ADD_OFFICE);
		if (sc.next().equals("y")) {
			do {
				logger.info(AppConstants.OFFICE_EXTENSION);
				officeDetails.setOfficeExtension(sc.next());
				logger.info(AppConstants.OFFICE_NUMBER);
				officeDetails.setOfficeNumber(sc.next());
			} while (!Validator.validateOfficeNumber(officeDetails.getOfficeExtension(),
					officeDetails.getOfficeNumber()));
		}
	}

	@Override
	public void addNewMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException {
		logger.info(AppConstants.ADD_MOBILE);
		if (sc.next().equals("y")) {
			do {
				logger.info(AppConstants.COUNTRY_CODE);
				mobileDetails.setMobileCountryCode(sc.next());
			} while (!Validator.validateCountryCode(mobileDetails.getMobileCountryCode()));
			do {
				logger.info(AppConstants.MOBILE_NUMBER);
				mobileDetails.setMobileNumber(sc.next());
			} while (!Validator.validateNumber(mobileDetails.getMobileNumber()));

		}
	}

	@Override
	public void addNewHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException {
		logger.info(AppConstants.ADD_HOME);
		if (sc.next().equals("y")) {
			do {
				logger.info(AppConstants.AREA_CODE);
				homeDetails.setHomeAreaCode(sc.next());
			} while (!Validator.validateAreaCode(homeDetails.getHomeAreaCode()));
			do {
				logger.info(AppConstants.HOME_NUMBER);
				homeDetails.setHomeNumber(sc.next());
			} while (!Validator.validateNumber(homeDetails.getHomeNumber()));

		}
	}

	@Override
	public void addNewContact(NameDetails nameDetails, EmailDetails emailDetails, HomeDetails homeDetails,
			MobileDetails mobileDetails, OfficeDetails officeDetails) throws SQLException {
		logger.info(AppConstants.FIRST_NAME);
		nameDetails.setFirstName(sc.next());
		logger.info(AppConstants.LAST_NAME);
		nameDetails.setLastName(sc.next());
		do {
			logger.info(AppConstants.EMAIL);
			emailDetails.setEmail(sc.next());
		} while (!Validator.validateEmail(emailDetails.getEmail()));

		if (Validator.isEmailPresent(emailDetails.getEmail())) {
			logger.info(AppConstants.EMAIL_PRESENT);
			if (sc.next().equalsIgnoreCase("u")) {
				updateContact(nameDetails, emailDetails);
				return;
			} else {
				do {
					logger.info(AppConstants.EMAIL);
					emailDetails.setEmail(sc.next());
				} while (Validator.isEmailPresent(emailDetails.getEmail())
						&& Validator.validateEmail(emailDetails.getEmail()));
			}
		}
		addNewName(nameDetails);
		addNewOffice(nameDetails, officeDetails);
		addNewMobile(nameDetails, mobileDetails);
		addNewHome(nameDetails, homeDetails);

		insertOffice(nameDetails, officeDetails);
		insertMobile(nameDetails, mobileDetails);
		insertHome(nameDetails, homeDetails);
		insertEmail(nameDetails, emailDetails);
	}

	@Override
	public void enterUpdateName(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException {

		HashMap<Integer, String> em = new HashMap<Integer, String>();
		logger.info(AppConstants.FIRST_NAME);
		nameDetails.setFirstName(sc.next());
		logger.info(AppConstants.LAST_NAME);
		nameDetails.setLastName(sc.next());
		if (Validator.isNamePresent(nameDetails.getFirstName(), nameDetails.getLastName())) {
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.SELECT_BY_NAME);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				display(pst.toString().substring(43), nameDetails, emailDetails);
				DBUtils.closeConnection(con, pst, null);
			}
			logger.info(AppConstants.CONTACT_ID);
			emailDetails.setEmail(em.get(sc.nextInt()));
		} else {
			logger.info(AppConstants.CONTACT_UNAVAILABLE);
			enterUpdateName(nameDetails, emailDetails);
		}
		updateContact(nameDetails, emailDetails);
	}

	@Override
	public void updateContact(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException {
		updateOffice(nameDetails, new OfficeDetails());
		updateMobile(nameDetails, new MobileDetails());
		updateHome(nameDetails, new HomeDetails());
		updateEmail(nameDetails, emailDetails);
	}

	@Override
	public void updateOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException {
		logger.info(AppConstants.ADD_UPDATE_OFFICE);
		String choice = sc.next();
		if (choice.equals("n"))
			return;
		if (choice.equals("a")) {
			do {
				logger.info(AppConstants.OFFICE_EXTENSION);
				officeDetails.setOfficeExtension(sc.next());
				logger.info(AppConstants.OFFICE_EXTENSION);
				officeDetails.setOfficeNumber(sc.next());
			} while (!Validator.validateOfficeNumber(officeDetails.getOfficeExtension(),
					officeDetails.getOfficeNumber()));

			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.ADD_OFFICE);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				pst.setString(3, officeDetails.getOfficeExtension());
				pst.setString(4, officeDetails.getOfficeNumber());
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}

		} else if (choice.equals("u")) {

			ResultSet res = null;
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.SELECT_OFFICE);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				res = pst.executeQuery();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				int count = 0;
				while (res.next()) {
					count++;
					logger.info(res.getInt("contact_id") + "\t" + res.getInt("office_id") + "\t"
							+ res.getString("office_extension") + "\t" + res.getString("office_number"));
				}
				if (count == 0) {
					logger.info(AppConstants.NTNG_TO_UPDATE);
					updateOffice(nameDetails, new OfficeDetails());
					return;
				}
				DBUtils.closeConnection(con, pst, null);
			}

			logger.info(AppConstants.OFFICE_ID);
			int officeId = sc.nextInt();

			do {
				logger.info(AppConstants.OFFICE_EXTENSION);
				officeDetails.setOfficeExtension(sc.next());
				logger.info(AppConstants.OFFICE_NUMBER);
				officeDetails.setOfficeNumber(sc.next());
			} while (!Validator.validateOfficeNumber(officeDetails.getOfficeExtension(),
					officeDetails.getOfficeNumber()));

			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.UPDATE_OFFICE);
				pst.setString(1, officeDetails.getOfficeExtension());
				pst.setString(2, officeDetails.getOfficeNumber());
				pst.setString(3, nameDetails.getFirstName());
				pst.setString(4, nameDetails.getLastName());
				pst.setInt(4, officeId);
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
		}
	}

	@Override
	public void updateMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException {
		logger.info(AppConstants.ADD_UPDATE_MOBILE);
		String choice = sc.next();
		if (choice.equals("n"))
			return;

		if (choice.equals("a")) {
			do {
				logger.info(AppConstants.COUNTRY_CODE);
				mobileDetails.setMobileCountryCode(sc.next());
			} while (!Validator.validateCountryCode(mobileDetails.getMobileCountryCode()));
			do {
				logger.info(AppConstants.MOBILE_NUMBER);
				mobileDetails.setMobileNumber(sc.next());
			} while (!Validator.validateNumber(mobileDetails.getMobileNumber()));

			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.UPDATE_MOBILE);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				pst.setString(3, mobileDetails.getMobileCountryCode());
				pst.setString(4, mobileDetails.getMobileNumber());
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}

		} else if (choice.equals("u")) {

			ResultSet res = null;
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.SELECT_BY_ID_MOBILE);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				res = pst.executeQuery();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {

				int count = 0;
				while (res.next()) {
					count++;
					logger.info(res.getInt("contact_id") + "\t" + res.getInt("mobile_id") + "\t"
							+ res.getString("country_code") + "\t" + res.getString("mobile_number"));
				}
				if (count == 0) {
					logger.info(AppConstants.NTNG_TO_UPDATE);
					updateMobile(nameDetails, new MobileDetails());
					return;
				}
				DBUtils.closeConnection(con, pst, null);
			}

			logger.info(AppConstants.MOBILE_ID);
			int mobileId = sc.nextInt();
			do {
				logger.info(AppConstants.COUNTRY_CODE);
				mobileDetails.setMobileCountryCode(sc.next());
			} while (!Validator.validateCountryCode(mobileDetails.getMobileCountryCode()));
			do {
				logger.info(AppConstants.MOBILE_NUMBER);
				mobileDetails.setMobileNumber(sc.next());
			} while (!Validator.validateNumber(mobileDetails.getMobileNumber()));

			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.ALTER_MOBILE);
				pst.setString(1, mobileDetails.getMobileCountryCode());
				pst.setString(2, mobileDetails.getMobileNumber());
				pst.setString(3, nameDetails.getFirstName());
				pst.setString(4, nameDetails.getLastName());
				pst.setInt(5, mobileId);
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
		}
	}

	@Override
	public void updateHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException {
		logger.info(AppConstants.ADD_UPDATE_HOME);
		String choice = sc.next();
		if (choice.equals("n"))
			return;

		if (choice.equals("a")) {

			do {
				logger.info(AppConstants.AREA_CODE);
				homeDetails.setHomeAreaCode(sc.next());
			} while (!Validator.validateAreaCode(homeDetails.getHomeAreaCode()));
			do {
				logger.info(AppConstants.HOME_NUMBER);
				homeDetails.setHomeNumber(sc.next());
			} while (!Validator.validateNumber(homeDetails.getHomeNumber()));

			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.ALTER_HOME);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				pst.setString(3, homeDetails.getHomeAreaCode());
				pst.setString(4, homeDetails.getHomeNumber());
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}

		} else if (choice.equals("u")) {

			ResultSet res = null;
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.SELECT_BY_ID_HOME);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				res = pst.executeQuery();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				int count = 0;
				while (res.next()) {
					count++;
					logger.info(res.getInt("contact_id") + "\t" + res.getInt("home_id") + "\t"
							+ res.getString("area_code") + "\t" + res.getString("home_number"));
				}
				if (count == 0) {
					logger.info(AppConstants.NTNG_TO_UPDATE);
					updateHome(nameDetails, new HomeDetails());
					return;
				}
				DBUtils.closeConnection(con, pst, null);
			}

			logger.info(AppConstants.HOME_ID);
			int homeId = sc.nextInt();
			do {
				logger.info(AppConstants.AREA_CODE);
				homeDetails.setHomeCountryCode(sc.next());
			} while (!Validator.validateAreaCode(homeDetails.getHomeAreaCode()));
			do {
				logger.info(AppConstants.HOME_NUMBER);
				homeDetails.setHomeNumber(sc.next());
			} while (!Validator.validateNumber(homeDetails.getHomeNumber()));
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.UPDATE_HOME);
				pst.setString(1, homeDetails.getHomeAreaCode());
				pst.setString(2, homeDetails.getHomeNumber());
				pst.setString(3, nameDetails.getFirstName());
				pst.setString(4, nameDetails.getLastName());
				pst.setInt(4, homeId);
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
		}
	}

	@Override
	public void updateEmail(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException {
		logger.info(AppConstants.ADD_UPDATE_EMAIL);
		String choice = sc.next();
		if (choice.equals("n"))
			return;

		if (choice.equals("a")) {
			do {
				logger.info(AppConstants.EMAIL);
				emailDetails.setEmail(sc.next());
			} while (!Validator.validateEmail(emailDetails.getEmail()));

			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.INSERT_EMAIL);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				pst.setString(3, emailDetails.getEmail());
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}

		} else if (choice.equals("u")) {

			ResultSet res = null;
			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.SELECT_BY_ID_EMAIL);
				pst.setString(1, nameDetails.getFirstName());
				pst.setString(2, nameDetails.getLastName());
				res = pst.executeQuery();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				int count = 0;
				while (res.next()) {
					count++;
					logger.info(res.getInt("contact_id") + "\t" + res.getInt("emailid_id") + "\t"
							+ res.getString("email_id"));
				}
				if (count == 0) {
					logger.info(AppConstants.NTNG_TO_UPDATE);
					updateEmail(nameDetails, emailDetails);
					return;
				}
				DBUtils.closeConnection(con, pst, null);
			}

			logger.info(AppConstants.EMAIL_ID);
			int emailidId = sc.nextInt();
			do {
				logger.info(AppConstants.EMAIL);
				emailDetails.setEmail(sc.next());
			} while (!Validator.validateEmail(emailDetails.getEmail()));

			try {
				con = DBUtils.getConnection();
				pst = con.prepareStatement(SQLConstants.UPDATE_EMAIL);
				pst.setString(1, emailDetails.getEmail());
				pst.setString(2, nameDetails.getFirstName());
				pst.setString(3, nameDetails.getLastName());
				pst.setInt(4, emailidId);
				pst.executeUpdate();

			} catch (SQLException e) {
				logger.info(AppConstants.SQL_ERROR);
			} finally {
				DBUtils.closeConnection(con, pst, null);
			}
		}
	}

	@Override
	public void searchByFirstName(NameDetails nameDetails, EmailDetails emailDetails, HomeDetails homeDetails,
			MobileDetails mobileDetails, OfficeDetails officeDetails) throws SQLException {
		logger.info(AppConstants.FIRST_NAME);
		nameDetails.setFirstName(sc.next());
		display(SQLConstants.SELECT_BY_FIRST_NAME, nameDetails, emailDetails);
	}

	@Override
	public void display(String sort, NameDetails nameDetails, EmailDetails emailDetails) throws SQLException {

		con = DBUtils.getConnection();

		pst = con.prepareStatement(sort);
		ResultSet res = pst.executeQuery();

		while (res.next()) {

			int contactId = res.getInt("contact_id");
			pst = con.prepareStatement(SQLConstants.SELECT_ALL_OFFICE);
			pst.setInt(1, contactId);
			ResultSet off = pst.executeQuery();

			pst = con.prepareStatement(SQLConstants.SELECT_ALL_MOBILE);
			pst.setInt(1, contactId);
			ResultSet mob = pst.executeQuery();

			pst = con.prepareStatement(SQLConstants.SELECT_ALL_HOME);
			pst.setInt(1, contactId);
			ResultSet home = pst.executeQuery();

			pst = con.prepareStatement(SQLConstants.SELECT_ALL_EMAIL);
			pst.setInt(1, contactId);
			ResultSet email = pst.executeQuery();

			logger.info(res.getInt(1) + "\n" + res.getString(2) + "\n" + res.getString(3));

			while (off.next()) {
				logger.info(AppConstants.OFFICE_NUMBER_PRINT + off.getString(3) + "-" + off.getString(4) + ", ");
			}

			while (mob.next()) {
				logger.info(AppConstants.MOBILE_NUMBER_PRINT + mob.getString(3) + "-" + mob.getString(4) + ", ");
			}

			while (home.next()) {
				logger.info(AppConstants.HOME_NUMBER_PRINT + home.getString(3) + "-" + home.getString(4) + ", ");
			}

			while (email.next()) {
				logger.info(AppConstants.EMAIL_PRINT + email.getString(3) + ", ");
			}

		}

	}

	@Override
	public void insertOffice(NameDetails nameDetails, OfficeDetails officeDetails) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_OFFICE);
			pst.setString(1, nameDetails.getFirstName());
			pst.setString(2, nameDetails.getLastName());
			pst.setString(3, officeDetails.getOfficeExtension());
			pst.setString(4, officeDetails.getOfficeNumber());
			pst.executeUpdate();

		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}

	}

	@Override
	public void insertMobile(NameDetails nameDetails, MobileDetails mobileDetails) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_MOBILE);
			pst.setString(1, nameDetails.getFirstName());
			pst.setString(2, nameDetails.getLastName());
			pst.setString(3, mobileDetails.getMobileCountryCode());
			pst.setString(4, mobileDetails.getMobileNumber());
			pst.executeUpdate();

		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}

	}

	@Override
	public void insertHome(NameDetails nameDetails, HomeDetails homeDetails) throws SQLException {

		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_HOME);
			pst.setString(1, nameDetails.getFirstName());
			pst.setString(2, nameDetails.getLastName());
			pst.setString(3, homeDetails.getHomeAreaCode());
			pst.setString(4, homeDetails.getHomeNumber());
			pst.executeUpdate();

		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}

	}

	@Override
	public void insertEmail(NameDetails nameDetails, EmailDetails emailDetails) throws SQLException {
		try {
			con = DBUtils.getConnection();
			pst = con.prepareStatement(SQLConstants.INSERT_EMAIL);
			pst.setString(1, nameDetails.getFirstName());
			pst.setString(2, nameDetails.getLastName());
			pst.setString(3, emailDetails.getEmail());
			pst.executeUpdate();

		} catch (SQLException e) {
			logger.info(AppConstants.SQL_ERROR);
		} finally {
			DBUtils.closeConnection(con, pst, null);
		}

	}
}
