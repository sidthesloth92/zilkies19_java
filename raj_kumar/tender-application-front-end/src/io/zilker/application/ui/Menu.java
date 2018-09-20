package io.zilker.application.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.logsession.UserSession;
import io.zilker.application.utils.StatusCheck;

public class Menu {
	private static final Logger LOGGER = Logger.getLogger(Menu.class.getName());

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		UserUI userUI = new UserUI();
		UserMenu userMenu = new UserMenu();
		AdminMenu adminMenu = new AdminMenu();
		StatusCheck statusCheck = new StatusCheck();
		ContractorUI contractorUI = new ContractorUI();
		ContractorMenu contractorMenu = new ContractorMenu();

		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tT %4$s %5$s%6$s%n");
		// Daily Status Change
		try {
			statusCheck.statusCheck();
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.ERR_STATUS_CHECK);
		}

		boolean displayMenu = true;
		while (displayMenu) {
			LOGGER.info(DisplayConstants.MENU_DISPLAY_OPTION);
			int option = in.nextInt();
			switch (option) {
			case 1:
				userUI.displayAllprojects();
				break;
			case 2:
				// Every Users Login
				UserSession userSession = userUI.userLogin();
				System.out.println(userSession.getUserRoll());
				if (userSession.getUserId() != 0 && userSession.getUserRoll().equals("USER")) {
					userMenu.displayUserMenu(userSession);
				} else if (userSession.getUserId() != 0 && userSession.getUserRoll().equals("CONTRACTOR")) {
					contractorMenu.displayContractorMenu(userSession);
				} else if (userSession.getUserId() != 0 && userSession.getUserRoll().equals("ADMIN")) {
					adminMenu.displayAdminMenu();
				} else {
					LOGGER.info(DisplayConstants.ERR_LOGIN);
				}
				break;
			case 3:
				// User Registration
				userUI.getUserInput();
				break;
			case 4:
				// Contractor Registration
				contractorUI.getContractorInput();
				break;
			case 5:
				displayMenu = false;
			default:
				break;
			}
		}
	}
}
