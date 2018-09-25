package io.ztech.placementportal.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;
import io.ztech.placementportal.delegate.ValidateInput;

@WebServlet("/updateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public UpdateStudent() {
		super();
		log = Logger.getLogger("UpdateStudent.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering doget() of UpdateStudent()");
		ValidateInput validateInput = new ValidateInput();
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		Object object = JSONValue.parse(request.getParameter("updateStudent"));
		JSONObject studentJson = (JSONObject) object;
		StudentDetailDelegate delegate = new StudentDetailDelegate();
		try {

			if (!(validateInput.validateDetail(Regex.STUDENT_NAME, studentJson.get("name").toString()))) {

				response.getWriter().write("nameError");
				return;
			}
			if (!(validateInput.validateMark(studentJson.get("markXII").toString()))) {
				response.getWriter().write("hscError");
				return;

			}
			if (!(validateInput.validateMark(studentJson.get("markX").toString()))) {
				response.getWriter().write("sslcError");
				return;

			}
			if (!(validateInput.validateCgpa(studentJson.get("cgpa").toString()))) {

				response.getWriter().write("cgpaError");
				return;

			}
			if (!(validateInput.validateArrearCount(studentJson.get("arrearCount").toString()))) {
				response.getWriter().write("arrearCountError");
				return;

			}

			if (delegate.updateStudentDetail(studentJson)) {

				response.getWriter().write("success");
			}
		}

		catch (Exception e) {
			log.warning(e.toString());
			e.printStackTrace();
		}
		log.info("Exiting doget() of UpdateStudent()");

	}

}
