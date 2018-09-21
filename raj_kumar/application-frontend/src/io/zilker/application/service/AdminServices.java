package io.zilker.application.service;

import java.util.ArrayList;
import java.util.logging.Logger;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.delegate.AdminDelegate;

public class AdminServices {
	private static final Logger LOGGER = Logger.getLogger(AdminServices.class.getName());
	AdminDelegate adminDelegate = new AdminDelegate();

	public void addNewProject(Project project) throws Exception {
		adminDelegate.addNewProject(project);
	}

	public ArrayList<RequestedProject> displayRequested() {
		return adminDelegate.displayRequested();
	}

	public void approveProject(int requestID) {
		try {
			adminDelegate.approveProject(requestID);
		}catch (Exception e) {
			LOGGER.info("An Error Occured While Approving a Project !");
		}
		
	}

	public ArrayList<Contractor> displayAllContractors() {
		return adminDelegate.displayAllContractors();
	}
}
