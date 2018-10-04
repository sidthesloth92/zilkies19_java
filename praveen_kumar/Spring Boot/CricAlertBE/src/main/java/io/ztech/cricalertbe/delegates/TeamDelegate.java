package io.ztech.cricalertbe.delegates;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricalertbe.beans.Player;
import io.ztech.cricalertbe.beans.Team;
import io.ztech.cricalertbe.beans.UpdateTeam;
import io.ztech.cricalertbe.dao.CricketDAO;

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
	
	public Team fetchTeam(int teamId) {
		return dao.fetchTeam(teamId);
	}
	
	public ArrayList<Team> fetchTeams(int userId) {
		return dao.fetchTeams(userId);
	}
	
	public ArrayList<Player> fetchTeamPlayers(int teamId) {
		return dao.fetchTeamPlayers(teamId);
	}
	
	public void createTeam(Team team) {
		dao.insertTeam(team);
	}
	
	public void updateTeam(UpdateTeam updateTeam) {
		dao.updateTeamName(updateTeam.getTeam());
		dao.removePlayerFromTeam(updateTeam.getTeam());
		dao.updateTeamPlayers(updateTeam.getPlayerList());
	}
	
	public void removeTeam(Team team) {
		dao.deleteTeam(team);
	}
	
	/*public void updateTeamName(Team team) {
		dao.updateTeamName(team);
	}
	
	public void updateTeamPlayers(ArrayList<Player> playerList, Team team) {
		dao.removePlayerFromTeam(team);
		dao.updateTeamPlayers(playerList);
	}*/
}
