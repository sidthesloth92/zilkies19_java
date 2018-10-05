package com.ztech.io.fitnessfirstprototype.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ztech.io.fitnessfirstprototype.beans.FoodItem;
import com.ztech.io.fitnessfirstprototype.beans.FoodLog;
import com.ztech.io.fitnessfirstprototype.constants.SQLQueryStrings;
import com.ztech.io.fitnessfirstprototype.dbutils.DBConfiguration;

public class FoodTrackerDao {
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfiguration connectionObject;

	public FoodTrackerDao() {
		connectionObject = new DBConfiguration();
	}

	// get food suggestions
	public ArrayList<FoodItem> getFoodList(String foodname) throws ClassNotFoundException, SQLException {
		ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
		FoodItem item = null;
		try {
			System.out.println("food tracker dao");
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_FOOD_ITEMS);
			ps.setString(1, "%" + foodname + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				item = new FoodItem();
				item.setFoodID(rs.getInt("food_id"));
				item.setFoodName(rs.getString("food_name"));
				item.setServing(rs.getString("serving_name"));
				item.setCalories(rs.getInt("calories"));

				foodList.add(item);
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return foodList;
	}

	// get food id
	public int getFoodID(FoodLog log) throws ClassNotFoundException, SQLException {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_FOOD_ID);
			ps.setString(1, log.getFoodName());

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
	}

	// get unique id for user and date
	public int addFoodTrackerEntry(FoodLog log) throws ClassNotFoundException, SQLException {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_FOOD_TRACKER_LOG_ID);
			ps.setString(1, log.getUserName());
			ps.setString(2, log.getDate());

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				ps = connection.prepareStatement(SQLQueryStrings.INSERT_FOOD_TRACKER_LOG);
				ps.setString(1, log.getUserName());
				ps.setString(2, log.getDate());

				int added = ps.executeUpdate();
				return addFoodTrackerEntry(log);
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
	}

	// get unique id for food and quantity
	public int addMealItemEntry(FoodLog log) throws ClassNotFoundException, SQLException {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_MEAL_ITEM_ID);
			ps.setInt(1, log.getFoodID());
			ps.setFloat(2, log.getQuantity());

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("returned"+rs.getInt(1));
				return rs.getInt(1);
			} else {
				
				System.out.println("meal doesnt exist"+log.getFoodID()+log.getQuantity());
				ps = connection.prepareStatement(SQLQueryStrings.ADD_MEAL_ITEM);
				ps.setInt(1, log.getFoodID());
				ps.setFloat(2, log.getQuantity());

				int added = ps.executeUpdate();
				return addMealItemEntry(log);
			}
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
	}

	// add food log of user(food tracker id), mealtime and meal
	public boolean addFoodLog(FoodLog log) {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.ADD_FOOD_LOG);
			ps.setInt(1, log.getFoodtrackerID());
			ps.setInt(2, log.getMealTime());
			ps.setInt(3, log.getMealItemID());

			int added = ps.executeUpdate();
			if (added > 0) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

}