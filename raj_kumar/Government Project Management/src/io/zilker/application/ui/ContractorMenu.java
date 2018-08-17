package io.zilker.application.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.logsession.ContractorLog;
import io.zilker.application.service.ContractorServices;

public class ContractorMenu {
	private static final Logger LOGGER = Logger.getLogger(ContractorMenu.class.getName());
	public void displayContractorMenu(ContractorLog contractorLog) {
		ContractorUI contractorUI = new ContractorUI();
		ContractorServices contractorServices = new ContractorServices();
		Scanner in = new Scanner(System.in);
		boolean displayMenu = true;
		while(displayMenu) {
			LOGGER.info("Welcome, "+contractorLog.getContractorName());
			LOGGER.info(DisplayConstants.CONTRACTOR_MENU);
			int option = in.nextInt();
			switch(option) {
				case 1:
					// The projects of the Contractor
					contractorUI.contractorProjects(contractorLog);
					LOGGER.info("Enter the Project ID of the Completed project! else Enter -1");
					int completedOption = in.nextInt();
					if(completedOption == -1) {
						break;
					}else {
						contractorServices.projectCompleted(completedOption);
						LOGGER.info("Project Completed !");
					}
					break;
				case 2:
					// Check All Available Government Projects
					contractorUI.displayAvailableProjects();
					LOGGER.info("Enter a Project Id that you want to get Tender !\nEnter -1 to Exit");
					int projectID = in.nextInt();
					if(projectID == -1) {
						break;
					}else {
						contractorUI.tenderRequestInput(projectID, contractorLog);
					}
					break;
				case 3:
					//  Delayed Project Operation
					contractorUI.viewDelayedProjects(contractorLog);
					break;
				case 4:
					displayMenu = false;
				case 5:
					// To Edit Contractor Information 
					
					break;
				default: 
					break;
			}
		}
	}
}
