package io.ztech.autorate.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.autorate.beans.User;
import io.ztech.autorate.constants.AppConstants;
import io.ztech.autorate.delegates.LoginDelegate;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginDelegate loginDelegate = new LoginDelegate();

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

	protected void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getHeader("Referer").contains("CarServlet?id=")) {
			String temp = request.getHeader("Referer");
			for (int i = 0; i < temp.length(); i++) {
				if (temp.charAt(i) == '&') {
					response.sendRedirect(temp.substring(0, i));
					return;
				}
			}
		} else if (request.getHeader("Referer").contains("EditRatingServlet?car-id=")) {
			System.out.println(request.getParameter("car-id"));
			response.sendRedirect("WEB-INF/pages/car.jsp?id=" + request.getParameter("car-id"));
			return;
		}

		response.sendRedirect(request.getHeader("Referer"));
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
		user.setUsername(userName);
		user.setPassword(password);
		try {
			if (!loginDelegate.isUser(user)) {
				redirect(request, response);
				return;
			}
		} catch (Exception e) {
			redirect(request, response);
			return;
		}

		try {
			if (loginDelegate.isAdmin(user)) {
				user.setAdminStatus(AppConstants.ADMIN);
				loginDelegate.login(user);
				request.getSession().setAttribute("username", user.getUsername());
				request.getSession().setAttribute("status", "ADMIN");
				request.getRequestDispatcher("/FetchAdminServlet").forward(request, response);
				return;
			} else {
				user.setAdminStatus(AppConstants.USER);
				loginDelegate.login(user);
				request.getSession().setAttribute("username", user.getUsername());
				request.getSession().setAttribute("status", "USER");

				redirect(request, response);
				return;
			}
		} catch (Exception e) {
//			logger.info(AppConstants.LOGIN_ERROR);
			redirect(request, response);
			return;
		}

	}

}
