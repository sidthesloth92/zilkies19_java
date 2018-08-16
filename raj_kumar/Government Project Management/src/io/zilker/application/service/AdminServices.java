package io.zilker.application.service;

import java.util.ArrayList;


import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.delegate.AdminDelegate;

public class AdminServices {
	AdminDelegate adminDelegate = new AdminDelegate();
	public void addNewProject(Project project) {
		adminDelegate.addNewProject(project);
	}
	public boolean adminLoginService(String adminName, String password) {
		return adminDelegate.adminLoginService(adminName, password);
	}
	public ArrayList<RequestedProject> displayRequested() {
		return adminDelegate.displayRequested();
	}
	public void approveProject(int requestID) {
		adminDelegate.approveProject(requestID);
	}
}
