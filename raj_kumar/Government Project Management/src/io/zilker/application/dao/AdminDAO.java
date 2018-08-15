package io.zilker.application.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.config.ConnectionConfig;
import io.zilker.application.constants.SQLConstants;

public class AdminDAO extends ConnectionConfig{
	private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());
	static Connection con = getConnection();
	public void executionResult(String firstName, boolean result) {
		if(result == true) {
			LOGGER.info("Details of "+firstName+" Not Saved");
		}else {
			LOGGER.info("Details of "+firstName+" Saved");
		}
	}
	
	public void insertNewProject(Project project) {
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.INSERT_NEW_PROJECT);
			preparedStmt.setString(1, project.getProjectName());
			preparedStmt.setString(2, project.getLocation());
			preparedStmt.setString(3, project.getDescription());
			executionResult(project.getProjectName(), preparedStmt.execute());
		}catch(SQLException e) {
			System.out.println("An Error Occured !");
			e.printStackTrace();
		}
	}
	
	public boolean adminCheck(String adminName, String password) {
		if(adminName.equals("Tony") && password.equals("tony")) {
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<RequestedProject> displayRequestedProjects() {
		ArrayList<RequestedProject> requestedList = new ArrayList<>();
		ResultSet rs = null;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.DISPLAY_REQUESTED_PROJ);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				RequestedProject requestedProject = new RequestedProject();
				requestedProject.setRequestID(rs.getInt("REQUEST_ID"));
				requestedProject.setProjectName(rs.getString("PROJ_NAME"));
				requestedProject.setStartDate(rs.getDate("START_DATE"));
				requestedProject.setEndDate(rs.getDate("END_DATE"));
				requestedProject.setEstCost(rs.getBigDecimal("EST_COST"));
				requestedProject.setLocation(rs.getString("LOCATION"));
				requestedProject.setDescription(rs.getString("DESCRIPTION"));
				requestedList.add(requestedProject);
			}
			closeConnection(con, preparedStmt, rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return requestedList;
	}
	
	public void approveProjectDAO(int requestID) {
		ResultSet rs = null;
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.GET_REQUESTED_PROJ);
			preparedStmt.setInt(1, requestID);
			rs = preparedStmt.executeQuery();
			rs.next();
			PreparedStatement preparedStmtApprove = con.prepareStatement(SQLConstants.APPROVE_PROJECT);
			preparedStmtApprove.setString(1, rs.getString("PROJ_NAME"));
			preparedStmtApprove.setDate(2, rs.getDate("START_DATE"));
			preparedStmtApprove.setDate(3, rs.getDate("END_DATE"));
			preparedStmtApprove.setInt(4, rs.getInt("CONTR_ID"));
			preparedStmtApprove.setString(5, "Ongoing");
			preparedStmtApprove.setString(6, rs.getString("LOCATION"));
			preparedStmtApprove.setBigDecimal(7, rs.getBigDecimal("EST_COST"));
			preparedStmtApprove.setString(8, rs.getString("DESCRIPTION"));
			executionResult(rs.getString("PROJ_NAME"),preparedStmtApprove.execute());
			PreparedStatement preparedStmtDelete = con.prepareStatement(SQLConstants.DELETE_REQUEST);
			// To delete in the available table 
			preparedStmtDelete.setInt(1, requestID);
			executionResult(rs.getString("PROJ_NAME"),preparedStmtDelete.execute());
			PreparedStatement availableDelete = con.prepareStatement(SQLConstants.DELETE_AVAILABLE);
			availableDelete.setInt(1, rs.getInt("AVAILABLE_ID"));
			executionResult(rs.getString("PROJ_NAME"),availableDelete.execute());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dailyStatusCheck() {
		try {
			PreparedStatement preparedStmt = con.prepareStatement(SQLConstants.GET_DELAYED_STATUS);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			preparedStmt.setDate(1, sqlDate);
			System.out.println(sqlDate);
			ResultSet rs = preparedStmt.executeQuery();
			while(rs.next()) {
				// Changing the Status of the Project ID
				PreparedStatement preparedStmtUpdate = con.prepareStatement(SQLConstants.UPDATE_STATUS);
				preparedStmtUpdate.setInt(1, rs.getInt("PROJ_ID"));
				LOGGER.info(preparedStmtUpdate.executeUpdate()+" Status Changed");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}














