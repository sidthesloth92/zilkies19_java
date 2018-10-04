package com.ztech.FitnessAppWeb.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.FitnessAppWeb.beans.UserProfile;
import com.ztech.FitnessAppWeb.delegates.ProfileOperationDelegate;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;

	public DashboardServlet() {
		logger = Logger.getLogger(DashboardServlet.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("enter doGet @ DashboardServlet -> to go to dashboard");
		HttpSession session = request.getSession(false);
		String userName = (String) session.getAttribute("userName");

		try {
			UserProfile profile = new ProfileOperationDelegate().getProfile(userName);
			profile.setTdee(ProfileOperationDelegate.calculateTDEE(profile));

			request.setAttribute("userProfile", profile);
			request.setAttribute("bmiCategory", ProfileOperationDelegate.getBmiCategory(profile.getBmi()));
			request.setAttribute("bmiStatus", ProfileOperationDelegate.getBmiStatus((profile.getBmi())));
			request.setAttribute("tdee", profile.getTdee());

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/userdashboard.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("exit doGet @ DashboardServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		// for set target
//		HttpSession session = request.getSession(false);
//		if (session == null) {
//			response.getWriter().print("loggedout");
//			return;
//		}
//
//		String userName = (String) session.getAttribute("userName");
//		UserProfile profile = new UserProfile();
//		profile.setUsername(userName);
//		profile.setTarget(Integer.parseInt(request.getParameter("target")));
//		try {
//			boolean isUpdated = new ProfileOperationDelegator().setTarget(profile);
//			if (isUpdated) {
//				response.getWriter().print("updated");
//				return;
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			response.getWriter().print("notupdated");
//			e.printStackTrace();
//		}
	}

}
