package io.ztech.placementportal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import io.ztech.placementportal.bean.Company;
import io.ztech.placementportal.bean.Student;
import io.ztech.placementportal.delegate.ApplicationValidateDelegate;
import io.ztech.placementportal.delegate.RetrieveDetailDelegate;
import io.ztech.placementportal.delegate.SubmitApplicationDelegate;
import io.ztech.placementportal.ui.ValidateInput;

@WebServlet("/job")
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public JobController() {
		super();
		log = Logger.getLogger("JobContoller.class");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering get method of jobcontroller");
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		try {
			ArrayList<Company> list = retrieveDelegate.getCompanyDetails();
			request.setAttribute("company", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/job.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			log.warning(e.toString());
		}
		log.info("Exiting Get method of GetAvailableCompany controller");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of GetAvailableCompany controller");
		HttpSession session = request.getSession(false);
		RetrieveDetailDelegate retrieveDelegate = new RetrieveDetailDelegate();
		ApplicationValidateDelegate validateDelegte = new ApplicationValidateDelegate();
		SubmitApplicationDelegate submitDelegate = new SubmitApplicationDelegate();
		ValidateInput validateInput = new ValidateInput();
		Company companyDetail = new Company();
		Student studentDetail = new Student();
		String student_id = (String) session.getAttribute("student_id");
		boolean isDisabled = true;
		companyDetail.setCompanyId(Integer.parseInt(request.getParameter("id")));
		try {
			Company company = retrieveDelegate.getSpecificCompanyDetail(companyDetail);
			Date date = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String currentDate = dateFormatter.format(date);
			if (request.getParameter("type") == null) {

				if (validateInput.validateDate(currentDate, company.getLastDateForRegistration())) {
					isDisabled = false;
				}
				request.setAttribute("company_detail", company);
				request.setAttribute("isDisabled", isDisabled);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applyJob.jsp");
				dispatcher.forward(request, response);
			} else {
				if (validateDelegte.checkIsApplied(company, student_id)) {
					response.getWriter().write("applied");
				} else {
					studentDetail = retrieveDelegate.viewDetail(student_id);
					if (submitDelegate.completeRegistration(company, studentDetail, currentDate)) {
						response.getWriter().write("success");
					} else {
						response.getWriter().write("non-eligible");

					}

				}
			}
		} catch (SQLException e) {
			log.warning(e.toString());
		}
		log.info("Exiting post method of GetAvailableCompany controller");

	}

}
