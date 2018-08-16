package com.zilker.beans;

public class Schedule {
	int team1_id = 0, team2_id = 0, tournament_id;
	String matches = "";

	public void setTeam1Id(int team1_id) {
		this.team1_id = team1_id;
	}

	public int getTeam1Id() {
		return this.team1_id;
	}

	public void setTeam2Id(int team2_id) {
		this.team2_id = team2_id;
	}

	public int getTeam2Id() {
		return this.team2_id;
	}

	public void setTournamentId(int tournament_id) {
		this.tournament_id = tournament_id;
	}

	public int getTournamentId() {
		return this.tournament_id;
	}

	public void setMatches(String matches) {
		this.matches = matches;
	}

	public String getMatches() {
		return this.matches;
	}
}
