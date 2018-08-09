package io.ztech.contact_app.services;

import io.ztech.contact_app.bean.*;
import io.ztech.contact_app.constants.ApplicationConstants;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ScanInput {
	// scanning first_name
	static Scanner scan = new Scanner(System.in);
	static Logger log = Logger.getLogger("ScanInput.class");

	public void scanFirstName(ContactDetail contact) {
		boolean flag;
		do {
			flag = false;
			String f_name = scan.nextLine();
			if (ValidateInput.validateName(f_name)) {
				contact.setFirstName(f_name);

			} else {
				flag = true;
			}
		} while (flag);
	}

//scanning last_name
	public void scanLastName(ContactDetail contact) {
		boolean flag;
		do {
			flag = false;
			String l_name = scan.nextLine();
			if (ValidateInput.validateName(l_name)) {
				contact.setLastName(l_name);

			} else {
				flag = true;
			}
		} while (flag);
	}

//scanning Mobile-Numbers
	public void scanMobileNumber(ContactDetail contact, int flag) {
		ArrayList<String> temp = new ArrayList<>();
		char choice;
		do {
			boolean isValid = false;
			do {
				String number = scan.nextLine();
				if (ValidateInput.validateMobileNumber(number)) {
					temp.add(number);
				} else {
					isValid = true;
					log.info(ApplicationConstants.ENTER_VALID_NUMBER);
				}
			} while (isValid);
			if (flag == 0)
				break;
			log.info(ApplicationConstants.MULTIPLE_MOBILENO);
			choice = scan.nextLine().charAt(0);
			if (choice == 'Y') {
				log.info(ApplicationConstants.MOBILE_NUMBER);
			}
		} while (choice == 'Y');
		contact.setMobileNumber(temp);

	}

//scanning Office_numbers
	public void scanOfficeNumber(ContactDetail contact, int flag) {
		ArrayList<String> temp = new ArrayList<>();
		char choice;
		do {
			boolean isValid = false;
			do {
				String number = scan.nextLine();
				if (ValidateInput.validateOfficeNumber(number)) {
					temp.add(number);
				} else {
					isValid = true;
					log.info(ApplicationConstants.ENTER_VALID_NUMBER);
				}
			} while (isValid);
			if (flag == 0)
				break;
			log.info(ApplicationConstants.MULTIPLE_OFFICENO);
			choice = scan.nextLine().charAt(0);
			if (choice == 'Y') {
				log.info(ApplicationConstants.OFFICE_NUMBER);
			}
		} while (choice == 'Y');
		contact.setOfficeNumber(temp);

	}

//scanning home_number
	public void scanHomeNumber(ContactDetail contact, int flag) {
		ArrayList<String> temp = new ArrayList<>();
		char choice;
		do {
			boolean isValid = false;
			do {
				String number = scan.nextLine();
				if (ValidateInput.validateOfficeNumber(number)) {
					temp.add(number);
				} else {
					isValid = true;
					log.info(ApplicationConstants.ENTER_VALID_NUMBER);
				}
			} while (isValid);
			if (flag == 0)
				break;
			log.info(ApplicationConstants.MULTIPLE_HOMENO);
			choice = scan.nextLine().charAt(0);
			if (choice == 'Y') {
				log.info(ApplicationConstants.HOME_NUMBER);
			}
		} while (choice == 'Y');
		contact.setHomeNumber(temp);

	}

//scanning email
	public void scanEmail(ContactDetail contact, int flag) {
		ArrayList<String> temp = new ArrayList<>();
		char choice;
		do {
			boolean isValid = false;
			do {
				String email = scan.nextLine();
				if (ValidateInput.validateEmail(email)) {
					temp.add(email);
				} else {
					isValid = true;
					log.info(ApplicationConstants.ENTER_VALID_MAIL);
				}
			} while (isValid);
			if (flag == 0)
				break;
			log.info(ApplicationConstants.MULTIPLE_EMAIL);
			choice = scan.nextLine().charAt(0);
			if (choice == 'Y') {
				log.info(ApplicationConstants.EMAIL);
			}
		} while (choice == 'Y');
		contact.setEmail(temp);

	}

	public int scanOption() {
		// TODO Auto-generated method stub
		int number = scan.nextInt();
		scan.nextLine();
		return number;

	}

	public char scanContinueChoice() {
		// TODO Auto-generated method stub
		return scan.next().charAt(0);

	}

	public String scanId(ArrayList<String> id) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		do {
			String contact_id = scan.nextLine();
			if (ValidateInput.validateId(contact_id, id)) {
				isValid = false;
				return contact_id;
			} else {
				log.info(ApplicationConstants.WRONG_ID);

			}
		} while (isValid);
		return null;
	}

	public String scanMobileId(ArrayList<String> id) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		do {
			String mobile_id = scan.nextLine();
			if (ValidateInput.validateMobileId(mobile_id, id)) {
				isValid = false;
				return mobile_id;
			} else {
				log.info(ApplicationConstants.WRONG_MOBILEID);

			}
		} while (isValid);
		return null;
	}

	public String scanOfficeId(ArrayList<String> id) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		do {
			String office_id = scan.nextLine();
			if (ValidateInput.validateOfficeId(office_id, id)) {
				isValid = false;
				return office_id;
			} else {
				log.info(ApplicationConstants.WRONG_OFFICEID);

			}
		} while (isValid);

		return null;
	}

	public String scanHomeId(ArrayList<String> id) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		do {
			String home_id = scan.nextLine();
			if (ValidateInput.validateHomeId(home_id, id)) {
				isValid = false;
				return home_id;
			} else {
				log.info(ApplicationConstants.WRONG_HOMEID);

			}
		} while (isValid);

		return null;
	}

	public String scanEmailId(ArrayList<String> id) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		do {
			String email_id = scan.nextLine();
			if (ValidateInput.validateEmailId(email_id, id)) {
				isValid = false;
				return email_id;
			} else {
				log.info(ApplicationConstants.WRONG_MAILID);

			}
		} while (isValid);

		return null;
	}

}
