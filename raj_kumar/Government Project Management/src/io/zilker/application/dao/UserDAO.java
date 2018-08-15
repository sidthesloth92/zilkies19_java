package io.zilker.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.User;
import io.zilker.application.config.ConnectionConfig;
import io.zilker.application.constants.SQLConstants;
import io.zilker.application.loginfo.UserLog;

public class UserDAO extends ConnectionConfig{
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	static Connection con = getConnection();
	public void executionResult(String firstName, boolean result) {
		if(result == true) {
			LOGGER.info("Details of "+firstName+" Not Saved");
		}else {
			LOGGER.info("Details of "+firstName+" Saved");
		}
	}
	
	public ArrayList<ApprovedProject> displayProjects() {
		ArrayList<ApprovedProject> projectList = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.DISPLAY_PROJECTS);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				project.setDescription(rs.getString("DESCRIPTION"));
				projectList.add(project);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return projectList;
	}
	
	public void userCreation(User user) {
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.USER_CREATION);
			preparedStmt.setString(1, user.user.getUsername());
			preparedStmt.setString(2, user.user.getFirstName());
			preparedStmt.setString(3, user.user.getLastName());
			preparedStmt.setString(4, user.password.getPassword());
			preparedStmt.setString(5, user.location.getLocation());
			executionResult(user.user.getFirstName(), preparedStmt.execute());
		}catch(Exception e) {
			System.out.println("An Error Occured !");
			e.printStackTrace();
		}
	}
	
	public boolean isUserPresentDAO(String username) {
		boolean returnValue = false;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.SELECT_USER_NAME);
			preparedStmt.setString(1, username);
			ResultSet rs = preparedStmt.executeQuery();
			if(!rs.next()) {
				returnValue = false;
			}else {
				returnValue = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public boolean isUserPresentDAO(String username, String password) {
		boolean returnValue = false;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.USER_LOGIN);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			ResultSet rs = preparedStmt.executeQuery();
			if(!rs.next()) {
				returnValue = false;
			}else {
				UserLog.setUSER_ID(rs.getInt("USER_ID"));
				returnValue = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public ArrayList<ApprovedProject> projectDetails(int projectID) {
		// Need to change all ResultSet to HashMap data and pass it  
		System.out.println("Inside User DAO !");
		ArrayList<ApprovedProject> projectList = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.PROJ_DETAIL);
			preparedStmt.setInt(1, projectID);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				project.setDescription(rs.getString("DESCRIPTION"));
				projectList.add(project);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return projectList;
	}
	
	public ArrayList<Comments> getComments(int projectID) {
		ResultSet rs = null;
		ResultSet rsUser = null;
		// Need to change all ResultSet to HashMap data and pass it 
		ArrayList<Comments> commentList = new ArrayList<>();
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.GET_COMMENTS);
			preparedStmt.setInt(1, projectID);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				Comments comment = new Comments();
				comment.setCommentMsg(rs.getString("COMMENTS"));
				comment.setCommentDate(rs.getDate("COMMENT_DATE"));
				PreparedStatement preparedStmtName = con.prepareStatement(SQLConstants.GET_COMMENT_NAME);
				preparedStmtName.setInt(1, rs.getInt("USER_ID"));
				rsUser = preparedStmtName.executeQuery();
				rsUser.next();
				comment.setUserName(rsUser.getString("USERNAME"));
				commentList.add(comment);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return commentList;
	}
	
	public void addComment(int userID, int projectID, String comment) {
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.ADD_COMMENT);
			preparedStmt .setInt(1, projectID);
			preparedStmt.setString(2, comment);
			preparedStmt.setDate(3, sqlDate);
			preparedStmt.setInt(4, userID);
			executionResult(String.valueOf(userID), preparedStmt.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ApprovedProject> projectInMyLocation(int userID) {
		// Need to change all ResultSet to HashMap data and pass it 
		ArrayList<ApprovedProject> projectList = new ArrayList<ApprovedProject>();
		ResultSet rsInLocation = null;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.GET_USER_LOCATION);
			preparedStmt.setInt(1, userID);
			ResultSet rsLocation = preparedStmt.executeQuery();
			rsLocation.next();
			String location = rsLocation.getString("LOCATION");
			PreparedStatement preparedStmtProj = con.prepareStatement(SQLConstants.GET_PROJ_OF_LOCATION);
			preparedStmtProj.setString(1, location);
			rsInLocation = preparedStmtProj.executeQuery();
			while(rsInLocation.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectName(rsInLocation.getString("PROJ_NAME"));
				project.setStartDate(rsInLocation.getDate("START_DATE"));
				project.setEndDate(rsInLocation.getDate("END_DATE"));
				project.setContrID(rsInLocation.getInt("CONTR_ID"));
				project.setProjectStatus(rsInLocation.getString("PROJ_STATUS"));
				project.setLocation(rsInLocation.getString("LOCATION"));
				project.setEstCost(rsInLocation.getBigDecimal("EST_COST"));
				project.setDescription(rsInLocation.getString("DESCRIPTION"));
				projectList.add(project);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return projectList;
	}
}
















