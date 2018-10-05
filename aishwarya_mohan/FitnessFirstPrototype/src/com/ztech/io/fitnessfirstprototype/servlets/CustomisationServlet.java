package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.io.fitnessfirstprototype.beans.UserProfile;
import com.ztech.io.fitnessfirstprototype.beans.WeightLog;
import com.ztech.io.fitnessfirstprototype.delegates.ProfileOperationDelegator;
import com.ztech.io.fitnessfirstprototype.delegates.TrackerOperationDelegator;

@WebServlet("/CustomisationServlet")
public class CustomisationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomisationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println(request.toString());
		System.out.println(request.getParameter("height"));
		System.out.println(request.getParameter("age"));
		int gender = Integer.parseInt(request.getParameter("gender"));
		int age = Integer.parseInt(request.getParameter("age"));
		float height = Float.parseFloat(request.getParameter("height"));
		float weight = Float.parseFloat(request.getParameter("weight"));
		int lifestyle = Integer.parseInt(request.getParameter("lifestyle"));

		if (age < 10 || age > 150) {
			response.getWriter().print("invalidage");
			return;
		}

		if (height < 10) {
			response.getWriter().print("invalidheight");
			return;
		}
		if (weight < 5) {
			response.getWriter().print("invalidweight");
			return;
		}

		UserProfile newProfile = new UserProfile();

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.getWriter().print("loggedout");
			return;
		}
		newProfile.setUsername(session.getAttribute("userName").toString());
		newProfile.setAge(age);
		newProfile.setHeight(height);
		newProfile.setWeight(weight);
		newProfile.setGender(gender);
		newProfile.setLifestyle(lifestyle);
		newProfile.setTarget(0);

		// calculate bmi
		newProfile.setBmi(ProfileOperationDelegator.calculateBMI(newProfile));
		newProfile.setBmr(ProfileOperationDelegator.calculateBMR(newProfile));
		newProfile.setTdee((ProfileOperationDelegator.calculateTDEE(newProfile)));

		WeightLog log = new WeightLog();
		log.setUserName(newProfile.getUsername());
		log.setWeight(weight);

		LocalDate date = LocalDate.now();
		System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date));

		log.setDate(date + "");

		try {
			boolean flag = new ProfileOperationDelegator().customise(newProfile)
					&& new TrackerOperationDelegator().addWeightLog(log);
			if (flag) {
				response.getWriter().print("true");
			}
		} catch (ClassNotFoundException | SQLException e) {
			response.getWriter().print("false");
			e.printStackTrace();
		}

	}

}
