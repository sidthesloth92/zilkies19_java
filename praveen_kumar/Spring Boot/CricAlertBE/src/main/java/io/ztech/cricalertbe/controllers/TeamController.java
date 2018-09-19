package io.ztech.cricalertbe.controllers;

import java.util.ArrayList;

import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.Team;
import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.constants.Regex;
import io.ztech.cricalertfe.constants.UserMessages;
import io.ztech.cricalertfe.delegates.TeamDelegate;
import io.ztech.cricalertfe.exceptions.InvalidNameException;

public class TeamController {

	TeamDelegate teamDelegate;
	Validator validator;

	public TeamController() {
		teamDelegate = new TeamDelegate();
		validator = new Validator();
	}

	public ArrayList<Team> fetchTeams(User user) {
		return teamDelegate.fetchTeams(user);
	}

	public void createTeam(Team newTeam, User user) throws InvalidNameException {
		for (Player player : newTeam.getPlayers()) {
			if (!(validator.validateInput(Regex.nameRegex, player.getFirstName(), UserMessages.INVALID_FIRST_NAME)
					&& validator.validateInput(Regex.nameRegex, player.getLastName(), UserMessages.INVALID_LAST_NAME))) {
				throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
			}
		}
		
		user.addTeam(newTeam);
		user.addPlayer(newTeam.getPlayers());
		
		teamDelegate.createTeam(user);
	}
	
	/*public boolean searchTeam(int teamId, User user) {
		return teamDelegate.searchTeam(teamId, user);
	}*/
	
	public void addNewPlayer(Player player, User user) throws InvalidNameException {
		if (!(validator.validateInput(Regex.nameRegex, player.getFirstName(), UserMessages.INVALID_FIRST_NAME)
				&& validator.validateInput(Regex.nameRegex, player.getLastName(), UserMessages.INVALID_LAST_NAME))) {
			throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
		}
		user.addPlayer(player);
		teamDelegate.addNewPlayer(user);
	}
	
	public void updateTeamName(Team team) throws InvalidNameException {
		if (!(validator.validateInput(Regex.nameRegex, team.getTeamName(), UserMessages.INVALID_NAME))) {
			throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
		}
		teamDelegate.updateTeamName(team);
	}
	
	public void updateTeamPlayers(ArrayList<Player> playerList, Team team) {
		teamDelegate.updateTeamPlayers(playerList, team);
	}
	
	public Team fetchTeam(int teamId) {
		return teamDelegate.fetchTeam(teamId);
	}
	
	public ArrayList<Player> fetchTeamPlayers(Team team) {
		return teamDelegate.fetchTeamPlayers(team);
	}
	
	public boolean isTeamCreated(User user) {
		return teamDelegate.isTeamCreated(user);
	}
	
	public void removeTeam(Team team) {
		teamDelegate.removeTeam(team);
	}
}
