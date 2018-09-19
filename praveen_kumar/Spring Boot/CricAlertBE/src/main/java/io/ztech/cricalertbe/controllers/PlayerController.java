package io.ztech.cricalertbe.controllers;

import java.util.ArrayList;

import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.delegates.PlayerDelegate;

public class PlayerController {

	PlayerDelegate playerDelegate;
	
	public PlayerController() {
		playerDelegate = new PlayerDelegate();
	}
	
	/*public boolean searchPlayer(int playerId, User user) {
		return playerDelegate.searchPlayer(playerId, user);
	}*/
	
	
	// Called in DeletePlayer servlet
	public void removePlayer(Player player) {
		playerDelegate.removePlayer(player);
	}
	
	public ArrayList<Player> fetchPlayers(User user) {
		return playerDelegate.fetchPlayers(user);
	}
	
	// Called by AddTeam servlet
	public Player fetchPlayer(int playerId) {
		return playerDelegate.fetchPlayer(playerId);
	}
	
	/*public void updateTeamId(Team team) {
		playerDelegate.updateTeamId(team);
	}
	
	public void updatePlayerName(Player player, String query) {
		playerDelegate.updatePlayerName(player, query);
	}*/
	
	public void updatePlayer(Player player) {
		playerDelegate.updatePlayer(player);
	}
}
