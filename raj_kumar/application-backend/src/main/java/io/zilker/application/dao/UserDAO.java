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
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.constants.SQLConstants;
import io.zilker.application.hash.Password;
import io.zilker.application.logsession.UserSession;

public class UserDAO {
	private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

	public void executionResult(String firstName, boolean result) {
		LOGGER.info("Entering executionResult");
		if (result == true) {
			LOGGER.info("Details of " + firstName + " Not Saved");
		} else {
			LOGGER.info("Details of " + firstName + " Saved");
		}
		LOGGER.info("Leaving executionResult");
	}

	public ArrayList<ApprovedProject> displayProjects() {
		LOGGER.info("Entering displayProjects");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> projectList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.DISPLAY_PROJECTS);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				PreparedStatement preparedStmtGetContractor = con.prepareStatement(SQLConstants.GET_CONTRACTOR_NAME);
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("USER_ID"));
				preparedStmtGetContractor.setInt(1, rs.getInt("USER_ID"));
				ResultSet contractorRs = preparedStmtGetContractor.executeQuery();
				contractorRs.next();
				project.setContractorName(
						contractorRs.getString("FIRST_NAME") + " " + contractorRs.getString("LAST_NAME"));
//				String contractorName = userDAO.getContractorName(rs.getInt("USER_ID"));
//				project.setContractorName(contractorName);
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				project.setDescription(rs.getString("DESCRIPTION"));
				projectList.add(project);
			}
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving executionResult");
		}
		return projectList;
	}

	public void userCreation(User user) throws Exception {
		LOGGER.info("Entering userCreation");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.USER_CREATION);
			preparedStmt.setString(1, user.user.getUsername());
			preparedStmt.setString(2, user.user.getFirstName());
			preparedStmt.setString(3, user.user.getLastName());
			preparedStmt.setString(4, user.getPassword());
			preparedStmt.setString(5, user.getLocation());
			preparedStmt.setString(6, user.getRole());
			executionResult(user.user.getFirstName(), preparedStmt.execute());
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving userCreation");
		}
	}

	public String getContractorName(int contrID) {
		LOGGER.info("Entering getContractorName");
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
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving getContractorName");
		}
		return contrName;
	}

	public boolean isUserPresentDAO(String username) {
		LOGGER.info("Entering isUserPresentDAO");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		boolean returnValue = false;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.SELECT_USER_NAME);
			preparedStmt.setString(1, username);
			rs = preparedStmt.executeQuery();
			if (!rs.next()) {
				returnValue = false;
			} else {
				returnValue = true;
			}
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving isUserPresentDAO");
		}
		return returnValue;
	}

	public UserSession isUserPresentDAO(String username, String password) {
		LOGGER.info("Entering isUserPresentDAO");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		UserSession userLog = new UserSession();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.USER_LOGIN);
			preparedStmt.setString(1, username);
			String hashedPassword = Password.getSecurePassword(password);
			System.out.println(hashedPassword);
			preparedStmt.setString(2, hashedPassword);
			rs = preparedStmt.executeQuery();
			if (!rs.next()) {
				LOGGER.info("No such User Found !");
			} else {
				userLog.setUserId(rs.getInt("USER_ID"));
				userLog.setUserName(rs.getString("USERNAME"));
				userLog.setUserRoll(rs.getString("ROLE"));
				userLog.setFirstName(rs.getString("FIRST_NAME"));
				userLog.setLastName(rs.getString("LAST_NAME"));
				userLog.setLocation(rs.getString("LOCATION"));
				System.out.println(userLog.getUserRoll());
			}
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Entering isUserPresentDAO");
		}
		return userLog;
	}

	public ArrayList<ApprovedProject> projectDetails(int projectID) {
		LOGGER.info("Entering projectDetails");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> projectList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.PROJ_DETAIL);
			preparedStmt.setInt(1, projectID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("USER_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				project.setDescription(rs.getString("DESCRIPTION"));
				projectList.add(project);
			}
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving projectDetails");
		}
		return projectList;
	}

	public ArrayList<Comments> getComments(int projectID) {
		LOGGER.info("Entering getComments");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ResultSet rsUser = null;
		ArrayList<Comments> commentList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_COMMENTS);
			preparedStmt.setInt(1, projectID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
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
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving getComments");
		}
		return commentList;
	}

	public void addComment(int userID, int projectID, String comment) throws Exception {
		LOGGER.info("Entering addComment");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		try {
			preparedStmt = con.prepareStatement(SQLConstants.ADD_COMMENT);
			preparedStmt.setInt(1, projectID);
			preparedStmt.setString(2, comment);
			preparedStmt.setDate(3, sqlDate);
			preparedStmt.setInt(4, userID);
			executionResult(String.valueOf(userID), preparedStmt.execute());
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving addComment");
		}
	}

	public ArrayList<ApprovedProject> projectInMyLocation(int userID) {
		LOGGER.info("Entering projectInMyLocation");
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
			while (rsInLocation.next()) {
				ApprovedProject project = new ApprovedProject();
				PreparedStatement preparedStmtGetContractor = con.prepareStatement(SQLConstants.GET_CONTRACTOR_NAME);
				project.setProjectID(rsInLocation.getInt("PROJ_ID"));
				project.setProjectName(rsInLocation.getString("PROJ_NAME"));
				project.setStartDate(rsInLocation.getDate("START_DATE"));
				project.setEndDate(rsInLocation.getDate("END_DATE"));
				project.setContrID(rsInLocation.getInt("USER_ID"));
				preparedStmtGetContractor.setInt(1, rsInLocation.getInt("USER_ID"));
				ResultSet contractorRs = preparedStmtGetContractor.executeQuery();
				contractorRs.next();
				project.setContractorName(
						contractorRs.getString("FIRST_NAME") + " " + contractorRs.getString("LAST_NAME"));
				project.setProjectStatus(rsInLocation.getString("PROJ_STATUS"));
				project.setLocation(rsInLocation.getString("LOCATION"));
				project.setEstCost(rsInLocation.getBigDecimal("EST_COST"));
				project.setDescription(rsInLocation.getString("DESCRIPTION"));
				projectList.add(project);
			}
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rsInLocation);
			LOGGER.info("Leaving projectInMyLocation");
		}
		return projectList;
	}
}
