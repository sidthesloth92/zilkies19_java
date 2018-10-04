package io.ztech.cricalertfe.beans;

import java.sql.Timestamp;

import io.ztech.cricalertfe.constants.MatchResult;

public class Match {
	private int matchId;
	private User user;
	private String matchDatetime;
	private Team teamA, teamB;
	private LineUp teamALineUp, teamBLineUp;
	private String status, venue;
	private MatchResult matchResult;
	private MatchStats matchStats;
	
	public MatchResult getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public LineUp getTeamALineUp() {
		return teamALineUp;
	}

	public void setTeamALineUp(LineUp teamALineUp) {
		this.teamALineUp = teamALineUp;
	}

	public LineUp getTeamBLineUp() {
		return teamBLineUp;
	}

	public void setTeamBLineUp(LineUp teamBLineUp) {
		this.teamBLineUp = teamBLineUp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MatchStats getMatchStats() {
		return matchStats;
	}

	public void setMatchStats(MatchStats matchStats) {
		this.matchStats = matchStats;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public Team getTeamA() {
		return teamA;
	}

	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}

	public Timestamp getMatchDatetime() {
		return Timestamp.valueOf(matchDatetime);
	}

	public void setMatchDatetime(Timestamp matchDatetime) {
		this.matchDatetime = matchDatetime.toString();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
