package io.ztech.jkingsley.employeemanagement.ui;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.employeemanagement.constants.Regex;
import io.ztech.jkingsley.employeemanagement.constants.Titles;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.EmergencyContact;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Experience;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Mail;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Phone;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Profile;
import io.ztech.jkingsley.employeemanagementsystem.services.EmployeeManagement;

public class EmployeeLimitedEmployeeManagementUI {

	public enum EmployeeLimitedMenuOption {
		ADD_PHONE_NUMBER, ADD_EMERGENCY_CONTACT, ADD_MAIL, ADD_EXPERIENCE,

		GET_EMPLOYEE_DETAILS,

		UPDATE_PHONE_NUMBER, UPDATE_EMERGENCY_CONTACT, UPDATE_MAIL, BACK
	}

	private final Logger LOGGER = Logger.getLogger(Application.class.getName());

	Scanner scanner;
	BigInteger empID;

	public EmployeeLimitedEmployeeManagementUI(BigInteger empID) {
		super();
		scanner = new Scanner(System.in);
		this.empID = empID;
	}

	public void displayEmployeeMenuLimited() {
		LOGGER.info("\n");
		LOGGER.info(Titles.EMPLOYEE_MANAGAMENT_EMPLOYEE_LIMITED);
		EmployeeLimitedMenuOption[] menuOptions = EmployeeLimitedMenuOption.values();
		for (int i = 0; i < menuOptions.length; i++) {
			LOGGER.info(i + 1 + ". " + menuOptions[i].toString());
		}
		LOGGER.info("Enter an option (" + 1 + "-" + menuOptions.length + ") :");
	}

	public EmployeeLimitedMenuOption getMenuOption() {
		int n = 0;
		do {
			String input = scanner.nextLine();
			if(Validation.isValid(input, Regex.INTEGER_REGEX)) {
				n = Integer.parseInt(input);
			}
			if (n < 1 || n > EmployeeLimitedMenuOption.values().length) {
				LOGGER.info(Titles.INVALID_OPTION);
			}
		} while (n < 1 || n > EmployeeLimitedMenuOption.values().length);

		EmployeeLimitedMenuOption menuOption = EmployeeLimitedMenuOption.values()[n - 1];
		return menuOption;
	}

	public boolean execute(EmployeeLimitedMenuOption menuOption) {

		boolean notExit = true;

		EmployeeManagement employeeManagement = new EmployeeManagement();

		switch (menuOption) {
		case ADD_PHONE_NUMBER:
			Phone phone = getInputForAddPhoneNumber();
			if(phone == null) {
				LOGGER.info(Titles.NOTHING_ADDED);
				break;
			}
			phone.setEmp_id(empID);
			if (employeeManagement.addPhoneNumber(phone)) {
				LOGGER.info("Phone Number added");
			}
			break;
		case ADD_EMERGENCY_CONTACT:
			EmergencyContact emergencyContact = getInputForAddEmergencyContact();
			if(emergencyContact == null) {
				LOGGER.info(Titles.NOTHING_ADDED);
				break;
			}
			emergencyContact.setEmp_id(empID);
			if (employeeManagement.addEmergencyContact(emergencyContact)) {
				LOGGER.info("Emergency Contact added");
			}
			break;
		case ADD_MAIL:
			Mail mail = getInputForAddMail();
			if(mail == null) {
				LOGGER.info(Titles.NOTHING_ADDED);
				break;
			}
			mail.setEmp_id(empID);
			if (employeeManagement.addMailAddress(mail)) {
				LOGGER.info(Titles.ADDED_MAIL);
			}
			break;
		case ADD_EXPERIENCE:
			Experience experience = getInputForAddExperience();
			if(experience == null) {
				LOGGER.info(Titles.NOTHING_ADDED);
				break;
			}
			experience.setEmp_id(empID);
			if (employeeManagement.addExperience(experience)) {
				LOGGER.info(Titles.ADDED_EXPERIENCE);
			}
			break;
		case GET_EMPLOYEE_DETAILS:
			Profile profile = employeeManagement.findEmployeeById(empID);
			Printer printer = new Printer();
			printer.print(profile);
			break;
		case UPDATE_PHONE_NUMBER:
			Phone newPhone = getInputForUpdatePhone();
			if(newPhone == null) {
				LOGGER.info(Titles.NO_PHONE_NUMBERS);
				break;
			}
			if (employeeManagement.updatePhoneNumberOfID(newPhone)) {
				LOGGER.info(Titles.UPDATED_PHONE_NUMBER);
			}
			break;
		case UPDATE_EMERGENCY_CONTACT:
			EmergencyContact emergencyContact2 = getInputForUpdateEmergencyContact();
			if(emergencyContact2 == null) {
				LOGGER.info(Titles.NO_EMERGENCY_CONTACTS);
				break;
			}
			if(employeeManagement.updateEmergencyContact(emergencyContact2)) {
				LOGGER.info(Titles.UPDATED_EMERGENCY_CONTACT);
			}
			break;
		case UPDATE_MAIL:
			Mail mail2 = getInputForUpdateMail();
			if(mail2 == null) {
				LOGGER.info(Titles.NO_MAIL_ADDRESSES);
				break;
			}
			if(employeeManagement.updateMailAddressOfID(mail2)) {
				LOGGER.info(Titles.UPDATED_MAIL);
			}
			break;
		case BACK:
			notExit = false;
		default:
			notExit = false;
			break;
		}
		return notExit;
	}

