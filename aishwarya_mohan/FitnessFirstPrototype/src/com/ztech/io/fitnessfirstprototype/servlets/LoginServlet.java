package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.delegates.AccountOperationsDelegator;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// forwarding to homepage
		response.setContentType("text/html");
		request.getRequestDispatcher("/pages/homepage.jsp").include(request, response);

		HttpSession session = request.getSession();
		session.invalidate();

		response.getWriter().print("loggedout");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		System.out.println(user.getUsername());

		try {
			boolean isValidUser = new AccountOperationsDelegator().login(user);
			// System.out.println(isValidUser);
			if (isValidUser) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", user.getUsername());
				response.getWriter().print("validuser");
			} else {
				response.getWriter().print("invaliduser");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
