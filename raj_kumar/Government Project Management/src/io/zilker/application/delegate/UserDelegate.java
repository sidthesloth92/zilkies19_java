package io.zilker.application.delegate;

import java.util.ArrayList;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.User;
import io.zilker.application.dao.UserDAO;
import io.zilker.application.loginfo.UserLog;

public class UserDelegate {
	UserDAO userDAO = new UserDAO();
	public void userCreationService(User user) {
		userDAO.userCreation(user);
	}
	public boolean isUserPresent(String username) {
		return userDAO.isUserPresentDAO(username);
	}
	public boolean userLogin(String username, String password) {
		return userDAO.isUserPresentDAO(username, password);
	}
	public void addComment(int userID, int projectID, String comment) {
		userDAO.addComment(UserLog.getUSER_ID(), projectID, comment);
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
	
}
