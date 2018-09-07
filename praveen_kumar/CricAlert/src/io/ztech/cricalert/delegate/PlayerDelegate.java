package io.ztech.cricalert.delegate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.constants.UserMessages;
import io.ztech.cricalert.dao.CricketDAO;

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
	
	public void removePlayer(Team team) {
		dao.deletePlayer(team);
	}
	
	public void displayPlayer(User user) {
		ArrayList<Team> teamList = dao.fetchTeams(user);
		for (Team team : teamList) {
			printPlayerDetails(team);
		}
	}
	
	public void printPlayerDetails(Team team) {
		ArrayList<Player> playerList = dao.fetchTeamPlayers(team);
		logger.info(UserMessages.PLAYER_TABLE);
		for (Player player : playerList) {
			logger.info(player.getTeamId() + "\t" + player.getPlayerId() + "\t" + player.getFirstName() + "\t\t" + player.getLastName());
		}
	}
	
	public void updateTeamId(Team team) {
		dao.updatePlayerTeam(team);
	}
	
	public void updatePlayerName(Player player, String query) {
		dao.updatePlayerName(player, query);
	}
}
