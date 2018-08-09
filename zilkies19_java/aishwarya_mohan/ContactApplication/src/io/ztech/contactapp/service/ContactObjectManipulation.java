package io.ztech.contactapp.service;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.beans.Contact;
import io.ztech.contactapp.beans.ContactObjectStorage;
import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.dao.DBUsage;

public class ContactObjectManipulation {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public Contact getContactDetailsFromUser() {
		String firstname, lastname, countryCodeMobile, mobileNumber, extNum, officeNumber, countryCodeHome, areaCode,
				homeNumber, mailID;

		int fieldChoice;

		logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
		firstname = sc.next();
		logger.info(ApplicationStringConstants.LAST_NAME_INPUT);
		lastname = sc.next();

		Contact newContact = new Contact(firstname, lastname);

		do {
			logger.info(ApplicationStringConstants.FIELD_MENU);
			logger.info(ApplicationStringConstants.ADD_DETAIL_CHOICE);
			fieldChoice = sc.nextInt();

			switch (fieldChoice) {
			case 1:// mobile
				logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
				countryCodeMobile = sc.next();
				logger.info(ApplicationStringConstants.MOBILE_NUMBER_INPUT);
				mobileNumber = sc.next();

				newContact.addMobileNumber(countryCodeMobile, mobileNumber);

				break;
			case 2:// office
				logger.info(ApplicationStringConstants.EXT_NUM_INPUT);
				extNum = sc.next();
				logger.info(ApplicationStringConstants.OFFICE_NUM_INPUT);
				officeNumber = sc.next();

				newContact.addOfficeNumber(extNum, officeNumber);

				break;
			case 3:// home
				logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
				countryCodeHome = sc.next();
				logger.info(ApplicationStringConstants.AREA_CODE_INPUT);
				areaCode = sc.next();
				logger.info(ApplicationStringConstants.HOME_NUM_INPUT);
				homeNumber = sc.next();

				newContact.addHomeNumber(countryCodeHome, areaCode, homeNumber);

				break;
			case 4:// email
				logger.info(ApplicationStringConstants.EMAIL_INPUT);
				mailID = sc.next();

				newContact.setEmailAddress(mailID);

				break;

			default: // invalid input
				logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);

			}

			logger.info(ApplicationStringConstants.CONTINUE_CHOICE);

		} while (sc.next().charAt(0) == 'y');

		return newContact;
	}

	public void addDetails(int con_id) { // for update
		String countryCodeMobile, mobileNumber, extNum, officeNumber, countryCodeHome, areaCode, homeNumber, mailID;

		int fieldChoice;

		Contact existingContact = ContactObjectStorage.retrieveContact(con_id);
		DBUsage dbobj = new DBUsage();

		do {
			logger.info(ApplicationStringConstants.FIELD_MENU);
			logger.info(ApplicationStringConstants.ADD_DETAIL_CHOICE);
			fieldChoice = sc.nextInt();

			switch (fieldChoice) {
			case 1:// mobile
				logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
				countryCodeMobile = sc.next();
				logger.info(ApplicationStringConstants.MOBILE_NUMBER_INPUT);
				mobileNumber = sc.next();

				if (UserInput.chooseToSaveChanges()) {
					existingContact.addMobileNumber(countryCodeMobile, mobileNumber);
					dbobj.uploadMobileToDB(countryCodeMobile, mobileNumber, con_id);
				}

				break;
			case 2:// office
				logger.info(ApplicationStringConstants.EXT_NUM_INPUT);
				extNum = sc.next();
				logger.info(ApplicationStringConstants.OFFICE_NUM_INPUT);
				officeNumber = sc.next();

				if (UserInput.chooseToSaveChanges()) {
					existingContact.addOfficeNumber(extNum, officeNumber);
					dbobj.uploadOfficeToDB(extNum, officeNumber, con_id);
				}
				break;
			case 3:// home
				logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
				countryCodeHome = sc.next();
				logger.info(ApplicationStringConstants.AREA_CODE_INPUT);
				areaCode = sc.next();
				logger.info(ApplicationStringConstants.HOME_NUM_INPUT);
				homeNumber = sc.next();

				if (UserInput.chooseToSaveChanges()) {
					existingContact.addHomeNumber(countryCodeHome, areaCode, homeNumber);
					dbobj.uploadHomeToDB(countryCodeHome, areaCode, homeNumber, con_id);
				}
				break;
			case 4:// email
				logger.info(ApplicationStringConstants.EMAIL_INPUT);
				mailID = sc.next();
				if (UserInput.chooseToSaveChanges()) {
					existingContact.setEmailAddress(mailID);
					dbobj.uploadEmailToDB(mailID, con_id);
				}
				break;
			}

			logger.info(ApplicationStringConstants.CONTINUE_CHOICE);

		} while (sc.next().charAt(0) == 'y');

	}

	public void editDetails(int con_id) { // for update
		String countryCodeMobile, mobileNumber, extNum, officeNumber, countryCodeHome, areaCode, homeNumber, mailID;

		int fieldChoice, mob_id, office_id, email_id_num, home_id;

		DBUsage dbobj = new DBUsage();

		do {
			logger.info(ApplicationStringConstants.FIELD_MENU);
			logger.info(ApplicationStringConstants.EDIT_DETAIL_CHOICE);
			fieldChoice = sc.nextInt();

			switch (fieldChoice) {
			case 1:// mobile
				logger.info(ApplicationStringConstants.MOBILE_ID_INPUT);
				mob_id = sc.nextInt();

				logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
				countryCodeMobile = sc.next();
				logger.info(ApplicationStringConstants.MOBILE_NUMBER_INPUT);
				mobileNumber = sc.next();

				if (UserInput.chooseToSaveChanges()) {
					dbobj.alterMobileInDB(countryCodeMobile, mobileNumber, con_id, mob_id);
				}

				break;
			case 2:// office
				logger.info(ApplicationStringConstants.OFFICE_ID_INPUT);
				office_id = sc.nextInt();

				logger.info(ApplicationStringConstants.EXT_NUM_INPUT);
				extNum = sc.next();
				logger.info(ApplicationStringConstants.OFFICE_NUM_INPUT);
				officeNumber = sc.next();

				if (UserInput.chooseToSaveChanges()) {
					dbobj.alterOfficeInDB(extNum, officeNumber, con_id, office_id);
				}

				break;
			case 3:// home
				logger.info(ApplicationStringConstants.HOME_ID_INPUT);
				home_id = sc.nextInt();

				logger.info(ApplicationStringConstants.COUNTRY_CODE_INPUT);
				countryCodeHome = sc.next();
				logger.info(ApplicationStringConstants.AREA_CODE_INPUT);
				areaCode = sc.next();
				logger.info(ApplicationStringConstants.HOME_NUM_INPUT);
				homeNumber = sc.next();

				if (UserInput.chooseToSaveChanges()) {
					dbobj.alterHomeInDB(countryCodeHome, areaCode, homeNumber, con_id, home_id);
				}

				break;
			case 4:// email
				logger.info(ApplicationStringConstants.EMAIL_ID_NUM_INPUT);
				email_id_num = sc.nextInt();

				logger.info(ApplicationStringConstants.EMAIL_INPUT);
				mailID = sc.next();

				if (UserInput.chooseToSaveChanges()) {
					dbobj.alterEmailInDB(mailID, con_id, email_id_num);
				}

				break;
			}

			logger.info(ApplicationStringConstants.CONTINUE_CHOICE);

		} while (sc.next().charAt(0) == 'y');

	}
}
