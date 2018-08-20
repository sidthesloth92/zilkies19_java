package io.zilker.fantasy.service;

import java.util.ArrayList;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.delegate.UserOperations;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.ui.UserUI;

public class UserService {
	private UserUI userUI = new UserUI();

	/*public void userOperationsRedirect(User user) {
		UserOperations userOperations = new UserOperations();
		userOperations.userMainMenu(user);
	}*/

	public int callIntegerInputs() {
		return userUI.getIntInputs();
	}

	public String callStringInputs() {
		return userUI.getStringInputs();
	}

	public void callDisplayAlert(String message) {
		userUI.displayAlert(message);
	}

	public void displayResult(ResultBoard resultBoard) {
		// TODO Auto-generated method stub
		userUI.displayResult(resultBoard);

	}

	public void printPlayers(int playerId, String playerName, String type, int rating) {
		// TODO Auto-generated method stub
		userUI.printPlayers(playerId, playerName, type, rating);

	}

	public void printMessages(String userName, String message, String insertedTime) {
		// TODO Auto-generated method stub
		userUI.printMessages(userName, message, insertedTime);

	}

	public void displayMatches(ArrayList<Integer> matchId, ArrayList<String> teamOne, ArrayList<String> teamTwo) {
		// TODO Auto-generated method stub
		userUI.displayMatches(matchId, teamOne, teamTwo);

	}

	public void displayPlayerNames(ArrayList<String> playerNames) {
		// TODO Auto-generated method stub
		userUI.printPlayers(playerNames);
	}

	public void displayPlayerNamesList(ArrayList<Player> playersList) {
		// TODO Auto-generated method stub
		userUI.displayPlayerNamesList(playersList);

	}

	public void callDisplayMessages(ArrayList<Message> messages) {
		// TODO Auto-generated method stub
		userUI.displayMessages(messages);

	}

	public ArrayList<Player> pickTeamCaller(User user) {
		// TODO Auto-generated method stub
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList = new UserOperations().pickTeam(user);
		return playerList;
	}

	public ArrayList<Player> viewTeamCaller(User user) {
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList = new UserOperations().viewTeam(user);
		return playerList;
	}

	public boolean modifyTeamCaller(User user) {
		// TODO Auto-generated method stub
		boolean isDone = new UserOperations().modifyTeam(user);
		return isDone;
	}

	public boolean chatCaller(User user) {
		// TODO Auto-generated method stub
		boolean isDone = new UserOperations().chat(user);
		return isDone;
	}

	public ResultBoard viewLeaderBoardCaller(User user) {
		// TODO Auto-generated method stub
		ResultBoard resultBoard = new ResultBoard();
		resultBoard = new UserOperations().viewLeaderBoard(user);
		return resultBoard;
	}

	public ArrayList<String> seeMostPickedCaller() {
		// TODO Auto-generated method stub
		ArrayList<String> playerNames = new ArrayList<String>();
		playerNames = new UserOperations().viewMostPicked();
		return playerNames;
	}

	public ResultBoard previousResultsCaller(User user) {
		// TODO Auto-generated method stub
		ResultBoard resultBoard = new ResultBoard();
		resultBoard = new UserOperations().ViewPreviousResults(user);
		return resultBoard;
	}
	
	public ArrayList<Match> getUpcomingMatchesCaller(User user) {
		// TODO Auto-generated method stub
		ArrayList<Match> matchList =new  ArrayList<Match>();
		matchList = new UserOperations().getUpcomingMatches();
		return matchList;
	}
	

}
