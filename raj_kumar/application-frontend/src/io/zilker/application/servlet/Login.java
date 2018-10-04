package io.zilker.application.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.application.delegate.UserDelegate;
import io.zilker.application.logsession.UserSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Login() {
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
		UserDelegate userDelegate = new UserDelegate();
		UserSession userSession = userDelegate.userLogin(request.getParameter("username"),
				request.getParameter("password"));
		System.out.println("In the Servlet " + userSession.getUserId() + " the Role " + userSession.getUserRoll());

		if (userSession.getUserId() != 0 && userSession.getUserRoll().equals("USER")) {
			// Forwarding to User Page
			HttpSession session = request.getSession();
			session.setAttribute("userSession", userSession);
			session.setAttribute("isLoggedIn", "true");
			System.out.println("------------------");
			System.out.println(session.getAttribute("isLoggedIn"));
			request.getRequestDispatcher("GeneralUser").forward(request, response);

		} else if (userSession.getUserId() != 0 && userSession.getUserRoll().equals("CONTRACTOR")) {

			// Forwarding to Contractor Page
			HttpSession session = request.getSession();
			session.setAttribute("userSession", userSession);
			session.setAttribute("isLoggedIn", "true");
			request.getRequestDispatcher("Contractor?get-type=contractor-home").forward(request, response);

		} else if (userSession.getUserId() != 0 && userSession.getUserRoll().equals("ADMIN")) {

			// Forwarding to Admin Page
			HttpSession session = request.getSession();
			session.setAttribute("userSession", userSession);
			session.setAttribute("isLoggedIn", "true");
			request.getRequestDispatcher("WEB-INF/pages/admin.jsp").forward(request, response);
		} else {

			// When the login credentials are wrong
			String message = "Invalid Username or Password";
			String URLMapping = "index.jsp?message=" + URLEncoder.encode(message, "UTF-8");
			request.getRequestDispatcher(URLMapping).forward(request, response);

		}

	}

}
