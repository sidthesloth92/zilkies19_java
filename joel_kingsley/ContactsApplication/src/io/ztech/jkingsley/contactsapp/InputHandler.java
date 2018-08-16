package io.ztech.jkingsley.contactsapp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.contactsapp.beans.Contact;
import io.ztech.jkingsley.contactsapp.beans.Email;
import io.ztech.jkingsley.contactsapp.beans.HomeNumber;
import io.ztech.jkingsley.contactsapp.beans.MobileNumber;
import io.ztech.jkingsley.contactsapp.beans.OfficeNumber;
import io.ztech.jkingsley.contactsapp.beans.PhoneNumber;
import io.ztech.jkingsley.contactsapp.beans.User;
import io.ztech.jkingsley.contactsapp.constants.Fields;
import io.ztech.jkingsley.contactsapp.constants.Titles;
import io.ztech.jkingsley.contactsapp.constants.ValidationLibrary;
import io.ztech.jkingsley.contactsapp.services.DBLibrary;

public final class InputHandler {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	private static Scanner scanner = new Scanner(System.in);

	public static enum Update {
		firstName, lastName, phoneNum, emailID
	}

	public static Contact inputForAddContact() {

		scanner = new Scanner(System.in);
		Contact contact = new Contact();

		LOGGER.info(Titles.FIRST_NAME);
		String firstName = scanner.nextLine();
		contact.user.setFirstName(firstName);

		LOGGER.info(Titles.LAST_NAME);
		String lastName = scanner.nextLine();
		contact.user.setLastName(lastName);

		LOGGER.info(Titles.MOBILE_NUMBERS_LOOP);
		Long mobileNumber = -1L;
		do {
			mobileNumber = scanner.nextLong();
			if (ValidationLibrary.isValidMobileNumber(mobileNumber)) {
				MobileNumber mobile = new MobileNumber();
				mobile.putNumber(mobileNumber);
				contact.mobileNumbers.add(mobile);
			} else if (mobileNumber != -1) {
				LOGGER.info(Titles.INVALID_MOBILE_NUMBER);
			}
		} while (mobileNumber != -1);

		LOGGER.info(Titles.OFFICE_NUMBERS_LOOP);
		Long officeNumber = -1L;
		do {
			officeNumber = scanner.nextLong();
			if (ValidationLibrary.isValidOfficeNumber(officeNumber)) {
				OfficeNumber officeObj = new OfficeNumber();
				officeObj.putNumber(officeNumber);
				contact.officeNumbers.add(officeObj);
			} else if (officeNumber != -1) {
				LOGGER.info(Titles.INVALID_OFFICE_NUMBER);
			}
		} while (officeNumber != -1);

		LOGGER.info(Titles.HOME_NUMBERS_LOOP);
		Long homeNumber = -1L;
		do {
			homeNumber = scanner.nextLong();
			if (ValidationLibrary.isValidHomeNumber(homeNumber)) {
				HomeNumber homeObj = new HomeNumber();
				homeObj.putNumber(homeNumber);
				contact.homeNumbers.add(homeObj);
			} else if (homeNumber != -1) {
				LOGGER.info(Titles.INVALID_HOME_NUMBER);
			}
		} while (homeNumber != -1);

		scanner.nextLine();

		LOGGER.info(Titles.EMAIL_IDS_LOOP);
		String email = "";
		do {
			email = scanner.nextLine();
			if (ValidationLibrary.isValidEmail(email)) {
				Email emailObj = new Email();
				emailObj.putAddress(email);
				contact.emails.add(emailObj);
			} else if (!email.equals("-1")) {
				LOGGER.info(Titles.INVALID_EMAIL);
			}
		} while (!email.equals("-1"));

		return contact;
	}

	public static Long inputIdForUpdateContact() {
		ArrayList<User> users = DBLibrary.listUsers();
		for (int i = 0; i < users.size(); i++) {
			LOGGER.info(i + 1 + ": " + users.get(i).toString());
		}

		if (users.isEmpty()) {
			return -1L;
		}

		LOGGER.info(Titles.USER_OPTION_UPDATE);

		int n = 0;

		do {
			n = Integer.parseInt(scanner.nextLine());

		} while (n <= 0 || n > users.size());

		return users.get(n - 1).getId();

	}

