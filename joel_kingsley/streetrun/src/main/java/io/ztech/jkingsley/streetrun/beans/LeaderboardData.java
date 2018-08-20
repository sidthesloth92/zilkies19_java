package io.ztech.jkingsley.streetrun.beans;

import java.sql.Time;

public class LeaderboardData {
	Float total_distance_traveled;
	Time cumulative_avg_pace;
	
	public Float getTotal_distance_traveled() {
		return total_distance_traveled;
	}
	public void setTotal_distance_traveled(Float total_distance_traveled) {
		this.total_distance_traveled = total_distance_traveled;
	}
	public Time getCumulative_avg_pace() {
		return cumulative_avg_pace;
	}
	public void setCumulative_avg_pace(Time cumulative_avg_pace) {
		this.cumulative_avg_pace = cumulative_avg_pace;
	}
	
	
}
