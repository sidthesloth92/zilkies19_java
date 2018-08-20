package io.zilker.fantasy.bean;

import java.util.ArrayList;

public class UserPickedTeam {
	private ArrayList<Integer> playerId = new ArrayList<Integer>();
	private ArrayList<Integer> credits = new ArrayList<Integer>();
	private int matchId, index;

	public void setPlayerId(ArrayList<Integer> playerId) {
		for (int index = 0; index < playerId.size(); index++) {
			this.playerId.add(playerId.get(index));
		}
	}

	public void setPlayerCredits(ArrayList<Integer> credits) {
		for (index = 0; index < credits.size(); index++) {
			this.credits.add(credits.get(index));
		}
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public ArrayList<Integer> getPlayerId() {
		return playerId;
	}

	public ArrayList<Integer> getCredits() {
		return credits;
	}

	public int getMatchId() {
		return matchId;
	}

}
