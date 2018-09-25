package io.zilker.application.delegate;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.User;
import io.zilker.application.dao.AdminDAO;
import io.zilker.application.dao.ContractorDAO;
import io.zilker.application.dao.UserDAO;
import io.zilker.application.hash.Password;

public class UserDelegate {
	private static final Logger logger = Logger.getLogger(UserDelegate.class.getName());
	UserDAO userDAO = new UserDAO();
	AdminDAO adminDAO = new AdminDAO();
	ContractorDAO contractorDAO = new ContractorDAO();

	public void userCreationService(User user) throws Exception {
		logger.info("Entering userCreation");
		logger.info(user.getPassword());
		String hashedPassword = Password.getSecurePassword(user.getPassword());
		user.setPassword(hashedPassword);
		userDAO.userCreation(user);
		logger.info("Leaving userCreation");
	}

	public boolean isUserPresent(String username) {
		return userDAO.isUserPresentDAO(username);
	}

	public String userLogin(String request) {
		logger.info("Entering userLogin");
		adminDAO.dailyStatusCheck();

		// Converting String to JSON Using simple JSON Parser
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(request);
			System.out.println("Username using Simple Json " + json.get("username"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info(request);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonData = null;
		try {
			jsonData = ow.writeValueAsString(
					userDAO.isUserPresentDAO((String) json.get("username"), (String) json.get("password")));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("In Backend Delegate " + jsonData);
		logger.info("Leaving userLogin");
		return jsonData;
	}

	public void addComment(int userID, int projectID, String comment) throws Exception {
		logger.info("Entering addComment");
		userDAO.addComment(userID, projectID, comment);
		logger.info("Leaving addComment");
	}

	public ArrayList<ApprovedProject> displayProjects() {
		logger.info("Entering displayProjects");
		return userDAO.displayProjects();
	}

	public ArrayList<Comments> getComments(int projectID) {
		logger.info("Entering getComments");
		return userDAO.getComments(projectID);
	}

	public ArrayList<ApprovedProject> detailDisplay(int projectID) {
		logger.info("Entering detailDisplay");
		return userDAO.projectDetails(projectID);
	}

	public ArrayList<ApprovedProject> projectsInLocationSer(int userID) {
		logger.info("Entering projectsInLocation");
		return userDAO.projectInMyLocation(userID);
	}

	public String getContractor(int contrID) {
		logger.info("getContractor");
		return userDAO.getContractorName(contrID);
	}

	public Contractor getContractorDetails(int contrID) {
		logger.info("getContractorDetails");
		return contractorDAO.getContractorDetails(contrID);
	}

}
