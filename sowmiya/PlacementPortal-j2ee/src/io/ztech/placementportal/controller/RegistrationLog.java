package io.ztech.placementportal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/registrationLog")
public class RegistrationLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public RegistrationLog() {
		super();
		log = Logger.getLogger("RegistrationLog.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering get method of RegistrationLog");
		RetrieveDetailDelegate retrieveDetailsDelegate = new RetrieveDetailDelegate();
		try {
		HttpSession session=request.getSession(false);
		Student student = new Student();
		String student_id =(String) session.getAttribute("student_id");
		student.setStudentId(student_id);
		ArrayList<Company> registrationLog = retrieveDetailsDelegate.getRegistrationLog(student);
		request.setAttribute("registered_company", registrationLog);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registrationLog.jsp");
		dispatcher.forward(request, response);
		log.info("Exiting get method of RegistrationLog");
	}
		catch (Exception e) {
			log.warning(e.toString());
		}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}