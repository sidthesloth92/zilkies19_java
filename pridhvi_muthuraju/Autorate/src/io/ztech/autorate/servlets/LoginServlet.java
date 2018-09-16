package io.ztech.autorate.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.services.CheckLoginService;
import io.ztech.autorate.services.LoginService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CheckLoginService checkLoginService = new CheckLoginService();
	LoginService loginService = new LoginService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");


		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		try {
			if (!checkLoginService.isUser(user)) {
//				logger.info(AppConstants.NO_USER_SIGNUP);
//				response.sendRedirect(request.getHeader("Referer"));

				String message = "Invalid Username or Password";
				response.sendRedirect(request.getHeader("Referer")+"?message=" + URLEncoder.encode(message, "UTF-8"));
				return;
			}
		} catch (SQLException e) {
//			logger.info(AppConstants.LOGIN_ERROR);
			String message = "Login Error! Try Again";
			response.sendRedirect(request.getHeader("Referer")+"?message=" + URLEncoder.encode(message, "UTF-8"));
			return;
		}
		try {
			if (checkLoginService.isAdmin(user)) {
				user.setAdminStatus(AppConstants.ADMIN);
				loginService.login(user);
				response.sendRedirect("pages/admin.html");return;
			} else {
				user.setAdminStatus(AppConstants.USER);
				loginService.login(user);
				request.getSession().setAttribute("loggedInUser", user);
				response.sendRedirect(request.getHeader("Referer"));return;
			}
		} catch (SQLException e) {
//			logger.info(AppConstants.LOGIN_ERROR);
			response.sendRedirect(request.getHeader("Referer"));return;
		}

	}

}
