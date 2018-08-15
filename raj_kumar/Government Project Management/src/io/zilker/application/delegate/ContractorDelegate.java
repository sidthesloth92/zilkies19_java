package io.zilker.application.delegate;

import java.util.ArrayList;
import java.util.Date;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Response;
import io.zilker.application.dao.ContractorDAO;

public class ContractorDelegate {
	ContractorDAO contractorDAO = new ContractorDAO();
	public void contractorCreationService(Contractor contractor) {
		contractorDAO.contractorCreation(contractor);
	}
	public boolean contractorLoginService(String email, String password) {
		return contractorDAO.isPresent(email, password);
	}
	public void requestTender(int projectID, int contractorID, Date start, Date end, long estCost) {
		contractorDAO.tenderRequest(projectID, contractorID, start, end, estCost);
	}
	
	public ArrayList<ApprovedProject> getContractorProject(int contractorID) {
		return contractorDAO.getProjects(contractorID);
	}
	
	public ArrayList<ApprovedProject> delayedProjects(int contractorID) {
		return contractorDAO.delayedProjectsDAO(contractorID);
	}
	public ArrayList<ApprovedProject> viewDelayedDetail(int ID, int contractorID) {
		return contractorDAO.viewDelayedDetail(ID, contractorID);
	}
	public void addResponseService(int projectID, int contractorID, String response) {
		contractorDAO.addResponseDAO(projectID, contractorID, response);
	}
	public ArrayList<AvailableProject> displayProjects() {
		return contractorDAO.viewAvailableProjects();
	}
	public ArrayList<Response> getResponses(int projectID){
		return contractorDAO.displayResponse(projectID);
	}
}
