package io.ztech.placementportal.delegate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import com.example.demo.utility.RequestAndResponseHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;

public class RetrieveDetailDelegate {
	private Logger log;

	public RetrieveDetailDelegate() {
		super();
		log = Logger.getLogger("RetrieveDetailDelegate.class");
	}

	public ArrayList<Company> getCompanyDetails() {
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		String url = "http://localhost:8006/demo/company";
		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			Gson gson = new Gson();
			String jsonOutput = response.toString();
			Type listType = new TypeToken<List<Company>>() {
			}.getType();
			ArrayList<Company> company = gson.fromJson(jsonOutput, listType);
			return company;
		} catch (Exception e) {
			log.warning(e.toString());
		}
		return null;
	}

	public String getSpecificCompanyDetail(Company company) {
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		log.info("Entering into getSpecificDetailCompany()");
		String url = "http://localhost:8006/demo/company/" + company.getCompanyId();
		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting into getSpecificDetailCompany()");
		return null;

	}

	public ArrayList<Student> getStudentDetail() {
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		String url = "http://localhost:8006/demo/students";
		String response = requestAndResponseHandler.getRequestHandler(url, "GET");
		System.out.println(response);
		Gson gson = new Gson();
		String jsonOutput = response.toString();
		Type listType = new TypeToken<List<Student>>() {
		}.getType();
		ArrayList<Student> student = gson.fromJson(jsonOutput, listType);
		return student;
	}

	public String getSpecificStudentDetail(Student student) {
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		String url = "http://localhost:8006/demo/students/" + student.getStudentId();
		String response = requestAndResponseHandler.getRequestHandler(url, "GET");
		return response;
	}

	public ArrayList<PlacedDetail> getPlacementDetails() {
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		String url = "http://localhost:8006/demo/placement-record";
		String response = requestAndResponseHandler.getRequestHandler(url, "GET");
		System.out.println(response);
		Gson gson = new Gson();
		Type listType = new TypeToken<List<PlacedDetail>>() {
		}.getType();
		ArrayList<PlacedDetail> placedDetail = gson.fromJson(response, listType);
		return placedDetail;
	}

	public ArrayList<Student> getStudentsPlacedInASpecificCompany(Company company) throws SQLException {
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		String url = "http://localhost:8006/demo/placement-record/students/" + company.getCompanyId();
		String response = requestAndResponseHandler.getRequestHandler(url, "GET");
		System.out.println(response);
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Student>>() {
		}.getType();
		ArrayList<Student> studentDetails = gson.fromJson(response, listType);
		return studentDetails;
	}

	public ArrayList<Student> getEligiblityList(Company company) throws SQLException {
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		String url = "http://localhost:8006/placement/registration-list/" + company.getCompanyId();
		String response = requestAndResponseHandler.getRequestHandler(url, "GET");
		System.out.println(response);
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Student>>() {
		}.getType();
		ArrayList<Student> studentDetails = gson.fromJson(response, listType);
		return studentDetails;
	}

	public ArrayList<Profile> getProfileDetail(String registerNumber, String category) {
		try {
			RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
			String url = null;
			if (category.equals(ApplicationConstants.ACHIEVEMENT)) {
				url = "http://localhost:8006/demo/students/achievements/" + registerNumber;
			}
			if (category.equals(ApplicationConstants.PROJECT)) {
				url = "http://localhost:8006/demo/students/projects/" + registerNumber;
			}
			if (category.equals(ApplicationConstants.CERTIFICATIONS)) {
				url = "http://localhost:8006/demo/students/courses/" + registerNumber;
			}
			String response = requestAndResponseHandler.getRequestHandler(url, "GET");
			Gson gson = new Gson();
			Type listType = new TypeToken<List<Profile>>() {
			}.getType();
			ArrayList<Profile> profileDetail = gson.fromJson(response, listType);
			return profileDetail;

		} catch (Exception e) {
			log.warning(e.toString());
		}
		return null;
	}

}
