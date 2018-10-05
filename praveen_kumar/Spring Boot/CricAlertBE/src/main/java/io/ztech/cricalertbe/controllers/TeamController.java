package io.ztech.cricalertbe.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.cricalertbe.beans.Player;
import io.ztech.cricalertbe.beans.Team;
import io.ztech.cricalertbe.beans.UpdateTeam;
import io.ztech.cricalertbe.delegates.TeamDelegate;

@RestController
@RequestMapping("/CricAlertBE/teams")
public class TeamController {
	
	@GetMapping("/{id}")
	public Team fetchTeam(@PathVariable ("id") int teamId) {
		TeamDelegate teamDelegate = new TeamDelegate();
		return teamDelegate.fetchTeam(teamId);
	}
	
	@GetMapping("/user/{id}")
	public ArrayList<Team> fetchTeams(@PathVariable ("id") int userId) {
		TeamDelegate teamDelegate = new TeamDelegate();
		return teamDelegate.fetchTeams(userId);
	}
	
	@GetMapping("/{id}/players")
	public ArrayList<Player> fetchTeamPlayers(@PathVariable ("id") int teamId) {
		TeamDelegate teamDelegate = new TeamDelegate();
		return teamDelegate.fetchTeamPlayers(teamId);
	}

	@PostMapping("/")
	public void createTeam(@RequestBody Team team) {
		TeamDelegate teamDelegate = new TeamDelegate();
		teamDelegate.createTeam(team);
	}
	
	@PutMapping("/{id}")
	public void updateTeam(@RequestBody UpdateTeam updateTeam) {
		TeamDelegate teamDelegate = new TeamDelegate();
		teamDelegate.updateTeam(updateTeam);
	}
	
	/*@PutMapping("/{id}/name")
	public void updateTeamName(@RequestBody Team team) {
		TeamDelegate teamDelegate = new TeamDelegate();
		teamDelegate.updateTeamName(team);
	}
	
	@PutMapping("/{id}/players")
	public void updateTeamPlayers(ArrayList<Player> playerList, Team team) {
		TeamDelegate teamDelegate = new TeamDelegate();
		teamDelegate.updateTeamPlayers(playerList, team);
	}*/
	
	@DeleteMapping("/{id}")
	public void removeTeam(@RequestBody Team team) {
		TeamDelegate teamDelegate = new TeamDelegate();
		teamDelegate.removeTeam(team);
	}
}
