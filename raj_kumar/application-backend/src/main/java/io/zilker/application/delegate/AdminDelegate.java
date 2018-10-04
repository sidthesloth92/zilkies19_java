package io.zilker.application.delegate;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.Gson;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.dao.AdminDAO;
import io.zilker.application.exception.SystemException;

public class AdminDelegate {
	private static final Logger logger = Logger.getLogger(AdminDelegate.class.getName());
	AdminDAO adminDAO = new AdminDAO();
	Gson gson = new Gson();

	public void addNewProject(String project) throws Exception {
		logger.info("Entering addNewProject");
		try {
			Project projectBean = gson.fromJson(project, Project.class);
			adminDAO.insertNewProject(projectBean);
		} catch (SystemException e) {
			logger.info(e.getMessage());
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} catch (Exception e) {
			logger.info(DisplayConstants.INTERNAL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			logger.info("Leaving addNewProject");
		}
	}

	public ArrayList<RequestedProject> displayRequested() throws Exception {
		ArrayList<RequestedProject> requestedProject = new ArrayList<RequestedProject>();
		try {
			requestedProject = adminDAO.displayRequestedProjects();
		} catch (SystemException e) {
			logger.info(e.getMessage());
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} catch (Exception e) {
			logger.info(DisplayConstants.INTERNAL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			logger.info("Leaving addNewProject");
		}
		return requestedProject;
	}

	public void approveProject(int requestID) throws Exception {
		logger.info("Entering approveProject");
		try {
			adminDAO.approveProjectDAO(requestID);
		} catch (SystemException e) {
			logger.info(e.getMessage());
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} catch (Exception e) {
			logger.info(DisplayConstants.INTERNAL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			logger.info("Leaving approveProject");
		}

	}

	public ArrayList<Contractor> displayAllContractors() throws Exception {
		logger.info("Entering displayAllContractors");
		ArrayList<Contractor> allContractorList = new ArrayList<Contractor>();
		try {
			allContractorList = adminDAO.displayAllContractors();
		} catch (SystemException e) {
			logger.info(e.getMessage());
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} catch (Exception e) {
			logger.info(DisplayConstants.INTERNAL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			logger.info("Leaving displayAllContractors");
		}
		return allContractorList;
	}

	public Contractor getContractor(int contractorID) throws Exception {
		logger.info("Entering getContractor");
		Contractor contractor = new Contractor();
		try {
			contractor = adminDAO.getContractor(contractorID);
		} catch (SystemException e) {
			logger.info(e.getMessage());
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} catch (Exception e) {
			logger.info(DisplayConstants.INTERNAL_ERR);
			throw new SystemException(DisplayConstants.SYSTEM_EXCEPTION);
		} finally {
			logger.info("Leaving displayAllContractors");
		}
		return contractor;
	}
}
