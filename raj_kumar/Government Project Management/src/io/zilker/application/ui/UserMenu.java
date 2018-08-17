package io.zilker.application.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.logsession.UserLog;
import io.zilker.application.service.DisplayProjects;

public class UserMenu {
	private static final Logger LOGGER = Logger.getLogger(UserMenu.class.getName());
	DisplayProjects displayProjects = new DisplayProjects();
	Scanner in = new Scanner(System.in);
	UserUI userUI = new UserUI();
	public void displayUserMenu(UserLog userLog) {
		boolean displayMenu = true;
		while(displayMenu) {
			LOGGER.info("Welcome, "+userLog.getUserName());
			LOGGER.info(DisplayConstants.USER_MENU);
			int option = in.nextInt();
			switch(option) {
				case 1:
					userUI.displayAllprojects();
					LOGGER.info("Enter the Projects ID you want to see !");
					int projectID = in.nextInt();
					if(userUI.isLoggedIn(userLog)) {
						userUI.detailDisplay(projectID, userLog);
					}else {
						LOGGER.info("Please Login to Continue !");
					}
					break;
				case 2:
					// View Projects in my location 
					if(userUI.isLoggedIn(userLog)) {
						userUI.projectsInLocation(userLog);
					}else {
						LOGGER.info("Please Login to Continue");
					}
					break;
				case 3:
					displayMenu = false;
				default: 
					break;
			}
		}
	}
}
