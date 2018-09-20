package com.ztech.io.fitnessfirstprototype.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ztech.io.fitnessfirstprototype.beans.FoodItem;
import com.ztech.io.fitnessfirstprototype.dao.FoodTrackerDao;

@WebServlet("/CalorieCalculatorServlet")
public class CalorieCalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalorieCalculatorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String foodname = request.getParameter("foodname");

		try {
			ArrayList<FoodItem> foodList = new FoodTrackerDao().getFoodList(foodname);

			JSONArray jsonArray = new JSONArray();

			for (FoodItem item : foodList) {
				JSONObject obj = new JSONObject();
				obj.put("foodId", item.getFoodID());
				obj.put("foodName", item.getFoodName());
				obj.put("servingsize", item.getServing());
				obj.put("calories", item.getCalories());
				jsonArray.add(obj);
			}
			response.getWriter().print(jsonArray);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
