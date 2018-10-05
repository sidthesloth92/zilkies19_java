package com.zilker.beans;

public class Schedule {
	int teamOneId, teamTwoId, tournamentId;
	String matches;

	public void setTeamOneId(int teamOneId) {
		this.teamOneId = teamOneId;
	}

	public int getTeamOneId() {
		return this.teamOneId;
	}

	public void setTeamTwoId(int teamTwoId) {
		this.teamTwoId = teamTwoId;
	}

	public int getTeamTwoId() {
		return this.teamTwoId;
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
