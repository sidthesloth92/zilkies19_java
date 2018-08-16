package io.zilker.application.ui;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.Response;
import io.zilker.application.beans.User;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.constants.ValidationConstants;
import io.zilker.application.logsession.UserLog;
import io.zilker.application.service.DisplayProjects;
import io.zilker.application.service.UserServices;
import io.zilker.application.utils.UserValidation;


public class UserUI {
	private static final Logger LOGGER = Logger.getLogger(UserUI.class.getName());
	UserServices userService = new UserServices();
	DisplayProjects displayProjects = new DisplayProjects();
	Scanner in = new Scanner(System.in);
	
	// Creating a New User
	public void getUserInput() {
		String password, retypePassword, username;
		User user = new User();
		LOGGER.info("Enter the user First Name !");
		String fName = in.nextLine();
		while(!UserValidation.isValid(fName, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info("You Have Entered a Invalid Name ! Enter Again");
			fName = in.nextLine();
		}
		user.user.setFirstName(fName);
		LOGGER.info("Enter the user Last Name !");
		String lastName = in.nextLine();
		while(!UserValidation.isValid(lastName, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info("You Have Entered a Invalid Name ! Enter Again");
			lastName = in.nextLine();
		}
		user.user.setLastName(lastName);
		do {
			LOGGER.info("Enter a unique Username For your Account !");
			username = in.nextLine();
		}while(userService.isUserPresent(username));
		user.user.setUsername(username);
		do {
			LOGGER.info("Enter a Password ! (Please Do Remember it !)");
			password = in.nextLine();
			LOGGER.info("Enter the Password Again");
			retypePassword = in.nextLine();
		}while(!(password.equals(retypePassword)));
		user.password.setPassword(password);
		
		LOGGER.info("Enter the location !");
		String location = in.nextLine();
		while(!UserValidation.isValid(location, ValidationConstants.NAME_VALIDATION)) {
			LOGGER.info("You Have Entered a Invalid Location ! Enter Again");
			location = in.nextLine();
		}
		user.location.setLocation(location);
		userService.userCreationService(user);
	}
	
	// Making the User Login
	public UserLog userLogin() {
		LOGGER.info("Enter the User Name");
		String username = in.next();
		LOGGER.info("Enter the Password !");
		String password = in.next();
		return userService.userLogin(username, password);
	}
	// To Check if the User is Logged in 
	public boolean isLoggedIn(UserLog userLog) {
		if(userLog.getUSER_ID() == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	// To get the Projects in the Users Location
	public void projectsInLocation(UserLog userLog) {
		int userID = userLog.getUSER_ID();
		ArrayList<ApprovedProject> projectList = displayProjects.projectsInLocationSer(userID);
		if(!projectList.isEmpty()) {
			for (ApprovedProject project : projectList) { 		      
		           System.out.print(project.getProjectID()+"         ");
		           System.out.print(project.getProjectName()+"   ");
		           System.out.print(project.getProjectStatus()+"   "); 
		           System.out.print(project.getStartDate()+"   ");
		           System.out.print(project.getEndDate()+"   ");
		           System.out.print(project.getContrID()+"   ");
		           System.out.print(project.getLocation()+"   ");
		           System.out.print(project.getEstCost()+"   ");
		           System.out.println(project.getDescription()+" ");
		    }
		}else {
			LOGGER.info("Sorry, No Projects in Your Location !");
		}
		
	}
	
	
	public void displayAllprojects() {
		ArrayList<ApprovedProject> projectList = displayProjects.displayProjects();
		LOGGER.info(DisplayConstants.ALL_PROJECTS);
		for (ApprovedProject project : projectList) { 		      
	           System.out.print(project.getProjectID()+"        ");
	           System.out.print(project.getProjectName()+"      ");
	           System.out.print(project.getProjectStatus()+"     "); 
	           System.out.print(project.getStartDate()+"     ");
	           System.out.print(project.getEndDate()+"     ");
	           System.out.print(project.getContrID()+"     ");
	           System.out.print(project.getLocation()+"     ");
	           System.out.print(project.getEstCost()+"     ");
	           System.out.println(project.getDescription()+" ");
	    }
	}
	
	public void detailDisplay(int projectID, UserLog userLog) {
		String comment;
		ArrayList<ApprovedProject> projectList = displayProjects.detailDisplay(projectID);
		for (ApprovedProject project : projectList) {
	           System.out.print(project.getProjectID()+" ");
	           System.out.println("Project Name: "+project.getProjectName()+"\n");
	           System.out.println("Status: "+project.getProjectStatus()+"\n"); 
	           System.out.println("Contractor Name: "+userService.getContractor(project.getContrID())+"\n");
	           System.out.println("Start Date: "+project.getStartDate()+"\n");
	           System.out.print("End Date: "+project.getEndDate());
	           System.out.println(project.getContrID()+"\n");
	           System.out.println("Location is: "+project.getLocation()+"\n");
	           System.out.println("Estimated Cost is: "+project.getEstCost()+"\n");
	           System.out.println("Description: "+project.getDescription()+"\n");
	    }
		ArrayList<Response> responseList = displayProjects.getResponses(projectID);
		if(responseList.isEmpty()) {
			LOGGER.info("No Response Found !");
		}else {
			System.out.println("The Response is ");
			for (Response response : responseList) { 		      
		           System.out.println(response.getResponseText()+" ");
		    }
			System.out.println("");
		}
		ArrayList<Comments> commentsList = displayProjects.getComments(projectID);
		if(commentsList.isEmpty()) {
			LOGGER.info("No Comments Found !");
		}else {
			for (Comments eachComment : commentsList) { 		      
		           System.out.print(eachComment.getUserName()+" Commented That ");
		           System.out.println(eachComment.getCommentMsg()+"   ");
		    }
		}
		LOGGER.info("Enter -1 if you want to comment !");
		int commentOption = in.nextInt();
		in.nextLine();
		if(commentOption == -1) {
			LOGGER.info("Enter your Comment for Project ID "+projectID);
			comment = in.nextLine();
			userService.addComment(userLog.getUSER_ID(), projectID, comment);
			
		}
	}
	
}







