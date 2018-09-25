package io.zilker.fantasy.bean;

import java.util.ArrayList;

public class Team {
	private ArrayList<Integer> players = new ArrayList<Integer>();
	
	//set the team picked
	public void setPlayers(ArrayList<Integer> players) {
		for(int i=0;i<players.size();i++) {
			this.players.add(players.get(i));
		}
	}
	
	public ArrayList<Integer> getPlayers() {
		return players;
	}

}
