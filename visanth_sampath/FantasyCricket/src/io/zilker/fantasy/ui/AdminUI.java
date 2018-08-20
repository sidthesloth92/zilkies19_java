package io.zilker.fantasy.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.constants.DisplayConstants;
import io.zilker.fantasy.service.AdminService;
import io.zilker.fantasy.utility.GeneralValidators;

public class AdminUI {
	private int index, choice;
	private Scanner scanner = new Scanner(System.in);
	private Logger logger = Logger.getLogger(AdminUI.class.getName());
	boolean isDone;
	String inputString;

	public void printMatches(int matchId, String teamOne, String teamTwo) {
		logger.info(matchId + " " + teamOne + " " + teamTwo + "\n");
	}

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

	// main menu for admin
	public void displayAdminOperations() {
		try {
			do {
				displayAlert(DisplayConstants.DISPLAY_ADMIN_OPTIONS);
				choice = getIntInputs();
				switch (choice) {
				case 1:
					isDone = new AdminService().scheduleNewMatchCaller();
					if (isDone) {
						displayAlert(DisplayConstants.MATCH_ADD_SUCCESS);
					}
					break;
				case 2:
					isDone = new AdminService().startMatchCaller();
					if (isDone) {
						displayAlert(DisplayConstants.MATCH_DISABLED);
					}
					break;
				case 3:
					isDone = new AdminService().addPlayerCaller();
					if (isDone) {
						displayAlert(DisplayConstants.PLAYER_ADDED_SUCESS);
					}
					break;
				case 4:
					isDone = new AdminService().editPlayerRatingCaller();
					if (isDone) {
						displayAlert(DisplayConstants.RATING_MODIFIED_SUCESSFULLY);
					}
					break;
				case 5:
					isDone = new AdminService().endMatchCaller();
					if (isDone) {
						displayAlert(DisplayConstants.DISPLAY_MATCH_COMPLETED);
					}
					break;
				default:
					break;
				}
			} while (choice < 6);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
