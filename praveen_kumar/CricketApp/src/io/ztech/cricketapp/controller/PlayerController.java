package io.ztech.cricketapp.controller;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.delegate.PlayerDelegate;

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
	
	public void displayPlayer(User user) {
		playerDelegate.displayPlayer(user);
	}
	
	public void updateTeamId(Team team) {
		playerDelegate.updateTeamId(team);
	}
	
	public void updatePlayerName(Player player, String query) {
		playerDelegate.updatePlayerName(player, query);
	}
}
