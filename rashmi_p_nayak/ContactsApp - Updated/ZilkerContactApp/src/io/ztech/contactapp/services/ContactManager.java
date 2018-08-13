package io.ztech.contactapp.services;

import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Scanner;

import io.ztech.contactapp.beans.Contact;
import io.ztech.contactapp.beans.EmailId;

import io.ztech.contactapp.beans.Number;
import io.ztech.contactapp.beans.HomeNumber;
import io.ztech.contactapp.beans.MobileNumber;
import io.ztech.contactapp.beans.OfficeNumber;
import io.ztech.contactapp.dao.ContactDAO;
import io.ztech.contactapp.constants.*;

//=========================================================================================================================================
//CONTACT MANAGER CLASS - MANAGES AND PROVIDES SERVICES OF CONTACTS APP
//=========================================================================================================================================
public class ContactManager implements DisplayConstants, RegexConstants {
	Scanner in;
	ContactDAO contactDao;
	Validator validator;

	public ContactManager() {
		in = new Scanner(System.in);
		contactDao = new ContactDAO();
		validator = new Validator();
	}

	// =========================================================================================================================================
	// ADD CONTACT AND CONTACT DETAILS
	// =========================================================================================================================================

	// =======================================================================
	// ADD A CONTACT
	// =======================================================================
	public void addEntireContact() {
		char choice;
		Contact contact = new Contact();
		ArrayList<Number> phoneNumbers = new ArrayList<Number>();
		ArrayList<EmailId> emailIds = new ArrayList<EmailId>();
		System.out.println(ENTER_FIRST_NAME);
		contact.setFirstName(in.next());
		System.out.println(ENTER_LAST_NAME);
		contact.setLastName(in.next());

		System.out.println(ADD_NEW_MOBILE_NUMBER);
		choice = in.next().charAt(0);
		in.nextLine();
		while (choice == 'y' || choice == 'Y') {
			phoneNumbers.add(addMobileNumberDetails());
			System.out.println(ADD_NEW_MOBILE_NUMBER);
			choice = in.next().charAt(0);
			in.nextLine();

		}

		System.out.println(ADD_NEW_HOME_NUMBER);
		choice = in.next().charAt(0);
		in.nextLine();
		while (choice == 'y' || choice == 'Y') {
			phoneNumbers.add(addHomeNumberDetails());
			System.out.println(ADD_NEW_HOME_NUMBER);
			choice = in.next().charAt(0);
			in.nextLine();

		}

		System.out.println(ADD_NEW_OFFICE_NUMBER);
		choice = in.next().charAt(0);
		in.nextLine();
		while (choice == 'y' || choice == 'Y') {
			phoneNumbers.add(addOfficeNumberDetails());
			System.out.println(ADD_NEW_OFFICE_NUMBER);
			choice = in.next().charAt(0);
			in.nextLine();
		}

		System.out.println(ADD_NEW_EMAIL);
		choice = in.next().charAt(0);
		in.nextLine();
		while (choice == 'y' || choice == 'Y') {
			emailIds.add(addEmailIdDetails());
			System.out.println(ADD_NEW_EMAIL);
			choice = in.next().charAt(0);
			in.nextLine();

		}

		contact.setPhoneNumbers(phoneNumbers);
		contact.setEmailIds(emailIds);

		contactDao.insertIntoContactDetails(contact);

	}

	// =======================================================================
	// ADD A MOBILE NUMBER
	// =======================================================================
	public MobileNumber addMobileNumberDetails() {
		MobileNumber mobileNumber = new MobileNumber();
		System.out.println(ENTER_MOBILE_NUMBER);
		String fullNumber = in.nextLine();
		String[] phoneNumber = fullNumber.split("\\s");
		if (phoneNumber.length != 2 || !validator.isValidated(phoneNumber[0], numRegex)
				|| !validator.isValidated(phoneNumber[1], numRegex)) {
			System.out.println(INVALID_FORMAT);
			return addMobileNumberDetails();

		} else {
			mobileNumber.setCountryCode(phoneNumber[0]);
			mobileNumber.setPhoneNumber(phoneNumber[1]);
			return mobileNumber;
		}

	}

