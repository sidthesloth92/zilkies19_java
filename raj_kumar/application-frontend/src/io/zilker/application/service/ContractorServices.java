package io.zilker.application.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.logsession.UserSession;

public class ContractorServices {
	private static final Logger logger = Logger.getLogger(ContractorServices.class.getName());
	ContractorDelegate contractorDelegate = new ContractorDelegate();

	public void contractorCreationService(Contractor contractor) throws Exception {
		logger.info("Entering contractorCreationService");
		contractorDelegate.contractorCreationService(contractor);
		logger.info("Leaving contractorCreationService");
	}

	public void requestTender(int projectID, int contractorID, Date start, Date end, long estCost) throws Exception {
		logger.info("Entering requestTender");
		contractorDelegate.requestTender(projectID, contractorID, start, end, estCost);
		logger.info("Leaving requestTender");
	}

	public ArrayList<ApprovedProject> getContractorProject(int contractorID) {
		logger.info("Entering getContractorProject");
		ArrayList<ApprovedProject> approvedProject = new ArrayList<ApprovedProject>();
		try {
			approvedProject = contractorDelegate.getContractorProject(contractorID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(DisplayConstants.SERVICE_LAYER_ERR);
		} finally {
			logger.info("Leaving getContractorProject");
		}
		return approvedProject;
	}

	public ArrayList<ApprovedProject> delayedProjects(int contractorID) {
		logger.info("Entering delayedProjects");
		return contractorDelegate.delayedProjects(contractorID);

	}

	public ArrayList<ApprovedProject> viewDelayedDetail(int ID, int contractorID) {
		return contractorDelegate.viewDelayedDetail(ID, contractorID);
	}

	public void addResponseService(int projectID, int contractorID, String response) throws Exception {
		contractorDelegate.addResponseService(projectID, contractorID, response);
	}

	public ArrayList<AvailableProject> displayProjects() {
		return contractorDelegate.displayProjects();
	}

	public void projectCompleted(int projectID) {
		contractorDelegate.projectCompleted(projectID);
	}

	public ArrayList<RequestedProject> getRequestedProjects(UserSession userSession) {
		return contractorDelegate.getRequestedProjects(userSession);
	}

	public void updateStartDate(int updateOption, int requestId, Date newStartDate) {
		contractorDelegate.updateStartDate(updateOption, requestId, newStartDate);
	}

	public void updateEndDate(int updateOption, int requestId, Date newStartDate) {
		contractorDelegate.updateEndDate(updateOption, requestId, newStartDate);
	}

	public void updateEstCost(int updateOption, int requestId, long newEstCost) {
		contractorDelegate.updateEstCost(updateOption, requestId, newEstCost);
	}

	public String getContractorName(int contractorID) {
		return contractorDelegate.getContractorName(contractorID);
	}
}
