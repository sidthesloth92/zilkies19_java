package io.ztech.cricalertfe.beans;

import java.util.ArrayList;

public class LineUp {
	private int matchId, teamId;
	private ArrayList<Integer> playerId;
	
	public LineUp() {
		playerId = new ArrayList<>();
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public ArrayList<Integer> getPlayerId() {
		return playerId;
	}

	public void setPlayerId(ArrayList<Integer> playerId) {
		this.playerId = playerId;
	}

	
}
