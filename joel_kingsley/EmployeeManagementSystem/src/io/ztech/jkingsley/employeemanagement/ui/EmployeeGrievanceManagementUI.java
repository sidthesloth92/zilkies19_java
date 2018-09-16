package io.ztech.jkingsley.employeemanagement.ui;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.employeemanagement.constants.Regex;
import io.ztech.jkingsley.employeemanagement.constants.Titles;
import io.ztech.jkingsley.employeemanagementsystem.services.EmployeeManagement;

public class EmployeeGrievanceManagementUI {
	enum EmployeeLimitedMenuOption {
		FILE_GRIEVANCE, LIST_GRIEVANCES, RESPOND_TO_HR_RESPONSE, BACK
	}

	private final Logger LOGGER = Logger.getLogger(EmployeeGrievanceManagementUI.class.getName());

	Scanner scanner;
	BigInteger empID;

	public EmployeeGrievanceManagementUI(BigInteger empID) {
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
		
		return false;
	}
}
