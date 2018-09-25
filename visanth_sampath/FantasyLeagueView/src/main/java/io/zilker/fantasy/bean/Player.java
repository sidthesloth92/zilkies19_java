package io.zilker.fantasy.bean;

public class Player {
	private String playerName, playerTeam, playerType;
	private int playerRating, playerId;

	// setters for player
	public void setPlayer(String playerName, String team, String type, int rating) {
		this.playerName = playerName;
		this.playerTeam = team;
		this.playerType = type;
		this.playerRating = rating;
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
		return playerTeam;
	}

	// getter return playerName
	public String getplayerType() {
		return playerType;
	}

	// getter return playerName
	public int getplayerRating() {
		return playerRating;
	}

	// getter return playerID
	public int getPlayerId() {
		return playerId;
	}

}
