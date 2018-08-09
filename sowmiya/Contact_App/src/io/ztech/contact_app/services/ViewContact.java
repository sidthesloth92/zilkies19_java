package io.ztech.contact_app.services;

import java.util.logging.Logger;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.dao.ViewContactDao;

public class ViewContact {
	Logger log = Logger.getLogger("ViewContact.class");
	ScanInput scan = new ScanInput();
	ViewContactDao viewDao = new ViewContactDao();
	ContactDetail contact = new ContactDetail();

	public void viewContactDetails() {
		int sortChoice = 0, viewChoice = 0;
		String fname, lname;
		log.info(ApplicationConstants.VIEWOPTION);
		viewChoice = scan.scanOption();
		if (viewChoice == 2) {
			log.info(ApplicationConstants.SORTCHOICE);
			sortChoice = scan.scanOption();
			viewDao.viewAllContact(sortChoice);
		}
		if (viewChoice == 1) {
			// log.info(ApplicationConstants.first_name);

			log.info(ApplicationConstants.FIRST_NAME);
			scan.scanFirstName(contact);
			// log.info(ApplicationConstants.last_name);
			log.info(ApplicationConstants.LAST_NAME);
			scan.scanLastName(contact);
			viewDao.viewSpecificContact(contact);

		}
	}
}
