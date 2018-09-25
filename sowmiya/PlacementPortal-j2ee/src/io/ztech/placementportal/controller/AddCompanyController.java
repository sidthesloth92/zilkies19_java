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
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;
import io.ztech.placementportal.delegate.StudentDetailDelegate;
import io.ztech.placementportal.delegate.UpdateCompanyDetailDelegate;
import io.ztech.placementportal.ui.ValidateInput;

@WebServlet("/company")
public class AddCompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public AddCompanyController() {
		super();
		log = Logger.getLogger("AddCompanyController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of AddCompanyController");
		ValidateInput validateInput = new ValidateInput();
		CompanyDetailDelegate insertDelegate = new CompanyDetailDelegate();
		UpdateCompanyDetailDelegate updateDelegate = new UpdateCompanyDetailDelegate();
		Object object = JSONValue.parse(request.getParameter("company"));
		JSONObject jsonObject = (JSONObject) object;
		Company company = new Company();
		company.setCompanyName(jsonObject.get("company-name").toString());
		company.setCompanyType(jsonObject.get("type").toString());
		company.setCompanyDescription(jsonObject.get("description").toString());
		company.setJobLocation(jsonObject.get("location").toString());
		company.setDesignation(jsonObject.get("designation").toString());
		company.setArrearCount(Integer.parseInt(jsonObject.get("arrear-count").toString()));
		company.setCgpa(Float.parseFloat(jsonObject.get("cgpa").toString()));
		company.setMarkX(Float.parseFloat(jsonObject.get("hsc").toString()));
		company.setMarkXII(Float.parseFloat(jsonObject.get("sslc").toString()));
		company.setDayOfRecruitment(jsonObject.get("recruitment-date").toString());
		company.setLastDateForRegistration(jsonObject.get("last-date").toString());
		company.setPayment(jsonObject.get("package").toString());
		company.setCompanyId(Integer.parseInt(jsonObject.get("company_id").toString()));
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormatter.format(date);

		try {

			if (!(validateInput.validateMark(jsonObject.get("hsc").toString()))) {
				response.getWriter().write("hscError");
				return;
			}

			if (!(validateInput.validateMark(jsonObject.get("sslc").toString()))) {
				response.getWriter().write("sslcError");
				return;

			}
			if (!(validateInput.validateCgpa(jsonObject.get("cgpa").toString()))) {
				response.getWriter().write("cgpaError");
				return;

			}
			if (!(validateInput.validateArrearCount(jsonObject.get("arrear-count").toString()))) {
				response.getWriter().write("arrearCountError");
				return;
			}
			if (!(validateInput.validateDate(currentDate, jsonObject.get("recruitment-date").toString()))) {
				response.getWriter().write("recruitmentdateError");
				return;
			}

			if (!(validateInput.validateDate(jsonObject.get("last-date").toString(),
					jsonObject.get("recruitment-date").toString()))) {
				response.getWriter().write("lastdateError");
				return;
			}
			if (request.getParameter("operation").equals("add")) {
				if (insertDelegate.addCompanyDetail(company)) {
					response.getWriter().write("success");
				}
			}
			if (request.getParameter("operation").equals("update")) {
				if (updateDelegate.updateCompany(company)) {
					response.getWriter().write("success");

				}
			}
		}

		catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting post method of AddCompanyController");

	}

}
