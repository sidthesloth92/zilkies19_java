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

import com.google.gson.Gson;

import io.ztech.placementportal.bean.Profile;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.delegate.BuildProfileDelegate;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;

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
		Gson gson = new Gson();
		try {
			HttpSession session = request.getSession(false);
			Student student = new Student();
			student.setStudentId(session.getAttribute("student_id").toString());
			String studentDetail = delegate.getSpecificStudentDetail(student);
			System.out.println(studentDetail);
			ArrayList<Profile> project = delegate.getProfileDetail(session.getAttribute("student_id").toString(),
					ApplicationConstants.PROJECT);
			System.out.println(project);
			ArrayList<Profile> achievement = delegate.getProfileDetail(session.getAttribute("student_id").toString(),
					ApplicationConstants.ACHIEVEMENT);
			ArrayList<Profile> certification = delegate.getProfileDetail(session.getAttribute("student_id").toString(),
					ApplicationConstants.CERTIFICATIONS);
			System.out.println(achievement);
			request.setAttribute("student", gson.fromJson(studentDetail, Student.class));
			request.setAttribute("project", project);
			request.setAttribute("courses", certification);
			request.setAttribute("achievement", achievement);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/student.jsp");
			dispatcher.forward(request, response);
		}

		catch (Exception e) {
			log.warning(e.toString());
			e.printStackTrace();
		}
		log.info("Exiting doget() of StudentProfileController");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering dopost() of StudentProfileController");
		try {
			String profileType = request.getParameter("profileType");
			StudentDetailDelegate profileDelegate = new StudentDetailDelegate();
//			UpdateProfileDelegate updateProfileDelegate = new UpdateProfileDelegate();
		   Object object = JSONValue.parse(request.getParameter("profileDetail"));
			HttpSession session = request.getSession(false);
	        JSONObject profile = (JSONObject) object;
	        profile.put("studentId",session.getAttribute("student_id").toString());
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
//				System.out.println(jsonObject.get("id"));
//				if (updateProfileDelegate.updateProfileDetail(profile, profileType)) {
//					response.getWriter().write("success");
//				}
			}
		} catch (Exception e) {
			log.warning(e.toString());
			e.printStackTrace();
		}
		log.info("Exiting dopost() of StudentProfileController");

	
	}
}
