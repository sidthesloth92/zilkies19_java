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

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/placedStudents")
public class PlacedStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public PlacedStudents() {
		super();
		log = Logger.getLogger("PlacedStudents.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of PlacedStudents controller");
		RetrieveDetailDelegate retrieveDetail = new RetrieveDetailDelegate();
		Company company = new Company();
		company.setCompanyId(Integer.parseInt(request.getParameter("id")));

		try {
			ArrayList<Student> StudentList = retrieveDetail.getStudentsPlacedInASpecificCompany(company);
			request.setAttribute("placement", StudentList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/placedStudentDetails.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			log.warning(e.toString());
		}
		log.info("Exiting post method of PlacedStudents controller");

	}

}
