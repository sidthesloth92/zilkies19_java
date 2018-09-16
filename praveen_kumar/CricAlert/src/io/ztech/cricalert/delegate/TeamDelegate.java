package io.ztech.cricalert.delegate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.dao.CricketDAO;

public class TeamDelegate {
	Scanner scanner;
	Logger logger;
	PlayerDelegate playerDelegate;
	CricketDAO dao;
	
	public TeamDelegate() {
		logger = Logger.getLogger(TeamDelegate.class.getName());
		scanner = new Scanner(System.in);
		playerDelegate = new PlayerDelegate();
		dao = new CricketDAO();
	}
	
	public void createTeam(User user) {
		dao.insertTeam(user);
	}
	
	public void addNewPlayer(User user) {
		dao.insertPlayer(user);
	}
	
	public void updateTeamName(Team team) {
		dao.updateTeamName(team);
	}
	
	public void updateTeamPlayers(ArrayList<Player> playerList, Team team) {
		dao.removePlayerFromTeam(team);
		dao.updateTeamPlayers(playerList);
	}
	
	/*public boolean searchTeam(int teamId, User user) {
		return dao.searchTeam(user, teamId);
	}*/
	
	public Team fetchTeam(int teamId) {
		return dao.fetchTeam(teamId);
	}
	
	public ArrayList<Team> fetchTeams(User user) {
		return dao.fetchTeams(user);
	}
	
	public ArrayList<Player> fetchTeamPlayers(Team team) {
		return dao.fetchTeamPlayers(team);
	}
	
	public void removeTeam(Team team) {
		dao.deleteTeam(team);
	}
	
	public boolean isTeamCreated(User user) {
		ArrayList<Team> teamList = dao.fetchTeams(user);
		if (teamList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
