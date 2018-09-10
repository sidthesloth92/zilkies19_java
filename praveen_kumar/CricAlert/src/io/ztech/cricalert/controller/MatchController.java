package io.ztech.cricalert.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import io.ztech.cricalert.beans.BallStats;
import io.ztech.cricalert.beans.Match;
import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.constants.Regex;
import io.ztech.cricalert.constants.UserMessages;
import io.ztech.cricalert.delegate.MatchDelegate;
import io.ztech.cricalert.exceptions.InvalidDateException;

public class MatchController {
	
	MatchDelegate matchDelegate;
	Validator validator;
	
	public MatchController() {
		matchDelegate = new MatchDelegate();
		validator = new Validator();
	}
	
	public void setMatch(Match newMatch) {
		matchDelegate.setMatch(newMatch);
	}
	
	public void displayMatches(User user) {
		matchDelegate.displayMatches(user);
	}
	
	public boolean searchMatch(User user, int matchId) {
		return matchDelegate.searchMatch(user, matchId);
	}
	
	public Match fetchMatch(int matchId) {
		return matchDelegate.fetchMatch(matchId);
	}
	
	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return matchDelegate.fetchPlayers(players);
	}
	
	public void insertBallStats(BallStats ballStats) {
		matchDelegate.insertBallStats(ballStats);
	}
	
	public boolean isMatchScheduled(User user) {
		return matchDelegate.isMatchScheduled(user);
	}
	
	public void updateMatchDate(String date, Match match) throws InvalidDateException {
		if (!(validator.validateInput(Regex.dateRegex, date, UserMessages.INVALID_DATE))) {
			throw new InvalidDateException(UserMessages.INVALID_DATE_EXCEPTION);
		}
		Timestamp newMatchDatetime = Date.valueOf(date);	// ?? how to do
		match.setMatchDatetime(newMatchDate);
		matchDelegate.updateMatchDate(match);
	}

	public void updateTeam(Match match, String team) {
		matchDelegate.updateTeam(match, team);
	}
}
