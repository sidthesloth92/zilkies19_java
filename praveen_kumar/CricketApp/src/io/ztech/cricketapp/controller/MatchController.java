package io.ztech.cricketapp.controller;

import java.sql.Date;
import java.util.ArrayList;

import io.ztech.cricketapp.beans.BallStats;
import io.ztech.cricketapp.beans.Match;
import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.Regex;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.delegate.MatchDelegate;
import io.ztech.cricketapp.exceptions.InvalidDateException;

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
		Date newMatchDate = Date.valueOf(date);
		match.setMatchDate(newMatchDate);
		matchDelegate.updateMatchDate(match);
	}

	public void updateTeam(Match match, String team) {
		matchDelegate.updateTeam(match, team);
	}
}
