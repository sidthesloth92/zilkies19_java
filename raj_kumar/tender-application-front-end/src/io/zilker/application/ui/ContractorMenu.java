package io.zilker.application.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.logsession.UserSession;
import io.zilker.application.service.ContractorServices;

public class ContractorMenu {
	private static final Logger LOGGER = Logger.getLogger(ContractorMenu.class.getName());

	public void displayContractorMenu(UserSession userSession) {
		ContractorUI contractorUI = new ContractorUI();
		ContractorServices contractorServices = new ContractorServices();
		Scanner in = new Scanner(System.in);
		boolean displayMenu = true;
		while (displayMenu) {
			LOGGER.info("Welcome, " + userSession.getUserName());
			LOGGER.info(DisplayConstants.CONTRACTOR_MENU);
			int option = in.nextInt();
			switch (option) {
			case 1:
				// The projects of the Contractor
				contractorUI.contractorProjects(userSession);
				LOGGER.info(DisplayConstants.COMPLETED_PROJECT);
				int completedOption = in.nextInt();
				if (completedOption == -1) {
					break;
				} else {
					contractorServices.projectCompleted(completedOption);
					LOGGER.info("Project Completed !");
				}
				break;
			case 2:
				// Check All Available Government Projects
				contractorUI.displayAvailableProjects();
				LOGGER.info(DisplayConstants.PROJECT_ID_TO_TENDER);
				int projectID = in.nextInt();
				if (projectID == -1) {
					break;
				} else {
					contractorUI.tenderRequestInput(projectID, userSession);
				}
				break;
			case 3:
				// Delayed Project Operation
				if (userSession.getUserId() == 0) {
					LOGGER.info(DisplayConstants.REQUEST_LOGIN);
				} else {
					contractorUI.viewDelayedProjects(userSession);
				}
				break;
			case 4:
				displayMenu = false;
			case 5:
				// To Edit Contractor Applied Information
				if (userSession.getUserId() == 0) {
					LOGGER.info(DisplayConstants.REQUEST_LOGIN);
				} else {
					contractorUI.getAppliedProjects(userSession);
				}
				break;
			default:
				break;
			}
		}
	}
}
