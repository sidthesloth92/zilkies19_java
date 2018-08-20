package io.ztech.cricketapp.delegate;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import io.ztech.cricketapp.beans.Player;
import io.ztech.cricketapp.beans.Team;
import io.ztech.cricketapp.beans.User;
import io.ztech.cricketapp.constants.UserMessages;
import io.ztech.cricketapp.dao.CricketDAO;
import io.ztech.cricketapp.ui.UserEntry;

public class TeamDelegate {
	Scanner scanner;
	Logger logger;
	PlayerDelegate playerDelegate;
	CricketDAO dao;
	
	public TeamDelegate() {
		logger = Logger.getLogger(UserEntry.class.getName());
		scanner = new Scanner(System.in);
		playerDelegate = new PlayerDelegate();
		dao = new CricketDAO();
	}
	
	public void displayTeams(User user) {
		ArrayList<Team> teamList = dao.fetchTeams(user);
		for (Team team : teamList) {
			logger.info(UserMessages.TEAM_TABLE);
			logger.info(team.getTeamId() + "\t" + team.getTeamName());
			playerDelegate.printPlayerDetails(team);
		}
	}
	
	public void createTeam(User user) {
		dao.insertTeam(user);
	}
	
	public void updateTeamName(Team team) {
		dao.updateTeamName(team);
	}
	
	/*public boolean searchTeam(int teamId, User user) {
		return dao.searchTeam(user, teamId);
	}*/
	
	public void addNewPlayer(User user) {
		dao.insertPlayer(user);
	}
	
	public Team fetchTeam(int teamId) {
		return dao.fetchTeam(teamId);
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
