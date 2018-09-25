package io.ztech.placementportal.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.ztech.placementportal.bean.Register;
import io.ztech.placementportal.constants.Regex;
import io.ztech.placementportal.delegate.LoginDelegate;
import io.ztech.placementportal.ui.ValidateInput;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log;

	public LoginController() {
		super();
		log = Logger.getLogger("LoginController.class");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entering post method of login controller");
		Register login = new Register();
		Date date = new Date();
		Register validUser;
		LoginDelegate loginDelegate = new LoginDelegate();
		ValidateInput validate = new ValidateInput();
		if (validate.validateDetail(Regex.USERNAME_REGEX, request.getParameter("username"))) {
		} else {
			response.getWriter().write("username_error");
			return;
		}
		if (validate.validateDetail(Regex.PASSWORD_REGEX, request.getParameter("password"))) {
		} else {
			response.getWriter().write("password_error");
			return;
		}
		login.setUserName(request.getParameter("username"));
		login.setPassword(request.getParameter("password"));
		login.setTime(new Timestamp(date.getTime()));

		try {
			validUser = loginDelegate.checkLoginCredential(login);
			if (validUser != null) {
				HttpSession session = request.getSession();
				System.out.println(validUser.getUserName());
				session.setAttribute("username", validUser.getUserName());
				System.out.println(session.getAttribute("username"));
				session.setAttribute("student_id", login.getUserName());
				session.setAttribute("role", validUser.getRole());
				response.getWriter().write(validUser.getRole());
			} else {
				response.getWriter().write("invalid login credentials");
			}

		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting post method of login controller");

	}

}
