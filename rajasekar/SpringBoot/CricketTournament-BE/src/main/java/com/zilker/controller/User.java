package com.zilker.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Schedule;
import com.zilker.beans.Score;
import com.zilker.beans.Scorecard;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.UserDetails;
import com.zilker.dao.CricketTournamentDAO;
import com.zilker.delegates.DelegatesCrudOperations;

@RestController

@RequestMapping("/user")
public class User {
	@PostMapping(value = "/id")
	public Integer getUserId(@RequestBody Login info) {
		int userid = 0;
		;
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		Login credentials = new Login();
		try {
			credentials.setEmail(info.getEmail());
			userid = delegates.getUserId(credentials);
		} catch (Exception e) {
		}
		return userid;
	}

	@PostMapping(value = "/insert-tournament")
	public void insertTournament(@RequestBody Tournament tour) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		System.out.println("inside insert tour controller"+tour.getEmail()+" "+tour.getFormat()+" "+tour.getTournamentName()+" "+tour.getUserId()+" "+tour.getStatus());

		try {
			delegates.insertTournament(tour);
		} catch (Exception e) {
		}
	}

	@RequestMapping(value = "/show-tournament")
	public ArrayList displayTournament() {
		ArrayList tournamentlist = new ArrayList();
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			tournamentlist = delegates.showTournament();
		} catch (Exception e) {
		}
		return tournamentlist;
	}

	@PostMapping(value = "/update-tournament/{tour}")
	public void updateTournament(@PathVariable String tour) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			delegates.updateStatus(tour.toString());
		} catch (Exception e) {
		}
	}

	@PostMapping(value = "/reject-tournament/{tour}")
	public void rejectTournament(@PathVariable String tour) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			delegates.rejectTour(tour.toString());
		} catch (Exception e) {
		}
	}

	@RequestMapping(value = "/get-tour")
	public ArrayList getTournament() {
		ArrayList tournamentlist = new ArrayList();
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			tournamentlist = delegates.getTournament();
		} catch (Exception e) {
		}
		return tournamentlist;
	}

	@PostMapping(value = "/get-mobile/{userid}")
	public String getMobile(@PathVariable int userid) {
		String mobile = "";
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			mobile = delegates.getMobile(userid);
		} catch (Exception e) {
		}
		return mobile;
	}

	@PostMapping(value = "/user-tournament/{email}")
	public ArrayList displayUserTournament(@PathVariable String email) {
		ArrayList tournamentlist = new ArrayList();
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			tournamentlist = delegates.showTournament(email);
			System.out.println("tournamentlist in spring is:"+tournamentlist);
		} catch (Exception e) {
		}
		return tournamentlist;
	}

	@PostMapping(value = "/tour-id/{tournamentName}")
	public Integer getTournamentId(@PathVariable String tournamentName) {
		int tourid = 0;
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			tourid = delegates.getTourId(tournamentName);
		} catch (Exception e) {
		}
		return tourid;
	}

	@PostMapping(value = "/delete-fixture")
	public int deleteFixtures(@RequestBody Team tournamentId) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		int fixtureId = 0;
		try {
			fixtureId = delegates.deleteFixtures(tournamentId);
			System.out.println("fixture deleted successfully");
			System.out.println(fixtureId);
		} catch (Exception e) {

		}
		return fixtureId;
	}

	@PostMapping(value = "/team-id")
	public int[] deleteFixtures(@RequestBody String[] match) {
		int[] teamId = new int[2];
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			teamId = delegates.getTeamId(match);
			System.out.println("teamid1:"+teamId[0]);
			System.out.println("teamid2:"+teamId[1]);
		} catch (Exception e) {

		}
		return teamId;
	}

	@PostMapping(value = "/generate-fixtures")
	public void fixtures(@RequestBody Schedule obj) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			delegates.fixtures(obj);
		} catch (Exception e) {

		}
	}

	@PostMapping(value = "/view-schedule")
	public ArrayList viewSchedule(@RequestBody Team tournamentId) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		ArrayList schedule = new ArrayList();
		try {
			schedule = delegates.viewSchedule(tournamentId);
		} catch (Exception e) {
		}
		return schedule;
	}
	
	@PostMapping(value = "/view-teams")
	public ArrayList<String> viewTeams(@RequestBody Team tournamentId) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		ArrayList<String> teams = new ArrayList<String>();
		try {
			teams = delegates.viewTeams(tournamentId);
		} catch (Exception e) {
		}
		return teams;
	}

	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public String getTeamName(@RequestParam("tourid") int tourid, @RequestParam("email") String email)
			throws SQLException {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		String team = delegates.getTeamName(tourid, email);
		return team;
	}

	@PostMapping(value = "/teamdetails/{teamName}")
	public int getteamId(@PathVariable String teamName) {
		int teamid = 0;
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			teamid = delegates.getteamId(teamName);
		} catch (Exception e) {

		}
		return teamid;
	}

	@PostMapping(value = "/players/{teamid}")
	public ArrayList getplayers(@PathVariable int teamid) {
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		ArrayList players = new ArrayList();
		try {
			players = delegates.getplayers(teamid);
			System.out.println("players in backend:"+players);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return players;
	}
	
	@PostMapping(value = "/user-info")
	public void insertUserDetails(@RequestBody UserDetails userObj){
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		System.out.println("userobj:"+userObj);
		try {
			delegates.insertUserDetails(userObj);
		} catch (Exception e) {
			
		}
	}
	
	@RequestMapping(value = "/existinguser", method = RequestMethod.GET)
	public int isUserAlreadyRegistered(@RequestParam("tournamentid") int tournamentid, @RequestParam("email") String email) throws SQLException{
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		int flag=0;
		try {
			flag = delegates.isUserAlreadyRegistered(email,tournamentid);
		} catch (SQLException e) {
			throw e;
		}
		return flag;
	}
	
	@PostMapping(value = "/add-team/{userid}")
	public int addTeam(@RequestBody Team teamobj, @PathVariable int userid) throws SQLException {
		int teamid=0;
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			teamid = delegates.addTeam(teamobj,userid);
		} catch (SQLException e) {
			throw e;
		}
		return teamid;
	}
	
	@RequestMapping(value = "/add-player", method = RequestMethod.GET)
	public void addPlayer(@RequestParam("player") ArrayList<Player> player, @RequestParam("userid") int userid) throws SQLException {
		try {
			DelegatesCrudOperations delegates = new DelegatesCrudOperations();
			delegates.addPlayer(player,userid);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	@PostMapping(value = "/playerid/{teamid}")
	public int getPlayerId(@PathVariable int teamid) throws SQLException {
		int playerid=0;
		try {
			DelegatesCrudOperations delegates = new DelegatesCrudOperations();
			playerid=delegates.getPlayerId(teamid);
		} catch (SQLException e) {
			throw e;
		}
		return playerid;
	}
	
	@PostMapping(value = "/update-player/{userid}/{playerid}")
	public void updatePlayer(@PathVariable int userid,@PathVariable int playerid,@RequestBody ArrayList<Player> player) throws SQLException {
		try {
			System.out.println("user,playerid:"+userid+" "+playerid+" "+player);
			DelegatesCrudOperations delegates = new DelegatesCrudOperations();
			delegates.updatePlayer(player,userid,playerid);
		} catch (SQLException e) {
			throw e;
		}
	}
	@PostMapping(value = "/get-tournament/{email}")
	public ArrayList getUserTournament(@PathVariable String email) {
		ArrayList tournamentlist = new ArrayList();
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		try {
			tournamentlist = delegates.getTournament(email);
		} catch (Exception e) {
		}
		return tournamentlist;
	}
	@PostMapping(value = "/update-scorecard/{matchno}/{teamid}")
	public ArrayList updateScorecard(@PathVariable int matchno,@PathVariable int teamid,@RequestBody Score stats) {
		ArrayList tournamentlist = new ArrayList();
		DelegatesCrudOperations delegates = new DelegatesCrudOperations();
		System.out.println("matchno and team id::"+matchno+" "+teamid);
		try {
			delegates.updateScorecard(stats,matchno,teamid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tournamentlist;
	}
	
	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ArrayList getScore(@RequestParam("teamid") int teamid, @RequestParam("match") String match) throws SQLException{
		ArrayList tournamentlist = new ArrayList();
		try {
			DelegatesCrudOperations delegates = new DelegatesCrudOperations();
			 tournamentlist=delegates.getScore(teamid,match);
		}
		catch(SQLException e) {
			throw e;
		}
		return tournamentlist;
	}
}
