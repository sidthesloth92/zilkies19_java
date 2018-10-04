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
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/getAvailableCompany")
public class GetAvailableCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public GetAvailableCompany() {
		super();
		log = Logger.getLogger("getAvailableCompany");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering Get method of GetAvailableCompany controller");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		try {
			ArrayList<Company> list = retrieveDelegate.getCompanyDetails();
			request.setAttribute("company", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/RegistrationList.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			log.warning(e.toString());
		}
		log.info("Exiting Get method of GetAvailableCompany controller");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
