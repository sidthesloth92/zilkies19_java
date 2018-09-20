package io.zilker.application.delegate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.Project;
import io.zilker.application.beans.RequestedProject;
import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.utils.RequestAndResponseHandler;

public class AdminDelegate {
	private static final Logger logger = Logger.getLogger(AdminDelegate.class.getName());
	Gson gson = new Gson();

	public void addNewProject(Project project) throws Exception {
		logger.info("Entering addNewProject");
		// Converting object to JSON
		String json = gson.toJson(project, Project.class);

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/admin/add-new-project");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPostRequest(urlConnection, json);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		// print the response in String
		logger.info(response.toString());
		logger.info("Leaving addNewProject");
	}

	public ArrayList<RequestedProject> displayRequested() {
		logger.info("Entering displayRequested");
		ArrayList<RequestedProject> arrayOfRequestedProject = new ArrayList<RequestedProject>();

		try {
			// Define the server end point to send the HTTP request to
			HttpURLConnection urlConnection = RequestAndResponseHandler
					.getUrlConnection("http://localhost:8090/admin/get-requested-project");

			// Indicate that we want to write to the HTTP request body
			RequestAndResponseHandler.sendGetRequest(urlConnection);

			// Reading from the HTTP response body
			StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

			System.out.println("The reponse from the get" + response);
			String jsonOutput = response.toString();
			Type listType = new TypeToken<List<RequestedProject>>() {
			}.getType();
			arrayOfRequestedProject = gson.fromJson(jsonOutput, listType);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(DisplayConstants.DELEGATE_ERR);
		} finally {
			logger.info("Leaving displayRequested");
		}

		return arrayOfRequestedProject;
	}

	public void approveProject(int requestID) throws Exception {
		logger.info("Entering approveProject");

		// Define the server end point to send the HTTP request to
		HttpURLConnection urlConnection = RequestAndResponseHandler
				.getUrlConnection("http://localhost:8090/admin/approve-project");

		// Indicate that we want to write to the HTTP request body
		RequestAndResponseHandler.sendPostRequestAccept(urlConnection, "requestId=" + requestID);

		// Reading from the HTTP response body
		StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

		// print the response in String
		logger.info(response.toString());
		logger.info("Leaving approveProject");
	}

	public ArrayList<Contractor> displayAllContractors() {
		logger.info("Entering displayAllContractors");
		ArrayList<Contractor> arrayOfAllContractors = new ArrayList<Contractor>();
		try {
			// Define the server end point to send the HTTP request to
			HttpURLConnection urlConnection = RequestAndResponseHandler
					.getUrlConnection("http://localhost:8090/admin/get-all-contractors");

			// Indicate that we want to write to the HTTP request body
			RequestAndResponseHandler.sendGetRequest(urlConnection);

			// Reading from the HTTP response body
			StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

			System.out.println("The reponse from the get" + response);
			String jsonOutput = response.toString();
			Type listType = new TypeToken<List<Contractor>>() {
			}.getType();
			arrayOfAllContractors = gson.fromJson(jsonOutput, listType);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(DisplayConstants.DELEGATE_ERR);
		} finally {
			logger.info("Leaving displayAllContractors");
		}
		return arrayOfAllContractors;
	}

	public Contractor getContractor(int contractorID) {
		Contractor contractor = new Contractor();
		try {
			// Define the server end point to send the HTTP request to
			HttpURLConnection urlConnection = RequestAndResponseHandler
					.getUrlConnection("http://localhost:8090/admin/" + contractorID);

			// Indicate that we want to write to the HTTP request body
			RequestAndResponseHandler.sendGetRequest(urlConnection);

			// Reading from the HTTP response body
			StringBuffer response = RequestAndResponseHandler.readFromHttpResponse(urlConnection);

			String jsonOutput = response.toString();
			System.out.println(jsonOutput);
			contractor = gson.fromJson(jsonOutput, Contractor.class);

		} catch (IOException e) {
			logger.info(DisplayConstants.DELEGATE_ERR);
		}
		return contractor;
	}
}
