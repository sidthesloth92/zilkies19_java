package com.ztech.FitnessAppWeb.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ztech.FitnessAppWeb.delegates.FoodTrackerOperationsDelegate;

@WebServlet("/FoodTrackerServlet")
public class FoodTrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;

	public FoodTrackerServlet() {
		logger = Logger.getLogger(FoodTrackerServlet.class.getName());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("enter doGet @ FoodTrackerServlet");

		HttpSession session = request.getSession(false);
		String userName = session.getAttribute("userName").toString();
		String date = request.getParameter("date");
		int mealtime = Integer.parseInt(request.getParameter("mealtime"));

		String foodLogString = new FoodTrackerOperationsDelegate().getFoodLog(userName, date, mealtime);

		response.getWriter().print(foodLogString);
		
		logger.info("exit doGet @ FoodTrackerServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("enter doPost @ FoodTrackerServlet");

		HttpSession session = request.getSession(false);
		String userName = session.getAttribute("userName").toString();

		String jsonFoodListString = request.getParameter("foodslist");

		// System.out.println("json list string in servlet" + jsonFoodListString);

		//delete previous records in food logs
		
		
		
		
		boolean added = new FoodTrackerOperationsDelegate().addFoodLog(jsonFoodListString, userName);

		response.getWriter().print(added + "");
		logger.info("exit doPost @ FoodTrackerServlet");

	}

}

//JsonObject jsonObj = new JsonObject();
//jsonObj.addProperty("date", date);
//jsonObj.addProperty("mealtime", mealtime);
//jsonObj.addProperty("list", jsonFoodListString);
//
//String foodLogList = jsonObj.toString();
