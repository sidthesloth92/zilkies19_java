package io.ztech.contact_app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.dao.UpdateContactDao;
import io.ztech.contact_app.dao.ViewContactDao;

public class UpdateContact {
	Logger log = Logger.getLogger("UpdateContact.class");
	ScanInput scan = new ScanInput();
	ViewContactDao view = new ViewContactDao();
	UpdateContactDao updateDao = new UpdateContactDao();
	char continueChoice;
	ArrayList<String> id = new ArrayList<>();

	public void updateContact(ContactDetail contact) {
		int choice;
		String fname, lname, contact_id;
		HashMap<String, ArrayList<String>> list = null;
		log.info(ApplicationConstants.FIRST_NAME);
		scan.scanFirstName(contact);
		log.info(ApplicationConstants.LAST_NAME);
		scan.scanLastName(contact);
		list = view.viewSpecificContact(contact);
		id = list.get("contact_id");
		if (id.size() < 1) {
			return;
		}
		if (id.size() == 1)
			contact_id = id.get(0);
		else {
			log.info(ApplicationConstants.PCONTACT_ID);
			contact_id = scan.scanId(id);
		}

		do {
			log.info(ApplicationConstants.UPDATE_MSG);
			choice = scan.scanOption();
			switch (choice) {
			case 1:
				log.info(ApplicationConstants.MOBILE_NUMBER);
				scan.scanMobileNumber(contact, 1);
				break;
			case 2:
				log.info(ApplicationConstants.OFFICE_NUMBER);
				scan.scanOfficeNumber(contact, 1);
				break;
			case 3:
				log.info(ApplicationConstants.HOME_NUMBER);
				scan.scanHomeNumber(contact, 1);
				break;
			case 4:
				log.info(ApplicationConstants.EMAIL);
				scan.scanEmail(contact, 1);
				break;
			default:
				log.info(ApplicationConstants.WRONG_CHOICE);
			}
			log.info(ApplicationConstants.UPDATE_CHOICE);
			continueChoice = scan.scanContinueChoice();
		} while (continueChoice == 'Y');
		updateDao.updateContactDetails(contact_id, contact);
	}

}
