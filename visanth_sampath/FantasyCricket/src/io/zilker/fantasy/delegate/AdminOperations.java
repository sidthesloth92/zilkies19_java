package io.zilker.fantasy.delegate;

import java.util.ArrayList;
import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.constants.DisplayConstants;
import io.zilker.fantasy.dao.AdminDAO;
import io.zilker.fantasy.dao.UserDAO;
import io.zilker.fantasy.service.AdminService;
import io.zilker.fantasy.service.UserService;
import io.zilker.fantasy.utility.GeneralValidators;

public class AdminOperations {
	private boolean isValid = false;
	private int choice, matchCredits, type, rating, teamId, playerId, matchId, index, points;
	private String teamOne, teamTwo, scheduledDate, startTime, endTime, playerName, team;
	GeneralValidators newGeneralValidators = new GeneralValidators();
	AdminDAO adminDAO = new AdminDAO();
	Player newPlayer = new Player();
	AdminService adminService = new AdminService();
	Match match = new Match();
	UserDAO userDAO = new UserDAO();
	UserService userService = new UserService();

	// Schedule a new match
	public boolean scheduleNewMatch() {
		boolean isValid = false;
		try {
			adminService.displayAlert(DisplayConstants.ENTER_TEAM_ONE);
			teamOne = adminService.getStringInputs();
			adminService.displayAlert(DisplayConstants.ENTER_TEAM_TWO);
			teamTwo = adminService.getStringInputs();
			do {
				adminService.displayAlert(DisplayConstants.ENTER_SCHEDULED_DATE);
				scheduledDate = adminService.getStringInputs();
				isValid = newGeneralValidators.checkScheduledDate(scheduledDate);
				if (!isValid) {
					adminService.displayAlert(DisplayConstants.ENTER_VALID_FORMAT);
				}
			} while (!isValid);
			isValid = false;
			do {
				adminService.displayAlert(DisplayConstants.ENTER_START_TIME);
				startTime = adminService.getStringInputs();
				isValid = newGeneralValidators.checkScheduledTime(startTime);
				if (!isValid) {
					adminService.displayAlert(DisplayConstants.ENTER_VALID_FORMAT);
				}
			} while (!isValid);
			isValid = false;
			do {
				adminService.displayAlert(DisplayConstants.ENTER_END_TIME);
				endTime = adminService.getStringInputs();
				isValid = newGeneralValidators.checkScheduledTime(endTime);
				if (!isValid) {
					adminService.displayAlert(DisplayConstants.ENTER_VALID_FORMAT);
				}

			} while (!isValid);
			adminService.displayAlert(DisplayConstants.ENTER_MATCH_CREDITS);
			matchCredits = adminService.getIntInputs();
			Match newMatch = new Match();
			newMatch.setMatchTable(teamOne, teamTwo, scheduledDate, startTime, endTime, matchCredits);
			adminDAO.setNewMatch(newMatch);
			int matchId = adminDAO.getLastInsertedMatchId();
			newMatch.setStatusTable(matchId, 1, 1);
			adminDAO.setNewMatchStatus(newMatch);

		} catch (Exception e) {
			e.getStackTrace();
			return false;
		} finally {

		}
		return true;
	}

