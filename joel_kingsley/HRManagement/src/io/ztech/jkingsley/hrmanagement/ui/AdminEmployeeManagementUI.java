package io.ztech.jkingsley.hrmanagement.ui;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.hrmanagement.beans.objects.EmergencyContact;
import io.ztech.jkingsley.hrmanagement.beans.objects.Experience;
import io.ztech.jkingsley.hrmanagement.beans.objects.Mail;
import io.ztech.jkingsley.hrmanagement.beans.objects.Phone;
import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.beans.objects.Project;
import io.ztech.jkingsley.hrmanagement.beans.objects.Skill;
import io.ztech.jkingsley.hrmanagement.beans.types.EmployeeStatus;
import io.ztech.jkingsley.hrmanagement.constants.Regex;
import io.ztech.jkingsley.hrmanagement.constants.Titles;
import io.ztech.jkingsley.hrmanagement.services.EmployeeManagement;
import io.ztech.jkingsley.hrmanagement.ui.ManagerEmployeeManagementUI.ManagerMenuOption;

public class AdminEmployeeManagementUI {
	enum AdminMenuOption {
		ADD_PHONE_NUMBER, ADD_EMERGENCY_CONTACT, ADD_MAIL, ADD_EXPERIENCE, ADD_SKILL, ADD_PROJECT,

		FIND_EMPLOYEE_BY_ID, GET_EMPLOYEE_DETAILS,

		UPDATE_EMP_STATUS,

		UPDATE_PHONE_NUMBER, UPDATE_EMERGENCY_CONTACT, UPDATE_MAIL, BACK
	}

	private final Logger LOGGER = Logger.getLogger(Application.class.getName());

	Scanner scanner;
	BigInteger empID;

	public AdminEmployeeManagementUI(BigInteger empID) {
		super();
		scanner = new Scanner(System.in);
		this.empID = empID;
	}

	public void displayAdminMenu() {
		LOGGER.info("\n");
		LOGGER.info(Titles.EMPLOYEE_MANAGAMENT_ADMIN);
		AdminMenuOption[] menuOptions = AdminMenuOption.values();
		for (int i = 0; i < menuOptions.length; i++) {
			LOGGER.info(i + 1 + ". " + menuOptions[i].toString());
		}
		LOGGER.info("Enter an option (" + 1 + "-" + menuOptions.length + ") :");
	}

