package io.zilker.fantasy.bean;

public class Match {
	private String teamOne, teamTwo, scheduledDate, startTime, endTime;
	private int matchId, matchCredits, isActive, isUpcomming;

	// setter for setting values
	public void setMatchTable(String teamOne, String teamTwo, String scheduledDate, String startTime, String endTime,
			int matchCredits) {
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.scheduledDate = scheduledDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.matchCredits = matchCredits;
	}

	public void setStatusTable(int matchId, int isActive, int isUpcomming) {
		this.matchId = matchId;
		this.isActive = isActive;
		this.isUpcomming = isUpcomming;

	}

	// getters returns match id
	public int getMatchId() {
		return matchId;
	}

	// getters returns team one
	public String getTeamOne() {
		return teamOne;
	}

	// getters returns team two
	public String getTeamTwo() {
		return teamTwo;
	}

	// getters returns scheduled date
	public String getScheduledDate() {
		return scheduledDate;
	}

	// getters returns start time of match
	public String getStartTime() {
		return startTime;
	}

	// getters returns end time of match
	public String getEndTime() {
		return endTime;
	}

	// getters returns credits awarded to each user to pick players of match
	public int getMatchCredits() {
		return matchCredits;
	}

	// getters returns the active of match
	public int getActiveStatus() {
		return isActive;
	}

	// getters returns upcomming of match
	public int getUpcommingStatus() {
		return isUpcomming;
	}

}
