package io.zilker.application.delegate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.zilker.application.beans.ApprovedProject;
import io.zilker.application.beans.AvailableProject;
import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Name;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.beans.Response;
import io.zilker.application.hash.Password;
import io.zilker.application.logsession.UserSession;
import io.zilker.application.utils.RequestAndResponseHandler;

public class ContractorDelegate {
	Logger logger = Logger.getLogger(ContractorDelegate.class.getName());

	public void contractorCreationService(Contractor contractor) throws Exception {
		logger.info("Entering into Contractor Creation Delegate");
		// Converting object to JSON
		Gson gson = new Gson();
		String json = gson.toJson(contractor, Contractor.class);

		String hashedPassword = Password.getSecurePassword(contractor.getPassword());
		contractor.setPassword(hashedPassword);

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/register");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPostRequest(urlConnection, json);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		// print the response in String
		System.out.println(response.toString());
		logger.info("Leaving Contractor Creation Delegate");
	}

	public void requestTender(int projectID, int contractorID, Date startDate, Date endDate, long estCost)
			throws Exception {
		RequestedProject projectRequest = new RequestedProject();
		projectRequest.setAvailableID(projectID);
		projectRequest.setContrID(contractorID);
		projectRequest.setStartDate(startDate);
		projectRequest.setEndDate(endDate);
		BigDecimal estimatedCost = new BigDecimal(estCost);
		projectRequest.setEstCost(estimatedCost);

		logger.info("Entering into requestTender Delegate");
		// Converting object to JSON
		Gson gson = new Gson();
		String json = gson.toJson(projectRequest, RequestedProject.class);

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/request-tender");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPostRequest(urlConnection, json);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		// print the response in String
		System.out.println(response.toString());
		logger.info("Leaving requestTender Delegate");
	}

	public ArrayList<ApprovedProject> getContractorProject(int contractorID) throws IOException {
		Gson gson = new Gson();
		ArrayList<ApprovedProject> arrayOfProjects = new ArrayList<ApprovedProject>();

		logger.info("Entering into getContractorProject of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/" + contractorID + "/projects");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<ApprovedProject>>() {
		}.getType();
		arrayOfProjects = gson.fromJson(jsonOutput, listType);

		logger.info("Leaving getContractorProject of ContractorDelegate");

		return arrayOfProjects;
	}

	public ArrayList<ApprovedProject> delayedProjects(int contractorID) throws IOException {
		Gson gson = new Gson();
		ArrayList<ApprovedProject> arrayOfProjects = new ArrayList<ApprovedProject>();

		logger.info("Entering into getContractorProject of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/" + contractorID + "/delayed-projects");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<ApprovedProject>>() {
		}.getType();
		arrayOfProjects = gson.fromJson(jsonOutput, listType);

		logger.info("Leaving getContractorProject of ContractorDelegate");

		return arrayOfProjects;
	}

	public ArrayList<ApprovedProject> viewDelayedDetail(int ID, int contractorID) throws IOException {
		Gson gson = new Gson();
		ArrayList<ApprovedProject> arrayOfProjects = new ArrayList<ApprovedProject>();

		logger.info("Entering into getContractorProject of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/" + contractorID + "/delayed-projects");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<ApprovedProject>>() {
		}.getType();
		arrayOfProjects = gson.fromJson(jsonOutput, listType);

		logger.info("Leaving getContractorProject of ContractorDelegate");

		return arrayOfProjects;
	}

	public void addResponseService(int projectID, int contractorID, String responseData) throws Exception {
		Response responseObject = new Response();
		responseObject.setProjectID(projectID);
		responseObject.setID(contractorID);
		responseObject.setResponseText(responseData);

		logger.info("Entering into addResponse Creation Delegate");
		// Converting object to JSON
		Gson gson = new Gson();
		String json = gson.toJson(responseObject, Response.class);

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/add-response");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPostRequest(urlConnection, json);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		// print the response in String
		System.out.println(response.toString());
		logger.info("Leaving addResponse Creation Delegate");
	}

	public ArrayList<AvailableProject> displayProjects() throws IOException {
		Gson gson = new Gson();
		ArrayList<AvailableProject> arrayOfProjects = new ArrayList<AvailableProject>();

		logger.info("Entering into getAvailableProject of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/available-projects");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<AvailableProject>>() {
		}.getType();
		arrayOfProjects = gson.fromJson(jsonOutput, listType);

		logger.info("Leaving getAvailableProject of ContractorDelegate");

		return arrayOfProjects;
	}

	public ArrayList<Response> getResponses(int projectID) throws IOException {
		Gson gson = new Gson();
		ArrayList<Response> arrayOfResponses = new ArrayList<Response>();

		logger.info("Entering into getResponses of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/" + projectID + "/responses");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<Response>>() {
		}.getType();
		arrayOfResponses = gson.fromJson(jsonOutput, listType);

		logger.info("Leaving getResponses of ContractorDelegate");

		return arrayOfResponses;
	}

	public void projectCompleted(int projectID) throws IOException {
		logger.info("Entering into projectCompleted of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/" + projectID + "/completed");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPutRequest(urlConnection, "Change Status");

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

	}

	public ArrayList<RequestedProject> getRequestedProjects(UserSession userSession) throws IOException {
		Gson gson = new Gson();
		ArrayList<RequestedProject> arrayOfRequestedProject = new ArrayList<RequestedProject>();

		logger.info("Entering into getRequestedProjects of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/" + userSession.getUserId() + "/requested");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<RequestedProject>>() {
		}.getType();
		arrayOfRequestedProject = gson.fromJson(jsonOutput, listType);

		logger.info("Leaving getRequestedProjects of ContractorDelegate");

		return arrayOfRequestedProject;
	}

//	public void updateStartDate(int updateOption, int requestId, Date newStartDate) {
//		contractorDAO.updateStartDate(updateOption, requestId, newStartDate);
//	}
//
//	public void updateEndDate(int updateOption, int requestId, Date newStartDate) {
//		contractorDAO.updateEndDate(updateOption, requestId, newStartDate);
//	}
//
//	public void updateEstCost(int updateOption, int requestId, long newEstDate) {
//		contractorDAO.updateEstCost(updateOption, requestId, newEstDate);
//	}

	public String getContractorName(int contractorID) throws IOException {
		Name name = new Name();
		Gson gson = new Gson();

		logger.info("Entering into getContractorName of ContractorDelegate");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/contractors/" + contractorID);

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendGetRequest(urlConnection);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		System.out.println("The reponse from the get" + response);
		String jsonOutput = response.toString();
		name = gson.fromJson(jsonOutput, Name.class);

		String fullName = name.getFirstName() + " " + name.getLastName();
		logger.info("Leaving getRequestedProjects of ContractorDelegate");
		return fullName;
	}
}
