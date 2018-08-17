package com.zilker.beans;

public class Tournament {
	String tournament_name = "", format = "";

	public void setTournamentName(String tournament_name) {
		this.tournament_name = tournament_name;
	}

	public String getTournamentName() {
		return this.tournament_name;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return this.format;
	}
}