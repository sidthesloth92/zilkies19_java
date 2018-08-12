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
import io.ztech.jkingsley.contactsapp.constants.ValidationLibrary;
import io.ztech.jkingsley.contactsapp.services.DBLibrary;

public final class InputHandler {

	private static final Logger LOGGER = Logger.getLogger(UIClass.class.getName());
	private static Scanner scanner = new Scanner(System.in);

	public static enum Update {
		firstName, lastName, phoneNum, emailID
	}

	public static Contact inputForAddContact() {

		scanner = new Scanner(System.in);
		Contact contact = new Contact();

		LOGGER.info("Enter First Name:");
		String firstName = scanner.nextLine();
		contact.user.setFirstName(firstName);

		LOGGER.info("Enter Last Name:");
		String lastName = scanner.nextLine();
		contact.user.setLastName(lastName);

		LOGGER.info("Enter Mobile Numbers(Enter -1 to skip):");
		Long mobileNumber = -1L;
		do {
			mobileNumber = scanner.nextLong();
			if (ValidationLibrary.isValidMobileNumber(mobileNumber)) {
				MobileNumber mobile = new MobileNumber();
				mobile.putNumber(mobileNumber);
				contact.mobileNumbers.add(mobile);
			} else if (mobileNumber != -1) {
				LOGGER.info("Not a valid mobile number");
			}
		} while (mobileNumber != -1);

		LOGGER.info("Enter Office Numbers(Enter -1 to skip):");
		Long officeNumber = -1L;
		do {
			officeNumber = scanner.nextLong();
			if (ValidationLibrary.isValidOfficeNumber(officeNumber)) {
				OfficeNumber officeObj = new OfficeNumber();
				officeObj.putNumber(officeNumber);
				contact.officeNumbers.add(officeObj);
			} else if (officeNumber != -1) {
				LOGGER.info("Not a valid office number");
			}
		} while (officeNumber != -1);

		LOGGER.info("Enter Home Numbers(Enter -1 to skip):");
		Long homeNumber = -1L;
		do {
			homeNumber = scanner.nextLong();
			if (ValidationLibrary.isValidHomeNumber(homeNumber)) {
				HomeNumber homeObj = new HomeNumber();
				homeObj.putNumber(homeNumber);
				contact.homeNumbers.add(homeObj);
			} else if (homeNumber != -1) {
				LOGGER.info("Not a valid home number");
			}
		} while (homeNumber != -1);

		scanner.nextLine();

		LOGGER.info("Enter Email Ids(Enter -1 to skip):");
		String email = "";
		do {
			email = scanner.nextLine();
			if (ValidationLibrary.isValidEmail(email)) {
				Email emailObj = new Email();
				emailObj.putAddress(email);
				contact.emails.add(emailObj);
			} else if (!email.equals("-1")) {
				LOGGER.info("Not a valid email Id");
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

		LOGGER.info("Enter User number to update:");

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

		LOGGER.info("Enter User number to display(1 - " + users.size() + ") :");

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
		LOGGER.info("What do you want to update?");
		LOGGER.info("1. First Name");
		LOGGER.info("2. Last Name");
		LOGGER.info("3. Phone Number");
		LOGGER.info("4. Email ID");

		scanner = new Scanner(System.in);
		int n = 0;

		while (n < 1 || n > 4) {

			n = Integer.parseInt(scanner.nextLine());

		}

		return n;
	}

	public static String inputNewFirstName() {

		LOGGER.info("Enter new first name:");
		String firstName = scanner.next();
		scanner.nextLine();

		return firstName;
	}

	public static String inputNewLastName() {

		LOGGER.info("Enter new last name:");
		String lastName = scanner.next();
		scanner.nextLine();

		return lastName;
	}

	public static int selectNumberToUpdate(int listSize) {
		// TODO Auto-generated method stub
		int n = 0;

		while (n <= 0 || n > listSize) {
			LOGGER.info("Enter option to update(1 - " + listSize + "):");
			n = scanner.nextInt();
			scanner.nextLine();
			if (n <= 0 || n > listSize) {
				LOGGER.info("Enter a valid option");
			}
		}

		return n - 1;
	}

	public static Long inputNewPhoneNumber(PhoneNumber phoneNumber) {
		// TODO Auto-generated method stub
		Long newPhoneNumber = 0L;

		switch (phoneNumber.getPhoneType()) {
		case Fields.PHONE_OFFICE_TYPE:

			do {
				LOGGER.info("Enter new office number:");
				newPhoneNumber = scanner.nextLong();
				if (!ValidationLibrary.isValidOfficeNumber(newPhoneNumber)) {
					LOGGER.info("Enter a valid office number");
				}
			} while (!ValidationLibrary.isValidOfficeNumber(newPhoneNumber));
			break;
		case Fields.PHONE_HOME_TYPE:

			do {
				LOGGER.info("Enter new home number:");
				newPhoneNumber = scanner.nextLong();
				if (!ValidationLibrary.isValidHomeNumber(newPhoneNumber)) {
					LOGGER.info("Enter a valid home number");
				}
			} while (!ValidationLibrary.isValidHomeNumber(newPhoneNumber));
			break;
		case Fields.PHONE_MOBILE_TYPE:
			do {
				LOGGER.info("Enter new mobile number:");
				newPhoneNumber = scanner.nextLong();
				if (!ValidationLibrary.isValidMobileNumber(newPhoneNumber)) {
					LOGGER.info("Enter a valid mobile number");
				}
			} while (!ValidationLibrary.isValidMobileNumber(newPhoneNumber));
			break;
		}

		return newPhoneNumber;
	}

	public static String inputNewEmail(Email email) {
		// TODO Auto-generated method stub
		String newEmail = "";

		do {
			LOGGER.info("Enter new Email Id:");
			newEmail = scanner.nextLine();
			if (!ValidationLibrary.isValidEmail(newEmail)) {
				LOGGER.info("Enter a valid Email Id");
			}
		} while (!ValidationLibrary.isValidEmail(newEmail));

		return newEmail;
	}

}