package io.ztech.contactapp.service;

import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contactapp.DriverClass;
import io.ztech.contactapp.beans.Contact;
import io.ztech.contactapp.beans.HomeNumber;
import io.ztech.contactapp.beans.MobileNumber;
import io.ztech.contactapp.beans.OfficeNumber;
import io.ztech.contactapp.constants.ApplicationStringConstants;
import io.ztech.contactapp.dao.AlterDao;
import io.ztech.contactapp.dao.UploadDao;

public class ContactObjectManipulation {
	private static final Logger logger = Logger.getLogger(DriverClass.class.getName());
	private static final Scanner sc = new Scanner(System.in);

	public Contact getContactDetailsFromUser() {
		String firstname, lastname;

		int fieldChoice;

		Contact newContact = null;
		try {
			logger.info(ApplicationStringConstants.FIRST_NAME_INPUT);
			firstname = sc.next();
			logger.info(ApplicationStringConstants.LAST_NAME_INPUT);
			lastname = sc.next();

			newContact = new Contact(firstname, lastname);

			do {
				logger.info(ApplicationStringConstants.FIELD_MENU);
				logger.info(ApplicationStringConstants.ADD_DETAIL_CHOICE);
				fieldChoice = sc.nextInt();

				switch (fieldChoice) {
				case 1:// mobile
					MobileNumber m = UserInput.getMobileFromUser();
					if (m != null) {
						newContact.setMobileNumber(m);
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
						return null;
					}
					break;

				case 2:// office
					OfficeNumber o = UserInput.getOfficeFromUser();
					if (o != null) {
						newContact.setOfficeNumber(o);
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
						return null;
					}
					break;

				case 3:// home
					HomeNumber h = UserInput.getHomeFromUser();
					if (h != null) {
						newContact.setHomeNumber(h);
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
						return null;
					}
					break;

				case 4:// email
					String mail = UserInput.getEmailFromUser();
					if (mail != null) {
						newContact.setEmailAddress(mail);
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}
					break;

				default: // invalid input
					logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
				}

				logger.info(ApplicationStringConstants.CONTINUE_CHOICE);

			} while (sc.next().charAt(0) == 'y');

		} catch (Exception e) {
			logger.info(ApplicationStringConstants.NOTDONE_MSG);
			return null;
		}
		return newContact;
	}

	public boolean addDetails(int conId) { // for update
		int fieldChoice;
		UploadDao dbobj = new UploadDao();

		do {
			try {
				logger.info(ApplicationStringConstants.FIELD_MENU);
				logger.info(ApplicationStringConstants.ADD_DETAIL_CHOICE);
				fieldChoice = sc.nextInt();

				switch (fieldChoice) {
				case 1:// mobile
					MobileNumber m = UserInput.getMobileFromUser();
					if (m != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.uploadToDB(m, conId);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}

					break;
				case 2:// office
					OfficeNumber o = UserInput.getOfficeFromUser();
					if (o != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.uploadToDB(o, conId);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}

					break;
				case 3:// home
					HomeNumber h = UserInput.getHomeFromUser();
					if (h != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.uploadToDB(h, conId);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}

					break;
				case 4:// email
					String mailID = UserInput.getEmailFromUser();
					if (mailID != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.uploadToDB(mailID, conId);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}
					break;

				default: // invalid input
					logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
				}

				logger.info(ApplicationStringConstants.CONTINUE_CHOICE);

			} catch (Exception e) {
				logger.info(ApplicationStringConstants.NOTDONE_MSG);
				return false;
			}
		} while (sc.next().charAt(0) == 'y');
		return true;

	}

	public boolean editDetails(int conId) { // for adding details edit
		int fieldChoice, mob_id, office_id, email_id_num, home_id;
		AlterDao dbobj = new AlterDao();

		do {
			try {
				logger.info(ApplicationStringConstants.FIELD_MENU);
				logger.info(ApplicationStringConstants.EDIT_DETAIL_CHOICE);
				fieldChoice = sc.nextInt();

				switch (fieldChoice) {
				case 1:// mobile
					logger.info(ApplicationStringConstants.MOBILE_ID_INPUT);
					mob_id = sc.nextInt();

					MobileNumber m = UserInput.getMobileFromUser();
					if (m != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.alterMobileInDB(m, conId, mob_id);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}

					break;
				case 2:// office
					logger.info(ApplicationStringConstants.OFFICE_ID_INPUT);
					office_id = sc.nextInt();

					OfficeNumber o = UserInput.getOfficeFromUser();

					if (o != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.alterOfficeInDB(o, conId, office_id);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}

					break;
				case 3:// home
					logger.info(ApplicationStringConstants.HOME_ID_INPUT);
					home_id = sc.nextInt();

					HomeNumber h = UserInput.getHomeFromUser();
					if (h != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.alterHomeInDB(h, conId, home_id);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}

					break;
				case 4:// email
					logger.info(ApplicationStringConstants.EMAIL_ID_NUM_INPUT);
					email_id_num = sc.nextInt();

					String mailID = UserInput.getEmailFromUser();
					if (mailID != null) {
						if (UserInput.chooseToSaveChanges()) {
							dbobj.alterEmailInDB(mailID, conId, email_id_num);
						}
					} else {
						logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
					}

					break;

				default: // invalid input
					logger.info(ApplicationStringConstants.INVALID_INPUT_WARNING);
				}

				logger.info(ApplicationStringConstants.CONTINUE_CHOICE);
			} catch (Exception e) {
				logger.info(ApplicationStringConstants.NOTDONE_MSG);
				return false;
			}
		} while (sc.next().charAt(0) == 'y');
		return true;

	}
}
