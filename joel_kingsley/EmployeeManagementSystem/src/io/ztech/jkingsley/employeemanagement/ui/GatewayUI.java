package io.ztech.jkingsley.employeemanagement.ui;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.employeemanagement.constants.Regex;
import io.ztech.jkingsley.employeemanagement.constants.Titles;
import io.ztech.jkingsley.employeemanagementsystem.beans.objects.Profile;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.AccountType;
import io.ztech.jkingsley.employeemanagementsystem.beans.types.EmployeeStatus;
import io.ztech.jkingsley.employeemanagementsystem.services.EmployeeLogin;
import io.ztech.jkingsley.employeemanagementsystem.services.EmployeeRegistration;

public class GatewayUI {

	private final Logger LOGGER = Logger.getLogger(Application.class.getName());

	Scanner scanner = new Scanner(System.in);

	public enum MenuOption {
		REGISTRAION, LOGIN, EXIT
	}

	public void displayMenu() {
		LOGGER.info("\n");
		LOGGER.info(Titles.GATEWAY_HEADING);
		MenuOption[] menuOptions = MenuOption.values();
		for (int i = 0; i < menuOptions.length; i++) {
			LOGGER.info(i + 1 + ". " + menuOptions[i].toString());
		}
		LOGGER.info("Enter an option (" + 1 + "-" + menuOptions.length + ") :");
	}

	public MenuOption getMenuOption() {
		String input = "";
		int n = 0;
		do {
			input = scanner.nextLine();
			if(Validation.isValid(input, Regex.INTEGER_REGEX)) {
				n = Integer.parseInt(input);
			}
			if (n < 1 || n > MenuOption.values().length) {
				LOGGER.info(Titles.INVALID_OPTION);
			}
			
		} while (n < 1 || n > MenuOption.values().length);

		MenuOption menuOption = MenuOption.values()[n - 1];
		return menuOption;
	}

	public boolean execute(MenuOption menuOption) {
		switch (menuOption) {
		case REGISTRAION:
			EmployeeRegistration employeeRegistration = new EmployeeRegistration();
			Profile profile = getInputForAddEmployee();
			if (employeeRegistration.addEmployee(profile)) {
				LOGGER.info(Titles.ADDED_EMPLOYEE);
				return true;
			} else {
				LOGGER.info(Titles.NOT_ADDED_EMPLOYEE);
			}
			break;
		case LOGIN:
			EmployeeLogin employeeLogin = new EmployeeLogin();
			BigInteger emp_id = InputHandler.getEmployeeID();
			String password = InputHandler.getPassword();

			if (employeeLogin.isCorrect(emp_id, password)) {
				MainMenu menu = new MainMenu(emp_id);
				menu.start();
			} else {
				LOGGER.info(Titles.INCORRECT_USERNAME_PASSWORD);
			}
			break;
		case EXIT:
			System.exit(0);
		default:
			break;
		}
		return false;
	}

	private Profile getInputForAddEmployee() {
		Profile profile = new Profile();
		profile.employee.setEmp_name(InputHandler.getEmployeeName());
		profile.employee.setPassword(InputHandler.getPassword());
		profile.employee.setAccount_type(AccountType.EMPLOYEE_LIMITED);
		profile.employee.setGender(InputHandler.getGender());
		profile.employee.setMarital_status(InputHandler.getMaritalStatus());
		profile.employee.setDesignation_id(InputHandler.getDesignationId());
		profile.employee.setDob(InputHandler.getDob());
		profile.employee.setDoj(InputHandler.getDoj());
		profile.employee.setHighest_qualification(InputHandler.getHighestQualification());
		profile.employee.setBloodGroup(InputHandler.getBloodGroup());
		profile.employee.setPan(InputHandler.getPan());
		profile.employee.setAadhar(InputHandler.getAadhar());
		profile.employee.setUan(InputHandler.getUan());
		profile.employee.setPresent_address(InputHandler.getPresentAddress());
		profile.employee.setPermanent_address(InputHandler.getPermanentAddress());
		profile.employee.setEmp_status(EmployeeStatus.PROBATION);
		profile.setPhoneNumbers(InputHandler.getPhoneNumbers());
		profile.setEmergencyContacts(InputHandler.getEmergencyContacts());
		profile.setMailAddresses(InputHandler.getMailAddresses());
		profile.setTotalExperience(InputHandler.getListOfExperience());

		return profile;
	}
}
