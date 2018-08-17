package io.zilker.fantasy.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.constants.DisplayConstants;
import io.zilker.fantasy.service.UserService;
import io.zilker.fantasy.utility.GeneralValidators;

public class UserUI {
	private Scanner scanner = new Scanner(System.in);
	private Logger logger = Logger.getLogger(UserUI.class.getName());
	private int index, choice;
	ArrayList<Player> playerList = new ArrayList<Player>();
	boolean isDone;
	ResultBoard resultBoard = new ResultBoard();
	ArrayList<String> playerNames = new ArrayList<String>();
	String inputString;

	public int getIntInputs() {
		inputString = scanner.nextLine();
		boolean isValid = new GeneralValidators().checkIntegerInputs(inputString);
		if (isValid) {
			return Integer.parseInt(inputString);
		} else {
			displayAlert(DisplayConstants.INVALID_INPUT);
		}
		// return input;
		return 0;
	}

	public String getStringInputs() {
		return scanner.nextLine();
	}

	public void displayAlert(String message) {
		logger.info(message);
	}

	public void printPlayers(int playerId, String playerName, String type, int rating) {
		// TODO Auto-generated method stub
		logger.info(playerId + " " + playerName + " " + type + " " + rating + "\n");

	}

	public void printMessages(String userName, String message, String time) {
		// TODO Auto-generated method stub
		logger.info(userName + " " + message + " " + time + "\n");

	}

	public void displayMatches(ArrayList<Integer> matchId, ArrayList<String> teamOne, ArrayList<String> teamTwo) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		for (index = 0; index < matchId.size(); index++) {
			stringBuilder.append(
					Integer.toString(matchId.get(index)) + " " + teamOne.get(index) + " " + teamTwo.get(index) + "\n");
		}
		logger.info(stringBuilder.toString());
	}

	public void displayResult(ResultBoard resultBoard) {
		// TODO Auto-generated method stub
		ArrayList<Integer> userId = new ArrayList<Integer>();
		ArrayList<Integer> matchPoints = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		StringBuilder stringBuilder = new StringBuilder();
		userId = resultBoard.getUsers();
		matchPoints = resultBoard.getMatchPoints();
		names = resultBoard.getNames();
		for (index = 0; index < userId.size(); index++) {
			stringBuilder.append(Integer.toString(userId.get(index)) + " " + names.get(index) + " "
					+ Integer.toString(matchPoints.get(index)) + "\n");
		}
		logger.info(stringBuilder.toString());
	}

	public void printPlayers(ArrayList<String> playerNames) {
		// TODO Auto-generated method stub
		for (index = 0; index < playerNames.size(); index++) {
			logger.info(playerNames.get(index));
		}

	}

	public void displayPlayerNamesList(ArrayList<Player> playersList) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DisplayConstants.PLAYERS_TABLE_FORMAT);
		for (index = 0; index < playersList.size(); index++) {
			stringBuilder.append(playersList.get(index).getPlayerId() + "		"
					+ playersList.get(index).getplayerName() + "		" + playersList.get(index).getplayerType()
					+ " 	" + playersList.get(index).getplayerRating() + "\n");
		}
		logger.info(stringBuilder.toString());

	}

	// display messages
	public void displayMessages(ArrayList<Message> messages) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DisplayConstants.MESSAGES_TABLE_FORMAT);
		for (index = 0; index < messages.size(); index++) {
			stringBuilder.append(messages.get(index).getUserName() + "	 " + messages.get(index).getMessage() + "	 "
					+ messages.get(index).getInsertedTime() + "\n");
		}
		logger.info(stringBuilder.toString());
	}
	
	private void printMatches(ArrayList<Match> matchList) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DisplayConstants.MATCHES_TABLE_FORMAT);
		for (index = 0; index < matchList.size(); index++) {
			stringBuilder.append(matchList.get(index).getMatchId()+"	"+ matchList.get(index).getTeamOne()+"	"+matchList.get(index).getTeamTwo()+"\n");
		}
		logger.info(stringBuilder.toString());
	}

	// main options
	public void userMainMenu(User user) {
		try {
			displayAlert(DisplayConstants.UPCOMING_MATCHES);
			ArrayList<Match> matchList = new ArrayList<Match>();
			 matchList = new UserService().getUpcomingMatchesCaller(user);
			 printMatches(matchList);
			do {
				displayAlert(DisplayConstants.DISPLAY_USER_OPTIONS);
				choice = getIntInputs();
				switch (choice) {
				case 1:
					playerList = new UserService().pickTeamCaller(user);
					displayPlayerNamesList(playerList);
					break;
				case 2:
					playerList = new UserService().viewTeamCaller(user);
					displayPlayerNamesList(playerList);
					break;
				case 3:
					isDone = new UserService().modifyTeamCaller(user);
					if (isDone) {
						displayAlert(DisplayConstants.MODIFY_SUCCESS);
					}
					break;
				case 4:
					isDone = new UserService().chatCaller(user);
					break;
				case 5:
					resultBoard = new UserService().viewLeaderBoardCaller(user);
					displayResult(resultBoard);
					break;
				case 6:
					playerNames = new UserService().seeMostPickedCaller();
					printPlayers(playerNames);
					break;
				case 7:
					resultBoard = new UserService().previousResultsCaller(user);
					displayResult(resultBoard);
					break;
				default:
					break;
				}
			} while (choice < 8);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	

}
