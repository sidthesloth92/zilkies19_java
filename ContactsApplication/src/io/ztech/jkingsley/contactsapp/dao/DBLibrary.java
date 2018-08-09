package io.ztech.jkingsley.contactsapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.jkingsley.contactsapp.UIClass;
import io.ztech.jkingsley.contactsapp.beans.Contact;
import io.ztech.jkingsley.contactsapp.beans.Email;
import io.ztech.jkingsley.contactsapp.beans.HomeNumber;
import io.ztech.jkingsley.contactsapp.beans.MobileNumber;
import io.ztech.jkingsley.contactsapp.beans.OfficeNumber;
import io.ztech.jkingsley.contactsapp.beans.PhoneNumber;
import io.ztech.jkingsley.contactsapp.beans.User;
import io.ztech.jkingsley.contactsapp.constants.Strings;

public class DBLibrary {
	
	
	public static void addContact(Contact contact) {
		Long userId;
		userId = addUser(contact.user);
		addPhoneNumbers(userId, contact.mobileNumbers, contact.homeNumbers, contact.officeNumbers);
		addEmails(userId, contact.emails);
	}

	private static void addEmails(Long userId, ArrayList<Email> emails) {

		DBUtils dbUtils = new DBUtils();

		Connection connection = dbUtils.getConnection();

		for (int i = 0; i < emails.size(); i++) {
			PreparedStatement insertEmail;
			try {
				insertEmail = connection.prepareStatement(Strings.INSERT_EMAIL_QUERY);
				insertEmail.setLong(1, userId);
				insertEmail.setString(2, emails.get(i).getAddress());

				insertEmail.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static void addPhoneNumbers(Long userId, ArrayList<MobileNumber> mobileNumbers,
			ArrayList<HomeNumber> homeNumbers, ArrayList<OfficeNumber> officeNumbers) {

		DBUtils dbUtils = new DBUtils();

		Connection connection = dbUtils.getConnection();

		for (int i = 0; i < mobileNumbers.size(); i++) {
			PreparedStatement insertPhone = null;
			try {
				insertPhone = connection.prepareStatement(Strings.INSERT_PHONE_QUERY);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				insertPhone.setLong(1, userId);
				insertPhone.setString(2, mobileNumbers.get(i).phoneType);
				insertPhone.setLong(3, mobileNumbers.get(i).getNumber());

				insertPhone.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		for (int i = 0; i < officeNumbers.size(); i++) {
			PreparedStatement insertPhone;
			try {
				insertPhone = connection.prepareStatement(Strings.INSERT_PHONE_QUERY);
				insertPhone.setLong(1, userId);
				insertPhone.setString(2, officeNumbers.get(i).phoneType);
				insertPhone.setLong(3, officeNumbers.get(i).getNumber());

				insertPhone.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		for (int i = 0; i < homeNumbers.size(); i++) {
			PreparedStatement insertPhone;
			try {
				insertPhone = connection.prepareStatement(Strings.INSERT_PHONE_QUERY);
				insertPhone.setLong(1, userId);
				insertPhone.setString(2, homeNumbers.get(i).phoneType);
				insertPhone.setLong(3, homeNumbers.get(i).getNumber());

				insertPhone.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static Long addUser(User user) {

		DBUtils dbUtils = new DBUtils();

		Connection connection = dbUtils.getConnection();

		PreparedStatement insertUser = null;

		try {
			insertUser = connection.prepareStatement(Strings.INSERT_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
			insertUser.setString(1, user.getFirstName());
			insertUser.setString(2, user.getLastName());

			int affectedRows = insertUser.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try (ResultSet generatedKeys = insertUser.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user.getId();
	}

	public static void updateFirstName(Long userId, String firstName) {

		DBUtils dbUtils = new DBUtils();

		Connection connection = dbUtils.getConnection();

		PreparedStatement updateFirstName;
		try {
			updateFirstName = connection.prepareStatement(Strings.UPDATE_USER_FIRSTNAME_QUERY);
			updateFirstName.setString(1, firstName);
			updateFirstName.setLong(2, userId);

			updateFirstName.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateLastName(Long userId, String lastName) {

		DBUtils dbUtils = new DBUtils();

		Connection connection = dbUtils.getConnection();

		PreparedStatement updateLastName;
		try {
			updateLastName = connection.prepareStatement(Strings.UPDATE_USER_LASTNAME_QUERY);
			updateLastName.setString(1, lastName);
			updateLastName.setLong(2, userId);

			updateLastName.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<User> listUsers() {

		DBUtils dbUtils = new DBUtils();

		ArrayList<User> users = new ArrayList<>();

		Connection connection = dbUtils.getConnection();

		try {
			PreparedStatement listUsers = connection.prepareStatement(Strings.LIST_ALL_USERS_QUERY);

			ResultSet rs = listUsers.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong(Strings.USER_KEY_ID));
				user.setFirstName(rs.getString(Strings.USER_KEY_FIRSTNAME));
				user.setLastName(rs.getString(Strings.USER_KEY_LASTNAME));
				users.add(user);
			}

			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Contact getContact(Long userId) {
		Contact contact = new Contact();
		contact.user = getUser(userId);
		contact.emails = getEmailsOfUser(userId);
		ArrayList<PhoneNumber> phoneNumbers = getPhoneNumbersOfUser(userId);
		for(int i=0;i<phoneNumbers.size();i++) {
			switch(phoneNumbers.get(i).getPhoneType()) {
			case Strings.PHONE_HOME_TYPE:
				HomeNumber homeNumber = new HomeNumber();
				homeNumber.putNumber(phoneNumbers.get(i).getNumber());
				contact.homeNumbers.add(homeNumber);
				break;
			case Strings.PHONE_OFFICE_TYPE:
				OfficeNumber officeNumber = new OfficeNumber();
				officeNumber.putNumber(phoneNumbers.get(i).getNumber());
				contact.officeNumbers.add(officeNumber);
				break;
			case Strings.PHONE_MOBILE_TYPE:
				MobileNumber mobileNumber = new MobileNumber();
				mobileNumber.putNumber(phoneNumbers.get(i).getNumber());
				contact.mobileNumbers.add(mobileNumber);
				break;
			default:
				break;
			}
		}
		return contact;
	}
	
	public static User getUser(Long userId) {
		DBUtils dbUtils = new DBUtils();
		
		Connection connection = dbUtils.getConnection();
		
		User user = new User();

		try {
			PreparedStatement getUser = connection.prepareStatement(Strings.DISPLAY_USER_QUERY);
			getUser.setLong(1, userId);
			
			ResultSet rs = getUser.executeQuery();

			if (rs.first()) {
				user.setId(rs.getLong(Strings.USER_KEY_ID));
				user.setFirstName(rs.getString(Strings.USER_KEY_FIRSTNAME));
				user.setLastName(rs.getString(Strings.USER_KEY_LASTNAME));
			}

			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<PhoneNumber> getPhoneNumbersOfUser(Long userId) {
		DBUtils dbUtils = new DBUtils();
		
		Connection connection = dbUtils.getConnection();
		
		ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
	
		PreparedStatement getPhoneNumbers;
		try {
			getPhoneNumbers = connection.prepareStatement(Strings.DISPLAY_USER_PHONE_NUMBERS);
			getPhoneNumbers.setLong(1, userId);
			ResultSet rs = getPhoneNumbers.executeQuery();
			
			while(rs.next()) {
				String phoneType = rs.getString(Strings.PHONE_KEY_TYPE);
				switch(phoneType) {
				case Strings.PHONE_OFFICE_TYPE:
					OfficeNumber officeNumber = new OfficeNumber();
					officeNumber.putNumber(rs.getLong(Strings.PHONE_KEY_NUMBER));
					phoneNumbers.add(officeNumber);
					break;
				case Strings.PHONE_MOBILE_TYPE:
					MobileNumber mobileNumber = new MobileNumber();
					mobileNumber.putNumber(rs.getLong(Strings.PHONE_KEY_NUMBER));
					phoneNumbers.add(mobileNumber);
					break;
				case Strings.PHONE_HOME_TYPE:
					HomeNumber homeNumber = new HomeNumber();
					homeNumber.putNumber(rs.getLong(Strings.PHONE_KEY_NUMBER));
					phoneNumbers.add(homeNumber);
					break;
				default:
					throw new Exception();
				}
			}
			return phoneNumbers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			 
		return null;
	}
	
	public static ArrayList<Email> getEmailsOfUser(Long userId) {
		DBUtils dbUtils = new DBUtils();
		
		Connection connection = dbUtils.getConnection();
		
		ArrayList<Email> emails = new ArrayList<>();
	
		PreparedStatement getEmails;
		try {
			getEmails = connection.prepareStatement(Strings.DISPLAY_USER_EMAILS);
			getEmails.setLong(1, userId);
			ResultSet rs = getEmails.executeQuery();
			
			while(rs.next()) {
					Email email = new Email();
					email.putAddress(rs.getString(Strings.EMAIL_KEY_MAIL));
					emails.add(email);
			}
			return emails;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			 
		return null;
	}

	public static void updatePhoneNumber(Long userId, PhoneNumber oldPhoneNumber, Long newPhoneNumber) {
		// TODO Auto-generated method stub
		DBUtils dbUtils = new DBUtils();

		Connection connection = dbUtils.getConnection();

		PreparedStatement updatePhoneNumber;
		try {
			updatePhoneNumber = connection.prepareStatement(Strings.UPDATE_USER_PHONE_QUERY);
			updatePhoneNumber.setLong(1, newPhoneNumber);
			updatePhoneNumber.setLong(2, userId);
			updatePhoneNumber.setString(3, oldPhoneNumber.getPhoneType());

			updatePhoneNumber.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateEmail(Long userId, Email email, String newEmail) {
		// TODO Auto-generated method stub
		DBUtils dbUtils = new DBUtils();

		Connection connection = dbUtils.getConnection();

		PreparedStatement updateEmail;
		try {
			updateEmail = connection.prepareStatement(Strings.UPDATE_USER_EMAIL_QUERY);
			updateEmail.setString(1, newEmail);
			updateEmail.setLong(2, userId);

			updateEmail.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
