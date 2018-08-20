package com.zilker.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.Score;
import com.zilker.beans.UserDetails;
import com.zilker.beans.Schedule;
import com.zilker.beans.Scorecard;
import com.zilker.delegates.DelegatesCrudOperations;

public class ServiceCrudOperations {
	boolean flag = false;
	DelegatesCrudOperations delegates = new DelegatesCrudOperations();
	ArrayList hm = new ArrayList();
	ArrayList<String> al = new ArrayList<String>();
	int teamid = 0;
	int teamId[] = new int[2];

	public boolean getUserLoginInfo(Login loginObj) throws SQLException {
		try {
			flag = delegates.getUserLoginInfo(loginObj);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public boolean getAdminLoginInfo(Login loginObj) throws SQLException {
		try {
			flag = delegates.getAdminLoginInfo(loginObj);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}

	public void insertUserDetails(UserDetails userObj) throws Exception {
		try {
			delegates.insertUserDetails(userObj);
		} catch (Exception e) {
			throw e;
		}
	}

	public void insertAdminDetails(AdminDetails adminObj) throws Exception {
		try {
			delegates.insertAdminDetails(adminObj);
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList showTournament() throws SQLException {
		try {
			hm = delegates.showTournament();
		} catch (SQLException e) {
			throw e;
		}
		return hm;
	}

	public int addTeam(Team teamobj) throws SQLException {
		try {
			teamid = delegates.addTeam(teamobj);
		} catch (SQLException e) {
			throw e;
		}
		return teamid;
	}

	public void addPlayer(ArrayList<Player> al) throws SQLException {
		try {
			delegates.addPlayer(al);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void addTournament(Tournament tourobj) throws SQLException {
		try {
			delegates.addTournament(tourobj);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<String> viewTeams(Team object, Login login) throws SQLException {
		try {
			al = delegates.viewTeams(object, login);
			return al;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<String> viewTeams(Team tournamentId) throws SQLException {
		try {
			al = delegates.viewTeams(tournamentId);
			return al;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int[] getTeamId(String[] match) throws SQLException {
		try {
			teamId = delegates.getTeamId(match);
			return teamId;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int deleteFixtures(Team tournamentId) throws SQLException {
		try {
			return delegates.deleteFixtures(tournamentId);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void fixtures(Schedule obj) throws SQLException {
		try {
			delegates.fixtures(obj);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int checkTeamName(Team obj, Team object) throws SQLException {
		try {
			return delegates.checkTeamName(obj, object);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int updateTeamName(String[] teamName, Team teamobject) throws SQLException {
		try {
			return delegates.updateTeamName(teamName, teamobject);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void updatePlayer(ArrayList<String> al, Player playerId) throws SQLException {
		try {
			delegates.updatePlayer(al, playerId);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList viewSchedule(Team tournamentId) throws SQLException {
		try {
			return delegates.viewSchedule(tournamentId);
		} catch (SQLException e) {

			throw e;
		}
	}

	public ArrayList showPlayer(Team teamName) throws SQLException {
		try {
			return delegates.showPlayer(teamName);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void insertScorecard(ArrayList<Scorecard> al) throws SQLException {
		try {
			delegates.insertScorecard(al);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void updateScore(int[] info, Score obj) throws SQLException {
		try {
			delegates.updateScore(info, obj);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList viewScorecard(Scorecard matchNo) throws SQLException {
		try {
			return delegates.viewScorecard(matchNo);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int deleteTournament(Team tournamentId) throws SQLException {
		try {
			return delegates.deleteTournament(tournamentId);
		} catch (SQLException e) {
			throw e;
		}
	}
}
