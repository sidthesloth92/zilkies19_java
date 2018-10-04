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
	boolean flag;
	DelegatesCrudOperations delegates = new DelegatesCrudOperations();
	ArrayList hm = new ArrayList();
	ArrayList<String> al = new ArrayList<String>();
	int teamid;
	int teamId[] = new int[2];

	public ServiceCrudOperations() {
		teamid=0;
		flag=false;
	}

	public boolean getUserLoginInfo(Login loginObj) throws SQLException {
		try {
			flag = delegates.getUserLoginInfo(loginObj);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}
	
	public String getUserName(Login loginObj) throws SQLException {
		String user="";
		try {
			user = delegates.getUserName(loginObj);
		} catch (SQLException e) {
			throw e;
		}
		return user;
	}
	
	public int getUserId(Login loginObj) throws SQLException {
		int userId=0;
		try {
			userId = delegates.getUserId(loginObj);
		} catch (SQLException e) {
			throw e;
		}
		return userId;
	}
	
	public ArrayList getScore(int teamId,String match) throws SQLException{
		try {
			return delegates.getScore(teamId,match);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public int  getteamId(String teamName) throws SQLException{
		try {
			return delegates.getteamId(teamName);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public ArrayList getTournament() throws SQLException{
		try {
			return delegates.getTournament();
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public ArrayList getTournament(String email) throws SQLException{
		try {
			return delegates.getTournament(email);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public String getMobile(int userid) throws SQLException{
		try {
			return delegates.getMobile(userid);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public void updateStatus(String tour) throws SQLException{
		try {
			delegates.updateStatus(tour);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public void rejectTour(String tour) throws SQLException{
		try {
			delegates.rejectTour(tour);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public void insertTournament(Tournament tour) throws SQLException{
		try {
			delegates.insertTournament(tour);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public ArrayList getplayers(int teamid) throws SQLException{
		try {
			return delegates.getplayers(teamid);
		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public boolean isValidEmail(Login credentials) throws SQLException {
		boolean validate=false;
		try {
			validate = delegates.isValidEmail(credentials);
		}
		catch(SQLException e) {
			throw e;
		}
		return validate;
	}
	
	public boolean isValidCredentials(Login credentials) throws SQLException {
		boolean validate=false;
		try {
			validate = delegates.isValidCredentials(credentials);
		}
		catch(SQLException e) {
			throw e;
		}
		return validate;
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
	
	public ArrayList showTournament(String email) throws SQLException {
		try {
			hm = delegates.showTournament(email);
		} catch (SQLException e) {
			throw e;
		}
		return hm;
	}
	
	public int isUserAlreadyRegistered(String email,int tournamentid) throws SQLException{
		int flag=0;
		try {
			flag = delegates.isUserAlreadyRegistered(email,tournamentid);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}
	
	public String getTeamName(int tourid,String email) throws SQLException {
		String team="";
		try {
			team= delegates.getTeamName(tourid,email);
		}
		catch(SQLException e) {
			
		}
		return team;
	}

	public int addTeam(Team teamobj,int userid) throws SQLException {
		try {
			teamid = delegates.addTeam(teamobj,userid);
		} catch (SQLException e) {
			throw e;
		}
		return teamid;
	}

	public void addPlayer(ArrayList<Player> al,int userid) throws SQLException {
		try {
			delegates.addPlayer(al,userid);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void updatePlayer(ArrayList<Player> al,int userid,int playerid) throws SQLException {
		try {
			delegates.updatePlayer(al,userid,playerid);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public int getPlayerId(int teamid) throws SQLException {
		try {
			return delegates.getPlayerId(teamid);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public int getTourId(String tournamentName) throws SQLException{
		try {
			return delegates.getTourId(tournamentName);
		}
		catch(SQLException e) {
			throw e;
		}
	}

	public void addTournament(Tournament tourobj,Login loginObj) throws SQLException {
		try {
			delegates.addTournament(tourobj,loginObj);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList viewRequestStatus(Login credentials) throws SQLException  {
		ArrayList approvalStatus=new ArrayList();
		try {
			approvalStatus=delegates.viewRequestStatus(credentials);
		} catch (SQLException e) {
			throw e;
		}
		return approvalStatus;
	}
	
	public void changeRequestStatus(Team team) throws SQLException {
		try {
			delegates.changeRequestStatus(team);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public ArrayList viewRequestStatus() throws SQLException  {
		ArrayList approvalStatus=new ArrayList();
		try {
			approvalStatus=delegates.viewRequestStatus();
		} catch (SQLException e) {
			throw e;
		}
		return approvalStatus;
	}
	
	public void removeDeclineTournament(Team team) throws SQLException {
		try {
			delegates.removeDeclineTournament(team);
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
	Schedule scheduleObj = new Schedule();

	public ArrayList viewSchedule(Team tournamentId) throws SQLException {
		try {
			return delegates.viewSchedule(tournamentId);
		} catch (SQLException e) {

			throw e;
		}
	}
	
	public ArrayList<String> schedule(ArrayList<String> teamList) throws SQLException {
		try {
			return delegates.schedule(teamList);
		} catch (SQLException e) {

			throw e;
		}
	}
	
	public void updateScorecard(Score stats,Scorecard matchinfo) throws SQLException {
		try {
			delegates.updateScorecard(stats,matchinfo);
		}
		catch(SQLException e) {
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
	public boolean getRole(Login loginObj) throws SQLException {
		try {
			return delegates.getRole(loginObj);
		}
		catch (SQLException e) {
			throw e;
		}
	}
}