	// disable a active match
	public boolean startMatch() {
		int matchId;
		ArrayList<Integer> players = new ArrayList<Integer>();
		ArrayList<Match> matchList = new ArrayList<Match>();
		try {
			adminService.displayAlert(DisplayConstants.MATCH_TO_BE_DISABLED);
			matchList = adminDAO.listActiveMatches();
			if (matchList.size() > 0) {
				adminService.displayMatchList(matchList);
				matchId = adminService.getIntInputs();
				adminDAO.disableParticularMatch(matchId);
				players = adminDAO.getTopPicks(matchId);
				getTopPicks(players, matchId);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	private void getTopPicks(ArrayList<Integer> players, int matchId) {
		// TODO Auto-generated method stub
		try {
			for (index = 0; index < players.size(); index++) {
				adminDAO.setMostPickedPlayers(players.get(index), matchId);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// add a player
	public boolean addPlayer() {

		try {
			adminService.displayAlert(DisplayConstants.ENTER_PLAYER_NAME);
			playerName = adminService.getStringInputs();
			adminService.displayAlert(DisplayConstants.ENTER_PLAYER_TEAM);
			team = adminService.getStringInputs();
			do {
				adminService.displayAlert(DisplayConstants.ENTER_PLAYER_TYPE);
				type = adminService.getIntInputs();
				isValid = newGeneralValidators.checkValidRole(type);
				if (!isValid) {
					adminService.displayAlert(DisplayConstants.INVALID_ROLE);
				}
			} while (!isValid);
			do {
				adminService.displayAlert(DisplayConstants.ENTER_PLAYER_RATING);
				rating = adminService.getIntInputs();
				isValid = checkRating(rating);
			} while (!isValid);
			String role = getPlayerRole(type);
			newPlayer.setPlayer(playerName, team, role, rating);
			adminDAO.setPlayer(newPlayer);
			playerId = adminDAO.getLastPlayerId();
			teamId = adminDAO.getTeamId(team);
			if (teamId == 0) {
				adminDAO.insertIntoTeam(team);
				teamId = adminDAO.getTeamId(team);
			}
			adminDAO.setTeamAndPlayers(teamId, playerId);
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	private boolean checkRating(int rating) {
		// TODO Auto-generated method stub
		if (rating > 10) {
			adminService.displayAlert(DisplayConstants.CREDITS_INVALID);
			return false;
		}
		return true;
	}

	// enter playing 11
	public boolean endMatch() {
		ArrayList<Match> matchList = new ArrayList<Match>();
		try {
			matchList = adminDAO.displayOngoingMatches();
			adminService.displayMatchList(matchList);
			adminService.displayAlert(DisplayConstants.ENTER_MATCH_ID);
			matchId = adminService.getIntInputs();
			match = userDAO.setMatch(matchId);
			userDAO.displayTeam(match.getTeamOne());
			userDAO.displayTeam(match.getTeamTwo());
			ArrayList<Integer> playersInTeam = new ArrayList<Integer>();
			int count = 0;
			while (count < 22) {
				adminService.displayAlert(DisplayConstants.ENTER_PLAYERS_ID);
				playerId = adminService.getIntInputs();
				playersInTeam.add(playerId);
				count++;
			}
			getPlayingTeamData(matchId, playersInTeam);
			// done
			updateTotalScore(matchId);
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	private void updateTotalScore(int matchId) {
		// TODO Auto-generated method stub
		try {
			ArrayList<Integer> userId = new ArrayList<Integer>();
			userId = adminDAO.getUsersOfParticularMatch(matchId);
			for (index = 0; index < userId.size(); index++) {
				points = adminDAO.getTotalPointsOfUser(userId.get(index), matchId);
				adminDAO.setResultBoard(userId.get(index), matchId, points);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	private void getPlayingTeamData(int matchId, ArrayList<Integer> playersInTeam) {
		// TODO Auto-generated method stub
		try {
			for (index = 0; index < playersInTeam.size(); index++) {
				type = (int) (Math.random() * 100);
				adminDAO.setPlayingTeam(matchId, playersInTeam.get(index), type);
			}
			adminDAO.disableUpcomingMatch(matchId);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// edit Player Rating
	public boolean editPlayerRating() {
		int modifiedRating;
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			isValid = false;
			players = adminDAO.listPlayers();
			userService.displayPlayerNamesList(players);
			adminService.displayAlert(DisplayConstants.MODIFY_CREDIT_PLAYER_ID);
			choice = adminService.getIntInputs();
			do {
				adminService.displayAlert(DisplayConstants.MODIFIED_RATING);
				modifiedRating = adminService.getIntInputs();
				isValid = newGeneralValidators.checkRating(modifiedRating);
				if (isValid == false) {
					adminService.displayAlert(DisplayConstants.INVALID_RATING);
				}
			} while (!isValid);
			isValid = false;
			isValid = adminDAO.modifyPlayerRating(choice, modifiedRating);
			if (isValid == true) {
				adminService.displayAlert(DisplayConstants.MODIFY_SUCCESS);
			}
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	// get player status
	public String getPlayerRole(int type) {
		try {
			if (type == 1) {
				return "Batsman";
			} else if (type == 2) {
				return "Bowler";
			} else if (type == 3) {
				return "All Rounder";
			} else if (type == 4) {
				return "Wicket Keeper";
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

}
