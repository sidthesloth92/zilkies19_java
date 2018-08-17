package io.ztech.fitnessapplication.constants;

public class SQLQueryStringConstants {
	public static String INSERT_USER = "insert into user_account_details(access_id,first_name,last_name,user_name,pass_word,email_id,phone_no,login_status)"
			+ "values (?,?,?,?,?,?,?,0);";

	public static String GET_ACCOUNT_DETAILS = "select * from user_account_details where reg_id=?";
	public static String GET_REG_ID = "select reg_id from user_account_details where user_name=? and pass_word=?;";
	public static String GET_FIRST_NAME = "select first_name from user_account_details where reg_id=?;";
	public static String GET_LAST_NAME = "select last_name from user_account_details where reg_id=?;";
	public static String GET_USER_NAME = "select user_name from user_account_details where reg_id=?;";
	public static String GET_PASSWORD = "select pass_word from user_account_details where reg_id=?;";
	public static String GET_USER_TYPE = "select access_id from user_account_details where reg_id=?;";
	public static String GET_EMAIL = "select email_id from user_account_details where reg_id=?;";
	public static String GET_PHONE = "select phone_no from user_account_details where reg_id=?;";
	public static String GET_LOGIN_STATUS = "select login_status from user_account_details where reg_id=?;";

	public static String SET_REG_ID = "UPDATE user_account_details SET reg_id=? WHERE reg_id=?;";
	public static String SET_FIRST_NAME = "UPDATE user_account_details SET first_name=? WHERE reg_id=?;";
	public static String SET_LAST_NAME = "UPDATE user_account_details SET last_name=? WHERE reg_id=?;";
	public static String SET_USER_NAME = "UPDATE user_account_details SET user_name=? WHERE reg_id=?;";
	public static String SET_PASSWORD = "UPDATE user_account_details SET pass_word=? WHERE reg_id=?;";
	public static String SET_USER_TYPE = "UPDATE user_account_details SET access_id=? WHERE reg_id=?;";
	public static String SET_EMAIL = "UPDATE user_account_details SET email_id=? WHERE reg_id=?;";
	public static String SET_PHONE = "UPDATE user_account_details SET phone_no=? WHERE reg_id=?;";
	public static String SET_LOGIN_STATUS = "UPDATE user_account_details SET login_status=? WHERE reg_id=?;";

	public static String INSERT_STATS = "insert into user_stats(reg_id,gender,age,height,weight,activity,bmi,bmr)\n"
			+ "values(?,?,?,?,?,?,?,?);";
	public static String UPDATE_STATS = "update user_stats set height=?, weight=?, age=?, gender=?,activity=?, bmi=?, bmr=?,target=? where reg_id=?;";
	public static String GET_STATS = "select * from user_stats where reg_id=?";

	public static String INSERT_TARGET = "update user_stats set target=? where reg_id=?";

	public static String INSERT_WEIGHT_LOG = "insert into weight_tracker(reg_id,entry_date,weight)  values(?,curdate(),?);";

	public static String SET_WEIGHT = "update user_stats set weight=? where reg_id=?;";

	public static String GET_FOOD = "select food_id, food_name from food;";
	public static String GET_CALORIES = "select calories from food where food_id=?;";

	public static String ADD_LOG_TO_TRACKER = "insert into daily_food_tracker(reg_id,entry_date,meal_time)"
			+ "values(?,curdate(),?);";

	public static String GET_LOG_ID = "SELECT log_id from daily_food_tracker where reg_id=? and entry_date=curdate() and meal_time=?;";

	public static String GET_MEAL_ID = "select meal_id from food_intake where food_id=? and qnty=?;";
	public static String INSERT_MEAL = "insert into food_intake(food_id, qnty, calories) values(?,?,?);";

	public static String ENTER_FOOD_LOG = "insert into food_log(log_id, meal_id) values(?,?);";

}
