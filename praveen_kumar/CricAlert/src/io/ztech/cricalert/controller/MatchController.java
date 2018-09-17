package io.ztech.cricalert.controller;

import java.util.ArrayList;

import io.ztech.cricalert.beans.BallStats;
import io.ztech.cricalert.beans.Match;
import io.ztech.cricalert.beans.MatchStats;
import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.PlayerStats;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.delegate.MatchDelegate;

public class MatchController {
	
	MatchDelegate matchDelegate;
	Validator validator;
	
	public MatchController() {
		matchDelegate = new MatchDelegate();
		validator = new Validator();
	}
	
	// Called by AddMatch servlet
	public void setMatch(Match newMatch) {
		matchDelegate.setMatch(newMatch);
	}
	
	public boolean searchMatch(User user, int matchId) {
		return matchDelegate.searchMatch(user, matchId);
	}
	
	// Called by Play servlet
	public Match fetchMatch(int matchId) {
		return matchDelegate.fetchMatch(matchId);
	}
	
	// Called by FetchMatchStats
	public MatchStats fetchMatchStats(int matchId) {
		return matchDelegate.fetchMatchStats(matchId);
	}
	
	// Called by FetchPlayerStats
	public ArrayList<PlayerStats> fetchPlayerStats(int matchId) {
		return matchDelegate.fetchPlayerStats(matchId);
	}
	
	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return matchDelegate.fetchPlayers(players);
	}
	
	public void updateBallStats(BallStats ballStats) {
		matchDelegate.updateBallStats(ballStats);
	}
	
	public boolean isMatchScheduled(User user) {
		return matchDelegate.isMatchScheduled(user);
	}
	
	public void updateMatchDate(Match match) {
		matchDelegate.updateMatchDate(match);
	}

	public void updateTeam(Match match, String team) {
		matchDelegate.updateTeam(match, team);
	}
	
	// Called by WriteMatch
	public void updateMatchStatus(Match match, User user) {
		for (Match userMatch : user.getMatches()) {
			if (userMatch.getMatchId() == match.getMatchId()) {
				userMatch.setStatus(match.getStatus());
			}
		}
		matchDelegate.updateMatchStatus(match);
	}
	
	// Called by WriteMatchStats
	public void updateMatchStats(MatchStats matchStats) {
		matchDelegate.updateMatchStats(matchStats);
	}
	
	// Called by WritePlayerStats
	public void updatePlayerStats(PlayerStats playerStats) {
		matchDelegate.updatePlayerStats(playerStats);
	}
}
