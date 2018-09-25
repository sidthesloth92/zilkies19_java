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

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;

@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public Admin() {
		super();
		log = Logger.getLogger("Admin.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering get method of Admin");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		ArrayList<Company> list = retrieveDelegate.getCompanyDetails();
		request.setAttribute("company", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/company.jsp");
		dispatcher.forward(request, response);
		log.info("Exiting get method of Admin");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of Admin");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		Company companyDetail = new Company();
		companyDetail.setCompanyId(Integer.parseInt(request.getParameter("id")));
		try {
			String company = retrieveDelegate.getSpecificCompanyDetail(companyDetail);
			JSONObject companyJson = new JSONObject();
			/*companyJson.put("company_id", company.getCompanyId());
			companyJson.put("name", company.getCompanyName());
			companyJson.put("type", company.getCompanyType());
			companyJson.put("description", company.getCompanyDescription());
			companyJson.put("location", company.getJobLocation());
			companyJson.put("designation", company.getDesignation());
			companyJson.put("recruitment_date", company.getDayOfRecruitment());
			companyJson.put("last_date", company.getLastDateForRegistration());
			companyJson.put("salary", company.getPayment());
			companyJson.put("cgpa", company.getCgpa());
			companyJson.put("hsc", company.getMarkXII());
			companyJson.put("sslc", company.getMarkX());
			companyJson.put("arrear_count", company.getArrearCount());*/
			response.getWriter().write(company);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting get method of Admin");

	}

}