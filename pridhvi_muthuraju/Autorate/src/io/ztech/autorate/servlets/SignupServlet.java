package io.ztech.autorate.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.delegate.Validator;
import io.ztech.autorate.services.LoginService;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ArrayList<String> errorMessages;
	LoginService loginService = new LoginService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		errorMessages = new ArrayList<>();
		String firstName = request.getParameter("firstname");
		validateFirstName(request, firstName);
		String lastName = request.getParameter("lastname");
		validateLastName(request, lastName);
		String username = request.getParameter("username");
		validateUsername(request, username);
		String emailId = request.getParameter("email-id");
		validateEmailId(request, emailId);
		String password = request.getParameter("password");
		validatePassword(request, password);
		String confirmPassword = request.getParameter("confirm-password");
		validateConfirmPassword(request, confirmPassword, password);
		
		
		for(String error:errorMessages) {
			if(error.contains("a")) {
				request.setAttribute("errorMessages", errorMessages);
				request.getRequestDispatcher("WEB-INF/pages/signup.jsp").forward(request, response);
				return;
			}
		}

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setEmailId(emailId);
		user.setPassword(password);
		try {
			if (!loginService.signup(user)) {
				String message = "Signup Error! Try Again";
				response.sendRedirect("WEB-INF/pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
				return;
			} else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			String message = "Signup Error! Try Again";
			response.sendRedirect("WEB-INF/pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			return;
		}

	}

	protected boolean validateFirstName(HttpServletRequest request, String firstName) {
		String nameRegex = "[a-zA-Z]+";
		if (!Validator.validate(nameRegex, firstName)) {
			errorMessages.add("Invalid First Name");
			return false;
		} else {
			errorMessages.add(" ");
		}
		return true;
	}

	protected boolean validateLastName(HttpServletRequest request, String lastName) {
		String nameRegex = "[a-zA-Z]+";
		if (!Validator.validate(nameRegex, lastName)) {
			errorMessages.add("Invalid Last Name");
			return false;
		} else {
			errorMessages.add("");
		}
		return true;
	}

	protected boolean validateUsername(HttpServletRequest request, String username) {
		String usernameRegex = "^[a-zA-Z0-9._-]{3,}$";
		if (!Validator.validate(usernameRegex, username)) {
			errorMessages.add("Invalid Username");
			return false;
		} else {
			errorMessages.add("");
		}
		return true;
	}

	protected boolean validateEmailId(HttpServletRequest request, String emailId) {
		String emailRegex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		if (!Validator.validate(emailRegex, emailId)) {
			errorMessages.add("Invalid Email ID");
			return false;
		} else {
			errorMessages.add("");
		}
		return true;
	}

	protected boolean validatePassword(HttpServletRequest request, String password) {
		String passwordRegex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
		if (!Validator.validate(passwordRegex, password)) {
			errorMessages.add("Invalid Password");
			return false;
		} else {
			errorMessages.add("");
		}
		return true;
	}

	protected boolean validateConfirmPassword(HttpServletRequest request, String confirmPassword, String password) {
		if (!password.equals(confirmPassword)) {
			errorMessages.add("Passwords do not match");
			return false;
		} else {
			errorMessages.add("");
		}
		return true;
	}

}
