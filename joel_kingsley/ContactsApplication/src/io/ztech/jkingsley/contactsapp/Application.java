package io.ztech.jkingsley.contactsapp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.contactsapp.beans.Contact;
import io.ztech.jkingsley.contactsapp.beans.Email;
import io.ztech.jkingsley.contactsapp.beans.PhoneNumber;
import io.ztech.jkingsley.contactsapp.constants.Titles;
import io.ztech.jkingsley.contactsapp.dao.EmailDAO;
import io.ztech.jkingsley.contactsapp.dao.PhoneDAO;
import io.ztech.jkingsley.contactsapp.services.DBLibrary;

public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		int choice = 0;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			LOGGER.info("\n------");
			LOGGER.info(Titles.MENU);
			LOGGER.info(Titles.ADD_CONTACT_1);
			LOGGER.info(Titles.UPDATE_CONTACT_2);
			LOGGER.info(Titles.DISPLAY_CONTACT_3);
			LOGGER.info(Titles.OTHER_NUMBER_EXIT);

			while (!scanner.hasNext()) {
				//DO NOTHING
			}

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				Contact contact = InputHandler.inputForAddContact();
				DBLibrary.addContact(contact);
				break;

			case 2:
				Long userId = InputHandler.inputIdForUpdateContact();

				if (userId == -1L) {
					LOGGER.info(Titles.NO_USERS_UPDATE);
					break;
				}

				LOGGER.info("Selected user ID is " + userId);

				int updateType = InputHandler.inputTypeOfUpdate();

				switch (updateType) {
				case 1:
					String firstName = InputHandler.inputNewFirstName();
					DBLibrary.updateFirstName(userId, firstName);
					LOGGER.info(Titles.FIRST_NAME_UPDATED);
					break;
				case 2:
					String lastName = InputHandler.inputNewLastName();
					DBLibrary.updateLastName(userId, lastName);
					LOGGER.info(Titles.LAST_NAME_UPDATED);
					break;
				case 3:
					ArrayList<PhoneNumber> phoneNumbers = DBLibrary.getPhoneNumbersOfUser(userId);
					for (int i = 0; i < phoneNumbers.size(); i++) {
						LOGGER.info(i + 1 + ". " + phoneNumbers.get(i).toString());
					}
					int numberSelected = InputHandler.selectNumberToUpdate(phoneNumbers.size());

					PhoneNumber oldPhoneNumber = phoneNumbers.get(numberSelected);

					PhoneNumber newPhoneNumber = new PhoneNumber();
					newPhoneNumber.putNumber(InputHandler.inputNewPhoneNumber(phoneNumbers.get(numberSelected)));
					newPhoneNumber.setPhoneType(phoneNumbers.get(numberSelected).getPhoneType());

					PhoneDAO editPhone = new PhoneDAO();
					editPhone.setOldPhoneNumber(oldPhoneNumber);
					editPhone.setNewPhoneNumber(newPhoneNumber);
					editPhone.setUserId(userId);

					DBLibrary.updatePhoneNumber(editPhone);
					LOGGER.info(Titles.PHONE_NUMBER_UPDATED);
					break;
				case 4:
					ArrayList<Email> emails = DBLibrary.getEmailsOfUser(userId);
					for (int i = 0; i < emails.size(); i++) {
						LOGGER.info(i + 1 + ". " + emails.get(i).getAddress());
					}
					int numberSelected1 = InputHandler.selectNumberToUpdate(emails.size());
					Email newEmail = new Email();
					newEmail.putAddress(InputHandler.inputNewEmail(emails.get(numberSelected1)));

					EmailDAO editEmail = new EmailDAO();
					editEmail.setOldEmail(emails.get(numberSelected1));
					editEmail.setNewEmail(newEmail);
					editEmail.setUserId(userId);

					DBLibrary.updateEmail(editEmail);
					LOGGER.info(Titles.EMAIL_UPDATED);
					break;
				}

				break;

			case 3:
				Long userId1 = InputHandler.inputIdForDisplayContact();

				if (userId1 == -1L) {
					LOGGER.info(Titles.NO_USERS_DISPLAY);
					break;
				}

				LOGGER.info("Selected user ID is " + userId1);
				Contact contact1 = DBLibrary.getContact(userId1);
				LOGGER.info("Name: " + contact1.user.getFirstName() + " " + contact1.user.getLastName());
				LOGGER.info("Home Numbers:");
				for (int i = 0; i < contact1.homeNumbers.size(); i++) {
					System.out.print(contact1.homeNumbers.get(i).getNumber().toString());
					if (i < contact1.homeNumbers.size() - 1) {
						System.out.print(", ");
					}
				}
				LOGGER.info("");
				LOGGER.info("Mobile Numbers:");
				for (int i = 0; i < contact1.mobileNumbers.size(); i++) {
					System.out.print(contact1.mobileNumbers.get(i).getNumber().toString());
					if (i < contact1.mobileNumbers.size() - 1) {
						System.out.print(", ");
					}
				}
				LOGGER.info("");
				LOGGER.info("Office Numbers:");
				for (int i = 0; i < contact1.officeNumbers.size(); i++) {
					System.out.print(contact1.officeNumbers.get(i).getNumber().toString());
					if (i < contact1.officeNumbers.size() - 1) {
						System.out.print(", ");
					}
				}
				LOGGER.info("");
				System.out.print("Email:");
				for (int i = 0; i < contact1.emails.size(); i++) {
					System.out.print(contact1.emails.get(i).getAddress());
					if (i < contact1.emails.size() - 1) {
						System.out.print(", ");
					}
				}

				break;
			default:
				scanner.close();
				LOGGER.info(Titles.EXITTING);
				return;
			}

		}
	}

}
