package io.zilker.application.delegate;

import java.util.ArrayList;
import java.util.Date;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.beans.Response;
import io.zilker.application.dao.ContractorDAO;
import io.zilker.application.hash.Password;
import io.zilker.application.logsession.UserSession;

public class ContractorDelegate {
	ContractorDAO contractorDAO = new ContractorDAO();

	public void contractorCreationService(Contractor contractor) throws Exception {
		String hashedPassword = Password.getSecurePassword(contractor.getPassword());
		contractor.setPassword(hashedPassword);
		contractorDAO.contractorCreation(contractor);
	}

	public void requestTender(int projectID, int contractorID, Date start, Date end, long estCost) throws Exception {
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

	public void addResponseService(int projectID, int contractorID, String response) throws Exception {
		contractorDAO.addResponseDAO(projectID, contractorID, response);
	}

	public ArrayList<AvailableProject> displayProjects() {
		return contractorDAO.viewAvailableProjects();
	}

	public ArrayList<Response> getResponses(int projectID) {
		return contractorDAO.displayResponse(projectID);
	}

	public void projectCompleted(int projectID) {
		contractorDAO.projectCompleted(projectID);
	}

	public ArrayList<RequestedProject> getRequestedProjects(UserSession userSession) {
		return contractorDAO.getRequestedProjects(userSession);
	}

	public void updateStartDate(int updateOption, int requestId, Date newStartDate) {
		contractorDAO.updateStartDate(updateOption, requestId, newStartDate);
	}

	public void updateEndDate(int updateOption, int requestId, Date newStartDate) {
		contractorDAO.updateEndDate(updateOption, requestId, newStartDate);
	}

	public void updateEstCost(int updateOption, int requestId, long newEstDate) {
		contractorDAO.updateEstCost(updateOption, requestId, newEstDate);
	}
	public String getContractorName(int contractorID) {
		return contractorDAO.getContractorName(contractorID);
	}
}
