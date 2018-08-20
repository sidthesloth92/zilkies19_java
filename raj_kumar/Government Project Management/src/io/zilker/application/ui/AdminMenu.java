package io.zilker.application.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;

public class AdminMenu {
	Scanner in = new Scanner(System.in);
	private static final Logger LOGGER = Logger.getLogger(AdminMenu.class.getName());

	public void displayAdminMenu() {
		AdminUI admin = new AdminUI();
		boolean displayMenu = true;
		while (displayMenu) {
			LOGGER.info(DisplayConstants.ADMIN_MENU);
			int option = in.nextInt();
			switch (option) {
			case 1:
				admin.getNewProject();
				break;
			case 2:
				admin.approveProject();
				break;
			case 3:
				admin.displayAllContractors();
				break;
			case 4:
				displayMenu = false;
				break;
			default:
				break;
			}
		}

	}

}
