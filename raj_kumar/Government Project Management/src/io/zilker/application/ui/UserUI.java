package io.zilker.application.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Response;
import io.zilker.application.beans.User;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.constants.ValidationConstants;
import io.zilker.application.logsession.UserSession;
import io.zilker.application.service.ContractorServices;
import io.zilker.application.service.DisplayProjects;
import io.zilker.application.service.UserServices;
import io.zilker.application.utils.UserValidation;

public class UserUI {
	private static final Logger LOGGER = Logger.getLogger(UserUI.class.getName());
	UserServices userService = new UserServices();
	ContractorServices contractorServices = new ContractorServices();
	DisplayProjects displayProjects = new DisplayProjects();
	Scanner in = new Scanner(System.in);

	// Creating a New User
	public void getUserInput() {
		String password, retypePassword, username;
		User user = new User();
		LOGGER.info(DisplayConstants.GET_USER_FIRST_NAME);
		String fName = in.nextLine();
		while (!UserValidation.isValid(fName, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info(DisplayConstants.INVALID_NAME);
			fName = in.nextLine();
		}
		user.user.setFirstName(fName);
		LOGGER.info(DisplayConstants.GET_USER_LAST_NAME);
		String lastName = in.nextLine();
		while (!UserValidation.isValid(lastName, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info(DisplayConstants.INVALID_NAME);
			lastName = in.nextLine();
		}
		user.user.setLastName(lastName);
		do {
			LOGGER.info(DisplayConstants.GET_UNIQUE_USERNAME);
			username = in.nextLine();
		} while (userService.isUserPresent(username));
		user.user.setUsername(username);
		do {
			LOGGER.info(DisplayConstants.GET_USER_PASSWORD);
			password = in.nextLine();
			LOGGER.info(DisplayConstants.GET_USER_PASSWORD_AGAIN);
			retypePassword = in.nextLine();
		} while (!(password.equals(retypePassword)));
		user.setPassword(password);

		LOGGER.info(DisplayConstants.GET_USER_LOCATION);
		String location = in.nextLine();
		while (!UserValidation.isValid(location, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info(DisplayConstants.ERR_INVALID_LOCATION);
			location = in.nextLine();
		}
		user.setRole("USER");
		user.setLocation(location);
		try {
			userService.userCreationService(user);
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.ERR_USER_CREATION);
		}
	}

	// Making the User Login
	public UserSession userLogin() {
		LOGGER.info(DisplayConstants.GET_USERNAME);
		String username = in.next();
		LOGGER.info(DisplayConstants.GET_PASSWORD);
		String password = in.next();
		return userService.userLogin(username, password);
	}

	// To Check if the User is Logged in
	public boolean isLoggedIn(UserSession userLog) {
		if (userLog.getUSER_ID() == 0) {
			return false;
		} else {
			return true;
		}
	}

	// To get the Projects in the Users Location
	public void projectsInLocation(UserSession userLog) {
		int userID = userLog.getUSER_ID();
		ArrayList<ApprovedProject> projectList = displayProjects.projectsInLocationSer(userID);
		if (!projectList.isEmpty()) {
			for (ApprovedProject project : projectList) {
				LOGGER.info(DisplayConstants.SPLITTER);
				LOGGER.info(project.getProjectID() + "\nProject Name: " + project.getProjectName() + "\nStatus: "
						+ project.getProjectStatus() + "\nStart Date: " + project.getStartDate() + "\nEnd Date: "
						+ project.getEndDate() + "\nProject Contractor: "
						+ contractorServices.getContractorName(project.getContrID()) + "\nLocation: "
						+ project.getLocation() + "\nEstimated Cost: " + project.getEstCost() + "\nDescription :"
						+ project.getDescription() + "\n");
				LOGGER.info(DisplayConstants.SPLITTER);
			}
		} else {
			LOGGER.info("Sorry, No Projects in Your Location !");
		}

	}

	public void displayAllprojects() {
		ArrayList<ApprovedProject> projectList = displayProjects.displayProjects();
		LOGGER.info(DisplayConstants.ALL_PROJECTS);
		for (ApprovedProject project : projectList) {
			LOGGER.info(DisplayConstants.SPLITTER);
			LOGGER.info(project.getProjectID() + "\nProject Name: " + project.getProjectName() + "\nStatus: "
					+ project.getProjectStatus() + "\nStart Date: " + project.getStartDate() + "\nEnd Date: "
					+ project.getEndDate() + "\nProject Contractor: "
					+ contractorServices.getContractorName(project.getContrID()) + "\nLocation: "
					+ project.getLocation() + "\nEstimated Cost: $" + project.getEstCost() + "\nDescription: "
					+ project.getDescription() + "\n");
			LOGGER.info(DisplayConstants.SPLITTER);
		}
	}

	public void detailDisplay(int projectID, UserSession userLog) {
		int contrID = 0;
		String comment;
		ArrayList<ApprovedProject> projectList = displayProjects.detailDisplay(projectID);
		for (ApprovedProject project : projectList) {
			LOGGER.info(DisplayConstants.SPLITTER);
			LOGGER.info("Project ID: " + project.getProjectID() + "\nProject Name: " + project.getProjectName()
					+ "\n Project Status: " + project.getProjectStatus() + "\nProject Start Date: "
					+ project.getStartDate() + "\nProject End Date: " + project.getEndDate() + "\nContractor ID: "
					+ contractorServices.getContractorName(project.getContrID()) + "\nProject Location: "
					+ project.getLocation() + "\nProject Estimated Cost $" + project.getEstCost()
					+ "\nProject Description: " + project.getDescription() + "\n");
			contrID = project.getContrID();
		}
		ArrayList<Response> responseList = displayProjects.getResponses(projectID);
		if (responseList.isEmpty()) {
			LOGGER.info(DisplayConstants.NO_RESPONSE_FOUND);
		} else {
			LOGGER.info(DisplayConstants.RESPONSE_FOUND_IS);
			for (Response response : responseList) {
				LOGGER.info(response.getResponseText() + "\n");
			}
		}
		ArrayList<Comments> commentsList = displayProjects.getComments(projectID);
		LOGGER.info(DisplayConstants.COMMENTS_FOUND_ARE);
		if (commentsList.isEmpty()) {
			LOGGER.info(DisplayConstants.NO_COMMENTS_FOUND);
		} else {
			for (Comments eachComment : commentsList) {
				LOGGER.info(eachComment.getUserName() + " Commented That " + eachComment.getCommentMsg() + "\n");
			}
		}
		LOGGER.info(DisplayConstants.SPLITTER);
		LOGGER.info(DisplayConstants.DETAIL_DISPLAY_MENU);
		int commentOption = in.nextInt();
		in.nextLine();
		switch (commentOption) {
		case 1:
			LOGGER.info("Enter your Comment for Project ID " + projectID);
			comment = in.nextLine();
			try {
				userService.addComment(userLog.getUSER_ID(), projectID, comment);
			} catch (Exception e) {
				LOGGER.info(DisplayConstants.ERR_COMMENT);
			}
			break;
		case 2:
			// Display The Contractor Details Here !
			displayContractorDetailUI(userService.getContractorDetails(contrID));
			userService.getContractorDetails(contrID);
			break;
		case 3:
			break;
		}
		in.nextLine();
	}

	public void displayContractorDetailUI(Contractor contractor) {
		LOGGER.info(DisplayConstants.SPLITTER);
		LOGGER.info("Contractor Name: " + contractor.getFirstName() + "\nContractor Email: " + contractor.getEmail()
				+ "\nCompany Name: " + contractor.getCompany() + "\nContractor Revenue: $"
				+ contractor.getAnnualRevenue() + "\nNo Of Client: " + contractor.getNoOfClient() + "\n");
		LOGGER.info(DisplayConstants.SPLITTER);
	}

}
