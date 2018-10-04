package com.zilker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zilker.beans.*;
import com.zilker.service.ServiceCrudOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		String result = "";
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if (method.equals("emailvalidation")) {
			String email = request.getParameter("email");
			ServiceCrudOperations uservalidation = new ServiceCrudOperations();
			Login credentials = new Login();
			credentials.setEmail(email);
			try {
				boolean validate = uservalidation.isValidEmail(credentials);
				if (validate == false) {
					result = "Invalid email";
					response.getWriter().write(result);
				} else {
					result = "";
					response.getWriter().write(result);
				}
			} catch (SQLException e) {
			}
		}
		else if(method.equals("credentialsvalidation")) {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			ServiceCrudOperations uservalidation = new ServiceCrudOperations();
			Login credentials = new Login();
			credentials.setEmail(email);
			credentials.setPassword(password);
			try {
				boolean validate = uservalidation.isValidCredentials(credentials);
				if (validate == false) {
					result = "Invalid password";
					response.getWriter().write(result);
				} else {
					result = "";
					response.getWriter().write(result);
				}
			} catch (SQLException e) {
			}
		}
		
		else if(method.equals("emailtaken")) {
			String email=request.getParameter("email");
			ServiceCrudOperations uservalidation = new ServiceCrudOperations();
			Login credentials = new Login();
			credentials.setEmail(email);
			try {
				boolean validate = uservalidation.isValidEmail(credentials);
				if (validate == true) {
					result = "This e-mail id already taken";
					response.getWriter().write(result);
				} else {
					result = "";
					response.getWriter().write(result);
				}
			} catch (SQLException e) {
			}
		}
		else if(method.equals("admin")){
			response.setContentType("text/html");
			ArrayList tournamentDetails=new ArrayList();
			ServiceCrudOperations tournament=new ServiceCrudOperations();
			try {
				tournamentDetails=tournament.showTournament();
			} catch (SQLException e) {
			}
			request.setAttribute("tournamentDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/AdminHomePage.jsp").include(request, response);
		}
		else if(method.equals("user")){
			response.setContentType("text/html");
			ArrayList tournamentDetails=new ArrayList();
			ServiceCrudOperations tournament=new ServiceCrudOperations();
			try {
				tournamentDetails=tournament.showTournament();
			} catch (SQLException e) {
			}
			request.setAttribute("tournamentDetails", tournamentDetails);
			request.getRequestDispatcher("/WEB-INF/pages/AdminHomePage.jsp").include(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Login credentials = new Login();
		credentials.setEmail(email);
		credentials.setPassword(password);
		ServiceCrudOperations loginuser = new ServiceCrudOperations();
		try {
			boolean flag = loginuser.getUserLoginInfo(credentials);
			String userName = loginuser.getUserName(credentials);

			if (flag) {
				boolean userType = loginuser.getRole(credentials);
				HttpSession session = request.getSession();
				session.setAttribute("user", userName);
				session.setAttribute("email", email);
				ArrayList tournamentDetails=new ArrayList();
				ServiceCrudOperations tournament=new ServiceCrudOperations();
				tournamentDetails=tournament.showTournament();
				request.setAttribute("tournamentDetails", tournamentDetails);
				if (userType == true) {
					request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp").include(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/pages/AdminHomePage.jsp").include(request, response);
				}
			} else {
				String message = "Invalid Credentials";
				request.getRequestDispatcher("/WEB-INF/pages/UserHomePage.jsp?message=" + URLEncoder.encode(message, "UTF-8"))
						.forward(request, response);
			}
		} catch (SQLException e) {
		}
	}

}