	public AdminMenuOption getMenuOption() {
		int n = 0;
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, Regex.INTEGER_REGEX)) {
				n = Integer.parseInt(input);
			}
			if (n < 1 || n > AdminMenuOption.values().length) {
				LOGGER.info(Titles.INVALID_OPTION);
			}
		} while (n < 1 || n > ManagerMenuOption.values().length);

		AdminMenuOption menuOption = AdminMenuOption.values()[n - 1];
		return menuOption;
	}

	public boolean execute(AdminMenuOption menuOption) {
		boolean notExit = true;
		EmployeeManagement employeeManagement = new EmployeeManagement();
		switch (menuOption) {
		case ADD_EMERGENCY_CONTACT:
			EmergencyContact emergencyContact = getInputForAddEmergencyContact();
			emergencyContact.setEmp_id(empID);
			EmployeeManagement employeeManagement2 = new EmployeeManagement();
			if (employeeManagement2.addEmergencyContact(emergencyContact)) {
				LOGGER.info(Titles.ADDED_EMERGENCY_CONTACT);
			}
			break;
		case ADD_EXPERIENCE:
			Experience experience = getInputForAddExperience();
			if (experience == null) {
				break;
			}
			experience.setEmp_id(empID);
			if (employeeManagement.addExperience(experience)) {
				LOGGER.info(Titles.ADDED_EXPERIENCE);
			}
			break;
		case ADD_MAIL:
			Mail mail = getInputForAddMail();
			if (mail == null) {
				break;
			}
			mail.setEmp_id(empID);
			EmployeeManagement employeeManagement3 = new EmployeeManagement();
			if (employeeManagement3.addMailAddress(mail)) {
				LOGGER.info(Titles.ADDED_MAIL);
			}
			break;
		case ADD_PHONE_NUMBER:
			Phone phone = getInputForAddPhoneNumber();
			if (phone == null) {
				break;
			}
			phone.setEmp_id(empID);
			if (employeeManagement.addPhoneNumber(phone)) {
				LOGGER.info(Titles.ADDED_PHONE_NUMBER);
			}
			break;

		case GET_EMPLOYEE_DETAILS:
			Profile profile = employeeManagement.findEmployeeById(empID);
			if (profile == null) {
				LOGGER.info(Titles.EMPLOYEE_NOT_FOUND);
				break;
			}
			Printer printer = new Printer();
			printer.print(profile);
			break;
		case UPDATE_PHONE_NUMBER:
			Phone newPhone = getInputForUpdatePhone();
			if (newPhone == null) {
				LOGGER.info(Titles.NO_PHONE_NUMBERS);
				break;
			}
			if (employeeManagement.updatePhoneNumberOfID(newPhone)) {
				LOGGER.info(Titles.UPDATED_PHONE_NUMBER);
			}
			break;
		case UPDATE_EMERGENCY_CONTACT:
			EmergencyContact emergencyContact2 = getInputForUpdateEmergencyContact();
			if (emergencyContact2 == null) {
				LOGGER.info(Titles.NO_EMERGENCY_CONTACTS);
				break;
			}
			if (employeeManagement.updateEmergencyContact(emergencyContact2)) {
				LOGGER.info(Titles.UPDATED_EMERGENCY_CONTACT);
			}
			break;
		case UPDATE_MAIL:
			Mail mail2 = getInputForUpdateMail();
			if (mail2 == null) {
				LOGGER.info(Titles.NO_MAIL_ADDRESSES);
			}
			if (employeeManagement.updateMailAddressOfID(mail2)) {
				LOGGER.info(Titles.UPDATED_MAIL);
			}
			break;
		case ADD_SKILL:
			Skill skill = getInputForAddSkill();
			if (employeeManagement.addSkill(skill)) {
				LOGGER.info(Titles.ADDED_SKILL);
			}
			break;

		case ADD_PROJECT:
			Project project = getInputForAddProject();
			if (employeeManagement.addProject(project)) {
				LOGGER.info(Titles.ADDED_PROJECT);
			}
			break;
		case FIND_EMPLOYEE_BY_ID:
			BigInteger empID = getInputForFindEmployeeByID();
			Profile profile2 = employeeManagement.findEmployeeById(empID);
			Printer printer2 = new Printer();
			printer2.print(profile2);
			break;
		case UPDATE_EMP_STATUS:
			HashMap<BigInteger, EmployeeStatus> map = getInputForUpdateEMPStatus();
			if (employeeManagement.updateEmployeeStatus((BigInteger) map.keySet().toArray()[0],
					(EmployeeStatus) map.values().toArray()[0])) {
				LOGGER.info(Titles.UPDATE_EMP_STATUS);
			}
			break;
		/*
		 * case DELETE_EMPLOYEE: BigInteger deleteEmpID =
		 * getInputForDeleteEmployeeByID();
		 * LOGGER.info("Are you sure you want to delete employee " + deleteEmpID +
		 * " ? (y/n)"); char confirm = 'n'; do { confirm = scanner.nextLine().charAt(0);
		 * }while(confirm != 'y' && confirm != 'n' && confirm != 'Y' && confirm != 'N');
		 * if(confirm == 'y' || confirm == 'Y') {
		 * 
		 * } break;
		 */
		case BACK:
			notExit = false;
			break;
		default:
			break;

		}
		return notExit;
	}

	private BigInteger getInputForDeleteEmployeeByID() {
		return InputHandler.getEmployeeID();
	}

	private HashMap<BigInteger, EmployeeStatus> getInputForUpdateEMPStatus() {
		HashMap<BigInteger, EmployeeStatus> map = new HashMap<>();
		BigInteger empID = InputHandler.getEmployeeID();
		EmployeeStatus empStatus = InputHandler.getEmployeeStatus();
		map.put(empID, empStatus);
		return map;
	}

	private BigInteger getInputForFindEmployeeByID() {
		return InputHandler.getEmployeeID();
	}

	private Project getInputForAddProject() {
		return InputHandler.getProject();
	}

	private Skill getInputForAddSkill() {
		return InputHandler.getSkill();
	}

	private Phone getInputForAddPhoneNumber() {

		return InputHandler.getPhoneNumber();
	}

	private Mail getInputForAddMail() {
		return InputHandler.getMailAddress();
	}

	private Experience getInputForAddExperience() {
		return InputHandler.getExperience(empID);
	}

	private EmergencyContact getInputForAddEmergencyContact() {
		return InputHandler.getEmergencyContact();
	}

	private Mail getInputForUpdateMail() {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Mail> mails = employeeManagement.findMailAddressesOfID(empID);
		LOGGER.info(Titles.LIST_MAIL_OF_ID + empID);
		for (int i = 0; i < mails.size(); i++) {
			LOGGER.info(Integer.toString(i + 1) + ". " + mails.get(i).getMail_address() + " "
					+ mails.get(i).getMail_type());
		}
		if (mails.isEmpty()) {
			return null;
		}
		int option = -1;
		Mail mail = new Mail();
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, Regex.INTEGER_REGEX)) {
				option = Integer.parseInt(input);
			}
			if (option <= 0 || option > mails.size()) {
				mail.setMail_id(mails.get(option - 1).getMail_id());
				mail.setEmp_id(empID);
				mail.setMail_address(mails.get(option - 1).getMail_address());
				mail.setMail_type(mails.get(option - 1).getMail_type());
			} else {
				LOGGER.info(Titles.INVALID_OPTION);
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
		if (emergencyContacts.isEmpty()) {
			return null;
		}
		int option = -1;
		EmergencyContact emergencyContact = new EmergencyContact();
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, Regex.INTEGER_REGEX)) {
				option = Integer.parseInt(input);
			}
			if (option <= 0 || option > emergencyContacts.size()) {
				emergencyContact.setEmergency_contact_id(emergencyContacts.get(option - 1).getEmergency_contact_id());
				emergencyContact.setEmp_id(empID);
				emergencyContact
						.setEmergency_contact_phone(emergencyContacts.get(option - 1).getEmergency_contact_phone());
				emergencyContact
						.setEmergency_contact_name(emergencyContacts.get(option - 1).getEmergency_contact_name());
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
		if (phones.isEmpty()) {
			return null;
		}
		int option = -1;
		Phone phone = new Phone();
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, Regex.INTEGER_REGEX)) {
				option = Integer.parseInt(input);
			}
			if (option <= 0 || option > phones.size()) {
				phone.setPhone_id(phones.get(option - 1).getPhone_id());
				phone.setEmp_id(empID);
				phone.setPhone_number(phones.get(option - 1).getPhone_number());
				phone.setPhone_type(phones.get(option - 1).getPhone_type());
			} else {
				LOGGER.info(Titles.INVALID_OPTION);
			}
		} while (option <= 0 || option > phones.size());
		LOGGER.info(Titles.INPUT_NEW_PHONE);
		Phone newPhone = InputHandler.getPhoneNumber();
		newPhone.setEmp_id(empID);
		newPhone.setPhone_id(phone.getPhone_id());
		return newPhone;
	}

}
