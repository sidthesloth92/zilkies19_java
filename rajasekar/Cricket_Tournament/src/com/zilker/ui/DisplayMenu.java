package com.zilker.ui;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.UserDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.service.ServiceCrudOperations;
import com.zilker.ui.FetchAndDisplay;
import com.zilker.service.*;
import java.io.IOException;
import java.util.*;

public class DisplayMenu {

	int option = 0, loginOption = 0, userOption = 0, adminOption = 0, tournament_id = 0, team_id = 0,
			total_teams = 0,status=0,flag_status=0,team_count=0,player_id=0;
	boolean flag = false;
	int[] teamId=new int[2];
	char ch = ' ',flagstatus=' ';
	String email = "", password = "", admin_password = "",team="";
	String team_name[]=new String[2];
	ArrayList<Player> al = new ArrayList<Player>();
	ArrayList<String> team_list = new ArrayList<String>();
	int teamid=0;
	ArrayList hm=new ArrayList();
	ArrayList<String> arrayList=new ArrayList<String>();
	Login loginObj=null;
	Team obj=null;
	FetchAndDisplay fetch=new FetchAndDisplay();
	ServiceCrudOperations service=new ServiceCrudOperations();
	Schedule scheduleObj=new Schedule();
	public void Login() {
		loginObj= fetch.login();
		flag=service.getUserLoginInfo(loginObj);
		if (flag == false) {
			fetch.displayInvalid();
			Login();
		}
		while (ch==' ') {
			hm = service.showTournament();
			tournament_id=fetch.displayTournament(hm);
			if (tournament_id == 0) {
				continue;
			}
			do {
			option=fetch.getUserOption();
			switch(option) {
			case 1:
				while(flag_status==0) {
					obj = fetch.teamDetails(tournament_id,loginObj.getEmail());
					flag_status=service.checkTeamName(obj,tournament_id);
					fetch.showMessage(flag_status);
					}
					teamid=service.addTeam(obj);
					al = fetch.getPlayerDetails(teamid);
					service.addPlayer(al);
					fetch.teamRegister();
					team_list=service.viewTeams(obj.getTournamentId());
					fetch.displayTeam(team_list);
					team_list.clear();
					break;
			case 2:
				option=fetch.getEditOption();
				if(option==1) {
					team_list.clear();
					team_list=service.viewTeams(tournament_id,loginObj.getEmail());
					fetch.displayTeam(team_list);
					if(team_list.size()==0) {
						fetch.displayNoTeamsAvailable();
					}
					else {
					team_name=fetch.getTeamName();
					team_count=service.updateTeamName(team_name,tournament_id);
					if(team_count==0) {
						ch='y';
						fetch.displayInvalidTeamName(team_count);
						continue;
					}
					else {
						fetch.displayInvalidTeamName(team_count);
					}
					}
				}
				else if(option==2) {
					team_list.clear();
					team_list=service.viewTeams(tournament_id,loginObj.getEmail());
					fetch.displayTeam(team_list);
					if(team_list.size()==0) {
						fetch.displayInvalid();
					}
					else {
					hm.clear();
					team=fetch.getTeam();
					hm=service.showPlayer(team);
					if(hm.size()!=0) {
						do {
					player_id=fetch.getPlayerId(hm);
					arrayList=fetch.updatePlayerDetails(team);
					service.updatePlayer(arrayList,player_id);
					ch=fetch.option();
						}while(ch=='y'||ch=='Y');
					}
					else {
						fetch.noPlayers();
					}
					}
				}
				else {
					fetch.displayInvalid();
				}
				break;
			case 3:
				hm=service.viewSchedule(tournament_id);
				fetch.displaySchedule(hm);
				break;
			}
			ch=fetch.tournamentMenu();
		}while(ch=='y' || ch=='Y');
			ch='f';
		}
	}
	public void adminLogin() {
		loginObj= fetch.login();
		flag=service.getAdminLoginInfo(loginObj);
		if (flag == false) {
			fetch.displayInvalid();
			adminLogin();
		}
		do {
			adminOption = fetch.adminOption();
		if (adminOption == 1) {
			Tournament obj = fetch.getTournamentDetails();
			service.addTournament(obj);
		} else if (adminOption == 2) {
			hm= service.showTournament();
			tournament_id=fetch.displayTournament(hm);
			if (tournament_id == 0) {
				return;
			}
			team_list=service.viewTeams(tournament_id,loginObj.getEmail());
			fetch.displayTeam(team_list);
			if(team_list.size()==0) {
				fetch.displayNoTeamsAvailable();
			}
			else {
			status=service.deleteFixtures(tournament_id);
			arrayList=scheduleObj.scheduleMatches(team_list);
			for(int i=0;i<arrayList.size();i++) {
				String array[]=arrayList.get(i).split("v");
				teamId=service.getTeamId(array);
				com.zilker.beans.Schedule obj=fetch.fixSchedule(teamId,arrayList.get(i),tournament_id);
				if(status==1)
				service.fixtures(obj);
			}
			fetch.showScheduled();
			}
		}
		flagstatus=fetch.getAdminMenu();
	}while(flagstatus=='y'||flagstatus=='Y');
}

	public void UserMenu() {
		loginOption = fetch.loginRegisterChoice();
		if (loginOption == 1) {
			Login();
		} else if (loginOption == 2) {
			UserDetails userObj = fetch.getUserDetails();
			service.insertUserDetails(userObj);
			fetch.registerStatus();
			Login();
		} else {
			fetch.displayInvalid();
		}

	}

	public void AdminMenu() throws IOException {
		loginOption = fetch.loginRegisterChoice();
		if (loginOption == 1) {
			adminLogin();
		} else if (loginOption == 2) {
			AdminDetails adminObj = fetch.getAdminDetails();
			admin_password = fetch.getAdminPassword();
			/*if (!(adminObj.getPassword().equals(admin_password))) {
				fetch.displayInvalid();
				return;
			}*/
			service.insertAdminDetails(adminObj);
			fetch.registerStatus();
			adminLogin();
		} else {
			fetch.displayInvalid();
		}
	}

	public void showOption() throws IOException {
		do {
		option = fetch.loginOption();
		switch (option) {
		case 1:
			UserMenu();
			break;
		case 2:
			AdminMenu();
			break;
		default:
			fetch.displayInvalid();
			break;
		}
		ch=fetch.getMainmenu();
		}while(ch=='y' || ch=='Y');
		
	}
}
