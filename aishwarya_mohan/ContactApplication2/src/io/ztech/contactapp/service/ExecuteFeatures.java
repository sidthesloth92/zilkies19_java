package io.ztech.contactapp.service;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.beans.Contact;
import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.dao.CheckDao;
import io.ztech.contactapp.dao.DisplayDao;
import io.ztech.contactapp.dao.SortDao;
import io.ztech.contactapp.dao.UploadDao;

public class ExecuteFeatures {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	private static final ContactObjectManipulation obj = new ContactObjectManipulation();

	public void createContact() {
		Contact newContact = obj.getContactDetailsFromUser();

		if (newContact != null) {
			logger.info(ApplicationStringConstants.SAVE_CHOICE);
			char saveChoice = sc.next().charAt(0);
			boolean flag = true;
			if (saveChoice == 'y') {
				flag = saveContact(newContact);
			}
			if (flag) {
				logger.info(ApplicationStringConstants.DONE_MSG);
			} else {
				logger.info(ApplicationStringConstants.NOTDONE_MSG);
			}
		} else {
			logger.info(ApplicationStringConstants.NOTDONE_MSG);
		}
	}

	boolean saveContact(Contact newContact) {
		try {
			new UploadDao().uploadContactToDB(newContact);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void updateContact() {
		String firstName;
		int con_id;

		try {
			logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
			firstName = sc.next();

			new DisplayDao().showContacts(firstName);
			logger.info(ApplicationStringConstants.CONTACT_ID_INPUT);
			con_id = sc.nextInt();

			new DisplayDao().displayContact(con_id);

			boolean flag = obj.addDetails(con_id);
			if (flag) {
				logger.info(ApplicationStringConstants.DONE_MSG);
			} else {
				logger.info(ApplicationStringConstants.NOTDONE_MSG);
			}
		} catch (Exception e) {
			logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
		}

	}

	public void editContact() {
		String firstName;
		int con_id;

		try {
			logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
			firstName = sc.next();

			new DisplayDao().showContacts(firstName);
			logger.info(ApplicationStringConstants.CONTACT_ID_INPUT);
			con_id = sc.nextInt();

			new DisplayDao().displayContact(con_id);

			boolean flag = obj.editDetails(con_id);
			if (flag) {
				logger.info(ApplicationStringConstants.DONE_MSG);
			} else {
				logger.info(ApplicationStringConstants.NOTDONE_MSG);
			}
		} catch (Exception e) {
			logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
		}
	}

	public void searchContact() {
		String firstName;
		int con_id;

		try {
			logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
			firstName = sc.next();

			new DisplayDao().showContacts(firstName);
			if (new CheckDao().checkIfExists(firstName)) {
				logger.info(ApplicationStringConstants.CONTACT_ID_INPUT);
				con_id = sc.nextInt();

				new DisplayDao().displayContact(con_id);
			} else {
				logger.info(ApplicationStringConstants.NOT_FOUND);
			}

		} catch (Exception e) {
			logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
		}

	}

	public void displayContact() {
		try {
			logger.info(ApplicationStringConstants.DISPLAY_BASIS);
			int choice = sc.nextInt();

			new SortDao().sort(choice);
		} catch (Exception e) {
			logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
		}
	}

}