	private Mail getInputForUpdateMail() {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Mail> mails = employeeManagement.findMailAddressesOfID(empID);
		LOGGER.info("List of all mails of ID: " + empID);
		for (int i = 0; i < mails.size(); i++) {
			LOGGER.info(Integer.toString(i + 1) + ". " + mails.get(i).getMail_address() + " "
					+ mails.get(i).getMail_type());
		}
		if(mails.isEmpty()) {
			return null;
		}
		int option = -1;
		Mail mail = new Mail();
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, Regex.INTEGER_REGEX)) {
				option = Integer.parseInt(input);
			}
			if (option > 0 || option <= mails.size()) {
				mail.setMail_id(mails.get(option - 1).getMail_id());
				mail.setEmp_id(empID);
				mail.setMail_address(mails.get(option - 1).getMail_address());
				mail.setMail_type(mails.get(option - 1).getMail_type());
			} else {
				LOGGER.info("Please enter a valid option within the values specified.");
			}
		} while (option <= 0 || option > mails.size());
		Mail newMail = InputHandler.getMailAddress();
		newMail.setEmp_id(empID);
		newMail.setMail_id(mail.getMail_id());
		return newMail;
	}

	private EmergencyContact getInputForUpdateEmergencyContact() {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<EmergencyContact> emergencyContacts = employeeManagement.findEmergencyContactsOfID(empID);
		LOGGER.info(Titles.LIST_EMERGENCY_CONTACTS_OF_ID + empID);
		for (int i = 0; i < emergencyContacts.size(); i++) {
			LOGGER.info(Integer.toString(i + 1) + ". " + emergencyContacts.get(i).getEmergency_contact_name() + " "
					+ emergencyContacts.get(i).getEmergency_contact_phone());
		}
		if(emergencyContacts.isEmpty()) {
			return null;
		}
		int option = -1;
		EmergencyContact emergencyContact = new EmergencyContact();
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, Regex.INTEGER_REGEX)) {
				option = Integer.parseInt(input);
			}
			if (option > 0 || option <= emergencyContacts.size()) {
				emergencyContact.setEmergency_contact_id(emergencyContacts.get(option - 1).getEmergency_contact_id());
				emergencyContact.setEmp_id(empID);
				emergencyContact.setEmergency_contact_phone(emergencyContacts.get(option - 1).getEmergency_contact_phone());
				emergencyContact.setEmergency_contact_name(emergencyContacts.get(option - 1).getEmergency_contact_name());
			} else {
				LOGGER.info(Titles.INVALID_OPTION);
			}
		} while (option <= 0 || option > emergencyContacts.size());
		EmergencyContact newEmergencyContact = InputHandler.getEmergencyContact();
		newEmergencyContact.setEmp_id(empID);
		newEmergencyContact.setEmergency_contact_id(emergencyContact.getEmergency_contact_id());
		return newEmergencyContact;
	}

	private Phone getInputForUpdatePhone() {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Phone> phones = employeeManagement.findPhoneNumbersOfID(empID);
		LOGGER.info(Titles.LIST_PHONE_NUMBERS_OF_ID + empID);
		for (int i = 0; i < phones.size(); i++) {
			LOGGER.info(Integer.toString(i + 1) + ". " + phones.get(i).getPhone_number() + " "
					+ phones.get(i).getPhone_type());
		}
		if(phones.isEmpty()) {
			return null;
		}
		int option = -1;
		Phone phone = new Phone();
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, Regex.INTEGER_REGEX)) {
				option = Integer.parseInt(input);
			}
			if (option > 0 && option <= phones.size()) {
				phone.setPhone_id(phones.get(option - 1).getPhone_id());
				phone.setEmp_id(empID);
				phone.setPhone_number(phones.get(option - 1).getPhone_number());
				phone.setPhone_type(phones.get(option - 1).getPhone_type());
			} else {
				LOGGER.info(Titles.INVALID_OPTION);
			}
		} while (option <= 0 || option > phones.size());
		LOGGER.info(Titles.INPUT_PHONE);
		Phone newPhone = InputHandler.getPhoneNumber();
		newPhone.setEmp_id(empID);
		newPhone.setPhone_id(phone.getPhone_id());
		return newPhone;
	}

	private Experience getInputForAddExperience() {
		return InputHandler.getExperience(empID);
	}

	private Mail getInputForAddMail() {
		return InputHandler.getMailAddress();
	}

	private EmergencyContact getInputForAddEmergencyContact() {
		return InputHandler.getEmergencyContact();
	}

	private Phone getInputForAddPhoneNumber() {

		return InputHandler.getPhoneNumber();
	}
}
