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

import com.google.gson.Gson;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/registrationList")
public class RegistrationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public RegistrationList() {
		super();
		log = Logger.getLogger("RegistrationList.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		try {
			ArrayList<Company> list = retrieveDelegate.getCompanyDetails();
			response.setContentType("application/json");
			new Gson().toJson(list, response.getWriter());
		} catch (SQLException e) {
			log.warning(e.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of registration-list contollre");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		ArrayList<Student> registeredStudents = new ArrayList<>();
		Company company = new Company();
		company.setCompanyId(Integer.parseInt(request.getParameter("company")));
		try {
			registeredStudents = retrieveDelegate.getEligiblityList(company);
			request.setAttribute("students", registeredStudents);
			request.setAttribute("notFound", "NO RECORDS FOUND");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/RegistrationList.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			log.info(e.toString());
		}
		log.info("Exiting post method of registration-list controller");

	}

}
