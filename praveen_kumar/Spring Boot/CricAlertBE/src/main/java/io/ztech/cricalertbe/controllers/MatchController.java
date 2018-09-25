package io.ztech.cricalertbe.controllers;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.cricalertbe.beans.Match;
import io.ztech.cricalertbe.beans.MatchStats;
import io.ztech.cricalertbe.beans.PlayerStats;
import io.ztech.cricalertbe.delegates.MatchDelegate;

@RestController
@RequestMapping("/CricAlertBE/matches")
public class MatchController {
	
	Logger logger;
	public MatchController() {
		logger = Logger.getLogger(MatchController.class.getName());
	}
	
	@GetMapping("/{id}")
	public Match fetchMatch(@PathVariable("id") int matchId) {
		MatchDelegate matchDelegate = new MatchDelegate();
		return matchDelegate.fetchMatch(matchId);
	}
	
	@GetMapping("/{id}/match-stats")
	public MatchStats fetchMatchStats(@PathVariable("id") int matchId) {
		MatchDelegate matchDelegate = new MatchDelegate();
		return matchDelegate.fetchMatchStats(matchId);
	}
	
	@GetMapping("/{id}/player-stats")
	public ArrayList<PlayerStats> fetchPlayerStats(@PathVariable("id") int matchId) {
		MatchDelegate matchDelegate = new MatchDelegate();
		return matchDelegate.fetchPlayerStats(matchId);
	}
	
	@PostMapping("/")
	public void setMatch(@RequestBody Match newMatch) {
		MatchDelegate matchDelegate = new MatchDelegate();
		matchDelegate.setMatch(newMatch);
	}
	
	@PutMapping("/{id}")
	public void updateMatchStatus(@RequestBody Match match) {
		MatchDelegate matchDelegate = new MatchDelegate();
		matchDelegate.updateMatchStatus(match);
	}
	
	@PutMapping("/{id}/match-stats")
	public void updateMatchStats(@RequestBody MatchStats matchStats) {
		logger.info("Entered updateMatchStats Controller (BE)");
		MatchDelegate matchDelegate = new MatchDelegate();
		matchDelegate.updateMatchStats(matchStats);
		logger.info("Exited updateMatchStats Controller (BE)");
	}
	
	@PutMapping("/{id}/player-stats")
	public void updatePlayerStats(@RequestBody PlayerStats playerStats) {
		logger.info("Entered updateMatchStats Controller (BE)");
		MatchDelegate matchDelegate = new MatchDelegate();
		matchDelegate.updatePlayerStats(playerStats);
		logger.info("Exited updateMatchStats Controller (BE)");
	}
	
	/*public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return matchDelegate.fetchPlayers(players);
	}
	
	public void updateBallStats(BallStats ballStats) {
		matchDelegate.updateBallStats(ballStats);
	}
	
	public void updateMatchDate(Match match) {
		matchDelegate.updateMatchDate(match);
	}

	public void updateTeam(Match match, String team) {
		matchDelegate.updateTeam(match, team);
	}*/
}
