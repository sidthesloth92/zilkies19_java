package io.zilker.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.beans.Response;
import io.zilker.application.config.ConnectionConfig;
import io.zilker.application.constants.SQLConstants;
import io.zilker.application.logsession.UserSession;

public class ContractorDAO {
	private static final Logger LOGGER = Logger.getLogger(ContractorDAO.class.getName());

	public void executionResult(String firstName, boolean result) {
		if (result == true) {
			LOGGER.info("Details of " + firstName + " Not Saved");
		} else {
			LOGGER.info("Details of " + firstName + " Saved");
		}
	}

	public void contractorCreation(Contractor contractor) throws Exception {
		PreparedStatement preparedStmt = null, preparedStmtUser = null, preparedStmtRecent = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null, recentID = null;
		try {
			preparedStmtUser = con.prepareStatement(SQLConstants.USER_CREATION);
			// First Store it in the user table and then in the contractor details
			preparedStmtUser.setString(1, contractor.getUsername());
			preparedStmtUser.setString(2, contractor.getFirstName());
			preparedStmtUser.setString(3, contractor.getLastName());
			preparedStmtUser.setString(4, contractor.getPassword());
			preparedStmtUser.setString(5, contractor.getLocation());
			preparedStmtUser.setString(6, "CONTRACTOR");
			preparedStmtUser.execute();

			preparedStmtRecent = con.prepareStatement(SQLConstants.GET_RECENT_ID);
			recentID = preparedStmtRecent.executeQuery();
			recentID.next();
			int recentUserID = recentID.getInt("USER_ID");

			preparedStmt = con.prepareStatement(SQLConstants.CONTRACTOR_CREATION);
			preparedStmt.setInt(1, recentUserID);
			preparedStmt.setString(2, contractor.getCompany());
			preparedStmt.setLong(3, contractor.getAnnualRevenue());
			preparedStmt.setInt(4, contractor.getNoOfClient());
			preparedStmt.setString(5, contractor.getEmail());
			executionResult(contractor.getFirstName(), preparedStmt.execute());
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public ArrayList<AvailableProject> viewAvailableProjects() {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ArrayList<AvailableProject> listOfAvailable = new ArrayList<>();
		ResultSet rs = null;
		try {
			java.sql.Statement st = con.createStatement();
			rs = st.executeQuery(SQLConstants.SELECT_AVAILABLE_PROJ);
			while (rs.next()) {
				AvailableProject availableProject = new AvailableProject();
				availableProject.setProjectID(rs.getInt("ID"));
				availableProject.setProjectName(rs.getString("PROJ_NAME"));
				availableProject.setLocation(rs.getString("LOCATION"));
				availableProject.setDescription(rs.getString("DESCRIPTION"));
				listOfAvailable.add(availableProject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfAvailable;
	}

	public void tenderRequest(int projectID, int CONTR_ID, Date start, Date end, long estCost) throws Exception {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		java.sql.Date startDate = new java.sql.Date(start.getTime());
		java.sql.Date endDate = new java.sql.Date(end.getTime());
		try {
			PreparedStatement preparedStmtAvailable = con.prepareStatement(SQLConstants.SELECT_UNAPPROVED);
			preparedStmtAvailable.setInt(1, projectID);
			rs = preparedStmtAvailable.executeQuery();
			rs.next();
			preparedStmt = con.prepareStatement(SQLConstants.TENDER_REQUEST);
			preparedStmt.setInt(1, CONTR_ID);
			preparedStmt.setString(2, rs.getString("PROJ_NAME"));
			preparedStmt.setDate(3, startDate);
			preparedStmt.setDate(4, endDate);
			preparedStmt.setLong(5, estCost);
			preparedStmt.setString(6, rs.getString("LOCATION"));
			preparedStmt.setString(7, rs.getString("DESCRIPTION"));
			preparedStmt.setInt(8, projectID);
			executionResult(String.valueOf(CONTR_ID), preparedStmt.execute());
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public ArrayList<ApprovedProject> getProjects(int contractorID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> listOfProjects = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.CONTRACTOR_PROJECT);
			preparedStmt.setInt(1, contractorID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				listOfProjects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfProjects;
	}

	public ArrayList<ApprovedProject> delayedProjectsDAO(int contractorID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> listOfProject = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_DELAYED_PROJ);
			preparedStmt.setString(1, "Delayed");
			preparedStmt.setInt(2, contractorID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				listOfProject.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfProject;
	}

	public ArrayList<ApprovedProject> viewDelayedDetail(int ID, int contractorID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<ApprovedProject> listOfProjects = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.VIEW_DELAYED_DETAIL);
			preparedStmt.setString(1, "Delayed");
			preparedStmt.setInt(2, contractorID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				ApprovedProject project = new ApprovedProject();
				project.setProjectID(rs.getInt("PROJ_ID"));
				project.setProjectName(rs.getString("PROJ_NAME"));
				project.setStartDate(rs.getDate("START_DATE"));
				project.setEndDate(rs.getDate("END_DATE"));
				project.setContrID(rs.getInt("CONTR_ID"));
				project.setProjectStatus(rs.getString("PROJ_STATUS"));
				project.setLocation(rs.getString("LOCATION"));
				project.setEstCost(rs.getBigDecimal("EST_COST"));
				listOfProjects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return listOfProjects;
	}

	public void addResponseDAO(int projectID, int contractorID, String response) throws Exception {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.ADD_RESPONSE);
			preparedStmt.setInt(1, projectID);
			preparedStmt.setString(2, response);
			executionResult(String.valueOf(projectID), preparedStmt.execute());
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public ArrayList<Response> displayResponse(int projectID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		ArrayList<Response> responsesList = new ArrayList<>();
		try {
			preparedStmt = con.prepareStatement(SQLConstants.VIEW_RESPONSE);
			preparedStmt.setInt(1, projectID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				Response response = new Response();
				response.setProjectID(rs.getInt("PROJ_ID"));
				response.setResponseText(rs.getString("RESPONSE_TEXT"));
				responsesList.add(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return responsesList;
	}

	public void projectCompleted(int projectID) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.PROJECT_COMPLETED);
			preparedStmt.setInt(1, projectID);
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public ArrayList<RequestedProject> getRequestedProjects(UserSession userSession) {
		ArrayList<RequestedProject> list = new ArrayList<RequestedProject>();
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.SHOW_APPLIED_PROJECT);
			preparedStmt.setInt(1, userSession.getUSER_ID());
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				RequestedProject requestedProject = new RequestedProject();
				requestedProject.setContrID(rs.getInt("USER_ID"));
				requestedProject.setProjectName(rs.getString("PROJ_NAME"));
				requestedProject.setRequestID(rs.getInt("REQUEST_ID"));
				requestedProject.setStartDate(rs.getDate("START_DATE"));
				requestedProject.setEndDate(rs.getDate("END_DATE"));
				requestedProject.setEstCost(rs.getBigDecimal("EST_COST"));
				requestedProject.setLocation(rs.getString("LOCATION"));
				requestedProject.setDescription(rs.getString("DESCRIPTION"));
				requestedProject.setAvailableID(rs.getInt("AVAILABLE_ID"));
				list.add(requestedProject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return list;
	}

	public void updateStartDate(int updateOption, int requestId, Date newStartDate) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		java.sql.Date startDate = new java.sql.Date(newStartDate.getTime());
		try {
			preparedStmt = con.prepareStatement(SQLConstants.UPDATE_START_DATE);
			preparedStmt.setDate(1, startDate);
			preparedStmt.setInt(2, requestId);
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public void updateEndDate(int updateOption, int requestId, Date newEndDate) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		java.sql.Date endDate = new java.sql.Date(newEndDate.getTime());
		try {
			preparedStmt = con.prepareStatement(SQLConstants.UPDATE_END_DATE);
			preparedStmt.setDate(1, endDate);
			preparedStmt.setInt(2, requestId);
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public void updateEstCost(int updateOption, int requestId, long newEstCost) {
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.UPDATE_EST_COST);
			preparedStmt.setLong(1, newEstCost);
			preparedStmt.setInt(2, requestId);
			preparedStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
	}

	public Contractor getContractorDetails(int contrID) {
		Contractor contractor = new Contractor();
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_CONTR_DETAIL);
			preparedStmt.setInt(1, contrID);
			rs = preparedStmt.executeQuery();
			while (rs.next()) {
				contractor.setFirstName(rs.getString("FIRST_NAME"));
				contractor.setCompany(rs.getString("COMPANY_NAME"));
				contractor.setAnnualRevenue(rs.getLong("ANNUAL_REVENUE"));
				contractor.setNoOfClient(rs.getInt("NO_OF_CLIENT"));
				contractor.setEmail(rs.getString("EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return contractor;
	}
	
	
	public String getContractorName(int contractorID) {
		String contractorName = null;
		PreparedStatement preparedStmt = null;
		Connection con = ConnectionConfig.getConnection();
		ResultSet rs = null;
		try {
			preparedStmt = con.prepareStatement(SQLConstants.GET_CONTRACTOR_NAME);
			preparedStmt.setInt(1, contractorID);
			rs = preparedStmt.executeQuery();
			rs.next();
			contractorName = rs.getString("FIRST_NAME")+" "+rs.getString("LAST_NAME");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionConfig.closeConnection(con, preparedStmt, rs);
		}
		return contractorName;
	}
}
