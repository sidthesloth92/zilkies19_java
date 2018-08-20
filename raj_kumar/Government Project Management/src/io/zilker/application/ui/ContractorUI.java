package io.zilker.application.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.constants.ValidationConstants;
import io.zilker.application.logsession.UserSession;
import io.zilker.application.service.ContractorServices;
import io.zilker.application.service.UserServices;
import io.zilker.application.utils.UserValidation;

public class ContractorUI {
	ContractorServices contractorServices = new ContractorServices();
	UserServices userService = new UserServices();
	private static final Logger LOGGER = Logger.getLogger(ContractorUI.class.getName());
	Scanner in = new Scanner(System.in);

	public void getContractorInput() {
		Contractor contractor = new Contractor();
		String firstName, lastName, companyName, annualRevenue, noOfClients, location, username;

		LOGGER.info(DisplayConstants.GET_FIRST_NAME);
		firstName = in.nextLine();
		while (!UserValidation.isValid(firstName, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info(DisplayConstants.INVALID_NAME);
			firstName = in.nextLine();
		}
		contractor.setFirstName(firstName);

		LOGGER.info(DisplayConstants.GET_LAST_NAME);
		lastName = in.nextLine();
		while (!UserValidation.isValid(lastName, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info(DisplayConstants.INVALID_NAME);
			lastName = in.nextLine();
		}
		contractor.setLastName(lastName);

		LOGGER.info(DisplayConstants.GET_EMAIL);
		String email = in.nextLine();
		while (!UserValidation.isValid(email, ValidationConstants.EMAIL)) {
			LOGGER.info(DisplayConstants.EMAIL_ERROR);
			email = in.nextLine();
		}
		contractor.setEmail(email);

		do {
			LOGGER.info(DisplayConstants.GET_UNIQUE_USERNAME);
			username = in.nextLine();
		} while (userService.isUserPresent(username));
		contractor.setUsername(username);

		LOGGER.info(DisplayConstants.GET_CONTRACTOR_PASSWORD);
		String password = in.nextLine();
		contractor.setPassword(password);

		LOGGER.info(DisplayConstants.GET_COMPANY_NAME);
		companyName = in.nextLine();
		while (!UserValidation.isValid(companyName, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info(DisplayConstants.INVALID_NAME);
			companyName = in.nextLine();
		}
		contractor.setCompany(companyName);

		LOGGER.info(DisplayConstants.GET_ANNUAL_REVENUE);
		annualRevenue = in.nextLine();
		contractor.setAnnualRevenue(Long.parseLong(annualRevenue));

		LOGGER.info(DisplayConstants.GET_NO_OF_CLIENT);
		noOfClients = in.nextLine();
		while (!UserValidation.isValid(String.valueOf(noOfClients), ValidationConstants.NO_OF_CLIENT)) {
			LOGGER.info(DisplayConstants.INVALID_NAME);
			noOfClients = in.nextLine();
		}
		contractor.setNoOfClient(Integer.parseInt(noOfClients));

		LOGGER.info(DisplayConstants.GET_LOCATION);
		location = in.nextLine();
		while (!UserValidation.isValid(location, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info(DisplayConstants.INVALID_NAME);
			location = in.nextLine();
		}
		contractor.setLocation(location);

		try {
			contractorServices.contractorCreationService(contractor);
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.ERROR_CONTRACTOR_CREATION);
		}
	}

	public void tenderRequestInput(int projectID, UserSession userSession) {
		int CONTR_ID = userSession.getUSER_ID();
		LOGGER.info("Your CONTR_ID is: " + CONTR_ID + " ! \nEnter the start eg: (12-08-1997)");
		String startDate = in.next();
		Date start = dateFormatter(startDate);
		Date currentDate = new Date();
		// Start Date and End Date Validation Need to Do it
		System.out.println(start.before(currentDate));
		// in.nextLine();
		while (!UserValidation.isValid(startDate, ValidationConstants.DATE_VALIDATION) || start.before(currentDate)
				|| !UserValidation.dateCheck(startDate)) {

			LOGGER.info(DisplayConstants.ERROR_DATE);
			in.nextLine();
			startDate = in.nextLine();
			start = dateFormatter(startDate);
		}
		in.nextLine();
		start = dateFormatter(startDate);

		LOGGER.info("Enter the End Date !");
		String endDate = in.next();
		Date endDateTest = dateFormatter(startDate);
		System.out.println(endDateTest.after(currentDate));
		while (!UserValidation.isValid(startDate, ValidationConstants.DATE_VALIDATION) || start.before(currentDate)
				|| !UserValidation.dateCheck(startDate)) {
			LOGGER.info(DisplayConstants.ERROR_DATE);
			endDate = in.nextLine();
		}
		Date end = dateFormatter(endDate);
		LOGGER.info("Enter the estimated Cost !");
		long estCost = in.nextLong();
		try{
			contractorServices.requestTender(projectID, CONTR_ID, start, end, estCost);
		}catch(Exception e) {
			LOGGER.info(DisplayConstants.ERR_REQUEST_TENDER);
		}
	}

	public static Date dateFormatter(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dateToReturn = null;
		try {
			dateToReturn = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.info(DisplayConstants.ERROR_DATE);
			e.printStackTrace();
		}
		return dateToReturn;
	}

	public void contractorProjects(UserSession userSession) {
		int contractorID = userSession.getUSER_ID();
		ArrayList<ApprovedProject> contractorProj = contractorServices.getContractorProject(contractorID);
		if (!contractorProj.isEmpty()) {
			for (ApprovedProject project : contractorProj) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info(project.getProjectID() + "\nProject Name: " + project.getProjectName() + "\n Status: "
						+ project.getProjectStatus() + "\nStart Date: " + project.getStartDate() + "\nEnd Date: "
						+ project.getEndDate());
				LOGGER.info(DisplayConstants.SPLITTER);
			}
		} else {
			LOGGER.info("You Have to Projects !");
		}
	}

	public void viewDelayedProjects(UserSession userSession) {
		int contractorID = userSession.getUSER_ID();
		ArrayList<ApprovedProject> map = contractorServices.delayedProjects(contractorID);
		if (!map.isEmpty()) {
			for (ApprovedProject project : map) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info(project.getProjectID() + "\nProject Name: " + project.getProjectName() + "\nStatus: "
						+ project.getProjectStatus() + "\nStart Date: " + project.getStartDate() + "\nEnd Date: "
						+ project.getEndDate() + "\n");
				LOGGER.info(DisplayConstants.SPLITTER);
			}
			LOGGER.info("Enter the project ID you want to Select ! else -1 to Exit");
			int ID = in.nextInt();
			if (ID == -1) {
				return;
			}
			ArrayList<ApprovedProject> listOfDelayed = contractorServices.viewDelayedDetail(ID, contractorID);
			for (ApprovedProject project : listOfDelayed) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info(project.getProjectID() + "\nProject Name: " + project.getProjectName() + "\nStatus: "
						+ project.getProjectStatus() + "\nStart Date: " + project.getStartDate() + "\nEnd Date"
						+ project.getEndDate() + "\n");
				LOGGER.info(DisplayConstants.SPLITTER);
			}
			LOGGER.info(DisplayConstants.DELAYED_MENU);
			int option = in.nextInt();
			in.nextLine();
			switch (option) {
			case 1:
				LOGGER.info(DisplayConstants.GET_RESPONSE_TEXT);
				String response = in.nextLine();
				try{
					contractorServices.addResponseService(ID, contractorID, response);
				}catch(Exception e) {
					LOGGER.info(DisplayConstants.ERR_WHILE_ADDING_RESPONSE);
				}
				break;
			case 2:
				break;
			}
		} else {
			LOGGER.info(DisplayConstants.NO_DELAYED_PROJ);
		}
	}

