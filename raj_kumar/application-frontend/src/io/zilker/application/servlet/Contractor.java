package io.zilker.application.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.application.constants.DisplayConstants;
import io.zilker.application.constants.ValidationConstants;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.logsession.UserSession;
import io.zilker.application.utils.UserValidation;

/**
 * Servlet implementation class Contractor
 */
@WebServlet("/Contractor")
public class Contractor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final Logger LOGGER = Logger.getLogger(Contractor.class.getName());

	public Contractor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("entering doGet of Contractor Servlet");
		ContractorDelegate contractorDelegate = new ContractorDelegate();
		// TODO Auto-generated method stub
		if (request.getParameter("getType").equals("contractor-home")) {

			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			request.setAttribute("myProjectsList", contractorDelegate.getContractorProject(userSession.getUserId()));
			request.getRequestDispatcher("WEB-INF/pages/contractor-profile.jsp").forward(request, response);

		} else if (request.getParameter("getType").equals("view-available-project")) {

			request.setAttribute("availableProjectsList", contractorDelegate.displayProjects());
			request.getRequestDispatcher("WEB-INF/pages/contractor-profile-available.jsp").forward(request, response);

		} else if (request.getParameter("getType").equals("contractor-applied-project")) {

			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			request.setAttribute("appliedProjectsList", contractorDelegate.getRequestedProjects(userSession));
			request.getRequestDispatcher("WEB-INF/pages/contractor-profile-applied.jsp").forward(request, response);

		} else if (request.getParameter("getType").equals("view-delayed-project")) {

			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			request.setAttribute("delayedProjectsList", contractorDelegate.delayedProjects(userSession.getUserId()));
			System.out.println(request.getAttribute("delayedProjectsList"));
			request.getRequestDispatcher("WEB-INF/pages/contractor-profile-delayed.jsp").forward(request, response);
		}
		response.getWriter().append("Served at:").append(request.getContextPath());
		LOGGER.info("leaving doGet of Contractor Servlet");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		LOGGER.info("entering doPost of Contractor Servlet");
		ContractorDelegate contractorDelegate = new ContractorDelegate();
		LOGGER.info(request.getParameter("post-type"));
		if (request.getParameter("post-type") == null) {

			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			request.setAttribute("myProjectsList", contractorDelegate.getContractorProject(userSession.getUserId()));
			request.getRequestDispatcher("WEB-INF/pages/contractor-profile.jsp").forward(request, response);

		} else if (request.getParameter("post-type").equals("sending-request-for-project")) {
			boolean isValidForRequest = true;
			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			int CONTR_ID = userSession.getUserId();
			String startDate = request.getParameter("start-date");
			System.out.println(startDate);
			System.out.println("-----------------");
			Date start = dateFormatter(startDate);
			System.out.println(start);
			System.out.println("-----------------");
			Date currentDate = new Date();
			System.out.println(currentDate);
			System.out.println("is Valid " + !UserValidation.isValid(startDate, ValidationConstants.DATE_VALIDATION));
			System.out.println("start.after " + start.after(currentDate));
			System.out.println("dateCheck " + !UserValidation.dateCheck(startDate));
			if (UserValidation.isValid(startDate, ValidationConstants.DATE_VALIDATION) || start.before(currentDate)
					|| UserValidation.dateCheck(startDate)) {
				LOGGER.info("Error Occured");
				isValidForRequest = false;
			}
			String endDate = request.getParameter("end-date");

			Date end = dateFormatter(endDate);
			System.out.println("Date Validation = " + end.after(start) + " Second Validation = " + end.before(start));
			if (UserValidation.isValid(endDate, ValidationConstants.DATE_VALIDATION) || end.before(currentDate)
					|| UserValidation.dateCheck(endDate) || end.before(start)) {
				LOGGER.info("Error Occured");
				isValidForRequest = false;
			}
			System.out.println(end);
			System.out.println("-----------------");
			long estCost = Long.parseLong(request.getParameter("estimated-cost"));
			int projectID = Integer.parseInt(request.getParameter("project-id"));
			try {
				System.out.println(isValidForRequest);
				if (isValidForRequest) {
					contractorDelegate.requestTender(projectID, CONTR_ID, start, end, estCost);
					HttpSession sessionU = request.getSession();
					UserSession userSessionU = (UserSession) sessionU.getAttribute("userSession");
					request.setAttribute("myProjectsList",
							contractorDelegate.getContractorProject(userSessionU.getUserId()));
					request.getRequestDispatcher("WEB-INF/pages/contractor-profile.jsp?success=1").forward(request,
							response);
				} else {
					HttpSession sessionU = request.getSession();
					UserSession userSessionU = (UserSession) sessionU.getAttribute("userSession");
					request.setAttribute("myProjectsList",
							contractorDelegate.getContractorProject(userSessionU.getUserId()));
					System.out.println("================");
					request.getRequestDispatcher("WEB-INF/pages/contractor-profile.jsp?success=0").forward(request,
							response);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOGGER.info(DisplayConstants.INTERNAL_ERR);
			}

		} else if (request.getParameter("post-type").equals("add-response")) {
			LOGGER.info("Inside Add Response");
			BufferedReader br = request.getReader();
			String s = br.readLine();
			while (s != null) {
				System.out.println("read line is " + s);
				s = br.readLine();
			}
			br.close();
		} else if (request.getParameter("post-type").equals("project-completed")) {
			String projectID = request.getParameter("project-id");
			contractorDelegate.projectCompleted(Integer.parseInt(projectID));
			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			request.setAttribute("myProjectsList", contractorDelegate.getContractorProject(userSession.getUserId()));
			request.getRequestDispatcher("WEB-INF/pages/contractor-profile.jsp").forward(request, response);
		}
		doGet(request, response);
	}

	public static Date dateFormatter(String date) {
		LOGGER.info("Entering DateFormatter");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateToReturn = null;
		try {
			dateToReturn = dateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.info(DisplayConstants.INTERNAL_ERR);
		} finally {
			LOGGER.info("Leaving dateFormatter");
		}
		return dateToReturn;
	}

}
