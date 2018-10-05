package com.ztech.FitnessAppWeb.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.FitnessAppWeb.beans.User;
import com.ztech.FitnessAppWeb.delegates.AccountOperationsDelegate;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;

	public LoginServlet() {
		super();
		logger = Logger.getLogger(LoginServlet.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// logout
		// forwarding to homepage
		logger.info("enter doGet @ LoginServlet to logout");
		//request.getRequestDispatcher("/index.jsp").include(request, response);
		response.sendRedirect("index.jsp");

		HttpSession session = request.getSession();
		session.invalidate();

		response.getWriter().print("loggedout");
		logger.info("exit doGet @ LoginServlet (logged out)");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("enter doPost @ LoginServlet");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		// server validation
		if (userName.length() == 0) {
			response.getWriter().print("emptyusername");
		}

		if (password.length() == 0) {
			response.getWriter().print("emptypassword");
		}

		String regex = "[a-zA-Z0-9_-]{2,30}";
		if (!Pattern.matches(regex, userName)) {
			response.getWriter().print("invalidusername");
		}

		regex = "[/S]+";
		if (Pattern.matches(regex, password)) {
			response.getWriter().print("invalidpassword");
		}

		User user = new User();
		user.setUsername(userName);
		user.setPassword(password);

		try {
			boolean isValidUser = new AccountOperationsDelegate().login(user);
			if (isValidUser) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", user.getUserName());
				response.getWriter().print("validuser");
			} else {
				response.getWriter().print("invaliduser");
			}
			logger.info("exit doPost @ LoginServlet with status" + isValidUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
