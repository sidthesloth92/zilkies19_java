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
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.loginfo.ContractorLog;
import io.zilker.application.service.ContractorServices;


public class ContractorUI {
	ContractorServices contractorServices = new ContractorServices();
	private static final Logger LOGGER = Logger.getLogger(ContractorUI.class.getName());
	Scanner in = new Scanner(System.in);
	public void getContractorInput() {
		Contractor contractor = new Contractor();
		String name, companyName, annualRevenue, noOfClients;
		
		LOGGER.info("Enter the Contartor Name !");
		name = in.nextLine();
		contractor.name.setName(name);
		
		LOGGER.info("Enter the Contractor Email !");
		String email = in.nextLine();
		contractor.setEmail(email);
		
		LOGGER.info("Enter the Contractor Password !");
		String password = in.nextLine();
		contractor.setPassword(password);
		
		LOGGER.info("Enter the Company Name !");
		companyName = in.nextLine();
		contractor.company.setCompany(companyName);
		
		LOGGER.info("Enter the Annual Revenue of your Company !");
		annualRevenue = in.nextLine();
		contractor.revenue.setAnnualRevenue(Long.parseLong(annualRevenue));
		
		LOGGER.info("Enter the Number of client your company has !");
		noOfClients = in.nextLine();
		contractor.client.setNoOfClient(Integer.parseInt(noOfClients));
		
		contractorServices.contractorCreationService(contractor);
	}
	public boolean contractorLogin() {
		LOGGER.info("Enter your Email !");
		String email = in.next();
		LOGGER.info("Enter your Password !");
		String password = in.next();
		return contractorServices.contractorLoginService(email, password);
	}
	
	public void tenderRequestInput(int projectID) {
		int CONTR_ID = ContractorLog.getCONTR_ID();
		LOGGER.info("Your CONTR_ID is: "+CONTR_ID+" ! \nEnter the start eg: (12-08-1997)");
		String startDate = in.next();
		Date start = dateFormatter(startDate);
		LOGGER.info("Enter the End Date !");
		String endDate = in.next();
		Date end = dateFormatter(endDate);
		LOGGER.info("Enter the estimated Cost !");
		long estCost = in.nextLong();
		contractorServices.requestTender(projectID, CONTR_ID, start, end, estCost);
	}
	
	
	public static Date dateFormatter(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date2=null;
		try {
		    //Parsing the String
		    date2 = dateFormat.parse(date);
		} catch (ParseException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return date2;
	}
	
	public void contractorProjects() {
		int contractorID = ContractorLog.getCONTR_ID();
		ArrayList<ApprovedProject> contractorProj = contractorServices.getContractorProject(contractorID);
		for (ApprovedProject project : contractorProj) { 		      
	           System.out.print(project.getProjectID()+" ");
	           System.out.print(project.getProjectName()+" ");
	           System.out.print(project.getProjectStatus()+" "); 
	           System.out.print(project.getStartDate()+" ");
	           System.out.println(project.getEndDate()+" ");
	    }
	}
	
	public void viewDelayedProjects() {
		int contractorID = ContractorLog.getCONTR_ID();
		ArrayList<ApprovedProject> map = contractorServices.delayedProjects(contractorID);
		if(!map.isEmpty()) {
			for (ApprovedProject project : map) { 		      
		           System.out.print(project.getProjectID()+" ");
		           System.out.print(project.getProjectName()+" ");
		           System.out.print(project.getProjectStatus()+" "); 
		           System.out.print(project.getStartDate()+" ");
		           System.out.println(project.getEndDate()+" ");
		    }
			LOGGER.info("Enter the project ID you want to Select !");
			int ID = in.nextInt();
			ArrayList<ApprovedProject> listOfDelayed = contractorServices.viewDelayedDetail(ID, contractorID);
			for (ApprovedProject project : listOfDelayed) { 		      
		           System.out.print(project.getProjectID()+" ");
		           System.out.print(project.getProjectName()+" ");
		           System.out.print(project.getProjectStatus()+" "); 
		           System.out.print(project.getStartDate()+" ");
		           System.out.println(project.getEndDate()+" ");
		    }
			LOGGER.info(DisplayConstants.DELAYED_MENU);
			int option = in.nextInt();
			in.nextLine();
			switch(option) {
				case 1:
					LOGGER.info("Enter the Response text");
					String response = in.nextLine();
					contractorServices.addResponseService(ID, contractorID, response);
					break;
				case 2:
					break;
			}
		}else {
			LOGGER.info("You have no delayed Projects, Congrats !");
		}
	} 
	public void displayAvailableProjects() {
		ArrayList<AvailableProject> list = contractorServices.displayProjects();
		if(!list.isEmpty()) {
			for (AvailableProject project : list) { 		      
		           System.out.print(project.getProjectID()+" ");
		           System.out.print(project.getProjectName()+" ");
		           System.out.print(project.getLocation()+" ");
		           System.out.println(project.getDescription()+" ");
		    }
		}else {
			LOGGER.info("Sorry There are no Available Projects Right Now !");
		}
	}
	
}


















