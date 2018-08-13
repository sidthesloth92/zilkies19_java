package io.ztech.contactapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import io.ztech.contactapp.beans.Contact;
import io.ztech.contactapp.beans.EmailId;
import io.ztech.contactapp.beans.HomeNumber;
import io.ztech.contactapp.beans.MobileNumber;
import io.ztech.contactapp.beans.OfficeNumber;
import io.ztech.contactapp.beans.Number;
import io.ztech.contactapp.dbutils.DBManager;
import io.ztech.contactapp.constants.*;

//=========================================================================================================================================
//CONTACT DAO CLASS - DAO CLASS FOR DATABASE MANIPULATION
//=========================================================================================================================================

public class ContactDAO implements QueryConstants {

	ResultSet resultSet;
	PreparedStatement preparedStmt;
	Connection connection;
	DBManager dbManager;

	public ContactDAO() {
		dbManager = new DBManager();
	}

	// =========================================================================================================================================
	// DATABASE INSERTION
	// =========================================================================================================================================

	// =======================================================================
	// INSERT DATA INTO CONTACT TABLE
	// =======================================================================
	public void insertIntoContactDetails(Contact contact) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(INSERT_INTO_CONTACT_DETAILS);
			preparedStmt.setString(1, contact.getFirstName());
			preparedStmt.setString(2, contact.getLastName());
			preparedStmt.execute();
			int cId = getRecentCid();
			insertIntoPhoneNumberDetails(contact.getPhoneNumbers(), cId);
			insertIntoEmailDetails(contact.getEmailIds(), cId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// GET CID OF LAST INSERTED TUPLE
	// =======================================================================
	public int getRecentCid() {
		int cId = -1;
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(SELECT_RECENT_CID);
			resultSet = preparedStmt.executeQuery();
			if (resultSet.next())
				cId = resultSet.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
			return cId;
		}
	}

