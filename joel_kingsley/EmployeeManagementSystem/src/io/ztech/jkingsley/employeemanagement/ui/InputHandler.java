package io.ztech.jkingsley.employeemanagement.ui;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

import io.ztech.jkingsley.employeemanagement.constants.Regex;
import io.ztech.jkingsley.employeemanagement.constants.Titles;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Designation;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.EmergencyContact;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Experience;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Mail;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Phone;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Project;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Skill;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.BloodGroup;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.EmployeeStatus;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.Gender;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.MailType;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.MaritalStatus;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.PhoneType;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.Qualification;
import io.ztech.jkingsley.employeemanagementsystem.services.EmployeeManagement;

public class InputHandler {

	private final static Scanner scanner = new Scanner(System.in);

	private final static Logger LOGGER = Logger.getLogger(InputHandler.class.getName());

	public static ArrayList<Experience> removeDuplicatedInList(ArrayList<Experience> experiences) {
		Set<Experience> set = new HashSet<>();
		set.addAll(experiences);
		experiences.clear();
		experiences.addAll(set);
		return experiences;
	}
	
	public static BigInteger getEmployeeID() {
		LOGGER.info("Enter Employee ID:");
		String empID = "";
		empID = scanner.nextLine();
		return BigInteger.valueOf(Long.parseLong(empID));
	}

	public static String getEmployeeName() {
		LOGGER.info("Enter Employee Name:");
		String name = "";
		do {
			name = scanner.nextLine();

			if (!Validation.isValid(name, Regex.NAME_REGEX)) {
				LOGGER.info("Not a valid name. Enter a valid name");
			}
		} while (!Validation.isValid(name, Regex.NAME_REGEX));
		return name;
	}

	public static String getPassword() {
		LOGGER.info("Enter Account Password:");
		String password = "";
		do {
			password = scanner.nextLine();
			if (!Validation.isValid(password, Regex.PASSWORD_REGEX)) {
				LOGGER.info("Not a valid password. Enter a valid password with atleast 1 character upto 64 characters");
			}
		} while (!Validation.isValid(password, Regex.PASSWORD_REGEX));

		return password;
	}

	public static Gender getGender() {
		LOGGER.info("Enter Gender(male/female):");
		String gender = "";
		do {
			gender = scanner.nextLine();
			if ((!gender.equalsIgnoreCase("male")) && (!gender.equalsIgnoreCase("female"))) {
				LOGGER.info("Not a valid gender. Enter a valid gender");
			}
		} while ((!gender.equalsIgnoreCase("male")) && (!gender.equalsIgnoreCase("female")));

		return Gender.valueOf(gender.toUpperCase());
	}

	public static MaritalStatus getMaritalStatus() {
		LOGGER.info("Enter Marital Status(single/married/divorced/widowed/):");
		String maritalStatus = "";
		do {
			maritalStatus = scanner.nextLine();
			if ((!maritalStatus.equalsIgnoreCase("single")) && (!maritalStatus.equalsIgnoreCase("married"))
					&& (!maritalStatus.equalsIgnoreCase("divorced")) && (!maritalStatus.equalsIgnoreCase("widowed"))) {
				LOGGER.info("Not a valid marital status. Enter a valid marital status");
			}
		} while ((!maritalStatus.equalsIgnoreCase("single")) && (!maritalStatus.equalsIgnoreCase("married"))
				&& (!maritalStatus.equalsIgnoreCase("divorced")) && (!maritalStatus.equalsIgnoreCase("widowed")));

		return MaritalStatus.valueOf(maritalStatus.toUpperCase());
	}

