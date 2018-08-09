package io.ztech.contact_app.services;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.dao.InsertContact;
import io.ztech.contact_app.dao.ViewContactDao;

public class AddContact {
	public static Logger log = Logger.getLogger("AddContact.class");
	Scanner scanner = new Scanner(System.in);

	public void newContactDetails(ContactDetail contact) throws SQLException {
		ScanInput scan;
		InsertContact insertDao;
		char choice, continueChoice = ' ';
		scan = new ScanInput();
		insertDao = new InsertContact();
		log.info(ApplicationConstants.FIRST_NAME);
		scan.scanFirstName(contact);
		log.info(ApplicationConstants.LAST_NAME);
		scan.scanLastName(contact);
		if (insertDao.isExists(contact)) {
			log.info(ApplicationConstants.EXISTS);
			log.info(ApplicationConstants.CONTINUE_CHOICE);
			continueChoice = scan.scanContinueChoice();
		}
		if (continueChoice == 'N') {
			log.info(ApplicationConstants.THANKYOU);
			return;
		}
		log.info(ApplicationConstants.MOBILE_CHOICE);
		choice = scanner.nextLine().charAt(0);
		if (choice == 'Y') {
			log.info(ApplicationConstants.MOBILE_NUMBER);
			scan.scanMobileNumber(contact, 1);
		}
		log.info(ApplicationConstants.OFFICE_CHOICE);
		choice = scanner.nextLine().charAt(0);
		if (choice == 'Y') {
			log.info(ApplicationConstants.OFFICE_NUMBER);
			scan.scanOfficeNumber(contact, 1);
		}
		log.info(ApplicationConstants.HOME_CHOICE);
		choice = scanner.nextLine().charAt(0);
		if (choice == 'Y') {
			log.info(ApplicationConstants.HOME_NUMBER);
			scan.scanHomeNumber(contact, 1);
		}
		log.info(ApplicationConstants.EMAIL_CHOICE);
		choice = scanner.nextLine().charAt(0);
		if (choice == 'Y') {
			log.info(ApplicationConstants.EMAIL);
			scan.scanEmail(contact, 1);
		}
		insertDao.insertContact(contact);

	}

}
