package io.ztech.cricalertfe.beans;

import java.util.ArrayList;

public class User {
	private int userId;
	private String userName, name, email, password;
	private ArrayList<Player> players;
	private ArrayList<Team> teams;
	private ArrayList<Match> matches;

	public User() {
		players = new ArrayList<>();
		teams = new ArrayList<>();
		matches = new ArrayList<>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public void addPlayer(ArrayList<Player> players) {
		this.players.addAll(players);
	}

	public void addTeam(Team team) {
		this.teams.add(team);
	}

	public void addMatch(Match match) {
		this.matches.add(match);
	}
}
