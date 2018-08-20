package io.zilker.application.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.config.ConnectionConfig;
import io.zilker.application.constants.SQLConstants;

public class AdminDAO {
	private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());

	public void executionResult(String firstName, boolean result) {
		if (result == true) {
			LOGGER.info("Details of " + firstName + " Not Saved");
		} else {
			LOGGER.info("Details of " + firstName + " Saved");
		}
	}

	public void insertNewProject(Project project) throws Exception {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.INSERT_NEW_PROJECT);
			preparedStmt.setString(1, project.getProjectName());
			preparedStmt.setString(2, project.getLocation());
			// preparedStmt.setString(3, project.getDescription());
			executionResult(project.getProjectName(), preparedStmt.execute());
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public ArrayList<RequestedProject> displayRequestedProjects() {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ArrayList<RequestedProject> requestedList = new ArrayList<>();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.DISPLAY_REQUESTED_PROJ);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
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
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return requestedList;
	}

	public void approveProjectDAO(int requestID) throws Exception {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_REQUESTED_PROJ);
			preparedStmt.setInt(1, requestID);
			rs = preparedStmt.executeQuery();
			rs.next();
			PreparedStatement preparedStmtApprove = con.prepareStatement(SQLConstants.APPROVE_PROJECT);
			preparedStmtApprove.setString(1, rs.getString("PROJ_NAME"));
			preparedStmtApprove.setDate(2, rs.getDate("START_DATE"));
			preparedStmtApprove.setDate(3, rs.getDate("END_DATE"));
			preparedStmtApprove.setInt(4, rs.getInt("USER_ID"));
			preparedStmtApprove.setString(5, "Ongoing");
			preparedStmtApprove.setString(6, rs.getString("LOCATION"));
			preparedStmtApprove.setBigDecimal(7, rs.getBigDecimal("EST_COST"));
			preparedStmtApprove.setString(8, rs.getString("DESCRIPTION"));
			executionResult(rs.getString("PROJ_NAME"), preparedStmtApprove.execute());
			PreparedStatement preparedStmtDelete = con.prepareStatement(SQLConstants.DELETE_REQUEST);
			// To delete in the available table
			preparedStmtDelete.setInt(1, requestID);
			executionResult(rs.getString("PROJ_NAME"), preparedStmtDelete.execute());
			PreparedStatement availableDelete = con.prepareStatement(SQLConstants.DELETE_AVAILABLE);
			availableDelete.setInt(1, rs.getInt("AVAILABLE_ID"));
			executionResult(rs.getString("PROJ_NAME"), availableDelete.execute());
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public void dailyStatusCheck() throws Exception {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_DELAYED_STATUS);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(dateFormat.format(new Date()));
			// System.out.println(java.time.LocalDate.now());
			preparedStmt.setString(1, dateFormat.format(new Date()));
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				// Changing the Status of the Project ID
				PreparedStatement preparedStmtUpdate = con.prepareStatement(SQLConstants.UPDATE_STATUS);
				preparedStmtUpdate.setInt(1, rs.getInt("PROJ_ID"));
				LOGGER.info(preparedStmtUpdate.executeUpdate() + " Status Changed");
			}
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public static Date dateFormatter(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2;
	}

	public ArrayList<Contractor> displayAllContractors() {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ArrayList<Contractor> contractorList = new ArrayList<>();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.ALL_CONTRACTORS);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				Contractor contractor = new Contractor();
				contractor.setContrId(rs.getInt("USER_ID"));
				contractor.setFirstName(rs.getString("FIRST_NAME"));
				contractor.setLastName(rs.getString("LAST_NAME"));
				contractor.setCompany(rs.getString("COMPANY_NAME"));
				contractor.setLocation(rs.getString("LOCATION"));
				contractor.setAnnualRevenue(rs.getLong("ANNUAL_REVENUE"));
				contractor.setEmail(rs.getString("EMAIL"));
				contractor.setNoOfClient(rs.getInt("NO_OF_CLIENT"));
				contractorList.add(contractor);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return contractorList;
	}
}
