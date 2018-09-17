package io.ztech.cricalert.beans;

import java.util.ArrayList;

public class Team {
	private int teamId;
	private String teamName, abbreviation;
	private ArrayList<Player> players;
	private User user;

	public Team() {
		players = new ArrayList<Player>();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
}
