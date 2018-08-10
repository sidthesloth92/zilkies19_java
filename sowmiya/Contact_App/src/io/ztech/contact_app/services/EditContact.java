package io.ztech.contact_app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import io.ztech.contact_app.bean.ContactDetail;
import io.ztech.contact_app.constants.ApplicationConstants;
import io.ztech.contact_app.dao.EditContactDao;
import io.ztech.contact_app.dao.ViewContactDao;

public class EditContact {
	Logger log = Logger.getLogger("UpdateContact.class");
	ScanInput scan = new ScanInput();
	ViewContactDao view = new ViewContactDao();
	ArrayList<String> id = new ArrayList<>();
	EditContactDao editContact = new EditContactDao();
	int continueChoice;

	public void editDetails(ContactDetail contact) {
		int choice;
		String required_Id;
		String fname, lname, contact_id;
		HashMap<String, ArrayList<String>> list = null;
		log.info(ApplicationConstants.FIRST_NAME);
		scan.scanFirstName(contact);
		log.info(ApplicationConstants.LAST_NAME);
		scan.scanLastName(contact);
		list = view.viewSpecificContact(contact);

		try {
			id = list.get("contact_id");

			if (id.size() < 1) {
				return;
			}
		} catch (Exception e) {
			return;
		}
		if (id.size() == 1)
			contact_id = id.get(0);
		else {
			log.info(ApplicationConstants.PCONTACT_ID);
			contact_id = scan.scanId(id);
		}

		do {
			log.info(ApplicationConstants.EDIT_MSG);
			choice = scan.scanOption();
			switch (choice) {
			case 1:
				log.info(ApplicationConstants.LAST_NAME);
				scan.scanLastName(contact);
				editContact.editLastName(contact, contact_id);
				break;

			case 2:
				log.info(ApplicationConstants.PMOBILE_ID);
				required_Id = scan.scanMobileId(list.get("mobile_id"));
				System.out.println(required_Id);
				log.info(ApplicationConstants.MOBILE_NUMBER);
				scan.scanMobileNumber(contact, 0);
				editContact.editMobileNumber(contact, required_Id);
				break;
			case 3:
				log.info(ApplicationConstants.POFFICE_ID);
				required_Id = scan.scanOfficeId(list.get("office_id"));
				log.info(ApplicationConstants.OFFICE_NUMBER);
				scan.scanOfficeNumber(contact, 0);
				editContact.editOfficeNumber(contact, required_Id);
				break;
			case 4:
				log.info(ApplicationConstants.PHOME_ID);
				required_Id = scan.scanHomeId(list.get("home_id"));
				log.info(ApplicationConstants.HOME_NUMBER);
				scan.scanHomeNumber(contact, 0);
				editContact.editHomeNumber(contact, required_Id);
				break;
			case 5:
				log.info(ApplicationConstants.PEMAIL_ID);
				required_Id = scan.scanEmailId(list.get("email"));
				log.info(ApplicationConstants.EMAIL);
				scan.scanEmail(contact, 0);
				editContact.editMail(contact, required_Id);
				break;
			default:
				log.info(ApplicationConstants.WRONG_CHOICE);
				break;
			}
			if (choice <= 5) {
				log.info(ApplicationConstants.EDITCHOICE);
				continueChoice = scan.scanContinueChoice();
			} else {
				continueChoice = 'Y';

			}
		} while (continueChoice == 'Y');
	}
}