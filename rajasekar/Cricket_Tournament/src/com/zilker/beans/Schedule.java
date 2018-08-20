package com.zilker.beans;

public class Schedule {
	int team1Id, team2Id, tournamentId;
	String matches;

	public void setTeamOneId(int team1Id) {
		this.team1Id = team1Id;
	}

	public int getTeamOneId() {
		return this.team1Id;
	}

	public void setTeamtwoId(int team2Id) {
		this.team2Id = team2Id;
	}

	public int getTeamTwoId() {
		return this.team2Id;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public int getTournamentId() {
		return this.tournamentId;
	}

	public void setMatches(String matches) {
		this.matches = matches;
	}

	public String getMatches() {
		return this.matches;
	}
}
