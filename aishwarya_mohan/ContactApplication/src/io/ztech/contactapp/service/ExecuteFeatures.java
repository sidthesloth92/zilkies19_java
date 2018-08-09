package io.ztech.contactapp.service;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.beans.Contact;
import io.ztech.contactapp.beans.ContactObjectStorage;
import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.dao.DBUsage;

public class ExecuteFeatures {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	private static final ContactObjectManipulation obj = new ContactObjectManipulation();
	private static final DBUsage dbobj = new DBUsage();

	public void createContact() {

		Contact newContact = obj.getContactDetailsFromUser();

		logger.info(ApplicationStringConstants.SAVE_CHOICE);
		char saveChoice = sc.next().charAt(0);
		if (saveChoice == 'y') {
			saveContact(newContact);
		}
	}

	void saveContact(Contact newContact) {
		ContactObjectStorage.addContact(newContact);
		dbobj.uploadContactToDB(newContact);
	}

	public void updateContact() {
		String firstName;
		int con_id;

		logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
		firstName = sc.next();

		dbobj.showContacts(firstName);
		logger.info(ApplicationStringConstants.CONTACT_ID_INPUT);
		con_id = sc.nextInt();

		dbobj.displayContact(con_id);

		obj.addDetails(con_id);

	}

	public void editContact() {
		String firstName;
		int con_id;

		logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
		firstName = sc.next();

		dbobj.showContacts(firstName);
		logger.info(ApplicationStringConstants.CONTACT_ID_INPUT);
		con_id = sc.nextInt();

		dbobj.displayContact(con_id);

		obj.editDetails(con_id);
	}

	public void searchContact() {
		String firstName;
		int con_id;

		logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
		firstName = sc.next();

		dbobj.showContacts(firstName);
		logger.info(ApplicationStringConstants.CONTACT_ID_INPUT);
		con_id = sc.nextInt();

		dbobj.displayContact(con_id);

	}

	public void displayContact() {
		logger.info(ApplicationStringConstants.DISPLAY_BASIS);
		int choice = sc.nextInt();

		dbobj.sort(choice);
	}

}
