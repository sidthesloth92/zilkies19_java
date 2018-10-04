package io.zilker.application.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.application.delegate.UserDelegate;
import io.zilker.application.logsession.UserSession;

/**
 * Servlet implementation class GeneralUser
 */
@WebServlet("/GeneralUser")
public class GeneralUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralUser.class.getName());

	public GeneralUser() {
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
		LOGGER.info("entering doGet of GeneralUser Servlet");
		UserDelegate userDelegate = new UserDelegate();
		if (request.getParameter("get-type") == null) {
			// To get the projects in the users location
			HttpSession session = request.getSession();
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			request.setAttribute("userProjectLocation", userDelegate.projectsInLocationSer(userSession.getUserId()));
			request.getRequestDispatcher("WEB-INF/pages/user-profile.jsp").forward(request, response);

		} else if (request.getParameter("get-type").equals("register-user")) {

			request.getRequestDispatcher("WEB-INF/pages/user-registration.jsp").forward(request, response);

		} else if (request.getParameter("get-type").equals("register-contractor")) {

			request.getRequestDispatcher("WEB-INF/pages/contractor-registration.jsp").forward(request, response);

		} else if (request.getParameter("get-type").equals("all-projects")) {

			request.setAttribute("allProjects", userDelegate.displayProjects());
			request.getRequestDispatcher("WEB-INF/pages/user-profile-all-projects.jsp").forward(request, response);

		} else if (request.getParameter("get-type").equals("edit-profile")) {

			request.getRequestDispatcher("WEB-INF/pages/user-profile-edit.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: " + request.getParameter("message") + " ")
				.append(request.getContextPath());
		LOGGER.info("Leaving doGet of GeneralUser Servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