	// =======================================================================
	// ADD AN OFFICE NUMBER
	// =======================================================================
	public OfficeNumber addOfficeNumberDetails() {
		OfficeNumber officeNumber = new OfficeNumber();
		System.out.println(ENTER_OFFICE_NUMBER);
		String fullNumber = in.nextLine();
		String[] phoneNumber = fullNumber.split("\\s");
		boolean isAllValidated = true;
		for (String number : phoneNumber) {
			isAllValidated &= validator.isValidated(number, numRegex);
		}
		if (phoneNumber.length < 2 || !isAllValidated) {
			System.out.println(INVALID_FORMAT);
			return addOfficeNumberDetails();

		} else {
			switch (phoneNumber.length) {
			case 2:
				officeNumber.setCountryCode(phoneNumber[0]);
				officeNumber.setPhoneNumber(phoneNumber[1]);
				break;
			case 3:
				officeNumber.setCountryCode(phoneNumber[0]);
				officeNumber.setAreaCode(phoneNumber[1]);
				officeNumber.setPhoneNumber(phoneNumber[2]);
				break;
			case 4:
				officeNumber.setExtension(phoneNumber[0]);
				officeNumber.setCountryCode(phoneNumber[1]);
				officeNumber.setAreaCode(phoneNumber[2]);
				officeNumber.setPhoneNumber(phoneNumber[3]);
				break;

			}
			return officeNumber;
		}

	}

	// =======================================================================
	// ADD A HOME NUMBER
	// =======================================================================
	public HomeNumber addHomeNumberDetails() {
		HomeNumber homeNumber = new HomeNumber();
		System.out.println(ENTER_HOME_NUMBER);
		String fullNumber = in.nextLine();
		String[] phoneNumber = fullNumber.split("\\s");
		if (phoneNumber.length != 3 || !validator.isValidated(phoneNumber[0], numRegex)
				|| !validator.isValidated(phoneNumber[1], numRegex)
				|| !validator.isValidated(phoneNumber[2], numRegex)) {
			System.out.println(INVALID_FORMAT);
			return addHomeNumberDetails();
		} else {
			homeNumber.setCountryCode(phoneNumber[0]);
			homeNumber.setAreaCode(phoneNumber[1]);
			homeNumber.setPhoneNumber(phoneNumber[2]);
			return homeNumber;
		}
	}

	// =======================================================================
	// ADD AN EMAIL ID
	// =======================================================================
	public EmailId addEmailIdDetails() {
		EmailId emailId = new EmailId();
		System.out.println(ENTER_EMAIL_ID);
		String email = in.nextLine();
		if (!validator.isValidated(email, emailRegex)) {
			System.out.println(INVALID_FORMAT);
			return addEmailIdDetails();

		} else {
			emailId.setEmailId(email);
			return emailId;
		}
	}

	// =========================================================================================================================================
	// VIEW CONTACTS AND CONTACT DETAILS
	// =========================================================================================================================================

	// =======================================================================
	// VIEW ALL CONTACTS
	// =======================================================================
	public void viewContact() {
		ArrayList<Contact> contacts = contactDao.selectExistingContact();
		System.out.println(CONTACTS_HEADING);
		for (Contact contact : contacts) {
			System.out.printf("%-4d  %-10s  %-10s\n", contact.getcId(), contact.getFirstName(), contact.getLastName());
		}

		System.out.println(ENTER_CID);
		int cId = in.nextInt();
		viewContactDetails(cId);

	}

	// =======================================================================
	// VIEW CONTACTS DETAILS
	// =======================================================================
	public void viewContactDetails(int cId) {
		Contact contact = contactDao.selectContactDetails(cId);

		ArrayList<Number> phoneNumbers = contact.getPhoneNumbers();
		ArrayList<EmailId> emailIds = contact.getEmailIds();

		while (!phoneNumbers.isEmpty() && phoneNumbers.get(0) instanceof MobileNumber) {
			MobileNumber mobileNumber = (MobileNumber) phoneNumbers.remove(0);
			System.out.println(MOBILE_NUMBERS_HEADING);
			System.out.printf("%-4d  %s %s %s \n", mobileNumber.getmId(), mobileNumber.getCountryCode(),
					mobileNumber.getPhoneNumber());
		}

		while (!phoneNumbers.isEmpty() && phoneNumbers.get(0) instanceof HomeNumber) {
			HomeNumber homeNumber = (HomeNumber) phoneNumbers.remove(0);
			System.out.println(HOME_NUMBERS_HEADING);
			System.out.printf("%-4d  %s %s %s \n", homeNumber.gethId(), homeNumber.getCountryCode(),
					homeNumber.getAreaCode(), homeNumber.getPhoneNumber());
		}

		while (!phoneNumbers.isEmpty() && phoneNumbers.get(0) instanceof OfficeNumber) {
			OfficeNumber officeNumber = (OfficeNumber) phoneNumbers.remove(0);
			System.out.println(OFFICE_NUMBERS_HEADING);
			System.out.printf("%-4d  %s %s %s \n", officeNumber.getoId(), officeNumber.getExtension(),
					officeNumber.getCountryCode(), officeNumber.getAreaCode(), officeNumber.getPhoneNumber());

		}

		while (!emailIds.isEmpty()) {
			EmailId emailId = emailIds.remove(0);
			System.out.println(EMAIL_IDS_HEADING);
			System.out.printf("%-4d  %s %s %s \n", emailId.geteId(), emailId.getEmailId());
		}
	}

