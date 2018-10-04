package io.ztech.placementportal.delegate;

import java.sql.SQLException;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import com.example.demo.utility.RequestAndResponseHandler;

import io.ztech.placementportal.bean.PersonalInfo;
import io.ztech.placementportal.bean.PlacedDetail;
import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.SqlConstants;

public class StudentDetailDelegate {
	private Logger log;

	public StudentDetailDelegate() {
		log = Logger.getLogger("UpdateStudentDetailDelegate.class");
	}

	public void updatePlacementDetail(PlacedDetail student) throws SQLException {
	}

	public boolean updateStudentDetail(JSONObject studentJson) {
		log.info("Entering updateStudentDetail()");
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		System.out.println(studentJson);
		String url = "http://localhost:8006/demo/students/" + studentJson.get("registerNumber");
		String response = requestAndResponseHandler.postRequestHandler(url, studentJson.toJSONString(), "PUT");
		log.info("Exiting updateStudentDetail()");
		return Boolean.parseBoolean(response);

	}

	public boolean deleteStudentDetail(String id) {
		log.info("Entering deleteStudentDetail()");
		RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
		String url = "http://localhost:8006/demo/students/" + id;
		String response = requestAndResponseHandler.getRequestHandler(url, "DELETE");
		System.out.println(response);
		log.info("Exiting deleteCompany()");
		return Boolean.parseBoolean(response);

	}

	public boolean addToProfile(JSONObject profile, String category) {
		System.out.println("in frontend");
		System.out.println(profile);
		System.out.println(category);
		log.info("Entering addToProfile() in StudentDetailDelegate ");
		try {
			RequestAndResponseHandler requestAndResponseHandler = new RequestAndResponseHandler();
			String url = null;
			if (category.equals(ApplicationConstants.ACHIEVEMENT)) {
				url = "http://localhost:8006/demo/students/achievements";
			}
			if (category.equals(ApplicationConstants.PROJECT)) {
				url = "http://localhost:8006/demo/students/project";
			}
			if (category.equals(ApplicationConstants.CERTIFICATIONS)) {
				url = "http://localhost:8006/demo/students/courses";
			}
			String response = requestAndResponseHandler.postRequestHandler(url, profile.toString(), "POST");
			System.out.println(response);
			log.info("Exiting addToProfile() in StudentDetailDelegate");
			return Boolean.parseBoolean(response);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return false;
	}

	public void updatePersonalDetail(PersonalInfo info) throws SQLException {
	}

}
