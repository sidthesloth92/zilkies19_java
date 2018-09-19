package io.ztech.cricalertfe.delegates;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalert.dao.CricketDAO;
import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.User;

public class PlayerDelegate {
	Logger logger;
	Scanner scanner;
	CricketDAO dao;
	
	public PlayerDelegate() {
		logger = Logger.getLogger(PlayerDelegate.class.getName());
		scanner = new Scanner(System.in);
		dao = new CricketDAO();
	}
	
	/*public boolean searchPlayer(int playerId, User user) {
		return dao.searchPlayer(user, playerId);
	}*/
	
	public void removePlayer(Player player) {
		dao.deletePlayer(player);
	}
	
	public ArrayList<Player> fetchPlayers(User user) {
		return dao.fetchPlayers(user);
	}
	
	public Player fetchPlayer(int playerId) {
		return dao.fetchPlayer(playerId);
	}
	
/*	public void printPlayerDetails(Team team) {
		ArrayList<Player> playerList = dao.fetchTeamPlayers(team);
		logger.info(UserMessages.PLAYER_TABLE);
		for (Player player : playerList) {
			logger.info(player.getTeamId() + "\t" + player.getPlayerId() + "\t" + player.getFirstName() + "\t\t" + player.getLastName());
		}
	}*/
	
	/*public void updateTeamId(Team team) {
		dao.updatePlayerTeam(team);
	}
	
	public void updatePlayerName(Player player, String query) {
		dao.updatePlayerName(player, query);
	}*/
	
	public void updatePlayer(Player player) {
		dao.updatePlayer(player);
	}
}