	// =========================================================================================================================================
	// EDIT CONTACTS AND CONTACT DETAILS - ADD,UPDATE AND DELETE
	// =========================================================================================================================================

	// =======================================================================
	// EDIT CONTACTS DETAILS
	// =======================================================================
	public void editContactDetails() {
		try {
			System.out.println(SEARCH_PARAM);
			String name = in.next();
			ArrayList<Contact> contacts = contactDao.searchExistingContact(name);
			System.out.println(CONTACTS_HEADING);
			for (Contact contact : contacts) {
				System.out.printf("%-4d  %-10s  %-10s\n", contact.getcId(), contact.getFirstName(),
						contact.getLastName());
			}

			System.out.println(ENTER_CID);
			int cId = in.nextInt();
			viewContactDetails(cId);
			System.out.println("!!!!!!!!!!Here!!!!!!!!!");
			System.out.println(EDIT_OPTIONS);
			int choice = in.nextInt();
			EditOptions option = EditOptions.values()[choice];
			switch (option) {
			case ADD_DETAILS:
				addDetails(cId);
				break;
			case UPDATE_DETAILS:
				updateDetails(cId);
				break;
			case DELETE_DETAILS:
				deleteDetails(cId);
				break;
			case DELETE_CONTACT:
				contactDao.deleteContact(cId);
				break;
			default: {
				System.out.println(INVALID_CHOICE);
				editContactDetails();
				return;
			}

			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
			in.nextLine();
			editContactDetails();
			return;
		}

	}

	// =======================================================================
	// ADD CONTACTS DETAILS
	// =======================================================================
	public void addDetails(int cId) {
		try {

			ArrayList<Number> phoneNumbers = new ArrayList<Number>();
			ArrayList<EmailId> emailIds = new ArrayList<EmailId>();
			char repeat;
			do {
				System.out.println(DETAIL_CHOICE);
				int choice = in.nextInt();
				in.nextLine();
				DetailOptions option = DetailOptions.values()[choice];
				switch (option) {
				case MOBILE_NUMBER:
					phoneNumbers.add(addMobileNumberDetails());
					break;
				case HOME_NUMBER:
					phoneNumbers.add(addHomeNumberDetails());
					break;
				case OFFICE_NUMBER:
					phoneNumbers.add(addOfficeNumberDetails());
					break;
				case EMAIL_ID:
					emailIds.add(addEmailIdDetails());
					break;
				default: {
					System.out.println(INVALID_CHOICE);
					addDetails(cId);
					return;
				}
				}
				System.out.println(ADD_NEW_DETAIL);
				repeat = in.next().charAt(0);
				in.nextLine();
			} while (repeat == 'y' || repeat == 'Y');
			contactDao.insertIntoPhoneNumberDetails(phoneNumbers, cId);
			contactDao.insertIntoEmailDetails(emailIds, cId);
		} catch (InputMismatchException e) {
			System.out.println(INVALID_FORMAT);
			in.nextLine();
			addDetails(cId);
			return;

		}
	}

	// =======================================================================
	// UPDATE CONTACTS DETAILS
	// =======================================================================
	public void updateDetails(int cId) {
		try {
			System.out.println(UPDATE_CHOICE);
			int choice = in.nextInt();
			UpdateOptions option = UpdateOptions.values()[choice];
			switch (option) {
			case FIRST_NAME:
				updateFirstName(cId);
				break;
			case LAST_NAME:
				updateLastName(cId);
				break;
			case OFFICE_NUMBER:
				updateOfficeNumber(cId);
				break;
			case MOBILE_NUMBER:
				updateMobileNumber(cId);
				break;
			case HOME_NUMBER:
				updateHomeNumber(cId);
				break;
			case EMAIL_ID:
				updateEmailId(cId);
				break;
			default: {
				System.out.println(INVALID_CHOICE);
				updateDetails(cId);
				return;
			}

			}
		} catch (InputMismatchException emailId) {
			System.out.println(INVALID_FORMAT);
			in.nextLine();
			updateDetails(cId);
			return;
		}
	}

	// =======================================================================
	// UPDATE FIRST NAME
	// =======================================================================
	public void updateFirstName(int cId) {
		System.out.println(ENTER_FIRST_NAME);
		String name = in.next();
		contactDao.updateFirstName(name, cId);
	}

	// =======================================================================
	// UPDATE LAST NAME
	// =======================================================================
	public void updateLastName(int cId) {
		System.out.println(ENTER_LAST_NAME);
		String name = in.next();
		contactDao.updateLastName(name, cId);
	}

	// =======================================================================
	// UPDATE MOBILE NUMBER
	// =======================================================================
	public void updateMobileNumber(int cId) {
		try {
			System.out.println(ENTER_ID);
			int id = in.nextInt();
			in.nextLine();
			MobileNumber mobileNumber = addMobileNumberDetails();
			mobileNumber.setcId(cId);
			mobileNumber.setmId(id);
			contactDao.updateMobileNumber(mobileNumber);
		} catch (InputMismatchException emailId) {
			System.out.println(INVALID_FORMAT);
			in.nextLine();
			updateMobileNumber(cId);
			return;
		}
	}

	// =======================================================================
	// UPDATE OFFICE NUMBER
	// =======================================================================
	public void updateOfficeNumber(int cId) {
		try {
			System.out.println(ENTER_ID);
			int id = in.nextInt();
			in.nextLine();
			OfficeNumber officeNumber = addOfficeNumberDetails();
			officeNumber.setcId(cId);
			officeNumber.setoId(id);
			contactDao.updateOfficeNumber(officeNumber);

		} catch (InputMismatchException emailId) {
			System.out.println(INVALID_FORMAT);
			in.nextLine();
			updateOfficeNumber(cId);
			return;
		}
	}

	// =======================================================================
	// UPDATE HOME NUMBER
	// =======================================================================
	public void updateHomeNumber(int cId) {
		try {
			System.out.println(ENTER_ID);
			int id = in.nextInt();
			in.nextLine();
			HomeNumber homeNumber = addHomeNumberDetails();
			homeNumber.setcId(cId);
			homeNumber.sethId(id);
			contactDao.updateHomeNumber(homeNumber);
		} catch (InputMismatchException emailId) {
			System.out.println(INVALID_FORMAT);
			in.nextLine();
			updateHomeNumber(cId);
			return;
		}
	}

	// =======================================================================
	// UPDATE EMAIL ID
	// =======================================================================
	public void updateEmailId(int cId) {
		try {
			System.out.println(ENTER_ID);
			int id = in.nextInt();
			in.nextLine();
			EmailId emailId = addEmailIdDetails();
			emailId.setcId(cId);
			emailId.seteId(id);
			contactDao.updateEmailId(emailId);
		} catch (InputMismatchException emailId) {
			System.out.println(INVALID_FORMAT);
			in.nextLine();
			updateEmailId(cId);
			return;
		}
	}

	// =======================================================================
	// DELETE DETAILS
	// =======================================================================
	public void deleteDetails(int cId) {
		try {
			System.out.println(DETAIL_CHOICE);
			int choice = in.nextInt();
			System.out.println(ENTER_ID);
			int id = in.nextInt();
			DetailOptions option = DetailOptions.values()[choice];
			switch (option) {
			case MOBILE_NUMBER:
				contactDao.deleteMobileNumber(cId, id);
				break;
			case HOME_NUMBER:
				contactDao.deleteHomeNumber(cId, id);
				break;
			case OFFICE_NUMBER:
				contactDao.deleteOfficeNumber(cId, id);
				break;
			case EMAIL_ID:
				contactDao.deleteEmailId(cId, id);
				break;
			default: {
				System.out.println(INVALID_CHOICE);
				in.nextLine();
				deleteDetails(cId);
				return;
			}
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
			in.nextLine();
			deleteDetails(cId);
			return;
		}
	}

	// =========================================================================================================================================
	// VIEW MAIN MENU OF CONTACT APP
	// =========================================================================================================================================

	public void showMenu() {
		try {
			System.out.println();
			System.out.println(MENU);
			int choice = in.nextInt();
			MainMenuOptions option = MainMenuOptions.values()[choice];
			switch (option) {
			case ADD_CONTACT:
				addEntireContact();
				break;
			case VIEW_CONTACT:
				viewContact();
				break;
			case EDIT_CONTACT:
				editContactDetails();
				break;
			case EXIT:
				System.exit(0);
				break;
			default: {
				System.out.println(INVALID_CHOICE);
				showMenu();
				return;
			}
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
			in.nextLine();
			showMenu();
			return;
		} finally {
			showMenu();
		}
	}

}
