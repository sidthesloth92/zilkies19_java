package io.zilker.fantasy.controllers;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.UserPickedTeam;
import io.zilker.fantasy.delegate.AdminOperations;
import io.zilker.fantasy.delegate.UserOperations;

@RestController
@RequestMapping("/FantasyLeague/Admin")
public class AdminController {
	@RequestMapping(value="/AddMatch" , method= RequestMethod.POST)
	public JSONObject addMatch(@RequestParam("team-one") String teamOne , @RequestParam("team-two") String teamTwo , @RequestParam("scheduled-date") String scheduledDate ,@RequestParam("start-time") String startTime , @RequestParam("end-time") String endTime , @RequestParam("match-credits") int matchCredits) {
		boolean status = new AdminOperations().scheduleNewMatch(teamOne , teamTwo, scheduledDate, startTime, endTime , matchCredits);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	@RequestMapping(value="/AddPlayer" , method= RequestMethod.POST)
	public JSONObject addPlayer(@RequestParam("player-name") String playerName , @RequestParam("team") String team , @RequestParam("role") String role, @RequestParam("rating") int rating) {
		boolean status = new AdminOperations().addPlayer(playerName, team, role, rating);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	@RequestMapping(value="/EndMatch" , method= RequestMethod.POST , headers = "Accept=application/json")
	public JSONObject endMatch(@RequestBody() UserPickedTeam userTeam) {
		int matchId = userTeam.getMatchId();
		ArrayList<Integer> playersInTeam = userTeam.getPlayerId();
		boolean status = new AdminOperations().endMatch(matchId, playersInTeam);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	@RequestMapping(value="/CurrentPlayerRating" , method= RequestMethod.POST)
	public JSONObject currentPlayerRating(@RequestParam("player-id") int playerId) {
		int rating = new AdminOperations().getCurrentRating(playerId);
		 JSONObject json = new JSONObject();
		 json.put("rating", rating);
		 return json;
	}
	
	@RequestMapping(value="/UpdateRating" , method= RequestMethod.PUT)
	public JSONObject updateRating(@RequestParam("player-id") int playerId , @RequestParam("modified-rating") int modifiedRating) {
		boolean status = new AdminOperations().editPlayerRating( playerId , modifiedRating);;
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	
	@RequestMapping("/GetTeams")
	public ArrayList<String> getTeamNames() {
		return new AdminOperations().getTeamNames();
	}
	
	@RequestMapping("/GetMatches")
	public ArrayList<Match> getOngoingMatches() {
		return new AdminOperations().displayOngoingMatches();
	}
	
	@RequestMapping("/GetPlayerNames")
	public ArrayList<String> getPlayerNames() {
		return new AdminOperations().getPlayerNames();
	}
	
	@RequestMapping("/GetPlayerIds")
	public ArrayList<String> getPlayerIds() {
		return new AdminOperations().getPlayerIds();
	}
	
	@RequestMapping("/TeamOne")
	public ArrayList<Player> getTeamOne(@RequestParam("match-id") int matchId) {
		return new AdminOperations().getTeamOne(matchId);
	}
	
	@RequestMapping("/TeamTwo")
	public ArrayList<Player> getTeamTwo(@RequestParam("match-id") int matchId) {
		return new AdminOperations().getTeamTwo(matchId);
	}
	
}
