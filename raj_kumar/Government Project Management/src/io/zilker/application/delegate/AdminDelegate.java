package io.zilker.application.delegate;

import java.util.ArrayList;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.dao.AdminDAO;

public class AdminDelegate {
	AdminDAO adminDAO = new AdminDAO();

	public void addNewProject(Project project) throws Exception {
		adminDAO.insertNewProject(project);
	}

	public ArrayList<RequestedProject> displayRequested() {
		return adminDAO.displayRequestedProjects();
	}

	public void approveProject(int requestID) throws Exception {
		adminDAO.approveProjectDAO(requestID);
	}

	public ArrayList<Contractor> displayAllContractors() {
		return adminDAO.displayAllContractors();
	}
}
