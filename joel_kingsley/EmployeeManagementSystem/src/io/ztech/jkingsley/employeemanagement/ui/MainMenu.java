package io.ztech.jkingsley.employeemanagement.ui;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.jkingsley.employeemanagement.constants.Titles;

public class MainMenu {
	
	private final Logger LOGGER = Logger.getLogger(Application.class.getName());
	
	enum MenuOption{
		EMPLOYEE_MANAGEMENT,
		GRIEVANCE_MANAGEMENT,
		EXIT
	}
	
	public MainMenu(BigInteger emp_id) {
		super();
		this.emp_id = emp_id;
	}

	Scanner scanner;
	BigInteger emp_id;
	
	public void start() {
		boolean notExit = true;
		scanner = new Scanner(System.in);
		
		do {
			displayMenu();
			MenuOption menuOption = getMenuOption();
			switch(menuOption) {
			case EMPLOYEE_MANAGEMENT:
				EmployeeManagementUI eMUI = new EmployeeManagementUI(emp_id);
				eMUI.start();
				break;
			case GRIEVANCE_MANAGEMENT:
				LOGGER.info(Titles.UNDER_CONSTRUCTION);
				break;
			case EXIT:
				notExit = false;
				break;
			default:
				break;
			}
		}while(notExit);
		
	}
	
	public void displayMenu() {
		LOGGER.info("EMS Menu");
		MenuOption[] menuOptions = MenuOption.values(); 
		for(int i=0;i<menuOptions.length;i++) {
			LOGGER.info(i+1 + ". " + menuOptions[i].toString());
		}
		LOGGER.info("Enter an option (" + 1 + "-" + menuOptions.length +") :");
	}
	
	public MenuOption getMenuOption() {
		int n = 0;
		do {
			n = Integer.parseInt(scanner.nextLine());
			if(n<1 || n>MenuOption.values().length) {
				
			}
		}while(n<1 || n>MenuOption.values().length);
		
		MenuOption menuOption = MenuOption.values()[n-1];
		return menuOption;
	}
}
