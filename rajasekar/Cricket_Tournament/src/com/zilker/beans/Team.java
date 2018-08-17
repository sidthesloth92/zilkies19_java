package com.zilker.beans;

public class Team {
	String team_name = "",email="";
	int tournament_id = 0;

	public void setTeamName(String team_name) {
		this.team_name = team_name;
	}

	public String getTeamName() {
		return this.team_name;
	}

	public void setTournamentId(int tournament_id) {
		this.tournament_id = tournament_id;
	}

	public int getTournamentId() {
		return this.tournament_id;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}
}
