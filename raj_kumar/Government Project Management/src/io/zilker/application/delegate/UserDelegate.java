package io.zilker.application.delegate;

import java.util.ArrayList;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.User;
import io.zilker.application.dao.ContractorDAO;
import io.zilker.application.dao.UserDAO;
import io.zilker.application.hash.Password;
import io.zilker.application.logsession.UserSession;

public class UserDelegate {
	UserDAO userDAO = new UserDAO();
	ContractorDAO contractorDAO = new ContractorDAO();

	public void userCreationService(User user) throws Exception {
		String hashedPassword = Password.getSecurePassword(user.getPassword());
		user.setPassword(hashedPassword);
		userDAO.userCreation(user);
	}

	public boolean isUserPresent(String username) {
		return userDAO.isUserPresentDAO(username);
	}

	public UserSession userLogin(String username, String password) {
		return userDAO.isUserPresentDAO(username, password);
	}

	public void addComment(int userID, int projectID, String comment) throws Exception {
		userDAO.addComment(userID, projectID, comment);
	}

	public ArrayList<ApprovedProject> displayProjects() {
		return userDAO.displayProjects();
	}

	public ArrayList<Comments> getComments(int projectID) {
		return userDAO.getComments(projectID);
	}

	public ArrayList<ApprovedProject> detailDisplay(int projectID) {
		return userDAO.projectDetails(projectID);
	}

	public ArrayList<ApprovedProject> projectsInLocationSer(int userID) {
		return userDAO.projectInMyLocation(userID);
	}

	public String getContractor(int contrID) {
		return userDAO.getContractorName(contrID);
	}

	public Contractor getContractorDetails(int contrID) {
		return contractorDAO.getContractorDetails(contrID);
	}

}
