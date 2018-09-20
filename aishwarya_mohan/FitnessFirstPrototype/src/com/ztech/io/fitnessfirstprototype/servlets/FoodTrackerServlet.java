package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ztech.io.fitnessfirstprototype.beans.FoodLog;
import com.ztech.io.fitnessfirstprototype.delegates.TrackerOperationDelegator;

@WebServlet("/FoodTrackerServlet")
public class FoodTrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FoodTrackerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String userName = session.getAttribute("userName").toString();

		// System.out.println("username" + userName + " is in food trackerServlet");
		// System.out.println(request.getParameter("foodslist"));

		String date = request.getParameter("date");
		int mealtime = Integer.parseInt(request.getParameter("mealtime"));

		String s = request.getParameter("foodslist");

		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(s);
			// System.out.println("parsed" + obj);
			JSONArray array = (JSONArray) obj;

			// System.out.println(array);

			ArrayList<FoodLog> foodLogList = new ArrayList<>();
			FoodLog log;

			for (int i = 0; i < array.size(); i++) {
				Object object = array.get(i);
				JSONObject jsonObj = (JSONObject) object;
				// System.out.println(jsonObj.get("name") + " " + jsonObj.get("quantity"));

				log = new FoodLog();
				log.setFoodName(jsonObj.get("name").toString());
				log.setQuantity(Float.parseFloat(jsonObj.get("quantity").toString()));
				log.setUserName(userName);
				log.setDate(date);
				log.setMealTime(mealtime);

				foodLogList.add(log);
			}

			boolean updated = new TrackerOperationDelegator().addFoodLog(foodLogList);
			response.getWriter().print(updated + "");

		} catch (ParseException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