	public static BigInteger getDesignationId() {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Designation> designations = employeeManagement.findAllDesignations();

		if (designations.isEmpty()) {
			LOGGER.info("There are no designations entered yet");
			return BigInteger.ZERO;
		}

		for (int i = 0; i < designations.size(); i++) {
			LOGGER.info(String.valueOf(i + 1) + ". " + designations.get(i).getDesgination_name() + " ("
					+ designations.get(i).getDesignation_id() + ")");
		}

		LOGGER.info("Enter Designation Number:");
		int option = 0;
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, "[0-9]+")) {
				option = Integer.parseInt(input);
			}

			if (option < 1 && option > designations.size()) {
				LOGGER.info("Not a valid option. Enter a valid option");
			}
		} while (option < 1 && option > designations.size());

		return designations.get(option - 1).getDesignation_id();
	}

	@SuppressWarnings("deprecation")
	public static Date getDob() {
		String date = "";
		LOGGER.info("Enter date of birth in the format dd/MM/yyyy :");
		do {
			date = scanner.nextLine();
			if (!Validation.isValidDate(date, "dd/MM/yyyy")) {
				LOGGER.info("Not a valid date. Enter a valid date in the format dd/MM/YYYY");
			}
		} while (!Validation.isValidDate(date, "dd/MM/yyyy"));

		String dateSplit[] = date.trim().split("/");
		int day = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int year = Integer.parseInt(dateSplit[2]);

		return new Date(year, month, day);
	}

	@SuppressWarnings("deprecation")
	public static Date getDoj() {
		String date = "";
		LOGGER.info("Enter date of join in the format dd/MM/yyyy :");
		do {
			date = scanner.nextLine();
			if (!Validation.isValidDate(date, "dd/MM/yyyy")) {
				LOGGER.info("Not a valid date. Enter a valid date in the format dd/MM/YYYY");
			}
		} while (!Validation.isValidDate(date, "dd/MM/yyyy"));

		String dateSplit[] = date.trim().split("/");
		int day = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int year = Integer.parseInt(dateSplit[2]);

		return new Date(year, month, day);
	}

	public static Qualification getHighestQualification() {
		LOGGER.info("Enter Highest Qualification:");
		Qualification[] menuOptions = Qualification.values();
		for (int i = 0; i < menuOptions.length; i++) {
			LOGGER.info(i + 1 + ". " + menuOptions[i].toString());
		}
		LOGGER.info("Enter an option (" + 1 + "-" + menuOptions.length + ") :");
		int n = 0;
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, "[0-9]+")) {
				n = Integer.parseInt(input);
			}
			if (n < 1 || n > Qualification.values().length) {
				LOGGER.info("Not a valid option. Enter a valid option");
			}
		} while (n < 1 || n > Qualification.values().length);

		Qualification highest_qualification = Qualification.values()[n - 1];
		return highest_qualification;
	}

	public static BloodGroup getBloodGroup() {
		LOGGER.info("Enter Blood Group:");
		BloodGroup[] menuOptions = BloodGroup.values();
		for (int i = 0; i < menuOptions.length; i++) {
			LOGGER.info(i + 1 + ". " + menuOptions[i].toString());
		}
		LOGGER.info("Enter an option (" + 1 + "-" + menuOptions.length + ") :");
		int n = 0;
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, "[0-9]+")) {
				n = Integer.parseInt(input);
			}
			if (n < 1 || n > BloodGroup.values().length) {
				LOGGER.info("Not a valid option. Enter a valid option");
			}
		} while (n < 1 || n > BloodGroup.values().length);

		BloodGroup bloodGroup = BloodGroup.values()[n - 1];
		return bloodGroup;
	}

	public static String getPan() {
		LOGGER.info("Enter PAN(12 digits):");
		String pan = "";
		do {
			pan = scanner.nextLine();
			if (!Validation.isValid(pan, Regex.PAN_REGEX)) {
				LOGGER.info("Not a valid PAN. Enter a valid PAN");
			}
		} while (!Validation.isValid(pan, Regex.PAN_REGEX));

		return pan;
	}

	public static String getAadhar() {
		LOGGER.info("Enter Aadhar(12 digits):");
		String aadhar = "";
		do {
			aadhar = scanner.nextLine();
			if (!Validation.isValid(aadhar, Regex.AADHAR_REGEX)) {
				LOGGER.info("Not a valid Aadhar. Enter a valid Aadhar");
			}
		} while (!Validation.isValid(aadhar, Regex.AADHAR_REGEX));

		return aadhar;
	}

	public static String getUan() {
		LOGGER.info("Enter UAN:");
		String uan = "";
		do {
			uan = scanner.nextLine();
			if (!Validation.isValid(uan, Regex.UAN_REGEX)) {
				LOGGER.info("Not a valid UAN. Enter a valid UAN");
			}
		} while (!Validation.isValid(uan, Regex.UAN_REGEX));

		return uan;
	}

	public static String getPresentAddress() {
		LOGGER.info("Enter Present Address:");
		String address = "";
		do {
			address = scanner.nextLine();
			if (!Validation.isValid(address, Regex.ADDRESS_REGEX)) {
				LOGGER.info("Not a valid Address. Enter a valid Address");
			}
		} while (!Validation.isValid(address, Regex.ADDRESS_REGEX));

		return address;
	}

	public static String getPermanentAddress() {
		LOGGER.info("Enter Permanent Address:");
		String address = "";
		do {
			address = scanner.nextLine();
			if (!Validation.isValid(address, Regex.ADDRESS_REGEX)) {
				LOGGER.info("Not a valid Address. Enter a valid Address");
			}
		} while (!Validation.isValid(address, Regex.ADDRESS_REGEX));

		return address;
	}

	public static ArrayList<Phone> getPhoneNumbers() {
		ArrayList<Phone> phoneNumbers = new ArrayList<Phone>();
		LOGGER.info("Enter Phone Numbers and type(Enter -1 to skip):");

		String phoneNumber = "";
		String type = "";
		do {
			LOGGER.info("Enter number(10 digits):");
			phoneNumber = scanner.nextLine();
			if (phoneNumber.equals("-1")) {
				break;
			}
			LOGGER.info("Enter type(work/home):");
			type = scanner.nextLine().toUpperCase();

			if (Validation.isValid(phoneNumber, Regex.PHONE_REGEX) && Validation.isValidPhoneType(type)) {
				Phone phone = new Phone();
				phone.setPhone_number(phoneNumber);
				phone.setPhone_type(PhoneType.valueOf(type));
				phoneNumbers.add(phone);
			} else if (!phoneNumber.equals("-1") && !type.equals("-1")) {
				LOGGER.info("You entered an invalid Phone Number or type. Make sure it is valid");
			}
		} while (!phoneNumber.equals("-1") && !type.equals("-1"));
		return phoneNumbers;
	}

	public static ArrayList<EmergencyContact> getEmergencyContacts() {
		ArrayList<EmergencyContact> emergencyContacts = new ArrayList<>();
		LOGGER.info("Enter Emergency Contact name and number(Enter -1 to skip):");

		String name = "";
		String number = "";
		do {
			LOGGER.info("Enter name:");
			name = scanner.nextLine();
			if (name.equals("-1")) {
				break;
			}
			LOGGER.info("Enter number(10 digits):");
			number = scanner.nextLine();

			if (Validation.isValid(name, Regex.NAME_REGEX) && Validation.isValid(number, Regex.PHONE_REGEX)) {
				EmergencyContact emergencyContact = new EmergencyContact();
				emergencyContact.setEmergency_contact_name(name);
				emergencyContact.setEmergency_contact_phone(number);
				emergencyContacts.add(emergencyContact);
			} else if (!name.equals("-1") && !number.equals("-1")) {
				LOGGER.info("You entered an invalid Name or Phone Number. Make sure it is valid");
			}
		} while (!name.equals("-1") && !number.equals("-1"));
		return emergencyContacts;
	}

	public static ArrayList<Mail> getMailAddresses() {
		ArrayList<Mail> mails = new ArrayList<>();
		LOGGER.info("Enter Mail and type(Enter -1 to skip):");

		String mailId = "";
		String type = "";
		do {
			LOGGER.info(Titles.INPUT_MAIL);
			mailId = scanner.nextLine();
			if (mailId.equals("-1")) {
				break;
			}
			LOGGER.info(Titles.INPUT_MAIL_TYPE);
			type = scanner.nextLine().toUpperCase();

			if (Validation.isValid(mailId, Regex.MAIL_REGEX) && Validation.isValidMailType(type)) {
				Mail mail = new Mail();
				mail.setMail_address(mailId);
				mail.setMail_type(MailType.valueOf(type));
				mails.add(mail);
			} else if (!mailId.equals("-1") && !type.equals("-1")) {
				LOGGER.info(Titles.INVALID_PHONE_OR_TYPE);
			}
		} while (!mailId.equals("-1") && !type.equals("-1"));
		return mails;
	}

	public static ArrayList<Experience> getListOfExperience(BigInteger empID) {
		ArrayList<Experience> experiences = new ArrayList<>();

		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Skill> allSkills = employeeManagement.findNotAddedSkillsOfID(empID);

		LOGGER.info(Titles.LIST_SKILLS);
		for (int i = 0; i < allSkills.size(); i++) {
			LOGGER.info(Integer.toString(i + 1) + ". " + allSkills.get(i).getSkill_name());
		}

		LOGGER.info(Titles.INPUT_SKILLS);
		int skill = 0;
		do {
			String input = scanner.nextLine();
			if (input.equals("-1")) {
				break;
			}
			if (Validation.isValid(input, "[0-9]+")) {
				skill = Integer.parseInt(input);
			}
			if (skill > 0 && skill <= allSkills.size()) {
				Experience experience = new Experience();
				experience.setSkill_id(allSkills.get(skill - 1).getSkill_id());
				experiences.add(experience);
				LOGGER.info("Added skill " + skill);
			}

			else {
				LOGGER.info("You entered an invalid Skill. Make sure it is valid");
			}
		} while (skill != -1);
		
		experiences = removeDuplicatedInList(experiences);
		
		experiences.forEach(experience -> LOGGER.info(experience.getSkill_id().toString()));
		return experiences;

	}

	public static ArrayList<Experience> getListOfExperience() {
		ArrayList<Experience> experiences = new ArrayList<>();

		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Skill> allSkills = employeeManagement.findAllSkills();

		LOGGER.info("List of all valid skills:");
		for (int i = 0; i < allSkills.size(); i++) {
			LOGGER.info(Integer.toString(i + 1) + ". " + allSkills.get(i).getSkill_name());
		}

		LOGGER.info("Enter Skills one by one(eg. 1)(Enter -1 to skip):");
		int skill = 0;
		do {
			String input = scanner.nextLine();
			if (input.equals("-1")) {
				break;
			}
			if (Validation.isValid(input, "[0-9]+")) {
				skill = Integer.parseInt(input);
			}
			if (skill > 0 && skill <= allSkills.size()) {
				Experience experience = new Experience();
				experience.setSkill_id(allSkills.get(skill - 1).getSkill_id());
				experiences.add(experience);
				LOGGER.info("Added skill " + skill);
			} else {
				LOGGER.info("You entered an invalid Skill. Make sure it is valid");
			}
		} while (skill != -1);
		
		experiences = removeDuplicatedInList(experiences);
		
		experiences.forEach(experience -> LOGGER.info(experience.getSkill_id().toString()));
		return experiences;

	}

	public static Phone getPhoneNumber() {
		Phone phone = null;
		LOGGER.info("Enter Phone Number and type):");
		String phoneNumber = "";
		String type = "";

		do {
			LOGGER.info("Enter phone number:");
			phoneNumber = scanner.nextLine();
			LOGGER.info("Enter phone type(HOME/WORK)");
			type = scanner.nextLine().trim().toUpperCase();

			if (Validation.isValid(phoneNumber, Regex.PHONE_REGEX) && Validation.isValidPhoneType(type)) {
				phone = new Phone();
				phone.setPhone_number(phoneNumber);
				phone.setPhone_type(PhoneType.valueOf(type));
			} else {
				LOGGER.info("You entered an invalid Phone Number or type. Make sure it is valid");
			}
		} while (!Validation.isValid(phoneNumber, Regex.PHONE_REGEX) || !Validation.isValidPhoneType(type));
		return phone;
	}

	public static EmergencyContact getEmergencyContact() {
		EmergencyContact emergencyContact = null;
		LOGGER.info("Enter Emergency Contact name and number(eg. Rob 1234567890)(Enter -1 to skip):");

		String name = "";
		String number = "";
		do {
			name = scanner.nextLine();
			number = scanner.nextLine();

			if (Validation.isValid(name, Regex.NAME_REGEX) && Validation.isValid(number, Regex.PHONE_REGEX)) {
				emergencyContact = new EmergencyContact();
				emergencyContact.setEmergency_contact_name(name);
				emergencyContact.setEmergency_contact_phone(number);
			} else {
				LOGGER.info("You entered an invalid Name or Phone Number. Make sure it is valid");
			}
		} while (!Validation.isValid(name, Regex.NAME_REGEX) || !Validation.isValid(number, Regex.PHONE_REGEX));

		return emergencyContact;
	}

	public static Mail getMailAddress() {
		Mail mail = null;
		LOGGER.info("Enter Mail and type(eg. jkingsley@ztech.io COMPANY):");
		String mailId = "";
		String type = "";

		do {
			LOGGER.info("Enter mail:");
			mailId = scanner.nextLine();
			if (mailId.equals("-1")) {
				break;
			}
			LOGGER.info("Enter type(personal/company):");
			type = scanner.nextLine().toUpperCase();

			if (mailId.equals("-1") || type.equals("-1")) {
				break;
			}

			if (Validation.isValid(mailId, Regex.MAIL_REGEX) && Validation.isValidMailType(type)) {
				mail = new Mail();
				mail.setMail_address(mailId);
				mail.setMail_type(MailType.valueOf(type));
			} else {
				LOGGER.info("You entered an invalid Phone Number or type. Make sure it is valid");
			}
		} while (!Validation.isValid(mailId, Regex.MAIL_REGEX) || !Validation.isValidMailType(type));

		return mail;
	}

	public static Experience getExperience(BigInteger empID) {
		Experience experience = null;

		EmployeeManagement employeeManagement = new EmployeeManagement();
		ArrayList<Skill> allSkills = employeeManagement.findNotAddedSkillsOfID(empID);

		LOGGER.info("List of all remaining skills:");
		for (int i = 0; i < allSkills.size(); i++) {
			LOGGER.info(allSkills.get(i).getSkill_name());
		}

		LOGGER.info("Enter Skill number(eg. 1)(Enter -1 to skip):");
		String input = "";
		int skill = 0;
		do {
			input = scanner.nextLine();
			if (input.equals("-1")) {
				break;
			}
			if (Validation.isValid(input, "[0-9]+")) {
				skill = Integer.parseInt(input);
			}
			if (skill > 0 && skill <= allSkills.size()) {
				experience = new Experience();
				experience.setSkill_id(allSkills.get(skill - 1).getSkill_id());
				LOGGER.info("Added skill " + skill);
			} else {
				LOGGER.info("You entered an invalid Skill number. Make sure it is within the range");
			}
		} while (skill <= 0 || skill > allSkills.size());

		return experience;
	}

	public static Skill getSkill() {
		Skill skill = new Skill();
		LOGGER.info("Enter Skill name:");
		skill.setSkill_name(scanner.nextLine());
		return skill;
	}

	public static Project getProject() {
		Project project = new Project();
		LOGGER.info("Enter Project name:");
		project.setProject_name(scanner.nextLine());
		LOGGER.info("Enter Location:");
		project.setLocation(scanner.nextLine());
		LOGGER.info("Enter Reporting Manager ID:");

		String input = "";

		EmployeeManagement employeeManagement = new EmployeeManagement();

		do {
			input = scanner.nextLine();
			if (Validation.isValid(input, "[0-9]+")) {
				if (employeeManagement.findEmployeeById(BigInteger.valueOf(Long.parseLong(input))) != null) {
					project.setReporting_manager_id(BigInteger.valueOf(Long.parseLong(input)));
					break;
				}
			}
			LOGGER.info("Invalid employee ID. Enter a valid one.");
		} while (!Validation.isValid(input, "[0-9]+"));

		return project;
	}

	public static EmployeeStatus getEmployeeStatus() {
		LOGGER.info("Enter Employee Status:");
		EmployeeStatus[] menuOptions = EmployeeStatus.values();
		for (int i = 0; i < menuOptions.length; i++) {
			LOGGER.info(i + 1 + ". " + menuOptions[i].toString());
		}
		LOGGER.info("Enter an option (" + 1 + "-" + menuOptions.length + ") :");
		int n = 0;
		do {
			String input = scanner.nextLine();
			if (Validation.isValid(input, "[0-9]+")) {
				n = Integer.parseInt(input);
			}
			if (n < 1 || n > EmployeeStatus.values().length) {
				LOGGER.info("Not a valid option. Enter a valid option");
			}
		} while (n < 1 || n > EmployeeStatus.values().length);

		EmployeeStatus empStatus = EmployeeStatus.values()[n - 1];
		return empStatus;
	}

}
