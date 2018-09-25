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
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.constants.SQLConstants;
import io.zilker.application.exception.SystemException;

public class AdminDAO {
	private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());

	public void executionResult(String firstName, boolean result) {
		LOGGER.info("Entering executionResult");
		if (result == true) {
			LOGGER.info("Details of " + firstName + " Not Saved");
		} else {
			LOGGER.info("Details of " + firstName + " Saved");
		}
		LOGGER.info("Leaving executionResult");
	}

	public void insertNewProject(Project project) throws Exception {
		LOGGER.info("Entering insertNewProject");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.INSERT_NEW_PROJECT);
			preparedStmt.setString(1, project.getProjectName());
			preparedStmt.setString(2, project.getLocation());
			preparedStmt.setString(3, project.getDescription());
			executionResult(project.getProjectName(), preparedStmt.execute());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving insertNewProject");
		}
	}

	public ArrayList<RequestedProject> displayRequestedProjects() throws Exception {
		LOGGER.info("Entering displayRequestedProjects");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ArrayList<RequestedProject> requestedList = new ArrayList<>();
		ResultSet rs = null;

		try {
			preparedStmt = con.prepareStatement(SQLConstants.DISPLAY_REQUESTED_PROJ);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				RequestedProject requestedProject = new RequestedProject();
				System.out.println(rs.getInt("REQUEST_ID"));
				requestedProject.setRequestID(rs.getInt("REQUEST_ID"));
				requestedProject.setProjectName(rs.getString("PROJ_NAME"));
				requestedProject.setStartDate(rs.getDate("START_DATE"));
				requestedProject.setEndDate(rs.getDate("END_DATE"));
				requestedProject.setEstCost(rs.getBigDecimal("EST_COST"));
				requestedProject.setLocation(rs.getString("LOCATION"));
				requestedProject.setDescription(rs.getString("DESCRIPTION"));
				PreparedStatement getUsername = con.prepareStatement(SQLConstants.GET_CONTRACTOR_NAME);
				getUsername.setInt(1, rs.getInt("USER_ID"));
				ResultSet nameRs = getUsername.executeQuery();
				nameRs.next();
				requestedProject
						.setContractorName(nameRs.getString("FIRST_NAME") + " " + nameRs.getString("LAST_NAME"));
				requestedList.add(requestedProject);
			}
		} catch (SQLException e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving displayRequestedProjects");
		}
		return requestedList;
	}

	public void approveProjectDAO(int requestID) throws Exception {
		LOGGER.info("Entering approveProjectDAO");
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
			// To change the status of other project request to cancelled
			PreparedStatement changeRequestStatus = con.prepareStatement(SQLConstants.CHANGE_REQUEST_STATUS);
			changeRequestStatus.setString(1, "CANCELLED");
			changeRequestStatus.setInt(2, rs.getInt("AVAILABLE_ID"));
			executionResult(rs.getString("PROJ_NAME"), changeRequestStatus.execute());
			// To delete the other application for the same available ID
//			PreparedStatement deleteOtherRequest = con.prepareStatement(SQLConstants.DELETE_OTHER_REQUEST);
//			deleteOtherRequest.setInt(1, rs.getInt("AVAILABLE_ID"));
//			executionResult(rs.getString("PROJ_NAME"), deleteOtherRequest.execute());

			PreparedStatement availableDelete = con.prepareStatement(SQLConstants.DELETE_AVAILABLE);
			availableDelete.setInt(1, rs.getInt("AVAILABLE_ID"));
			executionResult(rs.getString("PROJ_NAME"), availableDelete.execute());
		} catch (SQLException e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving approveProjectDAO");
		}
	}

	public void dailyStatusCheck() {
		LOGGER.info("Entering dailyStatusCheck");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {

			preparedStmt = con.prepareStatement(SQLConstants.GET_DELAYED_STATUS);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(dateFormat.format(new Date()));
			preparedStmt.setString(1, dateFormat.format(new Date()));
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				// Changing the Status of the Project ID
				PreparedStatement preparedStmtUpdate = con.prepareStatement(SQLConstants.UPDATE_STATUS);
				preparedStmtUpdate.setInt(1, rs.getInt("PROJ_ID"));
				LOGGER.info(preparedStmtUpdate.executeUpdate() + " Status Changed");
			}
		} catch (SQLException e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving dailyStatusCheck");
		}
	}

	public static Date dateFormatter(String date) {
		LOGGER.info("Entering dailyStatusCheck");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date2 = null;
		try {
			date2 = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
		} finally {
			LOGGER.info("Leaving dailyStatusCheck");
		}
		return date2;
	}

	public ArrayList<Contractor> displayAllContractors() throws Exception {
		LOGGER.info("Entering displayAllContractors");
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
			LOGGER.info(DisplayConstants.SQL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving displayAllContractors");
		}
		return contractorList;
	}

	public Contractor getContractor(int contractorID) throws Exception {
		LOGGER.info("Entering getContractor");
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		Contractor contractor = new Contractor();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_CONTRACTOR_DETAIL);
			preparedStmt.setInt(1, contractorID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				contractor = new Contractor();
				contractor.setContrId(rs.getInt("USER_ID"));
				contractor.setUsername(rs.getString("USERNAME"));
				contractor.setFirstName(rs.getString("FIRST_NAME"));
				contractor.setLastName(rs.getString("LAST_NAME"));
				contractor.setCompany(rs.getString("COMPANY_NAME"));
				contractor.setLocation(rs.getString("LOCATION"));
				contractor.setAnnualRevenue(rs.getLong("ANNUAL_REVENUE"));
				contractor.setEmail(rs.getString("EMAIL"));
				contractor.setNoOfClient(rs.getInt("NO_OF_CLIENT"));
			}
		} catch (Exception e) {
			LOGGER.info(DisplayConstants.SQL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
			LOGGER.info("Leaving getContractor");
		}
		return contractor;
	}
}
