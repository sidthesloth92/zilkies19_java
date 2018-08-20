package io.ztech.fitnessapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import io.ztech.fitnessapplication.beans.FoodLog;
import io.ztech.fitnessapplication.constants.SQLQueryStringConstants;
import io.ztech.fitnessapplication.dbutils.Config;

public class FoodLogDao {
	private static final Config con_obj = new Config();
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public HashMap<Integer, String> displayFood() {
		HashMap<Integer, String> foodMap = new HashMap<>();
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_FOOD);
			rs = ps.executeQuery();

			while (rs.next()) {
				foodMap.put(rs.getInt(1), rs.getString(2));
			}

		} catch (SQLException e) {
			return foodMap;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return foodMap;
	}

	public int getMealID(FoodLog foodLog) {
		int mealID = 0;
		try {
			conn = con_obj.getConnection();
			ps = conn.prepareStatement(SQLQueryStringConstants.GET_MEAL_ID);
			ps.setInt(1, foodLog.getFoodID());
			ps.setFloat(2, foodLog.getQuantity());
			
			rs=ps.executeQuery();

			if (rs.next()) {
				mealID = rs.getInt(1);
			} else {
				ps = conn.prepareStatement(SQLQueryStringConstants.INSERT_MEAL);
				ps.setInt(1, foodLog.getFoodID());
				ps.setFloat(2, foodLog.getQuantity());
				ps.executeUpdate();
				return getMealID(foodLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			//con_obj.closeConnection(conn, rs, ps);
		}
		return mealID;
	}

	public int getLogID(FoodLog foodLog) {
		int logID = 0;
		try {
			conn = con_obj.getConnection();

			ps = conn.prepareStatement(SQLQueryStringConstants.GET_LOG_ID);
			ps.setString(1, foodLog.getUserName());
			ps.setInt(2, foodLog.getMealTime());
			rs = ps.executeQuery();
			
			if (rs.next()) {
				logID = rs.getInt(1);
			} else {
				ps = conn.prepareStatement(SQLQueryStringConstants.ADD_LOG_TO_TRACKER);
				ps.setString(1, foodLog.getUserName());
				ps.setInt(2, foodLog.getMealTime());

				ps.executeUpdate();
				return getLogID(foodLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			//con_obj.closeConnection(conn, rs, ps);
		}
		return logID;
	}

	public boolean enterFoodLog(FoodLog foodLog) {
		try {
			conn = con_obj.getConnection();
			ps = conn.prepareStatement(SQLQueryStringConstants.ENTER_FOOD_LOG);
			ps.setInt(1, foodLog.getLogID());
			ps.setInt(2, foodLog.getMealID());

			ps.executeUpdate();

		} catch (Exception e) {
			return false;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return true;
	}

	public float getCalories(FoodLog foodLog) {
		float calories = 0;
		try {
			conn = con_obj.getConnection();
			ps = conn.prepareStatement(SQLQueryStringConstants.GET_CALORIES);
			ps.setInt(1, foodLog.getFoodID());
			rs = ps.executeQuery();
			if (rs.next()) {
				calories = rs.getFloat(1);
			}

		} catch (Exception e) {
			return calories;
		} finally {
			con_obj.closeConnection(conn, rs, ps);
		}
		return calories;
	}
}
