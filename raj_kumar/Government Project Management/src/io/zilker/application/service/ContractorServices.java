package io.zilker.application.service;


import java.util.ArrayList;
import java.util.Date;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.logsession.ContractorLog;

public class ContractorServices {
	ContractorDelegate contractorDelegate = new ContractorDelegate();
	public void contractorCreationService(Contractor contractor) {
		contractorDelegate.contractorCreationService(contractor);
	}
	public ContractorLog contractorLoginService(String email, String password) {
		return contractorDelegate.contractorLoginService(email, password);
	}
	public void requestTender(int projectID, int contractorID, Date start, Date end, long estCost) {
		contractorDelegate.requestTender(projectID, contractorID, start, end, estCost);
	}
	
	public ArrayList<ApprovedProject> getContractorProject(int contractorID) {
		return contractorDelegate.getContractorProject(contractorID);
	}
	
	public ArrayList<ApprovedProject> delayedProjects(int contractorID) {
		return contractorDelegate.delayedProjects(contractorID);
	}
	public ArrayList<ApprovedProject> viewDelayedDetail(int ID, int contractorID) {
		return contractorDelegate.viewDelayedDetail(ID, contractorID);
	}
	public void addResponseService(int projectID, int contractorID, String response) {
		contractorDelegate.addResponseService(projectID, contractorID, response);
	}
	public ArrayList<AvailableProject> displayProjects() {
		return contractorDelegate.displayProjects();
	}
	public void projectCompleted(int projectID) {
		contractorDelegate.projectCompleted(projectID);
	}
}
