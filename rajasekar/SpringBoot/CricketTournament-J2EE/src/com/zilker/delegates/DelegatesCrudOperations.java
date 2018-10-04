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
	public ArrayList getScore(int teamId,String match) throws SQLException{
		try {
			return daoObj.getScore(teamId,match);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public int  getteamId(String teamName) throws SQLException{
		try {
			return daoObj.getteamId(teamName);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	public ArrayList getplayers(int teamid) throws SQLException{
		try {
			return daoObj.getplayers(teamid);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	
	public String getUserName(Login loginObj) throws SQLException {
		String user="";
		try {
			user = daoObj.getUserName(loginObj);
		} catch (SQLException e) {
			throw e;
		}
		return user;
	}
	
	public int getUserId(Login loginObj) throws SQLException {
		int userId=0;
		try {
			userId = daoObj.getUserId(loginObj);
		} catch (SQLException e) {
			throw e;
		}
		return userId;
	}

	public boolean getAdminLoginInfo(Login loginObj) throws SQLException {
		try {
			flag = daoObj.authenticateAdmin(loginObj);
			return flag;
		} catch (Exception e) {
			throw e;
		}

	}
	
	public String getMobile(int userid) throws SQLException{
		try {
			return daoObj.getMobile(userid);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public void updateStatus(String tour) throws SQLException{
		try {
			daoObj.updateStatus(tour);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public void rejectTour(String tour) throws SQLException{
		try {
			daoObj.rejectTour(tour);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public ArrayList getTournament() throws SQLException{
		try {
			return daoObj.getTournament();
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public ArrayList getTournament(String email) throws SQLException{
		try {
			return daoObj.getTournament(email);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public void insertTournament(Tournament tour) throws SQLException{
		try {
			daoObj.insertTournament(tour);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public boolean isValidEmail(Login credentials) throws SQLException {
		boolean validate=false;
		try {
			validate = daoObj.isValidEmail(credentials);
		}
		catch(SQLException e) {
			throw e;
		}
		return validate;
	}

	public boolean isValidCredentials(Login credentials) throws SQLException {
		boolean validate=false;
		try {
			validate = daoObj.isValidCredentials(credentials);
		}
		catch(SQLException e) {
			throw e;
		}
		return validate;
	}

	public void insertUserDetails(UserDetails userObj) throws SQLException {
		try {
			daoObj.insertUser(userObj);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int getTourId(String tournamentName) throws SQLException{
		try {
			return daoObj.getTourId(tournamentName);
		}
		catch(SQLException e) {
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
	
	public ArrayList showTournament(String email) throws SQLException {
		try {
			hm = daoObj.retrieveTournament(email);
			return hm;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public int isUserAlreadyRegistered(String email,int tournamentid) throws SQLException{
		int flag=0;
		try {
			flag = daoObj.isUserAlreadyRegistered(email,tournamentid);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}
	
	public String getTeamName(int tourid,String email) throws SQLException {
		String team="";
		try {
			team=daoObj.getTeamName(tourid,email);
		}
		catch(SQLException e) {
			
		}
		return team;
	}

	public int addTeam(Team teamobj,int userid) throws SQLException {
		try {
			teamid = daoObj.insertTeam(teamobj,userid);
			return teamid;
		} catch (SQLException e) {
			throw e;
		}
	}

	public void addPlayer(ArrayList<Player> al,int userid) throws SQLException {
		try {
			daoObj.insertPlayer(al,userid);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public int getPlayerId(int teamid) throws SQLException {
		try {
			return daoObj.getPlayerId(teamid);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void updatePlayer(ArrayList<Player> al,int userid,int playerid) throws SQLException {
		try {
			daoObj.updatePlayer(al,userid,playerid);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void addTournament(Tournament tourobj,Login loginObj) throws SQLException {
		try {
			daoObj.insertTournament(tourobj,loginObj);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList viewRequestStatus(Login credentials) throws SQLException{
		ArrayList approvalStatus=new ArrayList();
		try {
			approvalStatus=daoObj.viewRequestStatus(credentials);
		} catch (SQLException e) {
			throw e;
		}
		return approvalStatus;
	}
	
	public ArrayList viewRequestStatus() throws SQLException{
		ArrayList approvalStatus=new ArrayList();
		try {
			approvalStatus=daoObj.viewRequestStatus();
		} catch (SQLException e) {
			throw e;
		}
		return approvalStatus;
	}
	
	public void changeRequestStatus(Team team) throws SQLException {
		try {
			daoObj.changeRequestStatus(team);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void removeDeclineTournament(Team team) throws SQLException{
		try {
			daoObj.removeDeclineTournament(team);
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
	
	public ArrayList<String> schedule(ArrayList<String> teamList) throws SQLException {
		com.zilker.delegates.Schedule scheduleObj = new com.zilker.delegates.Schedule();
		try {
			return scheduleObj.scheduleMatches(teamList);
		} catch (Exception e) {

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
	
	public void updateScorecard(Score stats,Scorecard matchinfo) throws SQLException {
		try {
			daoObj.updateScorecard(stats,matchinfo);
		}
		catch(SQLException e) {
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
	public boolean getRole(Login loginObj) throws SQLException {
		try {
			return daoObj.getRole(loginObj);
		}
		catch (SQLException e) {
			throw e;
		}
	}
}