	public static Long inputIdForDisplayContact() {
		ArrayList<User> users = DBLibrary.listUsers();
		for (int i = 0; i < users.size(); i++) {
			LOGGER.info(i + 1 + ": " + users.get(i).toString());
		}

		if (users.isEmpty()) {
			return -1L;
		}

		LOGGER.info(Titles.USER_OPTION_DISPLAY + "(1 - " + users.size() + ") :");

		int n = 0;

		do {
			if (scanner.hasNextInt()) {
				n = scanner.nextInt();
				scanner.nextLine();
			}
		} while (n <= 0 || n > users.size());

		return users.get(n - 1).getId();
	}

	public static int inputTypeOfUpdate() {
		LOGGER.info(Titles.WHAT_TO_UPDATE);
		LOGGER.info(Titles.FIRST_NAME_1);
		LOGGER.info(Titles.LAST_NAME_2);
		LOGGER.info(Titles.PHONE_NUMBER_3);
		LOGGER.info(Titles.EMAIL_ID_4);

		scanner = new Scanner(System.in);
		int n = 0;

		while (n < 1 || n > 4) {

			n = Integer.parseInt(scanner.nextLine());

		}

		return n;
	}

	public static String inputNewFirstName() {

		LOGGER.info(Titles.NEW_FIRST_NAME);
		String firstName = scanner.next();
		scanner.nextLine();

		return firstName;
	}

	public static String inputNewLastName() {

		LOGGER.info(Titles.NEW_LAST_NAME);
		String lastName = scanner.next();
		scanner.nextLine();

		return lastName;
	}

	public static int selectNumberToUpdate(int listSize) {
		int n = 0;

		while (n <= 0 || n > listSize) {
			LOGGER.info(Titles.OPTION_UPDATE + "(1 - " + listSize + "):");
			n = scanner.nextInt();
			scanner.nextLine();
			if (n <= 0 || n > listSize) {
				LOGGER.info(Titles.INVALID_OPTION);
			}
		}

		return n - 1;
	}

	public static Long inputNewPhoneNumber(PhoneNumber phoneNumber) {
		Long newPhoneNumber = 0L;

		switch (phoneNumber.getPhoneType()) {
		case Fields.PHONE_OFFICE_TYPE:

			do {
				LOGGER.info(Titles.NEW_OFFICE_NUMBER);
				newPhoneNumber = scanner.nextLong();
				if (!ValidationLibrary.isValidOfficeNumber(newPhoneNumber)) {
					LOGGER.info(Titles.INVALID_OFFICE_NUMBER);
				}
			} while (!ValidationLibrary.isValidOfficeNumber(newPhoneNumber));
			break;
		case Fields.PHONE_HOME_TYPE:

			do {
				LOGGER.info(Titles.NEW_HOME_NUMBER);
				newPhoneNumber = scanner.nextLong();
				if (!ValidationLibrary.isValidHomeNumber(newPhoneNumber)) {
					LOGGER.info(Titles.INVALID_HOME_NUMBER);
				}
			} while (!ValidationLibrary.isValidHomeNumber(newPhoneNumber));
			break;
		case Fields.PHONE_MOBILE_TYPE:
			do {
				LOGGER.info(Titles.NEW_MOBILE_NUMBER);
				newPhoneNumber = scanner.nextLong();
				if (!ValidationLibrary.isValidMobileNumber(newPhoneNumber)) {
					LOGGER.info(Titles.INVALID_MOBILE_NUMBER);
				}
			} while (!ValidationLibrary.isValidMobileNumber(newPhoneNumber));
			break;
		}

		return newPhoneNumber;
	}

	public static String inputNewEmail(Email email) {
		String newEmail = "";

		do {
			LOGGER.info(Titles.INVALID_EMAIL);
			newEmail = scanner.nextLine();
			if (!ValidationLibrary.isValidEmail(newEmail)) {
				LOGGER.info(Titles.INVALID_EMAIL);
			}
		} while (!ValidationLibrary.isValidEmail(newEmail));

		return newEmail;
	}

}