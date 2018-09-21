package io.zilker.application.delegate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.Comments;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.User;
import io.zilker.application.beans.UserName;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.hash.Password;
import io.zilker.application.logsession.UserSession;
import io.zilker.application.utils.RequestAndResponseHandler;

public class UserDelegate {
	Logger logger = Logger.getLogger(UserDelegate.class.getName());
	Gson gson = new Gson();

	public void userCreationService(User user) throws Exception {
		logger.info("Entering into userCreationDelegate");
		// Converting object to JSON
		String json = gson.toJson(user, User.class);
		String hashedPassword = Password.getSecurePassword(user.getPassword());
		user.setPassword(hashedPassword);

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/register");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPostRequest(urlConnection, json);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		// print the response in String
		System.out.println(response.toString());
		logger.info("Leaving User Creation Service");
	}

	public boolean isUserPresent(String username) throws IOException {
		UserName user = new UserName();
		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/" + username);

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		String jsonOutput = response.toString();
		System.out.println(jsonOutput);
		user = gson.fromJson(jsonOutput, UserName.class);
		if (user.getStatus() == "PRESENT") {
			return true;
		} else {
			return false;
		}
	}

	public UserSession userLogin(String username, String password) {
		logger.info("Entering into User Login Delegate");
		JSONObject jsonObject = new JSONObject();
		UserSession user = null;

		try {
			jsonObject.put("username", username);
			jsonObject.put("password", password);
			String data = jsonObject.toString();
			System.out.println(data);

			// Define the server end point to send the HTTP request to
			HttpURLConnection urlConnection = RequestAndResponseHandler
					.getUrlConnection("http://localhost:8090/users/login");

			// Indicate that we want to write to the HTTP request body
			RequestAndResponseHandler.sendPostRequest(urlConnection, data);

			// Reading from the HTTP response body
			StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

			// Converting the JSON string to object in java
			String jsonInString = response.toString();
			user = gson.fromJson(jsonInString, UserSession.class);
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("User Details " + user.getFirstName() + " " + user.getLastName());
		logger.info("Leaving UserDelegate Login ");
		return user;
	}

	public void addComment(int userID, int projectId, String commentMessage) throws Exception {
		logger.info("Entering into addComment Delegate");
		Comments comment = new Comments();
		comment.setUserID(userID);
		comment.setProjectId(projectId);
		comment.setCommentMsg(commentMessage);

		// Converting object to JSON
		String json = gson.toJson(comment, Comments.class);
		logger.info(DisplayConstants.SPLITTER);
		logger.info(json);
		logger.info(DisplayConstants.SPLITTER);

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/comment");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPostRequest(urlConnection, json);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		// print the response in String
		System.out.println(response.toString());
		logger.info("Leaving into addComment Delegate");

	}

	public ArrayList<ApprovedProject> displayProjects() throws IOException {
		ArrayList<ApprovedProject> arrayOfAllProjects = new ArrayList<ApprovedProject>();

		logger.info("Entering into displayProject of UserDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/projects");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		logger.info("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<ApprovedProject>>() {
		}.getType();
		arrayOfAllProjects = gson.fromJson(jsonOutput, listType);

		logger.info("Leaving into displayProject of UserDelegate");

		return arrayOfAllProjects;
	}

	public ArrayList<Comments> getComments(int projectID) throws IOException {
		ArrayList<Comments> commentsList = new ArrayList<Comments>();

		logger.info("Entering into getComments of UserDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/projects/" + projectID + "/comments");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<Comments>>() {
		}.getType();
		commentsList = gson.fromJson(jsonOutput, listType);

		return commentsList;
	}

	public ArrayList<ApprovedProject> detailDisplay(int projectID) throws IOException {
		ArrayList<ApprovedProject> projectDetail = new ArrayList<ApprovedProject>();

		logger.info("Entering into projectsInLocationSer of UserDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/projects/" + projectID);

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<ApprovedProject>>() {
		}.getType();
		projectDetail = gson.fromJson(jsonOutput, listType);

		return projectDetail;
	}

	public ArrayList<ApprovedProject> projectsInLocationSer(int userID) throws IOException {
		ArrayList<ApprovedProject> approvedProjects = new ArrayList<ApprovedProject>();

		logger.info("Entering into projectsInLocationSer of UserDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/" + userID + "/projects");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		logger.info("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<ApprovedProject>>() {
		}.getType();
		approvedProjects = gson.fromJson(jsonOutput, listType);
		logger.info("Leaving projectsInLocationSer of UserDelegate");

		return approvedProjects;
	}

//	public String getContractor(int contractorID) {
//		logger.info("Entering into getContractor of UserDelegate");
//
//		// Define the server end point to send the HTTP request to
//		HttpURLConnection urlConnection = RequestAndResponseHandler
//				.getUrlConnection("http://localhost:8090/users/contractor" + contractorID);
//
//		// Indicate that we want to write to the HTTP request body
//		RequestAndResponseHandler.sendGetRequest(urlConnection);
//
//		// Reading from the HTTP response body
//		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);
//
//		logger.info("The reponse from the get" + response);
//		String jsonOutput = response.toString();
//		Type listType = new TypeToken<List<ApprovedProject>>() {
//		}.getType();
//		approvedProjects = gson.fromJson(jsonOutput, listType);
//		logger.info("Leaving projectsInLocationSer of UserDelegate");
//
//		return userDAO.getContractorName(contrID);
//	}

	public Contractor getContractorDetails(int contractorID) throws IOException {
		Contractor contractor = new Contractor();

		logger.info("Entering into projectsInLocationSer of UserDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/users/contractor" + contractorID);

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		logger.info("The reponse from the get" + response);
		String jsonOutput = response.toString();
		System.out.println(jsonOutput);
		contractor = gson.fromJson(jsonOutput, Contractor.class);

		return contractor;
	}

}
