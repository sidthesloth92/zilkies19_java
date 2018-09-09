package io.ztech.cricalert.controller;

import java.util.ArrayList;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.delegate.PlayerDelegate;

public class PlayerController {

	PlayerDelegate playerDelegate;
	
	public PlayerController() {
		playerDelegate = new PlayerDelegate();
	}
	
	/*public boolean searchPlayer(int playerId, User user) {
		return playerDelegate.searchPlayer(playerId, user);
	}*/
	
	public void removePlayer(Team team) {
		playerDelegate.removePlayer(team);
	}
	
	public ArrayList<Player> fetchPlayers(User user) {
		return playerDelegate.fetchPlayers(user);
	}
	
	public Player fetchPlayer(User user, int playerId) {
		return playerDelegate.fetchPlayer(user, playerId);
	}
	
	public void updateTeamId(Team team) {
		playerDelegate.updateTeamId(team);
	}
	
	public void updatePlayerName(Player player, String query) {
		playerDelegate.updatePlayerName(player, query);
	}
}
