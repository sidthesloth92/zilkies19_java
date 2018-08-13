package io.zilker.contact.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.contact.bean.Person;
import io.zilker.contact.constants.Applicationconstants;
import io.zilker.contact.constants.SqlConstants;

public class AddPerson {
	public static Scanner scanner = new Scanner(System.in);
	private static int choice, option, li;
	private static String firstName, emailInput, lastName, mobileNumber, countryCode, homeNumberInput,
			homeCountryCodeInput, areaCodeInput, officeNumberInput, extensionInput;
	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static Person newPerson = new Person();

	// public static ResultSet resultSet;
	public static Logger log = Logger.getLogger(AddPerson.class.getName());

	// add Personal details
	public static void addPersonal(Connection connection) {
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.INSERT_NAMES);
			preparedStatement.setString(1, newPerson.getFirstName());
			preparedStatement.setString(2, newPerson.getFirstName());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
		}

	}

	// add Mobile details
	public static void addMobileWithoutCollections(int lid, Connection connection) {
		try {
			// till 1 add mobile

			do {
				log.info(Applicationconstants.DECIDER);
				option = scanner.nextInt();
				if (option == 1) {
					log.info(Applicationconstants.ENTER_MOBILE_NUMBER);
					mobileNumber = scanner.next();
					if (!Validator.validateMobile(mobileNumber)) {
						log.info("Not valid");
						return;
					} else {
						log.info(Applicationconstants.ENTER_COUNTRY_CODE);
						countryCode = scanner.next();
						preparedStatement = connection.prepareStatement(SqlConstants.INSERT_MOBILE);
						preparedStatement.setInt(1, lid);
						preparedStatement.setString(2, mobileNumber);
						preparedStatement.setString(3, countryCode);
						preparedStatement.executeUpdate();
					}
				}
			} while (option == 1);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}

	// add Mail Ids
	public static void addMail(int lid, Connection connection) {
		try {
			// till 1 add Mail
			ArrayList<String> email = new ArrayList<String>();

			do {
				log.info(Applicationconstants.DECIDER);
				option = scanner.nextInt();
				if (option == 1) {
					log.info("Enter email");
					emailInput = scanner.next();
					boolean checkMail = Validator.validateEmail(emailInput);
					if (checkMail) {
						email.add(emailInput);
					} else {
						log.info(Applicationconstants.WRONG_MAIL);
					}
				}
			} while (option == 1);
			newPerson.setEmail(email);
			email = newPerson.getEmail();
			for (int i = 0; i < email.size(); i++) {
				preparedStatement = connection.prepareStatement(SqlConstants.INSERT_EMAIL);
				preparedStatement.setInt(1, lid);
				preparedStatement.setString(2, email.get(i));
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			log.info(e.toString());
		}

	}

	// collections test
	public static void addMobile(int lid, Connection connection) throws SQLException {
		try {
			ArrayList<String> mobileNumber = new ArrayList<String>();
			ArrayList<String> mobileCountryCode = new ArrayList<String>();
			do {
				log.info(Applicationconstants.DECIDER);
				option = scanner.nextInt();
				if (option == 1) {
					log.info(Applicationconstants.ENTER_MOBILE_NUMBER);
					String mobileFiresultSett = scanner.next();
					if (mobileFiresultSett.length() != 10) {
						log.info(Applicationconstants.WRONG_MOBILE);
						continue;
					}
					mobileNumber.add(mobileFiresultSett);
					log.info(Applicationconstants.ENTER_COUNTRY_CODE);
					mobileCountryCode.add(scanner.next());
				}
			} while (option == 1);
			newPerson.setMobile(mobileNumber, mobileCountryCode);
			mobileNumber = newPerson.getMobileNumber();
			mobileCountryCode = newPerson.getMobileCountryCode();
			for (int i = 0; i < mobileNumber.size(); i++) {
				preparedStatement = connection.prepareStatement(SqlConstants.INSERT_MOBILE);
				preparedStatement.setInt(1, lid);
				preparedStatement.setString(2, mobileNumber.get(i));
				preparedStatement.setString(3, mobileCountryCode.get(i));
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			log.info(e.toString());
		}
		finally {
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			
		}

	}

	// add Home NumberesultSet
	public static void addHome(int lid, Connection connection) {
		try {
			ArrayList<String> homeNumber = new ArrayList<String>();
			ArrayList<String> homeCountryCode = new ArrayList<String>();
			ArrayList<String> areaCode = new ArrayList<String>();
			do {
				log.info(Applicationconstants.DECIDER);
				option = scanner.nextInt();
				if (option == 1) {
					log.info(Applicationconstants.ENTER_HOME_NUMBER);
					homeNumberInput = scanner.next();
					homeNumber.add(homeNumberInput);
					log.info(Applicationconstants.ENTER_COUNTRY_CODE);
					homeCountryCodeInput = scanner.next();
					homeCountryCode.add(homeCountryCodeInput);
					log.info(Applicationconstants.ENTER_AREA_CODE);
					areaCodeInput = scanner.next();
					areaCode.add(areaCodeInput);
				}
			} while (option == 1);
			newPerson.setHome(homeNumber, homeCountryCode, areaCode);
			homeNumber = newPerson.getHomeNumber();
			homeCountryCode = newPerson.getHomeCountryCode();
			areaCode = newPerson.getAreaCode();
			for (int i = 0; i < homeNumber.size(); i++) {
				preparedStatement = connection.prepareStatement(SqlConstants.INSERT_HOME);
				preparedStatement.setInt(1, li);
				preparedStatement.setString(2, homeCountryCode.get(i));
				preparedStatement.setString(3, homeNumber.get(i));
				preparedStatement.setString(4, areaCode.get(i));
				preparedStatement.executeUpdate();
			}

		} catch (Exception e) {
			log.info(e.toString());
		}

	}

	// add office NumberesultSet
	public static void addOffice(int lid, Connection connection) {
		try {
			ArrayList<String> officeNumber = new ArrayList<String>();
			ArrayList<String> extension = new ArrayList<String>();
			do {
				log.info(Applicationconstants.OFFICE_OPTIONS);
				option = scanner.nextInt();
				if (option == 1) {
					log.info(Applicationconstants.ENTER_OFFICE);
					officeNumberInput = scanner.next();
					officeNumber.add(officeNumberInput);
					log.info(Applicationconstants.ENTER_EXTENSION);
					extensionInput = scanner.next();
					extension.add(extensionInput);					
				}

			} while (option == 1);
			newPerson.setOffice(officeNumber, extension);
			officeNumber = newPerson.getOfficeNumber();
			extension = newPerson.getExtension();
			for(int i=0;i<officeNumber.size();i++) {
				preparedStatement = connection.prepareStatement(SqlConstants.INSERT_OFFICE);
				preparedStatement.setInt(1, li);
				preparedStatement.setString(2, officeNumber.get(i));
				preparedStatement.setString(3, extension.get(i));
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			log.info(e.toString());
		}

	}

	// existing user
	public static void addOnExists(int li, Connection connection) throws SQLException {
		log.info(Applicationconstants.ADD_ON_EXISTS);
		option = scanner.nextInt();
		switch (option) {
		case 1:
			addMobile(li, connection);
			break;
		case 2:
			addMail(li, connection);
			break;
		case 3:
			addHome(li, connection);
			break;
		case 4:
			addOffice(li, connection);
			break;
		default:
			break;
		}
	}

	// add Person main
	public static void addMain(Connection connection) {
		try {
			{
				choice = 0;
			}
			log.info(Applicationconstants.FIRST_NAME_ENTER);
			firstName = scanner.next();
			log.info(Applicationconstants.LAST_NAME_ENTER);
			lastName = scanner.next();
			newPerson.setNames(firstName, lastName);

			connection.setAutoCommit(false);
			if (CheckExists.checkExists(firstName, lastName, connection)) {
				// already exists
				// select option
				log.info(Applicationconstants.ADD_USER_EXISTS);
				choice = scanner.nextInt();
				if (choice == 2) {
					ListPersons.displayUsers(firstName, lastName, connection);
					log.info(Applicationconstants.ENTER_ID);
					// prompt to enter id
					option = scanner.nextInt();
					// for update user
					addOnExists(option, connection);
				}
			}
			if (choice != 2) {
				addPersonal(connection);
				li = LastInserted.lastInsertedId(connection);
				addMobile(li, connection);
				addMail(li, connection);
				addHome(li, connection);
				addOffice(li, connection);
			}
			connection.commit();

		} catch (Exception e) {
			log.info(e.toString());
		} 
	}

	// main function
	/*
	 * public static void main(String [] args) { try {
	 * Class.forName("com.mysql.jdbc.Driver"); Connection connection =
	 * DbUtils.getConnection(); addMobile(19,connection); } catch(Exception e) {
	 * log.info(e.toString()); } }
	 */

}
