package io.ztech.placementportal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.lookup.ProblemFieldBinding;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.delegate.BuildProfileDelegate;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;
import io.ztech.placementportal.delegate.UpdateProfileDelegate;

@WebServlet("/studentProfile")
public class StudentProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log;

	public StudentProfileController() {
		super();
		log = Logger.getLogger("StudentProfileController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering doget() of StudentProfileController");
		RetrieveDetailDelegate delegate = new RetrieveDetailDelegate();
		Profile profile = new Profile();
		try {
			HttpSession session = request.getSession(false);
			Student student = delegate.viewDetail(session.getAttribute("student_id").toString());
			ArrayList<Profile> project = delegate.viewProfileDetail(session.getAttribute("student_id").toString(),
					ApplicationConstants.PROJECT);
			ArrayList<Profile> achievement = delegate.viewProfileDetail(session.getAttribute("student_id").toString(),
					ApplicationConstants.ACHIEVEMENT);
			ArrayList<Profile> certification = delegate.viewProfileDetail(session.getAttribute("student_id").toString(),
					ApplicationConstants.CERTIFICATIONS);
			request.setAttribute("student", student);
			request.setAttribute("project", project);
			request.setAttribute("courses", certification);
			request.setAttribute("achievement", achievement);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/student.jsp");
			dispatcher.forward(request, response);
		}

		catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting doget() of StudentProfileController");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering dopost() of StudentProfileController");
		try {
			String profileType = request.getParameter("profileType");
			BuildProfileDelegate profileDelegate = new BuildProfileDelegate();
			UpdateProfileDelegate updateProfileDelegate = new UpdateProfileDelegate();
			Object object = JSONValue.parse(request.getParameter("profileDetail"));
			HttpSession session = request.getSession(false);
			JSONObject jsonObject = (JSONObject) object;
			Profile profile = new Profile();
			profile.setTitle(jsonObject.get("title").toString());
			profile.setDescription(jsonObject.get("description").toString());
			profile.setStudent_id(session.getAttribute("student_id").toString());
			profile.setProfileDetailId(Integer.parseInt(jsonObject.get("id").toString()));
			if (profileType.equals("project")) {
				profileType = ApplicationConstants.PROJECT;
			} else if (profileType.equals("course")) {
				profileType = ApplicationConstants.CERTIFICATIONS;
			} else {
				profileType = ApplicationConstants.ACHIEVEMENT;
			}
			if (request.getParameter("operation").equals("add")) {
				if (profileDelegate.addToProfile(profile, profileType)) {
					response.getWriter().write("success");
				}
			}
			if (request.getParameter("operation").equals("update")) {
				System.out.println(jsonObject.get("id"));
				if (updateProfileDelegate.updateProfileDetail(profile, profileType)) {
					response.getWriter().write("success");
				}
			}
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Entering dopost() of StudentProfileController");

	}

}
