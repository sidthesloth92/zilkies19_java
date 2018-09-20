package io.zilker.application.delegate;

import java.util.ArrayList;

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
	UserDAO userDAO = new UserDAO();
	AdminDAO adminDAO = new AdminDAO();
	ContractorDAO contractorDAO = new ContractorDAO();

	public void userCreationService(User user) throws Exception {
		System.out.println(user.getPassword());
		String hashedPassword = Password.getSecurePassword(user.getPassword());
		user.setPassword(hashedPassword);
		userDAO.userCreation(user);
	}

	public boolean isUserPresent(String username) {
		return userDAO.isUserPresentDAO(username);
	}

	public String userLogin(String request) {
		adminDAO.dailyStatusCheck();
		System.out.println("Getting into UserDelegate");

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

		System.out.println(request);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonData = null;
		try {
			jsonData = ow.writeValueAsString(
					userDAO.isUserPresentDAO((String) json.get("username"), (String) json.get("password")));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("In Backend Delegate " + jsonData);
		return jsonData;
	}

	public void addComment(int userID, int projectID, String comment) throws Exception {
		userDAO.addComment(userID, projectID, comment);
	}

	public ArrayList<ApprovedProject> displayProjects() {
		return userDAO.displayProjects();
	}

	public ArrayList<Comments> getComments(int projectID) {
		return userDAO.getComments(projectID);
	}

	public ArrayList<ApprovedProject> detailDisplay(int projectID) {
		return userDAO.projectDetails(projectID);
	}

	public ArrayList<ApprovedProject> projectsInLocationSer(int userID) {
		return userDAO.projectInMyLocation(userID);
	}

	public String getContractor(int contrID) {
		return userDAO.getContractorName(contrID);
	}

	public Contractor getContractorDetails(int contrID) {
		return contractorDAO.getContractorDetails(contrID);
	}

}
