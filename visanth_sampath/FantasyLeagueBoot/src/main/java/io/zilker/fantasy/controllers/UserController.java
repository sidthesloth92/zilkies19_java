package io.zilker.fantasy.controllers;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.bean.UserPickedTeam;
import io.zilker.fantasy.delegate.UserOperations;


@RestController
@RequestMapping("/FantasyLeague/User")
public class UserController {
	@RequestMapping("/GetChat")
	public ArrayList<Message> getChat() {
		return new UserOperations().displayMessages();
	}
	
	@RequestMapping(value="/UpdateChat" , method= RequestMethod.POST)
	public JSONObject updateChat(@RequestParam("user-id") int userId , @RequestParam("message") String message ) {
		boolean status = new UserOperations().updateChat(userId, message);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	@RequestMapping("/LeaderBoard")
	public ResultBoard getLeaderboard(@RequestParam("user-id") int userId , @RequestParam("match-id") int matchId ) {
		 return new UserOperations().viewLeaderBoard(userId ,matchId);
	}

	@RequestMapping("/TopPicks")
	public ArrayList<String> getTopPicks(@RequestParam("match-id") int matchId ) {
		 return  new UserOperations().viewMostPicked(matchId);
	}
	
	@RequestMapping("/ViewTeam")
	public ArrayList<Player> viewTeam(@RequestParam("match-id") int matchId , @RequestParam("user-id") int userId ) {
		 return new UserOperations().viewTeam(userId , matchId);
	}
	
	@RequestMapping("/PickTeam/{userId}")
	public JSONObject pickTeam( @PathVariable("userId") int userId,@RequestBody() UserPickedTeam userTeam) {
		int matchId = userTeam.getMatchId();
		ArrayList<Integer> playingTeamArray = userTeam.getPlayerId();
		ArrayList<Integer> creditsArray = userTeam.getCredits();
		 boolean status = new UserOperations().addTeam(userId , matchId, playingTeamArray , creditsArray);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	@RequestMapping("/ModifyTeam/{userId}")
	public JSONObject modifyTeam(@PathVariable("userId") int userId,@RequestBody() UserPickedTeam userTeam) {
		int matchId = userTeam.getMatchId();
		ArrayList<Integer> playingTeamArray = userTeam.getPlayerId();
		ArrayList<Integer> creditsArray = userTeam.getCredits();
		 boolean status = new UserOperations().modifyTeam(userId , matchId, playingTeamArray , creditsArray);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	@RequestMapping("/IsTaken")
	public JSONObject isTeamTaken(@RequestParam("match-id") int matchId , @RequestParam("user-id") int userId) {
		 boolean status = new UserOperations().isTeamTaken(matchId, userId);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
	
	@RequestMapping("/GetMatchCredits")
	public JSONObject modifyTeam(@RequestParam("match-id") int matchId) {
		 int credits = new UserOperations().getMatchCredits(matchId);
		 JSONObject json = new JSONObject();
		 json.put("credits", credits);
		 return json;
	}
	
	@RequestMapping("/CompletedMatches")
	public ArrayList<Match> getCompletedMatches() {
		 return  new UserOperations().displayCompletedMatches();
	}
	
	@RequestMapping("/ActiveMatches")
	public ArrayList<Match> getActiveMatches() {
		 return  new UserOperations().displayActiveMatches();
	}
	
	@RequestMapping("/Team-Status")
	public JSONObject modifyTeam(@RequestParam("match-id") int matchId , @RequestParam("user-id") int userId) {
		 boolean status = new UserOperations().isTeamTaken(matchId, userId);
		 JSONObject json = new JSONObject();
		 json.put("status", status);
		 return json;
	}
}
