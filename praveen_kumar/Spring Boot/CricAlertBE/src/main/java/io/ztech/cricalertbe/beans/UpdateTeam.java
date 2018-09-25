package io.ztech.cricalertbe.beans;

import java.util.ArrayList;

public class UpdateTeam {
	private Team team;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	private ArrayList<Player> playerList;
}