	public void displayAvailableProjects() {
		ArrayList<AvailableProject> list = contractorServices.displayProjects();
		if (!list.isEmpty()) {
			for (AvailableProject project : list) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info(project.getProjectID() + "\nProject Name: " + project.getProjectName() + "Location: "
						+ project.getLocation() + "\nDescription: " + project.getDescription() + "\n");
				LOGGER.info(DisplayConstants.SPLITTER);
			}
		} else {
			LOGGER.info(DisplayConstants.NO_AVAILABLE_PROJECT);
		}
	}

	public void getAppliedProjects(UserSession userSession) {
		ArrayList<RequestedProject> list = contractorServices.getRequestedProjects(userSession);
		if (!list.isEmpty()) {
			for (RequestedProject project : list) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info("Request ID: " + project.getRequestID() + "\nProject Name: " + project.getProjectName()
						+ "\nLocation: " + project.getLocation() + "\nDescription: " + project.getDescription()
						+ "\nStart Date: " + project.getStartDate() + "\nEnd Date: " + project.getEndDate()
						+ "\nEstimated Cost: $" + project.getEstCost() + "\n");
				LOGGER.info(DisplayConstants.SPLITTER);
			}
			LOGGER.info(DisplayConstants.REQUESTED_EDIT);
			int option = in.nextInt();
			switch (option) {
			case 1:
				LOGGER.info(DisplayConstants.WHAT_TO_EDIT);
				int requestID = in.nextInt();
				LOGGER.info(DisplayConstants.REQUESTED_UPDATE_OPTION);
				int updateOption = in.nextInt();
				updateRequest(requestID, updateOption);
				break;
			case 2:
				break;
			default:
				break;
			}
		} else {
			LOGGER.info(DisplayConstants.NO_APPLIED_PROJECT);
		}
	}

	public void updateRequest(int requestId, int updateOption) {
		switch (updateOption) {
		case 1:
			LOGGER.info(DisplayConstants.GET_START_DATE);
			String newStartDate = in.next();
			while (!UserValidation.isValid(newStartDate, ValidationConstants.DATE_VALIDATION)) {
				LOGGER.info(DisplayConstants.ERROR_DATE);
				newStartDate = in.nextLine();
			}
			Date newStart = dateFormatter(newStartDate);
			contractorServices.updateStartDate(updateOption, requestId, newStart);
			break;
		case 2:
			LOGGER.info(DisplayConstants.GET_END_DATE);
			String newEndDate = in.next();
			while (!UserValidation.isValid(newEndDate, ValidationConstants.DATE_VALIDATION)) {
				LOGGER.info(DisplayConstants.ERROR_DATE);
				newStartDate = in.nextLine();
			}
			Date newEnd = dateFormatter(newEndDate);
			contractorServices.updateEndDate(updateOption, requestId, newEnd);
			break;
		case 3:
			LOGGER.info(DisplayConstants.GET_EST_COST);
			long newEstCost = in.nextLong();
			contractorServices.updateEstCost(updateOption, requestId, newEstCost);
			break;
		default:
			break;
		}
	}

}
