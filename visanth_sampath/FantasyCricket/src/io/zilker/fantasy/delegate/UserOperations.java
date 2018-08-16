package io.zilker.fantasy.delegate;

import java.util.ArrayList;
import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.bean.UserPickedTeam;
import io.zilker.fantasy.constants.DisplayConstants;
import io.zilker.fantasy.dao.UserDAO;
import io.zilker.fantasy.service.UserService;

public class UserOperations {
	private int choice, playerId, numberOfBatsmen = 0, numberOfBowler = 0, numberOfAllrounder = 0, numberOfKeeper = 0,
			totalCredits, matchId, userId, creditsCompleted = 0, playerCredits, matchCredits, index;
	private boolean isValid = false;
	private String role, message;
	ArrayList<Integer> players = new ArrayList<Integer>();
	ArrayList<Integer> credits = new ArrayList<Integer>();
	UserDAO userDAO = new UserDAO();
	UserService userService = new UserService();
	Match match = new Match();
	ResultBoard resultBoard = new ResultBoard();
	UserPickedTeam userPickedTeam = new UserPickedTeam();

	// deduce the roles of each players
	public void deduceRole(String role) {
		try {
			if (role.equals("Batsman")) {
				numberOfBatsmen++;
			} else if (role.equals("Bowler")) {
				numberOfBowler++;
			} else if (role.equals("Wicket Keeper")) {
				numberOfKeeper++;
			} else if (role.equals("All Rounder")) {
				numberOfAllrounder++;
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}

	// deduce the roles of each players
	public void addRole(String role) {
		try {
			if (role.equals("Batsman")) {
				numberOfBatsmen--;
			} else if (role.equals("Bowler")) {
				numberOfBowler--;
			} else if (role.equals("Wicket Keeper")) {
				numberOfKeeper--;
			} else if (role.equals("All Rounder")) {
				numberOfAllrounder--;
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}

	// check for adding to valid list
	public boolean checkForValidation(int playerId) {
		try {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i) == playerId) {
					userService.callDisplayAlert(DisplayConstants.ALREADY_PRESENT);
					userService.callDisplayAlert(DisplayConstants.INVALID_SELECTION);
					return false;
				}
			}
			role = userDAO.getRole(playerId);
			playerCredits = userDAO.getPlayerCredits(playerId);
			if (matchCredits < (creditsCompleted + playerCredits)) {
				userService.callDisplayAlert(Integer.toString(matchCredits));
				userService.callDisplayAlert(DisplayConstants.CREDITS_COMPLETED);
				userService.callDisplayAlert(DisplayConstants.INVALID_SELECTION);
				return false;
			}
			if (role.equals("Batsman") && numberOfBatsmen >= 4) {
				userService.callDisplayAlert(DisplayConstants.BATSMEN_EXCEED);
				userService.callDisplayAlert(DisplayConstants.INVALID_SELECTION);
				return false;
			} else if (role.equals("Bowler") && numberOfBowler >= 3) {
				userService.callDisplayAlert(DisplayConstants.BOWLER_EXCEED);
				userService.callDisplayAlert(DisplayConstants.INVALID_SELECTION);
				return false;
			} else if (role.equals("Wicket Keeper") && numberOfKeeper >= 1) {
				userService.callDisplayAlert(DisplayConstants.KEEPER_EXCEED);
				userService.callDisplayAlert(DisplayConstants.INVALID_SELECTION);
				return false;
			} else if (role.equals("All Rounder") && numberOfAllrounder >= 3) {
				userService.callDisplayAlert(DisplayConstants.ALLROUNDER_EXCEED);
				userService.callDisplayAlert(DisplayConstants.INVALID_SELECTION);
				return false;
			}
			players.add(playerId);
			credits.add(playerCredits);
			creditsCompleted += playerCredits;
			totalCredits -= playerCredits;
			deduceRole(role);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return true;

	}

	// pick a team of 11 players
	public void pickTeam(User user) {
		ArrayList <Player> playersList = new ArrayList <Player>();
		try {
			userDAO.displayActiveMatches();
			matchId = userService.callIntegerInputs();
			match = userDAO.setMatchBean(matchId);
			totalCredits = match.getMatchCredits();
			matchCredits = totalCredits;
			displayTeams(match);
			int count = 0;
			while (count < 11) {
				userService.callDisplayAlert(Integer.toString(totalCredits) + DisplayConstants.REMAINING_CREDITS);
				userService.callDisplayAlert(DisplayConstants.DISPLAY_REMAINING +Integer.toString(4-numberOfBatsmen) +DisplayConstants.DISPLAY_BATSMEN+Integer.toString(3-numberOfBowler)+DisplayConstants.DISPLAY_BOWLER+Integer.toString(3-numberOfAllrounder)+DisplayConstants.DISPLAY_ALLROUNDER+Integer.toString(1-numberOfKeeper)+DisplayConstants.DISPLAY_KEEPER);
				isValid = false;
				playerId = userService.callIntegerInputs();
				isValid = checkForValidation(playerId);
				if (isValid == true) {
					count++;
				}
			}
			userId = user.getUserId();
			addTeam(userId, matchId, players);
			playersList = userDAO.displaySelectedTeam(matchId, user.getUserId());
			userService.displayPlayerNamesList(playersList);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void displayTeams(Match match) {
		// TODO Auto-generated method stub
		ArrayList<Player> playersList = new ArrayList<Player>();
		try {
			playersList = userDAO.displayTeam(match.getTeamOne());
			userService.displayPlayerNamesList(playersList);
			playersList = userDAO.displayTeam(match.getTeamTwo());
			userService.displayPlayerNamesList(playersList);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}

	private void addTeam(int userId, int matchId, ArrayList<Integer> players) {
		// TODO Auto-generated method stub
		try {
			for (int index = 0; index < players.size(); index++) {
				userDAO.addPlayerToTeam(userId, matchId, players.get(index), credits.get(index));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	public void viewTeam(User user,int matchId) {
		// TODO Auto-generated method stub
		try {
			userId = user.getUserId();
			userDAO.displaySelectedTeam(matchId, userId);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void viewTeam(User user) {
		// TODO Auto-generated method stub
		try {
			userDAO.displayActiveMatches();
			matchId = userService.callIntegerInputs();
			userId = user.getUserId();
			userDAO.displaySelectedTeam(matchId, userId);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	// chat options
	private void chat(User user) {
		// TODO Auto-generated method stub
		try {
			do {
				ArrayList<Message> messages = new ArrayList<Message>();
				messages = userDAO.displayMessages();
				userService .callDisplayMessages(messages);
				userService.callDisplayAlert(DisplayConstants.CHAT_OPTIONS);
				choice = userService.callIntegerInputs();
				if (choice == 2) {
					break;
				}
				userService.callDisplayAlert(DisplayConstants.ENTER_MESSAGE);
				message = userService.callStringInputs();
				userId = user.getUserId();
				userDAO.insertIntoChat(userId, message);
			} while (choice != 2);
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// calculates the credits completed for a particular match
	public void calculateCreditsCompleted(ArrayList<Integer> credits, ArrayList<Integer> players) {
		try {
			int creditCount = 0;
			numberOfBatsmen = numberOfBowler = numberOfKeeper = numberOfAllrounder = 0;

			for (index = 0; index < credits.size(); index++) {
				role = userDAO.getRole(players.get(index));
				deduceRole(role);
				creditCount += credits.get(index);
			}
			creditsCompleted = creditCount;
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}

	// make the deletion and addition
	public void makeUpdation(User user, UserPickedTeam userPickedTeam, int matchCredits,int matchId) {
		try {
			int count = 0;
			viewTeam(user,matchId);
			players = userPickedTeam.getPlayerId();
			credits = userPickedTeam.getCredits();
			calculateCreditsCompleted(credits, players);
			this.matchCredits = matchCredits;
			do {
				userService.callDisplayAlert(DisplayConstants.DELETE_PLAYER_OPTIONS);
				choice = userService.callIntegerInputs();
				if (choice == 1) {
					userService.callDisplayAlert(DisplayConstants.DELETE_PLAYER_ID);
					playerId = userService.callIntegerInputs();
					role = userDAO.getRole(playerId);
					addRole(role);
					index = players.indexOf(playerId);
					creditsCompleted -= credits.get(index);
					players.remove(index);
					credits.remove(index);
					count++;
				}
			} while (choice == 1);
			while (count > 0) {
				count -= 1;
				userService.callDisplayAlert(DisplayConstants.ENTER_PLAYER_ID);
				playerId = userService.callIntegerInputs();
				checkForValidation(playerId);
			}
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		// userService.displayAlert("exiting");
	}

	// view result
	public void setResult(User user) {
		try {
			totalCredits = 0;
			userService.callDisplayAlert(DisplayConstants.ENTER_MATCH_ID);
			userDAO.displayCompletedMatches();
			matchId = userService.callIntegerInputs();
			/*totalCredits = userDAO.checkResultTable(user.getUserId(),matchId);
			if(totalCredits==0) {
				totalCredits = userDAO. calculateMatchScore(matchId,user.getUserId());
			}
			userService.displayAlert(Integer.toString(totalCredits));*/
			resultBoard = userDAO.setResultBoard(matchId);
			userService.displayResult(resultBoard);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
	}
	
	//view Top picked players of a match


	// modify the selected team
	public void modifyTeam(User user) {
		// TODO Auto-generated method stub
		try {
			userDAO.displayActiveMatches();
			matchId = userService.callIntegerInputs();
			match = userDAO.setMatchBean(matchId);
			matchCredits = match.getMatchCredits();
			// userService.displayAlert(Integer.toString(matchCredits));
			userPickedTeam = userDAO.setUserTeamBean(user.getUserId(), matchId);
			makeUpdation(user, userPickedTeam, matchCredits,matchId);
			userDAO.deleteOldTeam(matchId, user.getUserId());
			addTeam(user.getUserId(), matchId, players);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	//see most picked
	public void seeMostPicked() {
		try {
			ArrayList <String> playerNames = new ArrayList <String>();
			userService.callDisplayAlert(DisplayConstants.ENTER_MATCH_ID);
			userDAO.displayCompletedMatches();
			matchId = userService.callIntegerInputs();
			playerNames = userDAO.getPlayersName (matchId);
			userService.displayPlayerNames(playerNames);
		}catch(Exception e) {
			
		}
		
	}

	// main options
	public void userMainMenu(User user) {
		try {
			do {
				userService.callDisplayAlert(DisplayConstants.DISPLAY_USER_OPTIONS);
				choice = userService.callIntegerInputs();
				switch (choice) {
				case 1:
					pickTeam(user);
					break;
				case 2:
					viewTeam(user);
					break;
				case 3:
					modifyTeam(user);
					break;
				case 4:
					chat(user);
					break;
				case 5:
					setResult(user);
					break;
				case 6:
					seeMostPicked();
					break;
				default:
					break;
				}
			} while (choice < 7);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}

}
