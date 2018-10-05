package io.ztech.placementportal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.json.simple.JSONObject;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/updateStudentProfile")
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public UpdateProfileController() {
		super();
		log = Logger.getLogger("UpdateProfileController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of UpdateProfileController");
		Profile profile = new Profile();
		JSONObject profileJson = new JSONObject();
		RetrieveDetailDelegate delegate = new RetrieveDetailDelegate();
		try {
			if (request.getParameter("profileType").equals("project")) {
				profile = delegate.viewSpecificProfileDetail(Integer.parseInt(request.getParameter("id")),
						ApplicationConstants.PROJECT);
			} else if (request.getParameter("profileType").equals("course")) {
				profile = delegate.viewSpecificProfileDetail(Integer.parseInt(request.getParameter("id")),
						ApplicationConstants.CERTIFICATIONS);
			} else if (request.getParameter("profileType").equals("achievement")) {
				profile = delegate.viewSpecificProfileDetail(Integer.parseInt(request.getParameter("id")),
						ApplicationConstants.ACHIEVEMENT);

			}

			if (profile != null) {
				profileJson.put("title", profile.getTitle());
				profileJson.put("description", profile.getDescription());
				profileJson.put("profile_id", profile.getProfileDetailId());
				response.getWriter().write(profileJson.toString());
			}

		} catch (SQLException e) {
					}
		log.info("Exiting post method of UpdateProfileController");

	}
}
