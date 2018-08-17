package io.zilker.application.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.logsession.ContractorLog;
import io.zilker.application.logsession.UserLog;
import io.zilker.application.utils.StatusCheck;


public class Menu {
	private static final Logger LOGGER = Logger.getLogger(Menu.class.getName());

	static Scanner in = new Scanner(System.in);
	static UserUI userUI = new UserUI();
	static AdminUI admin = new AdminUI();
	static UserMenu userMenu = new UserMenu();
	static AdminMenu adminMenu = new AdminMenu();
	static StatusCheck statusCheck = new StatusCheck();
	static ContractorUI contractorUI = new ContractorUI();
	static ContractorMenu contractorMenu = new ContractorMenu();
	
	public static void main(String args[]) {
		// Daily Status Change
		statusCheck.statusCheck();
		boolean displayMenu = true;
		while(displayMenu) {
			LOGGER.info(DisplayConstants.MENU_DISPLAY_OPTION);
			int option = in.nextInt();
			switch(option) {
				case 1:
					userUI.displayAllprojects();
					break;
				case 2:
					UserLog userLog = userUI.userLogin();
					if(userLog.getUSER_ID() != 0) {
						userMenu.displayUserMenu(userLog);
					}else {
						LOGGER.info("Login Information Incorrect !");
					}
					break;
				case 3:
					userUI.getUserInput();
					break;
				case 4:
					contractorUI.getContractorInput();
					break;
				case 5: 
					ContractorLog contractorLog = contractorUI.contractorLogin();
					if(contractorLog.getCONTR_ID() != 0) {
						contractorMenu.displayContractorMenu(contractorLog);
					}else {
						break;
					}
					LOGGER.info("Login Information Incorrect !");
					break;
				case 6: 
					while(admin.adminLogin()) {
						adminMenu.displayAdminMenu();
						break;
					}
					LOGGER.info("Login Information Incorrect !");
					break;
				case 7:
					displayMenu = false;
				default: 
					break;
			}
		}
	}
}
