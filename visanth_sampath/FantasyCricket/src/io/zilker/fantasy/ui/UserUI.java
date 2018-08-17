package io.zilker.fantasy.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.constants.DisplayConstants;

public class UserUI {
	private Scanner scanner = new Scanner(System.in);
	private Logger logger = Logger.getLogger(UserUI.class.getName());
	private int index;

	public int getIntInputs() {
		return Integer.parseInt(scanner.nextLine());
		// return input;
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

}
