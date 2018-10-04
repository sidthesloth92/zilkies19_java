package com.ztech.FitnessAppWeb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ztech.FitnessAppWeb.delegates.FoodItemOperationsDelegate;

@WebServlet("/FoodDetailsServlet")
public class FoodDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FoodDetailsServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String foodname = request.getParameter("foodname");
		String foodList = new FoodItemOperationsDelegate().getFoodsList(foodname);
		//System.out.println("foodList in servlet" + foodList);
		response.getWriter().print(foodList);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}

//
//try {
//	ArrayList<FoodItem> foodList = new FoodTrackerDao().getFoodList(foodname);
//
//	JSONArray jsonArray = new JSONArray();
//
//	for (FoodItem item : foodList) {
//		JSONObject obj = new JSONObject();
//		obj.put("foodId", item.getFoodID());
//		obj.put("foodName", item.getFoodName());
//		obj.put("servingsize", item.getServing());
//		obj.put("calories", item.getCalories());
//		jsonArray.add(obj);
//	}
//	response.getWriter().print(jsonArray);

//} catch (ClassNotFoundException | SQLException e) {
//	e.printStackTrace();
//}
