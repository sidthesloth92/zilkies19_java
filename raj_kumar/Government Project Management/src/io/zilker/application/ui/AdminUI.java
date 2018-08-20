package io.zilker.application.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.service.AdminServices;

public class AdminUI {
	private static final Logger LOGGER = Logger.getLogger(AdminUI.class.getName());
	Project project = new Project();
	AdminServices adminServices = new AdminServices();
	Scanner in = new Scanner(System.in);

	public void getNewProject() {

		LOGGER.info(DisplayConstants.GET_PROJECT_NAME);
		String projectName = in.nextLine();
		project.setProjectName(projectName);

		LOGGER.info(DisplayConstants.GET_PROJECT_LOCATION);
		String location = in.nextLine();
		project.setLocation(location);

		LOGGER.info(DisplayConstants.GET_PROJECT_DESCRIPTION);
		String description = in.nextLine();
		project.setDescription(description);

		try {
			adminServices.addNewProject(project);
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.ERR_ADDING_PROJECT);
		}
	}

	public void approveProject() {
		ArrayList<RequestedProject> requested = adminServices.displayRequested();
		if (!requested.isEmpty()) {
			for (RequestedProject project : requested) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info(project.getRequestID() + "\nProject Name: " + project.getProjectName() + "\nDescription: "
						+ project.getDescription() + "\nStart Date: " + project.getStartDate() + "\nEnd Date: "
						+ project.getEndDate() + "\nContractorID " + project.getContrID() + "\nLocation: "
						+ project.getLocation() + "Estimated Cost: " + project.getEstCost() + "\n");
				LOGGER.info(DisplayConstants.SPLITTER);
			}
			LOGGER.info(DisplayConstants.ADMIN_APPROVE_MENU);
			int approveOption = in.nextInt();
			switch (approveOption) {
			case 1:
				LOGGER.info("Enter The Request Id you want to Approve !");
				int requestID = in.nextInt();
				adminServices.approveProject(requestID);
				break;
			case 2:
				break;
			default:
				break;
			}
		} else {
			LOGGER.info(DisplayConstants.NO_REQUEST_FOR_PROJECT);
		}

	}

	public void displayAllContractors() {
		ArrayList<Contractor> contractor = adminServices.displayAllContractors();
		if (!contractor.isEmpty()) {
			for (Contractor eachContractor : contractor) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info(eachContractor.getContrId() + "\nContractor Name: " + eachContractor.getFirstName()
						+ "\nEmail: " + eachContractor.getEmail() + "\nCompany Name: " + eachContractor.getCompany()
						+ "\nNo Of Clients Handled: " + eachContractor.getNoOfClient() + "\nAnnual Revenue: $"
						+ eachContractor.getAnnualRevenue() + "\n");
				LOGGER.info(DisplayConstants.SPLITTER);
			}
		} else {
			LOGGER.info(DisplayConstants.NO_CONTRACTOR_REGISTERED);
		}

	}
}
