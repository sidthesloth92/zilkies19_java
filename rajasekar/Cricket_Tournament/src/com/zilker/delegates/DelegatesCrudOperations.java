package com.zilker.delegates;

import java.util.ArrayList;
import java.util.HashMap;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Schedule;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.UserDetails;
import com.zilker.dao.DaoImplementation;

public class DelegatesCrudOperations {
	DaoImplementation daoObj = new DaoImplementation();
	boolean flag = false;
	ArrayList hm = new ArrayList();
	ArrayList<String> al = new ArrayList<String>();
	int teamid = 0,status=0;
	int teamId[] = new int[2];

	public boolean getUserLoginInfo(Login loginObj) {
		flag = daoObj.authenticateUser(loginObj);
		return flag;
	}
	public boolean getAdminLoginInfo(Login loginObj) {
		flag = daoObj.authenticateAdmin(loginObj);
		return flag;
	}

	public void insertUserDetails(UserDetails userObj) {
		daoObj.insertUser(userObj);
	}

	public void insertAdminDetails(AdminDetails adminObj) {
		daoObj.insertAdmin(adminObj);
	}

	public ArrayList showTournament() {
		hm = daoObj.retrieveTournament();
		return hm;
	}

	public int addTeam(Team teamobj) {
		teamid = daoObj.insertTeam(teamobj);
		return teamid;
	}

	public void addPlayer(ArrayList<Player> al) {
		daoObj.insertPlayer(al);
	}

	public void addTournament(Tournament tourobj) {
		daoObj.insertTournament(tourobj);
	}

	public ArrayList<String> viewTeams(int tournament_id,String email) {
		al = daoObj.viewTeams(tournament_id,email);
		return al;
	}
	public ArrayList<String> viewTeams(int tournament_id) {
		al = daoObj.viewTeams(tournament_id);
		return al;
	}

	public int[] getTeamId(String[] match) {
		teamId = daoObj.getTeamId(match);
		return teamId;
	}
	
	public int deleteFixtures(int tournament_id) {
		return daoObj.deleteFixedMatches(tournament_id);
	}

	public void fixtures(Schedule obj) {
		daoObj.insertSchedule(obj);
	}
	public int checkTeamName(Team obj,int tournament_id) {
		return daoObj.checkTeamName(obj,tournament_id);
	}
	public int updateTeamName(String[] team_name,int tournament_id) {
		return daoObj.updateTeamName(team_name,tournament_id);
	}
	public void updatePlayer(ArrayList<String> al,int player_id) {
		daoObj.updatePlayer(al,player_id);
	}
	public ArrayList viewSchedule(int tournament_id) {
		return daoObj.viewSchedule(tournament_id);
	}
	public ArrayList showPlayer(String team_name) {
		return daoObj.showPlayer(team_name);
	}
}
