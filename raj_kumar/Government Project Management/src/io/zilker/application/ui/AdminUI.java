package io.zilker.application.ui;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.service.AdminServices;

public class AdminUI {
	private static final Logger LOGGER = Logger.getLogger(AdminUI.class.getName());
	Project project = new Project();
	AdminServices adminServices = new AdminServices();
	Scanner in = new Scanner(System.in);
	public void getNewProject() {
		
		LOGGER.info("Enter the Project Name !");
		String projectName = in.nextLine();
		project.setProjectName(projectName);
		
		LOGGER.info("Enter the project Location !");
		String location = in.nextLine();
		project.setLocation(location);
		
		LOGGER.info("Enter Some Project Description !");
		String description = in.nextLine();
		project.setDescription(description);
		
		adminServices.addNewProject(project);
	}
	
	public boolean adminLogin() {
		LOGGER.info("Enter Admin Name");
		String adminName = in.nextLine();
		
		LOGGER.info("Enter Admin Password !");
		String adminPassword = in.nextLine();
		
		return adminServices.adminLoginService(adminName, adminPassword);
	}
	
	
	public void approveProject() {
		ArrayList<RequestedProject> requested = adminServices.displayRequested();
		for (RequestedProject project : requested) { 		      
	           System.out.print(project.getRequestID()+" ");
	           System.out.print(project.getProjectName()+" ");
	           System.out.print(project.getDescription()+" "); 
	           System.out.print(project.getStartDate()+" ");
	           System.out.print(project.getEndDate()+" ");
	           System.out.print(project.getContrID()+" ");
	           System.out.print(project.getLocation()+" ");
	           System.out.println(project.getEstCost()+" ");
	    }
		LOGGER.info("Enter The Request Id you want to Approve !");
		int requestID = in.nextInt();
		adminServices.approveProject(requestID);
	}
}
















