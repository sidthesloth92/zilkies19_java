package io.zilker.application.delegate;

import java.util.ArrayList;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.User;
import io.zilker.application.dao.UserDAO;
import io.zilker.application.hash.Password;
import io.zilker.application.logsession.UserLog;

public class UserDelegate {
	UserDAO userDAO = new UserDAO();
	public void userCreationService(User user) {
		// Need to add Password Hashing 
		String hashedPassword = Password.getSecurePassword(user.password.getPassword());
		user.password.setPassword(hashedPassword);
		userDAO.userCreation(user);
	}
	public boolean isUserPresent(String username) {
		return userDAO.isUserPresentDAO(username);
	}
	public UserLog userLogin(String username, String password) {
		return userDAO.isUserPresentDAO(username, password);
	}
	public void addComment(int userID, int projectID, String comment) {
		userDAO.addComment(userID, projectID, comment);
	}
	public ArrayList<ApprovedProject> displayProjects(){
		return userDAO.displayProjects();
	}
	public ArrayList<Comments> getComments(int projectID){
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
	
}
