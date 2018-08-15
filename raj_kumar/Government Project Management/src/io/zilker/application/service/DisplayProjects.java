package io.zilker.application.service;

import java.util.ArrayList;
import java.util.Scanner;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.Response;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.delegate.UserDelegate;



public class DisplayProjects {
	Scanner in = new Scanner(System.in);
	UserDelegate userDelegate =  new UserDelegate();
	ContractorDelegate contractorDelegate = new ContractorDelegate();
	// Displaying All Projects
	public  ArrayList<ApprovedProject> displayProjects() {
		return userDelegate.displayProjects();
	}
	//Get Comments of a Particular Project
	public ArrayList<Comments> getComments(int projectID){
		return userDelegate.getComments(projectID);
	}
	// Get Responses of a Project
	public ArrayList<Response> getResponses(int projectID){
		return contractorDelegate.getResponses(projectID);
	}
	//Display a Particular Project
	public ArrayList<ApprovedProject> detailDisplay(int projectID) {
		return userDelegate.detailDisplay(projectID);
	}
	
	// Displaying Projects in the Users Location 
	public ArrayList<ApprovedProject> projectsInLocationSer(int userID) {
		return userDelegate.projectsInLocationSer(userID);
	}
	
}












