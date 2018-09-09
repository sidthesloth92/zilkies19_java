package io.ztech.autorate.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.services.LoginService;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String userName = request.getParameter("username");
		String emailId = request.getParameter("email-id");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm-password");


		if (firstName.isEmpty()) {
			String message = "First Name must be filled out";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

		if (lastName.isEmpty()) {
			String message = "Last Name must be filled out";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

		if (userName.isEmpty()) {
			String message = "Username must be filled out";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}
		
		if (!Pattern.matches("^[a-z0-9_-]{3,15}$", userName)) {
			String message = "Invalid Username";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

		if (emailId.isEmpty()) {
			String message = "Email ID must be filled out";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

		if (password.isEmpty()) {
			String message = "Password must be filled out";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}
		
		if (!Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", password)) {
			String message = "Invalid Password";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

		if (confirmPassword.isEmpty()) {
			String message = "Password must be filled out";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

		if (!password.equals(confirmPassword)) {
			String message = "Passwords did not match";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(userName);
		user.setEmailId(emailId);
		user.setPassword(password);
		try {
			if (!loginService.signup(user)) {
				String message = "Signup Error! Try Again";
				response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
			} else {
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			String message = "Signup Error! Try Again";
			response.sendRedirect("pages/signup.jsp?message=" + URLEncoder.encode(message, "UTF-8"));
		}

	}

}
