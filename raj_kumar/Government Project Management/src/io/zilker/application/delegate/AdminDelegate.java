package io.zilker.application.delegate;

import java.util.ArrayList;

import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.dao.AdminDAO;

public class AdminDelegate {
	AdminDAO adminDAO = new AdminDAO();
	public void addNewProject(Project project) {
		adminDAO.insertNewProject(project);
	}
	public boolean adminLoginService(String adminName, String password) {
		return adminDAO.adminCheck(adminName, password);
	}
	public ArrayList<RequestedProject> displayRequested() {
		return  adminDAO.displayRequestedProjects();
	}
	public void approveProject(int requestID) {
		adminDAO.approveProjectDAO(requestID);
	}
}
