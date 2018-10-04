package com.ztech.FitnessApp.constants;

public class SQLQueryStrings {
	public static String GET_ACCOUNT_DETAILS = "select * from user_account_details where user_name=? and password=?";
	public static String CREATE_ACCOUNT = "insert into user_account_details (user_name, password, first_name, last_name, email_id, phone_no)\n"
			+ "values (?,?,?,?,?,?);";
	public static String LOGIN_CHECK = "select user_name from user_account_details where user_name=? and password=?";
	public static String USERNAME_EXISTS = "select * from user_account_details where user_name=?";
	public static String EMAIL_EXISTS = "select * from user_account_details where email_id=?";

	public static String CREATE_PROFILE = "insert into user_physical_details(user_name,gender, age, height, weight, activity_id, bmi, bmr) values (?,?,?,?,?,?,?,?)";
	public static String SET_AGE = "update user_physical_details set age=? where user_name=?";
	public static String SET_HEIGHT = "update user_physical_details set height=? where user_name=?";
	public static String SET_WEIGHT = "update user_physical_details set weight=? where user_name=?";
	public static String SET_LIFESTYLE = "update user_physical_details set lifestyle=? where user_name=?";
	public static String SET_BMI = "update user_physical_details set bmi=? where user_name=?";
	public static String SET_BMR = "update user_physical_details set bmr=? where user_name=?";
	public static String SET_TARGET = "update user_physical_details set target=? where user_name=?";

	public static String GET_PROFILE = "select * from user_physical_details where user_name=?";

	public static String GET_WEIGHT_LOGS = "select entry_date, weight from weight_tracker where user_name=?";
	public static String INSERT_WEIGHT_LOGS = "insert into weight_tracker(user_name, entry_date, weight) values(?,?,?)";

	public static String GET_FOOD_ITEMS = "select * from food_items where food_name like ?";
	public static String INSERT_FOOD_TRACKER_LOG = "insert into food_tracker(user_name, entry_date) values(?,?)";
	public static String GET_FOOD_TRACKER_LOG_ID = "select food_tracker_log_id from food_tracker where user_name=? and entry_date=?";

	public static String GET_FOOD_ID = "select food_id from food_items where food_name=?";
	public static String ADD_MEAL_ITEM = "insert into meal_item (food_id, quantity) values(?,?)";
	public static String GET_MEAL_ITEM_ID = "select meal_item_id from meal_item where food_id =? and quantity=?";

	public static String ADD_FOOD_LOG = "insert into food_logs(food_tracker_log_id, mealtime_id, meal_item_id) value(?,?,?)";

	public static String GET_MEAL_ITEMS = "select meal_item_id from food_logs where food_tracker_log_id = ? and mealtime_id=?";
	public static String GET_FOOD_ITEM = "select food_id, quantity from meal_item where meal_item_id=?";

	public static String GET_FOOD_NODE = "select * from food_items where food_id=?";

	public static String DELETE_FOOD_LOG = "delete from food_logs where food_tracker_log_id = ? and mealtime_id = ?";
}
