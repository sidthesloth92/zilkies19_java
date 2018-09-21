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

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;
import io.ztech.placementportal.delegate.ValidateInput;

@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public StudentController() {
		super();
		log = Logger.getLogger("StudentController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		log.info("Entering doget() of StudentController");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		try {
			ArrayList<Student> list = retrieveDelegate.getStudentDetail();
			request.setAttribute("students", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/studentDetail.jsp");
			dispatcher.forward(request, response);
		} catch (IOException | ServletException e) {
			log.info(e.toString());
		}
		log.info("Exiting doget() of StudentController");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*og.info("Entering dopost() of StudentController");
		StudentDetailDelegate studentDelegate = new StudentDetailDelegate();
		RetrieveDetailDelegate delegate = new RetrieveDetailDelegate();
		ValidateInput validateInput = new ValidateInput();
		Object object = JSONValue.parse(request.getParameter("register"));
		JSONObject jsonObject = (JSONObject) object;
		JSONObject registerJson = new JSONObject();
		
		try {
			if (validateInput.validateDetail(Regex.REGISTER_NO_REGEX, jsonObject.get("registerNumber").toString())) {

				if (delegate.checkIsAvailable(jsonObject.get("registerNumber").toString(),
						ApplicationConstants.REGISTER_NO)) {
					response.getWriter().write("registerNumber_exists");
					return;
				} else {
					response.getWriter().write("registerNumber_error");
					return;

				}
			}

			if (!validateInput.validateDetail(Regex.STUDENT_NAME, jsonObject.get("studentName").toString())) {
				response.getWriter().write("nameError");
				return;

			}
			if (!validateInput.validateMark(jsonObject.get("hsc").toString())) {
				response.getWriter().write("hscError");
				return;

			}
			if (!validateInput.validateMark(jsonObject.get("sslc").toString())) {
				response.getWriter().write("sslcError");
				return;
			}
			if (!validateInput.validateCgpa(jsonObject.get("cgpa").toString())) {
				response.getWriter().write("cgpaError");
				return;
			}
			if (!validateInput.validateArrearCount(jsonObject.get("arrearCount").toString())) {
				response.getWriter().write("arrearCountError");
				return;
			}

			if (!validateInput.validateDetail(Regex.EMAIL_REGEX, jsonObject.get("email").toString())) {
				response.getWriter().write("emailError");
				return;
			}
			if (studentDelegate.createStudentLogin(jsonObject) && studentDelegate.addStudentDetail(jsonObject)) {
				response.getWriter().write("success");
				return;
			}
		}

		catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting dopost() of StudentController");
	}*/

}
}