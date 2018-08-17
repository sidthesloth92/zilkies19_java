package io.ztech.jkingsley.streetrun.services;

import java.sql.Time;
import java.util.ArrayList;

import io.ztech.jkingsley.streetrun.beans.Activity;
import io.ztech.jkingsley.streetrun.beans.Group;
import io.ztech.jkingsley.streetrun.beans.LeaderboardData;
import io.ztech.jkingsley.streetrun.beans.Profile;

public class ActivityService {
	
	public boolean addActivity(Activity activity) {
		return true;
	}
	public boolean deleteActivity(Activity activity) {
		return true;
	}
	public ArrayList<Activity> getAllActivitiesOfProfile(Profile profile){
		return null;
	}
	public Float getTotalDistanceTraveledOfProfile(Profile profile) {
		return null;
	}
	public Long getTotalActivitiesOfProfile(Profile profile) {
		return null;
	}
	public Time getCumulativeAveragePaceOfProfile(Profile profile) {
		return null;
	}
	
	//LeaderboardData methods
	public LeaderboardData getProfileLeaderboardData(Profile profile){
		return null;
	}
	public ArrayList<LeaderboardData> getGroupLeaderboardData(Group group){
		return null;
	}
	public ArrayList<LeaderboardData> getGlobalLeaderboardData(){
		return null;
	}
	
}
