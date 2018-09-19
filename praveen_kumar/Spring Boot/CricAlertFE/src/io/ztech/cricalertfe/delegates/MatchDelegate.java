package io.ztech.cricalertfe.delegates;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalert.dao.CricketDAO;
import io.ztech.cricalertfe.beans.BallStats;
import io.ztech.cricalertfe.beans.Match;
import io.ztech.cricalertfe.beans.MatchStats;
import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.PlayerStats;
import io.ztech.cricalertfe.beans.User;

public class MatchDelegate {

	Scanner scanner;
	Logger logger;
	CricketDAO dao;

	public MatchDelegate() {
		logger = Logger.getLogger(MatchDelegate.class.getName());
		scanner = new Scanner(System.in);
		dao = new CricketDAO();
	}

	public void setMatch(Match newMatch) {
		dao.insertMatch(newMatch);
	}

	public boolean searchMatch(User user, int matchId) {
		return dao.searchMatch(user, matchId);
	}
	
	// Called by Play
	public Match fetchMatch(int matchId) {
		return dao.fetchMatch(matchId);
	}
	
	// Called by FetchMatchStats
	public MatchStats fetchMatchStats(int matchId) {
		return dao.fetchMatchStats(matchId);
	}
	
	// Called by FetchPlayerStats
	public ArrayList<PlayerStats> fetchPlayerStats(int matchId) {
		return dao.fetchPlayerStats(matchId);
	}

	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return dao.fetchPlayers(players);
	}

	public void updateBallStats(BallStats ballStats) {
		dao.insertBallStats(ballStats);
	}
	
	public boolean isMatchScheduled(User user) {
		return dao.fetchScheduledMatch(user);
	}
	
	public void updateMatchDate(Match match) {
		dao.updateMatchDate(match);
	}
	
	public void updateTeam(Match match, String team) {
		dao.updateTeam(match, team);
	}
	
	// Called by WriteMatch
	public void updateMatchStatus(Match match) {
		dao.updateMatchStatus(match);
	}
	
	// Called by WriteMatchStats
	public void updateMatchStats(MatchStats matchStats) {
		dao.updateMatchStats(matchStats);
	}
	
	// Called by WritePlayerStats
	public void updatePlayerStats(PlayerStats playerStats) {
		dao.updatePlayerStats(playerStats);
	}
}
