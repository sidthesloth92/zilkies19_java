package com.zilker.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.UserDetails;
import com.zilker.beans.Schedule;
import com.zilker.delegates.DelegatesCrudOperations;

public class ServiceCrudOperations {
	boolean flag = false;
	DelegatesCrudOperations delegates = new DelegatesCrudOperations();
	ArrayList hm = new ArrayList();
	ArrayList<String> al = new ArrayList<String>();
	int teamid = 0;
	int teamId[] = new int[2];

	public boolean getUserLoginInfo(Login loginObj) {
		flag = delegates.getUserLoginInfo(loginObj);
		return flag;
	}

	public boolean getAdminLoginInfo(Login loginObj) {
		flag = delegates.getAdminLoginInfo(loginObj);
		return flag;
	}

	public void insertUserDetails(UserDetails userObj) {
		delegates.insertUserDetails(userObj);
	}

	public void insertAdminDetails(AdminDetails adminObj) {
		delegates.insertAdminDetails(adminObj);
	}

	public ArrayList showTournament() {
		hm = delegates.showTournament();
		return hm;
	}

	public int addTeam(Team teamobj) {
		teamid = delegates.addTeam(teamobj);
		return teamid;
	}

	public void addPlayer(ArrayList<Player> al) {
		delegates.addPlayer(al);
	}

	public void addTournament(Tournament tourobj) {
		delegates.addTournament(tourobj);
	}

	public ArrayList<String> viewTeams(int tournament_id,String email) {
		al = delegates.viewTeams(tournament_id,email);
		return al;
	}
	public ArrayList<String> viewTeams(int tournament_id) {
		al = delegates.viewTeams(tournament_id);
		return al;
	}
	public int[] getTeamId(String[] match) {
		teamId = delegates.getTeamId(match);
		return teamId;
	}
	
	public int deleteFixtures(int tournament_id) {
		return delegates.deleteFixtures(tournament_id);
	}

	public void fixtures(Schedule obj) {
		delegates.fixtures(obj);
	}
	public int checkTeamName(Team obj,int tournament_id) {
		return delegates.checkTeamName(obj,tournament_id);
	}
	public int updateTeamName(String[] team_name,int tournament_id) {
		return delegates.updateTeamName(team_name,tournament_id);
	}
	public void updatePlayer(ArrayList<String> al,int player_id) {
		delegates.updatePlayer(al,player_id);
	}
	public ArrayList viewSchedule(int tournament_id) {
		return delegates.viewSchedule(tournament_id);
	}
	public ArrayList showPlayer(String team_name) {
		return delegates.showPlayer(team_name);
	}
}
