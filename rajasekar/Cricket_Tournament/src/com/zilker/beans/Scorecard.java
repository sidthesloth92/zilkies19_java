package com.zilker.beans;

public class Scorecard {
	int matchNo, teamId, runs, wickets, tournamentId;
	float overs;

	public int getmatchNo() {
		return matchNo;
	}

	public void setmatchNo(int matchNo) {
		this.matchNo = matchNo;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public float getOvers() {
		return overs;
	}

	public void setOvers(float overs) {
		this.overs = overs;
	}
}
