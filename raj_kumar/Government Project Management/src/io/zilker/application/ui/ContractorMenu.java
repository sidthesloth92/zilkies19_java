package io.zilker.application.ui;

import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;

public class ContractorMenu {
	private static final Logger LOGGER = Logger.getLogger(ContractorMenu.class.getName());
	public void displayContractorMenu() {
		ContractorUI contractorUI = new ContractorUI();
		Scanner in = new Scanner(System.in);
		boolean displayMenu = true;
		while(displayMenu) {
			LOGGER.info(DisplayConstants.CONTRACTOR_MENU);
			int option = in.nextInt();
			switch(option) {
				case 1:
					// The projects of the Contractor
					contractorUI.contractorProjects();
					break;
				case 2:
					// Check All Available Government Projects
					contractorUI.displayAvailableProjects();
					LOGGER.info("Enter a Project Id that you want to get Tender !\nEnter -1 to Exit");
					int projectID = in.nextInt();
					if(projectID == -1) {
						break;
					}else {
						contractorUI.tenderRequestInput(projectID);
					}
					break;
				case 3:
					//  Delayed Project Operation
					contractorUI.viewDelayedProjects();
					break;
				case 4:
					displayMenu = false;
				default: 
					break;
			}
		}
	}
}
