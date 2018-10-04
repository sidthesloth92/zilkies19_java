package com.zilker.beans;

public class Tournament {
	String tournamentName, format,email;
	int userId,status;

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public String getTournamentName() {
		return this.tournamentName;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return this.format;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}
	public void setUserId(int userid) {
		this.userId = userid;
	}

	public int getUserId() {
		return this.userId;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return this.status;
	}
}