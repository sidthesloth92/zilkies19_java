package com.ztech.FitnessApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.ztech.FitnessApp.beans.FoodItem;
import com.ztech.FitnessApp.beans.FoodLog;
import com.ztech.FitnessApp.beans.FoodLogEnquiryDetails;
import com.ztech.FitnessApp.constants.SQLQueryStrings;
import com.ztech.FitnessApp.dbutils.DBConfiguration;

public class FoodTrackerDao {
	private static Connection connection;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static DBConfiguration connectionObject;
	private static Logger logger;

	public FoodTrackerDao() {
		connectionObject = new DBConfiguration();
		logger = Logger.getLogger(FoodTrackerDao.class.getName());
	}

	public ArrayList<FoodItem> getFoodList(String foodname) {

		logger.info("enter getFoodList @ FoodTrackerDao");
		ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
		FoodItem item = null;
		try {
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
			logger.info("exit getFoodList @ FoodTrackerDao");
		}
		return foodList;
	}

	// get food id
	public int getFoodID(FoodLog log) {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_FOOD_ID);
			ps.setString(1, log.getFoodName());

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return 0;
	}

	// get unique id for user and date
	public int addFoodTrackerEntry(FoodLog log) {

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
				if (added > 0) {
					return addFoodTrackerEntry(log);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return 0;
	}

	// get unique id for food and quantity
	public int addMealItemEntry(FoodLog log) {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_MEAL_ITEM_ID);
			ps.setInt(1, log.getFoodID());
			ps.setFloat(2, log.getQuantity());

			rs = ps.executeQuery();

			if (rs.next()) {
				// System.out.println("returned" + rs.getInt(1));
				return rs.getInt(1);
			} else {

				// System.out.println("meal doesnt exist" + log.getFoodID() +
				// log.getQuantity());
				ps = connection.prepareStatement(SQLQueryStrings.ADD_MEAL_ITEM);
				ps.setInt(1, log.getFoodID());
				ps.setFloat(2, log.getQuantity());

				int added = ps.executeUpdate();
				if (added > 0) {
					return addMealItemEntry(log);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return 0;
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
		} catch (SQLIntegrityConstraintViolationException ex) {

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	// delete food log of user(food tracker id), mealtime
	public boolean deleteFoodLog(FoodLogEnquiryDetails log) {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.DELETE_FOOD_LOG);
			ps.setInt(1, log.getFoodTrackerID());
			ps.setInt(2, log.getMealTime());

			int added = ps.executeUpdate();
			if (added > 0) {
				return true;
			}
		} catch (SQLIntegrityConstraintViolationException ex) {

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return false;
	}

	// get id of user and date
	public int getFoodTrackerEntry(FoodLogEnquiryDetails enquiry) {

		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_FOOD_TRACKER_LOG_ID);
			ps.setString(1, enquiry.getUserName());
			ps.setString(2, enquiry.getDate());

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return 0;
	}

	// get id for meals of user in a mealtime
	public ArrayList<Integer> getMealList(FoodLogEnquiryDetails enquiry) {

		ArrayList<Integer> list = new ArrayList<>();
		try {
			connection = connectionObject.getConnection();
			ps = connection.prepareStatement(SQLQueryStrings.GET_MEAL_ITEMS);
			ps.setInt(1, enquiry.getFoodTrackerID());
			ps.setInt(2, enquiry.getMealTime());

			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return list;
	}

	// get food id and qnty
	public ArrayList<FoodLog> getFoodItemsList(ArrayList<Integer> mealList) {

		ArrayList<FoodLog> foodItemList = new ArrayList<>();
		try {
			connection = connectionObject.getConnection();
			for (Integer i : mealList) {
				ps = connection.prepareStatement(SQLQueryStrings.GET_FOOD_ITEM);
				ps.setInt(1, i);

				FoodLog logItem;

				rs = ps.executeQuery();
				if (rs.next()) {
					logItem = new FoodLog();
					logItem.setFoodID(rs.getInt("food_id"));
					logItem.setQuantity(rs.getFloat("quantity"));
					foodItemList.add(logItem);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return foodItemList;
	}

	// get food name for foodid in list
	public ArrayList<FoodItem> getFoodItemsListWithDetails(ArrayList<FoodLog> foodLogList) {

		ArrayList<FoodItem> foodsList = new ArrayList<>();
		FoodItem item;
		try {
			connection = connectionObject.getConnection();
			for (FoodLog log : foodLogList) {
				ps = connection.prepareStatement(SQLQueryStrings.GET_FOOD_NODE);
				ps.setInt(1, log.getFoodID());
				item = new FoodItem();

				rs = ps.executeQuery();
				if (rs.next()) {
					item.setFoodName(rs.getString("food_name"));
					item.setServing(rs.getString("serving_name"));
					item.setCalories(Integer.parseInt(rs.getString("calories")));
					item.setQuantity(log.getQuantity());
					foodsList.add(item);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			connectionObject.closeConnection(connection, rs, ps);
		}
		return foodsList;
	}

}