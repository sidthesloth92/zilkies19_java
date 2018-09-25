package io.ztech.cricalertbe.delegates;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalertbe.beans.Match;
import io.ztech.cricalertbe.beans.MatchStats;
import io.ztech.cricalertbe.beans.PlayerStats;
import io.ztech.cricalertbe.dao.CricketDAO;

public class MatchDelegate {

	Scanner scanner;
	Logger logger;
	CricketDAO dao;

	public MatchDelegate() {
		logger = Logger.getLogger(MatchDelegate.class.getName());
		scanner = new Scanner(System.in);
		dao = new CricketDAO();
	}

	public Match fetchMatch(int matchId) {
		return dao.fetchMatch(matchId);
	}
	
	public MatchStats fetchMatchStats(int matchId) {
		return dao.fetchMatchStats(matchId);
	}
	
	public ArrayList<PlayerStats> fetchPlayerStats(int matchId) {
		return dao.fetchPlayerStats(matchId);
	}
	
	public void setMatch(Match newMatch) {
		dao.insertMatch(newMatch);
	}
	
	public void updateMatchStatus(Match match) {
		dao.updateMatchStatus(match);
	}
	
	public void updateMatchStats(MatchStats matchStats) {
		logger.info("Entered updateMatchStats Delegate (BE)");
		dao.updateMatchStats(matchStats);
		logger.info("Exited updateMatchStats Delegate (BE)");
	}
	
	public void updatePlayerStats(PlayerStats playerStats) {
		dao.updatePlayerStats(playerStats);
	}
	
	/*public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return dao.fetchPlayers(players);
	}

	public void updateBallStats(BallStats ballStats) {
		dao.insertBallStats(ballStats);
	}
	
	public void updateMatchDate(Match match) {
		dao.updateMatchDate(match);
	}
	
	public void updateTeam(Match match, String team) {
		dao.updateTeam(match, team);
	}*/
}
