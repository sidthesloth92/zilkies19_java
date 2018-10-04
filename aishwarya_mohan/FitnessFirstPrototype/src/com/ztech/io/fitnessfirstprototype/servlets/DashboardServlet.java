package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.io.fitnessfirstprototype.beans.UserProfile;
import com.ztech.io.fitnessfirstprototype.delegates.ProfileOperationDelegator;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.getWriter().print("loggedout");
			return;
		}

		String userName = (String) session.getAttribute("userName");
		try {
			UserProfile profile = new ProfileOperationDelegator().getProfile(userName);
			profile.setTdee(ProfileOperationDelegator.calculateTDEE(profile));
            
			request.setAttribute("userProfile", profile);
			request.setAttribute("bmiCategory", ProfileOperationDelegator.getBmiCategory(profile.getBmi()));
			request.setAttribute("bmiStatus", ProfileOperationDelegator.getBmiStatus((profile.getBmi())));
			request.setAttribute("tdee", profile.getTdee());

			ServletContext sc = this.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/pages/userdashboard.jsp");
			rd.forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// for set target
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.getWriter().print("loggedout");
			return;
		}

		String userName = (String) session.getAttribute("userName");
		UserProfile profile = new UserProfile();
		profile.setUsername(userName);
		profile.setTarget(Integer.parseInt(request.getParameter("target")));
		try {
			boolean isUpdated = new ProfileOperationDelegator().setTarget(profile);
			if (isUpdated) {
				response.getWriter().print("updated");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			response.getWriter().print("notupdated");
			e.printStackTrace();
		}
	}

}
