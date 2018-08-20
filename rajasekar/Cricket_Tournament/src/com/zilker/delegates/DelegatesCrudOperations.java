package com.zilker.delegates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Schedule;
import com.zilker.beans.Scorecard;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.Score;
import com.zilker.beans.UserDetails;
import com.zilker.dao.CricketTournamentDAO;

public class DelegatesCrudOperations {
	CricketTournamentDAO daoObj = new CricketTournamentDAO();
	boolean flag;
	ArrayList hm = new ArrayList();
	ArrayList<String> al = new ArrayList<String>();
	int teamid, status;
	int teamId[] = new int[2];

	public DelegatesCrudOperations() {
		flag = false;
		teamid = 0;
		status = 0;
	}

	public boolean getUserLoginInfo(Login loginObj) throws SQLException {
		try {
			flag = daoObj.authenticateUser(loginObj);
			return flag;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean getAdminLoginInfo(Login loginObj) throws SQLException {
		try {
			flag = daoObj.authenticateAdmin(loginObj);
			return flag;
		} catch (Exception e) {
			throw e;
		}

	}

	public void insertUserDetails(UserDetails userObj) throws SQLException {
		try {
			daoObj.insertUser(userObj);
		} catch (Exception e) {
			throw e;
		}
	}

	public void insertAdminDetails(AdminDetails adminObj) throws SQLException {
		try {
			daoObj.insertAdmin(adminObj);
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList showTournament() throws SQLException {
		try {
			hm = daoObj.retrieveTournament();
			return hm;
		} catch (Exception e) {
			throw e;
		}
	}

	public int addTeam(Team teamobj) throws SQLException {
		try {
			teamid = daoObj.insertTeam(teamobj);
			return teamid;
		} catch (SQLException e) {
			throw e;
		}
	}

	public void addPlayer(ArrayList<Player> al) throws SQLException {
		try {
			daoObj.insertPlayer(al);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void addTournament(Tournament tourobj) throws SQLException {
		try {
			daoObj.insertTournament(tourobj);
		} catch (SQLException e) {
			throw e;
		}

	}

	public ArrayList<String> viewTeams(Team object, Login login) throws SQLException {
		try {
			al = daoObj.viewTeams(object, login);
			return al;
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<String> viewTeams(Team tournamentId) throws SQLException {
		try {
			al = daoObj.viewTeams(tournamentId);
			return al;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int[] getTeamId(String[] match) throws SQLException {
		try {
			teamId = daoObj.getTeamId(match);
			return teamId;
		} catch (SQLException e) {
			throw e;
		}
	}

	public int deleteFixtures(Team tournamentId) throws SQLException {
		try {
			return daoObj.deleteFixedMatches(tournamentId);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void fixtures(Schedule obj) throws SQLException {
		try {
			daoObj.insertSchedule(obj);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int checkTeamName(Team obj, Team object) throws SQLException {
		try {
			return daoObj.checkTeamName(obj, object);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int updateTeamName(String[] teamName, Team teamobject) throws SQLException {
		try {
			return daoObj.updateTeamName(teamName, teamobject);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void updatePlayer(ArrayList<String> al, Player playerId) throws SQLException {
		try {
			daoObj.updatePlayer(al, playerId);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList viewSchedule(Team tournamentId) throws SQLException {
		try {
			return daoObj.viewSchedule(tournamentId);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList showPlayer(Team teamName) throws SQLException {
		try {
			return daoObj.showPlayer(teamName);
		} catch (SQLException e) {
			throw e;
		}

	}

	public void insertScorecard(ArrayList<Scorecard> al) throws SQLException {
		try {
			daoObj.insertScorecard(al);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void updateScore(int[] info, Score obj) throws SQLException {
		try {
			daoObj.updateScore(info, obj);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList viewScorecard(Scorecard matchNo) throws SQLException {
		try {
			return daoObj.viewScorecard(matchNo);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int deleteTournament(Team tournamentId) throws SQLException {
		try {
			return daoObj.deleteTournament(tournamentId);
		} catch (SQLException e) {
			throw e;
		}
	}
}
