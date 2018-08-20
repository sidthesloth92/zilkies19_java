package io.ztech.cricketapp.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.EditMatchOptions;
import io.ztech.cricketapp.constants.EditPlayerOptions;
import io.ztech.cricketapp.constants.EditTeamOptions;
import io.ztech.cricketapp.constants.MainMenuOptions;
import io.ztech.cricketapp.constants.MatchMenuOptions;
import io.ztech.cricketapp.constants.PlayerMenuOptions;
import io.ztech.cricketapp.constants.TeamMenuOptions;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.controller.MatchController;
import io.ztech.cricketapp.controller.PlayerController;
import io.ztech.cricketapp.controller.TeamController;

public class Menu {
	Logger logger;
	Scanner scanner;
	TeamHandler teamHandler;
	TeamController teamController;
	PlayerHandler playerHandler;
	PlayerController playerController;
	MatchHandler matchHandler;
	MatchController matchController;
	User user;
	
	public Menu(User user) {
		logger = Logger.getLogger(UserEntry.class.getName());
		scanner = new Scanner(System.in);
		playerHandler = new PlayerHandler();
		playerController = new PlayerController();
		teamHandler = new TeamHandler();
		teamController = new TeamController();
		matchHandler = new MatchHandler();
		matchController = new MatchController();
		this.user = user;
	}
	
	public void showMainMenu() {
		char retry;
		do {
			try {
				retry = 'n';
				logger.info("");
				logger.info(UserMessages.MAIN_MENU);
				MainMenuOptions option = MainMenuOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case MATCHES:
					showMatchMenu();
					break;
				case TEAMS:
					showTeamMenu();
					break;
				case PLAYERS:
					showPlayerMenu();
					break;
				case EXIT:
					logger.info(UserMessages.LOG_OFF);
					return;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
				}
				logger.info(UserMessages.CONTINUE);
				retry = scanner.nextLine().charAt(0);
			} catch (InputMismatchException e) {
				logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');
	}
	
	public void showMatchMenu() {
		char retry;
		do {
			try {
				logger.info(UserMessages.MATCH_MENU);
				MatchMenuOptions option = MatchMenuOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case PLAY_MATCH:
					if (matchController.isMatchScheduled(user)) {
						matchHandler.playMatch(user);
					} else {
						logger.info(UserMessages.NO_MATCHES_SCHEDULED);
					}
					break;
				case VIEW_MATCHES:
					matchController.displayMatches(user);
					break;
				case EDIT_MATCH:
					showEditMatchMenu();
					break;
				case CREATE_MATCH:
					if (teamController.isTeamCreated(user)) {
						matchHandler.createMatch(user);
					} else {
						logger.info(UserMessages.NO_TEAMS_CREATED);
					}
					break;
				case GO_BACK:
					return;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
				}
				logger.info(UserMessages.CONTINUE_MATCH);
				retry = scanner.nextLine().charAt(0);
			} catch (InputMismatchException e) {
				logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');
	}
	
	public void showTeamMenu() {
		char retry;
		do {
			try {
				logger.info(UserMessages.TEAM_MENU);
				TeamMenuOptions option = TeamMenuOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case VIEW_TEAMS:
					teamController.displayTeams(user);
					break;
				case EDIT_TEAM:
					showEditTeamMenu();
					break;
				case CREATE_TEAM:
					teamHandler.createTeam(user);
					break;
				case GO_BACK:
					return;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
				}
				logger.info(UserMessages.CONTINUE_TEAM);
				retry = scanner.nextLine().charAt(0);
			} catch (InputMismatchException e) {
				logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');
	}
	
	public void showPlayerMenu() {
		char retry;
		do {
			try {
				retry = 'n';
				logger.info(UserMessages.PLAYER_MENU);
				PlayerMenuOptions option = PlayerMenuOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case VIEW_PLAYERS:
					playerController.displayPlayer(user);
					break;
				case EDIT_PLAYER:
					showEditPlayerMenu();
					break;
				case CREATE_PLAYER:
					if (teamController.isTeamCreated(user)) {
						playerHandler.addNewPlayer(user);
					} else {
						logger.info(UserMessages.NO_TEAMS_CREATED);
					}
					break;
				case GO_BACK:
					return;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
				}
				logger.info(UserMessages.CONTINUE_PLAYER);
				retry = scanner.nextLine().charAt(0);
			} catch (InputMismatchException e) {
				logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				
				scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');
	}
	
	public void showEditTeamMenu() {
		char retry;
		do {
			try {
				retry = 'n';
				logger.info(UserMessages.EDIT_TEAM_MENU);
				EditTeamOptions option = EditTeamOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case CHANGE_NAME:
					teamHandler.editTeamName(user);
					break;
				case ADD_PLAYER:
					playerHandler.addNewPlayer(user);
					break;
				case REMOVE_PLAYER:
					teamHandler.removePlayer(user);
					break;
				case GO_BACK:
					return;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
				}
				logger.info(UserMessages.FURTHER_CHANGES);
				retry = scanner.nextLine().charAt(0);
			} catch (InputMismatchException e) {
				logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');	
	}
	
	public void showEditPlayerMenu() {
		char retry;
		do {
			try {
				retry = 'n';
				logger.info(UserMessages.EDIT_PLAYER_MENU);
				EditPlayerOptions option = EditPlayerOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case TEAM:
					playerHandler.editPlayer(user, "team");
					break;
				case FIRST_NAME:
					playerHandler.editPlayer(user, "fname");
					break;
				case LAST_NAME:
					playerHandler.editPlayer(user, "lname");
					break;
				case GO_BACK:
					return;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
				}
				logger.info(UserMessages.FURTHER_CHANGES);
				retry = scanner.nextLine().charAt(0);
			} catch (InputMismatchException e) {
				logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');	
	}
	
	public void showEditMatchMenu() {
		char retry;
		do {
			try {
				retry = 'n';
				logger.info(UserMessages.EDIT_MATCH_MENU);
				EditMatchOptions option = EditMatchOptions.values()[scanner.nextInt() - 1];
				scanner.nextLine();
				switch (option) {
				case DATE:
					matchHandler.editMatch(user, "date");
					break;
				case TEAM_A:
					matchHandler.editMatch(user, "teamA");
					break;
				case TEAM_B:
					matchHandler.editMatch(user, "teamB");
					break;
				case GO_BACK:
					return;
				default:
					logger.info(UserMessages.INVALID_CHOICE);
				}
				logger.info(UserMessages.FURTHER_CHANGES);
				retry = scanner.nextLine().charAt(0);
			} catch (InputMismatchException e) {
				logger.info(UserMessages.INCORRECT_INPUT + e);
				retry = 'y';
				scanner.nextLine();
			}
		} while (Character.toLowerCase(retry) == 'y');	
	}
}
