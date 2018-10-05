package io.zilker.fantasy.bean;

public class Player {
	private String playerName, team, type;
	private int rating, playerId;

	// setters for player
	public void setPlayer(String playerName, String team, String type, int rating) {
		this.playerName = playerName;
		this.team = team;
		this.type = type;
		this.rating = rating;
	}

	// setter for player Id
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	// getter return playerName
	public String getplayerName() {
		return playerName;
	}

	// getter return team
	public String getplayerTeam() {
		return team;
	}

	// getter return playerName
	public String getplayerType() {
		return type;
	}

	// getter return playerName
	public int getplayerRating() {
		return rating;
	}

	// getter return playerID
	public int getPlayerId() {
		return playerId;
	}

}
