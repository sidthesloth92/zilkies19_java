package io.ztech.placementportal.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.constants.ApplicationConstants;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.delegate.CompanyDetailDelegate;
import io.ztech.placementportal.delegate.ValidateInput;

@WebServlet("/company")
public class CompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public CompanyController() {
		super();
		log = Logger.getLogger("CompanyController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of CompanyController");
		ValidateInput validateInput = new ValidateInput();
		CompanyDetailDelegate companyDelegate = new CompanyDetailDelegate();
		Object object = JSONValue.parse(request.getParameter("company"));
		System.out.println(object);
		JSONObject companyJson = (JSONObject) object;
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormatter.format(date);

		try {

			if (!(validateInput.validateMark(companyJson.get("markXII").toString()))) {
				response.getWriter().write("hscError");
				return;
			}

			if (!(validateInput.validateMark(companyJson.get("markX").toString()))) {
				response.getWriter().write("sslcError");
				return;

			}
			if (!(validateInput.validateCgpa(companyJson.get("cgpa").toString()))) {
				response.getWriter().write("cgpaError");
				return;

			}
			if (!(validateInput.validateArrearCount(companyJson.get("arrearCount").toString()))) {
				response.getWriter().write("arrearCountError");
				return;
			}
			if (!(validateInput.validateDate(currentDate, companyJson.get("dayOfRecruitment").toString()))) {
				response.getWriter().write("recruitmentdateError");
				return;
			}

			if (!(validateInput.validateDate(companyJson.get("lastDateForRegistration").toString(),
					companyJson.get("dayOfRecruitment").toString()))) {
				response.getWriter().write("lastdateError");
				return;
			}
			if (request.getParameter("operation").equals("add")) {
				if (companyDelegate.addCompanyDetail(companyJson)) {
					response.getWriter().write("success");
				}
			}
			if (request.getParameter("operation").equals("update")) {
				if (companyDelegate.updateCompany(companyJson)) {
					response.getWriter().write("success");

				}
			}
		}

		catch (Exception e) {
			log.warning(e.toString());
			e.printStackTrace();
		}
		log.info("Exiting post method of AddCompanyController");

	}

}
