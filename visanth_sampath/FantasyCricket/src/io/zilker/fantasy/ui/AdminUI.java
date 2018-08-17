package io.zilker.fantasy.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.constants.DisplayConstants;

public class AdminUI {
	private int index;
	private Scanner scanner = new Scanner(System.in);
	private Logger logger = Logger.getLogger(AdminUI.class.getName());

	public void printMatches(int matchId, String teamOne, String teamTwo) {
		logger.info(matchId + " " + teamOne + " " + teamTwo + "\n");
	}

	public int getIntInputs() {
		return scanner.nextInt();
		// return input;
	}

	public String getStringInputs() {
		return scanner.next();
	}

	// prints the available players
	public void printPlayers(int PlayerId, String playerName, String role, int playerRating) {
		logger.info(PlayerId + "	" + playerName + "	" + role + "	" + playerRating + "\n");
	}

	// prints the alert messages
	public void displayAlert(String message) {
		logger.info(message);
	}

	public void displayMatchList(ArrayList<Match> matchList) {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(DisplayConstants.MATCHES_TABLE_FORMAT);
		for (index = 0; index < matchList.size(); index++) {
			stringBuilder.append(matchList.get(index).getMatchId() + "	" + matchList.get(index).getTeamOne() + "	"
					+ matchList.get(index).getTeamTwo() + "\n");
		}
		logger.info(stringBuilder.toString());

	}
}
