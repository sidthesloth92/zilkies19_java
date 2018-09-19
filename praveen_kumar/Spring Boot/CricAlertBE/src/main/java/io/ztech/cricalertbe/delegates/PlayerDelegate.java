package io.ztech.cricalertbe.delegates;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalertbe.beans.Player;
import io.ztech.cricalertbe.beans.User;
import io.ztech.cricalertbe.dao.CricketDAO;

public class PlayerDelegate {
	Logger logger;
	Scanner scanner;
	CricketDAO dao;
	
	public PlayerDelegate() {
		logger = Logger.getLogger(PlayerDelegate.class.getName());
		scanner = new Scanner(System.in);
		dao = new CricketDAO();
	}
	
	public void removePlayer(Player player) {
		dao.deletePlayer(player);
	}
	
	public ArrayList<Player> fetchPlayers(User user) {
		return dao.fetchPlayers(user);
	}
	
	public Player fetchPlayer(int playerId) {
		return dao.fetchPlayer(playerId);
	}
	
	public void updatePlayer(Player player) {
		dao.updatePlayer(player);
	}
}
