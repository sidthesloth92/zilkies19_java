package io.ztech.cricketapp.controller;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.Regex;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.delegate.TeamDelegate;
import io.ztech.cricketapp.exceptions.InvalidNameException;

public class TeamController {

	TeamDelegate teamDelegate;
	Validator validator;

	public TeamController() {
		teamDelegate = new TeamDelegate();
		validator = new Validator();
	}

	public void displayTeams(User user) {
		teamDelegate.displayTeams(user);
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
	
	public boolean isTeamCreated(User user) {
		return teamDelegate.isTeamCreated(user);
	}
}
