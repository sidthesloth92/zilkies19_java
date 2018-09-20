package io.zilker.application.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.application.beans.Contractor;
import io.zilker.application.beans.User;
import io.zilker.application.constants.ValidationConstants;
import io.zilker.application.delegate.ContractorDelegate;
import io.zilker.application.delegate.UserDelegate;
import io.zilker.application.utils.UserValidation;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> errorMessages = new ArrayList<>();
		UserDelegate userDelegate = new UserDelegate();
		// TODO Auto-generated method stub
		if (request.getParameter("user_type").equals("USER")) {

			boolean isValid = true;
			User user = new User();
			response.setContentType("text/html");
			if (!UserValidation.isValid(request.getParameter("first-name"), ValidationConstants.NAME_VALIDATION)) {

				errorMessages.add("Invalid First Name");
				isValid = false;

			} else {
				errorMessages.add("");
			}

			user.user.setFirstName(request.getParameter("first-name"));
			if (!UserValidation.isValid(request.getParameter("last-name"), ValidationConstants.NAME_VALIDATION)) {

				errorMessages.add("Invalid Last Name");
				isValid = false;
			} else {
				errorMessages.add("");
			}

			user.user.setLastName(request.getParameter("last-name"));
			user.user.setUsername(request.getParameter("username"));
			if (!UserValidation.isValid(request.getParameter("location"), ValidationConstants.NAME_VALIDATION)) {
				errorMessages.add("Invalid Location");
				isValid = false;
			} else {
				errorMessages.add("");
			}
			user.setLocation(request.getParameter("location"));
			user.setRole(request.getParameter("user_type"));
			if (request.getParameter("password").equals(request.getParameter("re-password"))) {
				errorMessages.add("");
			} else {
				isValid = false;
				errorMessages.add("Passwords do not match");

			}
			if (userDelegate.isUserPresent(request.getParameter("username"))) {
				errorMessages.add("Username is already Taken");
				isValid = false;
			} else {
				errorMessages.add("");
			}
			user.setPassword(request.getParameter("password"));
			if (!isValid) {
				request.setAttribute("errorList", errorMessages);
				request.getRequestDispatcher("WEB-INF/pages/user-registration.jsp").forward(request, response);
			} else {
				try {
					System.out.println(user.getLocation());
					userDelegate.userCreationService(user);
					response.sendRedirect("index.jsp?success=1");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {

			boolean isValid = true;
			ContractorDelegate contractorDelegate = new ContractorDelegate();
			Contractor contractor = new Contractor();
			if (!UserValidation.isValid(request.getParameter("first-name"), ValidationConstants.NAME_VALIDATION)) {
				errorMessages.add("Invalid First Name");
				isValid = false;
			} else {
				errorMessages.add("");
			}
			contractor.setFirstName(request.getParameter("first-name"));

			if (!UserValidation.isValid(request.getParameter("last-name"), ValidationConstants.NAME_VALIDATION)) {
				errorMessages.add("Invalid Last Name");
				isValid = false;
			} else {
				errorMessages.add("");
			}
			contractor.setLastName(request.getParameter("last-name"));
//			2
//			if (!UserValidation.isValid(request.getParameter("company-name"), ValidationConstants.NAME_VALIDATION)) {
//				errorMessages.add("Invalid Company Name");
//				isValid = false;
//			} else {
//				errorMessages.add("");
//			}
			contractor.setCompany(request.getParameter("company-name"));

			if (!UserValidation.isValid(request.getParameter("email"), ValidationConstants.EMAIL)) {
				errorMessages.add("Invalid Email");
				System.out.println("Invalid Email");
				isValid = false;
			} else {
				errorMessages.add("");
			}
			contractor.setEmail(request.getParameter("email"));

//			if (!UserValidation.isValid(request.getParameter("location"), ValidationConstants.NAME_VALIDATION)) {
//				errorMessages.add("Invalid Location");
//			} else {
//				errorMessages.add("");
//			}
			errorMessages.add("");
			contractor.setLocation(request.getParameter("location"));

			if (!UserValidation.isValid(String.valueOf(request.getParameter("number-of-client")),
					ValidationConstants.NO_OF_CLIENT)) {
				errorMessages.add("Invalid No of Client");
				isValid = false;
			} else {
				errorMessages.add("");
				contractor.setNoOfClient(Integer.parseInt(request.getParameter("number-of-client")));
			}
			if (!request.getParameter("annual-revenue").equals("")) {
				contractor.setAnnualRevenue(Integer.parseInt(request.getParameter("annual-revenue")));
				errorMessages.add("");
			} else {
				System.out.println("Invalid Annual");
				errorMessages.add("Invalid Annual Revenue");
				isValid = false;
			}

			if (request.getParameter("password").equals(request.getParameter("re-password"))) {
				errorMessages.add("");

			} else {
				errorMessages.add("Passwords do not match");
				isValid = false;

			}
			if (userDelegate.isUserPresent(request.getParameter("username"))) {
				errorMessages.add("Username is already Taken");
				isValid = false;
			} else {
				errorMessages.add("");
			}
			contractor.setUsername(request.getParameter("username"));
			contractor.setPassword(request.getParameter("password"));
			if (!isValid) {
				request.setAttribute("errorList", errorMessages);
				request.getRequestDispatcher("WEB-INF/pages/contractor-registration.jsp").forward(request, response);
			} else {
				try {
					contractorDelegate.contractorCreationService(contractor);
					response.sendRedirect("index.jsp?success=1");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		System.out.println(request.getParameter("user_type"));
		doGet(request, response);
	}

}
