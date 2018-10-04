package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ztech.io.fitnessfirstprototype.beans.User;
import com.ztech.io.fitnessfirstprototype.beans.WeightLog;
import com.ztech.io.fitnessfirstprototype.delegates.TrackerOperationDelegator;

@WebServlet("/WeightTrackerServlet")
public class WeightTrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeightTrackerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();

		HttpSession session = request.getSession(false);
		user.setUsername(session.getAttribute("userName").toString());
		//System.out.println("username=" + user.getUsername());

		try {
			ArrayList<WeightLog> logList = new TrackerOperationDelegator().getWeightLog(user);

//			System.out.println(logList.size());
//			for (WeightLog l : logList) {
//				System.out.println(l.getDate() + "" + l.getWeight());
//			}

			JSONArray jsonArray = new JSONArray();
			for (WeightLog log : logList) {
				//System.out.println(log.getDate() + "" + log.getWeight());
				JSONObject obj = new JSONObject();
				obj.put("date", log.getDate());
				obj.put("weight", log.getWeight());
				jsonArray.add(obj);
			}

			// JSONObject responseObj = new JSONObject();
			// responseObj.put("jsonArray", jsonArray);

			System.out.println("string in servlet = " + jsonArray);

			response.getWriter().print(jsonArray);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		System.out.println("request" + request);
//
//		Enumeration<String> params = request.getParameterNames();
//		while (params.hasMoreElements()) {
//			String paramName = params.nextElement();
//			System.out.println("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
//		}

		WeightLog log = new WeightLog();
		HttpSession session = request.getSession(false);
		log.setUserName(session.getAttribute("userName").toString());
		//System.out.println("username=" + log.getUserName());

//		System.out.println("date in servlet" + request.getParameter("date"));
//		System.out.println("weight in servlet" + request.getParameter("weight"));

		String date = (String) request.getParameter("date");
		float weight = Float.parseFloat((String) request.getParameter("weight"));

		log.setDate(date);
		log.setWeight(weight);
		
		try {
			boolean updatedFlag = new TrackerOperationDelegator().updateWeight(log);
			response.getWriter().print(updatedFlag+"");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
