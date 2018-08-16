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
import io.zilker.application.hash.Password;
import io.zilker.application.logsession.UserLog;

public class UserDAO {
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	
	public void executionResult(String firstName, boolean result) {
		if(result == true) {
			LOGGER.info("Details of "+firstName+" Not Saved");
		}else {
			LOGGER.info("Details of "+firstName+" Saved");
		}
	}
	
	public ArrayList<ApprovedProject> displayProjects() {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> projectList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.DISPLAY_PROJECTS);
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
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return projectList;
	}
	
	public void userCreation(User user) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.USER_CREATION);
			preparedStmt.setString(1, user.user.getUsername());
			preparedStmt.setString(2, user.user.getFirstName());
			preparedStmt.setString(3, user.user.getLastName());
			preparedStmt.setString(4, user.password.getPassword());
			preparedStmt.setString(5, user.location.getLocation());
			executionResult(user.user.getFirstName(), preparedStmt.execute());
		}catch(Exception e) {
			System.out.println("An Error Occured !");
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}
	public String getContractorName(int contrID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		String contrName = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_CONTR_NAME);
			preparedStmt.setInt(1, contrID);
			rs = preparedStmt.executeQuery();
			rs.next();
			contrName = rs.getString("CONTR_NAME");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return contrName;
	}
	
	public boolean isUserPresentDAO(String username) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		boolean returnValue = false;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.SELECT_USER_NAME);
			preparedStmt.setString(1, username);
			rs = preparedStmt.executeQuery();
			if(!rs.next()) {
				returnValue = false;
			}else {
				returnValue = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return returnValue;
	}
	
	public UserLog isUserPresentDAO(String username, String password) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		UserLog userLog = new UserLog();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.USER_LOGIN);
			preparedStmt.setString(1, username);
			String hashedPassword = Password.getSecurePassword(password);
			System.out.println(hashedPassword);
			preparedStmt.setString(2, hashedPassword);
			rs = preparedStmt.executeQuery();
			if(!rs.next()) {
			}else {
				// Need to convert to a Non static variable 
				userLog.setUSER_ID(rs.getInt("USER_ID"));
				userLog.setUserName(rs.getString("USERNAME"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return userLog;
	}
	
	public ArrayList<ApprovedProject> projectDetails(int projectID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> projectList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.PROJ_DETAIL);
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
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return projectList;
	}
	
	public ArrayList<Comments> getComments(int projectID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ResultSet rsUser = null;
		ArrayList<Comments> commentList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_COMMENTS);
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
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return commentList;
	}
	
	public void addComment(int userID, int projectID, String comment) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		try {
			preparedStmt = con.prepareStatement(SQLConstants.ADD_COMMENT);
			preparedStmt .setInt(1, projectID);
			preparedStmt.setString(2, comment);
			preparedStmt.setDate(3, sqlDate);
			preparedStmt.setInt(4, userID);
			executionResult(String.valueOf(userID), preparedStmt.execute());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}
	
	public ArrayList<ApprovedProject> projectInMyLocation(int userID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ArrayList<ApprovedProject> projectList = new ArrayList<ApprovedProject>();
		ResultSet rsInLocation = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_USER_LOCATION);
			preparedStmt.setInt(1, userID);
			ResultSet rsLocation = preparedStmt.executeQuery();
			rsLocation.next();
			String location = rsLocation.getString("LOCATION");
			PreparedStatement preparedStmtProj = con.prepareStatement(SQLConstants.GET_PROJ_OF_LOCATION);
			preparedStmtProj.setString(1, location);
			rsInLocation = preparedStmtProj.executeQuery();
			while(rsInLocation.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rsInLocation.getInt("PROJ_ID"));
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
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rsInLocation);
		}
		return projectList;
	}
}
















