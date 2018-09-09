package io.ztech.cricalert.controller;

import java.util.ArrayList;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.constants.Regex;
import io.ztech.cricalert.constants.UserMessages;
import io.ztech.cricalert.delegate.TeamDelegate;
import io.ztech.cricalert.exceptions.InvalidNameException;

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
	
	public void updateTeamName(Team updateThisTeam, String newTeamName) throws InvalidNameException {
		if (!(validator.validateInput(Regex.nameRegex, newTeamName, UserMessages.INVALID_NAME))) {
			throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
		}
		updateThisTeam.setTeamName(newTeamName);
		teamDelegate.updateTeamName(updateThisTeam);
	}
	
	public void addNewPlayer(Player player, User user) throws InvalidNameException {
		if (!(validator.validateInput(Regex.nameRegex, player.getFirstName(), UserMessages.INVALID_FIRST_NAME)
				&& validator.validateInput(Regex.nameRegex, player.getLastName(), UserMessages.INVALID_LAST_NAME))) {
			throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
		}
		user.addPlayer(player);
		teamDelegate.addNewPlayer(user);
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
}
