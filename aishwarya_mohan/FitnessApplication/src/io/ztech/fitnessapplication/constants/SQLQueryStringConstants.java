package io.ztech.fitnessapplication.constants;

public class SQLQueryStringConstants {
	public static String INSERT_USER = "insert into user_account_details(user_name, password, role, first_name, last_name, email_id, phone_no)\n"
			+ "values(?,?,?,?,?,?,?);";

	public static String GET_ACCOUNT_DETAILS = "select * from user_account_details where user_name=? and password=?";
	public static String UPDATE_ACCOUNT_DETAILS = "update user_account_details set first_name=?,last_name=?,email=?,phone=? where user_name=?;";

	public static String GET_FIRST_NAME = "select first_name from user_account_details where user_name=? ;";
	public static String GET_LAST_NAME = "select last_name from user_account_details where user_name=? ;";
	public static String GET_USER_TYPE = "select role from user_account_details where user_name=?;";
	public static String GET_EMAIL = "select email_id from user_account_details where user_name=?;";
	public static String GET_PHONE = "select phone_no from user_account_details where user_name=?;";

	public static String SET_FIRST_NAME = "UPDATE user_account_details SET first_name=? WHERE user_name=?;";
	public static String SET_LAST_NAME = "UPDATE user_account_details SET last_name=? WHERE user_name=?;";
	public static String SET_USER_NAME = "UPDATE user_account_details SET user_name=? WHERE user_name=?;";
	public static String SET_PASSWORD = "UPDATE user_account_details SET password=? WHERE user_name=?;";
	public static String SET_USER_TYPE = "UPDATE user_account_details SET role=? WHERE user_name=?;";
	public static String SET_EMAIL = "UPDATE user_account_details SET email_id=? WHERE user_name=?;";
	public static String SET_PHONE = "UPDATE user_account_details SET phone_no=? WHERE user_name=?;";

	public static String INSERT_PROFILE = "insert into user_physical_details(user_name,gender,age,height,weight,activity_id, bmi, bmr, target_id)\n"
			+ "values(?,?,?,?,?,?,?,?,?);";
	public static String UPDATE_PROFILE = "update user_physical_details set height=?, weight=?, age=?, gender=?,activity_id=?, bmi=?, bmr=?,target_id=? where user_name=?;";
	public static String GET_PROFILE = "select * from user_physical_details where user_name=?";

	public static String INSERT_TARGET = "update user_physical_details set target_id=? where user_name=?";

	public static String INSERT_WEIGHT_LOG = "insert into weight_tracker(user_name,entry_date,weight)\n"
			+ "values (?,curdate(),?);";

	public static String SET_WEIGHT = "update user_physical_details set weight=? where user_name=?;";

	public static String GET_FOOD = "select food_id, food_name from food_items;";
	public static String GET_CALORIES = "select calories from food_items where food_id=?;";

	public static String ADD_LOG_TO_TRACKER = "insert into daily_food_tracker(user_name,entry_date,meal_time_id)\n"
			+ "values(?,curdate(),?);";

	public static String GET_LOG_ID = "SELECT log_id from daily_food_tracker where user_name=? and entry_date=curdate() and meal_time_id=?;";

	public static String GET_MEAL_ID = "select meal_id from meal_logs where food_id=? and quantity=?;";
	public static String INSERT_MEAL = "insert into meal_logs(food_id, quantity) values(?,?);";

	public static String ENTER_FOOD_LOG = "insert into user_food_log(log_id,meal_id) values (?,?);";

}
