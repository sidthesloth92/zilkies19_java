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
import io.ztech.placementportal.delegate.UpdateStudentDetailDelegate;
import io.ztech.placementportal.ui.ValidateInput;

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
		JSONObject jsonObject = (JSONObject) object;
		Student student = new Student();
		UpdateStudentDetailDelegate delegate = new UpdateStudentDetailDelegate();
		student.setDepartment(jsonObject.get("department").toString());
		try {
			student.setStudentId(jsonObject.get("registerNumber").toString());

			if (validateInput.validateDetail(Regex.STUDENT_NAME, jsonObject.get("studentName").toString())) {
				student.setName(jsonObject.get("studentName").toString());
			} else {
				response.getWriter().write("nameError");
				return;

			}
			if (validateInput.validateMark(jsonObject.get("hsc").toString())) {
				student.setMarkX(Float.parseFloat(jsonObject.get("hsc").toString()));
			} else {
				response.getWriter().write("hscError");
				return;

			}
			if (validateInput.validateMark(jsonObject.get("sslc").toString())) {
				student.setMarkXII(Float.parseFloat(jsonObject.get("sslc").toString()));
			} else {
				response.getWriter().write("sslcError");
				return;

			}
			if (validateInput.validateCgpa(jsonObject.get("cgpa").toString())) {
				student.setCgpa(Float.parseFloat(jsonObject.get("cgpa").toString()));
			} else {
				response.getWriter().write("cgpaError");

			}
			if (validateInput.validateArrearCount(jsonObject.get("arrearCount").toString())) {
				student.setArrearCount(Integer.parseInt(jsonObject.get("arrearCount").toString()));
			} else {
				response.getWriter().write("arrearCountError");

			}

			if (delegate.updateDetail(student)) {

				response.getWriter().write("success");
			}
		}

		catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting doget() of UpdateStudent()");

	}

}
