package com.zilker.beans;

public class Player {
	String player_name = "", player_role = "";
	int team_id = 0;

	public void setPlayerName(String player_name) {
		this.player_name = player_name;
	}

	public String getPlayerName() {
		return this.player_name;
	}

	public void setPlayerRole(String player_role) {
		this.player_role = player_role;
	}

	public String getPlayerRole() {
		return player_role;
	}

	public void setTeamId(int team_id) {
		this.team_id = team_id;
	}

	public int getTeamId() {
		return this.team_id;
	}
}
