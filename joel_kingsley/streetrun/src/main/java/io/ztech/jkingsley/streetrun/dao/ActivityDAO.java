package io.ztech.jkingsley.streetrun.dao;

import java.util.ArrayList;

import io.ztech.jkingsley.streetrun.beans.Account;
import io.ztech.jkingsley.streetrun.beans.Activity;
import io.ztech.jkingsley.streetrun.beans.User;

public interface ActivityDAO {
	/*BigInteger run_id;
	BigInteger user_id;
	Float distance_traveled;
	Time time_taken;
	Time avg_pace;
	Integer calories;
	Double start_lat;
	Double start_long;
	Double end_lat;
	Double end_long;
	Date run_date;*/
	
	public ArrayList<Activity> getAllActivities();
	public ArrayList<Activity> getActivitiesOfUser(User user);
	public ArrayList<Activity> getActivitiesOfAccount(Account account);
	
	public boolean addActivity(Activity activity);
}