	// =======================================================================
	// INSERT DATA INTO PHONE NUMBER TABLES
	// =======================================================================
	public void insertIntoPhoneNumberDetails(ArrayList<Number> phoneNumbers, int cId) {
		try {
			connection = dbManager.getConnection();
			while (!phoneNumbers.isEmpty() && phoneNumbers.get(0) instanceof MobileNumber) {
				MobileNumber mobileNumber = (MobileNumber) phoneNumbers.remove(0);
				preparedStmt = connection.prepareStatement(INSERT_INTO_MOBILE_DETAILS);
				preparedStmt.setInt(1, cId);
				preparedStmt.setString(2, mobileNumber.getPhoneNumber());
				preparedStmt.setString(3, mobileNumber.getCountryCode());
				preparedStmt.execute();
			}

			while (!phoneNumbers.isEmpty() && phoneNumbers.get(0) instanceof HomeNumber) {
				HomeNumber homeNumber = (HomeNumber) phoneNumbers.remove(0);
				preparedStmt = connection.prepareStatement(INSERT_INTO_HOME_DETAILS);
				preparedStmt.setInt(1, cId);
				preparedStmt.setString(2, homeNumber.getPhoneNumber());
				preparedStmt.setString(3, homeNumber.getCountryCode());
				preparedStmt.setString(4, homeNumber.getAreaCode());
				preparedStmt.execute();
			}

			while (!phoneNumbers.isEmpty() && phoneNumbers.get(0) instanceof OfficeNumber) {
				OfficeNumber officeNumber = (OfficeNumber) phoneNumbers.remove(0);
				preparedStmt = connection.prepareStatement(INSERT_INTO_OFFICE_DETAILS);
				preparedStmt.setInt(1, cId);
				preparedStmt.setString(2, officeNumber.getPhoneNumber());
				preparedStmt.setString(3, officeNumber.getExtension());
				preparedStmt.setString(4, officeNumber.getCountryCode());
				preparedStmt.setString(5, officeNumber.getAreaCode());
				preparedStmt.execute();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// INSERT DATA INTO EMAILID TABLE
	// =======================================================================
	public void insertIntoEmailDetails(ArrayList<EmailId> emailIds, int cId) {
		try {
			connection = dbManager.getConnection();
			while (!emailIds.isEmpty()) {
				EmailId emailId = emailIds.remove(0);
				preparedStmt = connection.prepareStatement(INSERT_INTO_EMAIL_DETAILS);
				preparedStmt.setInt(1, cId);
				preparedStmt.setString(2, emailId.getEmailId());
				preparedStmt.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =========================================================================================================================================
	// DATABASE SELECTION
	// =========================================================================================================================================

	// =======================================================================
	// SELECT EXISTING CONTACTS
	// =======================================================================
	public ArrayList<Contact> selectExistingContact() {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(SELECT_CONTACT_DETAILS);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				Contact contact = new Contact();
				contact.setcId(resultSet.getInt("c_id"));
				contact.setFirstName(resultSet.getString("first_name"));
				contact.setLastName(resultSet.getString("last_name"));
				contacts.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
			return contacts;
		}
	}

	// =======================================================================
	// SELECT PHONE NUMBER AND EMAILID DETAILS OF CONTACT
	// =======================================================================
	public Contact selectContactDetails(int cId) {
		Contact contact = new Contact();
		ArrayList<Number> phoneNumbers = new ArrayList<Number>();
		ArrayList<EmailId> emailIds = new ArrayList<EmailId>();
		try {
			connection = dbManager.getConnection();

			preparedStmt = connection.prepareStatement(SELECT_MOBILE_NUMBERS);
			preparedStmt.setInt(1, cId);
			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				MobileNumber mobileNumber = new MobileNumber();
				mobileNumber.setmId(resultSet.getInt("m_id"));
				mobileNumber.setCountryCode(resultSet.getString("country_code"));
				mobileNumber.setPhoneNumber(resultSet.getString("mob_num"));
				phoneNumbers.add(mobileNumber);
			}

			preparedStmt = connection.prepareStatement(SELECT_HOME_NUMBERS);
			preparedStmt.setInt(1, cId);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				HomeNumber homeNumber = new HomeNumber();
				homeNumber.sethId(resultSet.getInt("h_id"));
				homeNumber.setCountryCode(resultSet.getString("country_code"));
				homeNumber.setAreaCode(resultSet.getString("area_code"));
				homeNumber.setPhoneNumber(resultSet.getString("home_num"));
				phoneNumbers.add(homeNumber);

			}

			preparedStmt = connection.prepareStatement(SELECT_OFFICE_NUMBERS);
			preparedStmt.setInt(1, cId);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				OfficeNumber officeNumber = new OfficeNumber();
				officeNumber.setoId(resultSet.getInt("o_id"));
				officeNumber.setCountryCode(resultSet.getString("country_code"));
				officeNumber.setAreaCode(resultSet.getString("area_code"));
				officeNumber.setPhoneNumber(resultSet.getString("off_num"));
				officeNumber.setExtension(resultSet.getString("extn"));
				phoneNumbers.add(officeNumber);
			}

			preparedStmt = connection.prepareStatement(SELECT_EMAIL_IDS);
			preparedStmt.setInt(1, cId);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				EmailId emailId = new EmailId();
				emailId.seteId(resultSet.getInt("e_id"));
				emailId.setEmailId(resultSet.getString("email_id"));
				emailIds.add(emailId);
			}

			contact.setPhoneNumbers(phoneNumbers);
			contact.setEmailIds(emailIds);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
			return contact;
		}
	}

	// =======================================================================
	// SEARCH FOR CONTACTS BY NAME
	// =======================================================================

	public ArrayList<Contact> searchExistingContact(String name) {
		name = name + "%";
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(SEARCH_CONTACTS);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, name);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				Contact contact = new Contact();
				contact.setcId(resultSet.getInt("c_id"));
				contact.setFirstName(resultSet.getString("first_name"));
				contact.setLastName(resultSet.getString("last_name"));
				contacts.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
			return contacts;
		}
	}

	// =========================================================================================================================================
	// DATABASE UPDATION
	// =========================================================================================================================================

	// =======================================================================
	// UPDATE FIRST NAME
	// =======================================================================
	public void updateFirstName(String firstName, int cId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(UPDATE_FIRST_NAME);
			preparedStmt.setString(1, firstName);
			preparedStmt.setInt(2, cId);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// UPDATE LAST NAME
	// =======================================================================
	public void updateLastName(String lastName, int cId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(UPDATE_LAST_NAME);
			preparedStmt.setString(1, lastName);
			preparedStmt.setInt(2, cId);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// UPDATE MOBILE NUMBER
	// =======================================================================
	public void updateMobileNumber(MobileNumber mobileNumber) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(UPDATE_MOBILE_NUMBER);
			preparedStmt.setString(1, mobileNumber.getPhoneNumber());
			preparedStmt.setString(2, mobileNumber.getCountryCode());
			preparedStmt.setInt(3, mobileNumber.getcId());
			preparedStmt.setInt(4, mobileNumber.getmId());
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);

		}
	}

	// =======================================================================
	// UPDATE OFFICE NUMBER
	// =======================================================================
	public void updateOfficeNumber(OfficeNumber officeNumber) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(UPDATE_OFFICE_NUMBER);
			System.out.println("Updated number:+" + officeNumber.getCountryCode() + " " + officeNumber.getAreaCode()
					+ " " + officeNumber.getPhoneNumber() + " " + officeNumber.getcId() + " " + officeNumber.getoId());
			preparedStmt.setString(1, officeNumber.getPhoneNumber());
			preparedStmt.setString(2, officeNumber.getExtension());
			preparedStmt.setString(3, officeNumber.getCountryCode());
			preparedStmt.setString(4, officeNumber.getAreaCode());
			preparedStmt.setInt(5, officeNumber.getcId());
			preparedStmt.setInt(6, officeNumber.getoId());
			preparedStmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// UPDATE HOME NUMBER
	// =======================================================================
	public void updateHomeNumber(HomeNumber homeNumber) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(UPDATE_HOME_NUMBER);
			preparedStmt.setString(1, homeNumber.getPhoneNumber());
			preparedStmt.setString(2, homeNumber.getCountryCode());
			preparedStmt.setString(3, homeNumber.getAreaCode());
			preparedStmt.setInt(4, homeNumber.getcId());
			preparedStmt.setInt(5, homeNumber.gethId());
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// UPDATE EMAIL ID
	// =======================================================================
	public void updateEmailId(EmailId emailId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(UPDATE_EMAIL_ID);
			preparedStmt.setString(1, emailId.getEmailId());
			preparedStmt.setInt(2, emailId.getcId());
			preparedStmt.setInt(3, emailId.geteId());
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}
	// =========================================================================================================================================
	// DATABASE DELETION
	// =========================================================================================================================================

	// =======================================================================
	// DELETE ENTIRE CONTACT
	// =======================================================================
	public void deleteContact(int cId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(DELETE_CONTACT);
			preparedStmt.setInt(1, cId);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// DELETE A MOBILE NUMBER
	// =======================================================================
	public void deleteMobileNumber(int cId, int mId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(DELETE_MOBILE_NUMBER);
			preparedStmt.setInt(1, cId);
			preparedStmt.setInt(2, mId);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// DELETE A OFFICE NUMBER
	// =======================================================================
	public void deleteOfficeNumber(int cId, int oId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(DELETE_OFFICE_NUMBER);
			preparedStmt.setInt(1, cId);
			preparedStmt.setInt(2, oId);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// DELETE A HOME NUMBER
	// =======================================================================
	public void deleteHomeNumber(int cId, int hId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(DELETE_HOME_NUMBER);
			preparedStmt.setInt(1, cId);
			preparedStmt.setInt(2, hId);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

	// =======================================================================
	// DELETE A EMAIL ID
	// =======================================================================
	public void deleteEmailId(int cId, int eId) {
		try {
			connection = dbManager.getConnection();
			preparedStmt = connection.prepareStatement(DELETE_EMAIL_ID);
			preparedStmt.setInt(1, cId);
			preparedStmt.setInt(2, eId);
			preparedStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection(resultSet, preparedStmt, connection);
		}
	}

}
