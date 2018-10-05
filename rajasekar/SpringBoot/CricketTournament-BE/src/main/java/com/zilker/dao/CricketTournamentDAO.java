package com.zilker.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Schedule;
import com.zilker.beans.Scorecard;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.Score;
import com.zilker.beans.UserDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.constant.SqlQuery;
import com.zilker.dbutils.DbConnection;

public class CricketTournamentDAO {

	Logger logger = Logger.getLogger(CricketTournamentDAO.class.getName());
	ResultSet rs;
	PreparedStatement ps;
	boolean flag;
	Connection myconn;
	int tournamentId, incrementVariable, teamId, totalTeams, delStatus;
	int array[] = new int[2];
	char ch;
	String timeStamp;
	ArrayList<String> teamList = new ArrayList<String>();
	ArrayList hm = new ArrayList();
	boolean status;

	public CricketTournamentDAO() {
		rs = null;
		ps = null;
		flag = false;
		myconn = null;
		tournamentId = 0;
		incrementVariable = 0;
		teamId = 0;
		totalTeams = 0;
		delStatus = 0;
		ch = ' ';
		timeStamp = "";
		status = false;
	}

	public boolean authenticateUser(Login loginObj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.LOGIN);
			ps.setString(1, loginObj.getEmail());
			ps.setString(2, loginObj.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return flag;
	}

	public String getUserName(Login credentials) throws SQLException {
		String user = "";
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.USER);
			ps.setString(1, credentials.getEmail());
			ps.setString(2, credentials.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				user = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return user;
	}

	public int getUserId(Login credentials) throws SQLException {
		int userId = 0;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.USER_ID);
			ps.setString(1, credentials.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return userId;
	}

	public ArrayList getTournament() throws SQLException {
		ArrayList tournamentDetails = new ArrayList();
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TOUR);
			rs = ps.executeQuery();
			while (rs.next()) {
				tournamentDetails.add(rs.getInt(1));
				tournamentDetails.add(rs.getString(2));
				tournamentDetails.add(rs.getString(3));
				tournamentDetails.add(rs.getInt(4));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return tournamentDetails;
	}
	
	public ArrayList getTournament(String email) throws SQLException {
		ArrayList tournamentDetails = new ArrayList();
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TOUR_USER);
			ps.setString(1,email);
			rs = ps.executeQuery();
			while (rs.next()) {
				tournamentDetails.add(rs.getInt(1));
				tournamentDetails.add(rs.getString(2));
				tournamentDetails.add(rs.getString(3));
				tournamentDetails.add(rs.getInt(4));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return tournamentDetails;
	}
	
	public void updateStatus(String tour) throws SQLException{
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.UPDATE_TOUR_STATUS);
			ps.setString(1,tour);
			ps.executeUpdate();
		}
		catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}

	}
	
	public void rejectTour(String tour) throws SQLException{
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.REJECT_TOUR);
			ps.setString(1,tour);
			ps.executeUpdate();
		}
		catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}

	}

	public String getMobile(int userid) throws SQLException {
		String mobile="";
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_MOBILE);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			while(rs.next()) {
				mobile=rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return mobile;
	}

	public void insertTournament(Tournament tour) throws SQLException {
		try {
			System.out.println("inside insert tour dao"+tour.getEmail()+" "+tour.getFormat()+" "+tour.getTournamentName()+" "+tour.getUserId()+" "+tour.getStatus());
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_TOURNAMENT_STATUS);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, tour.getTournamentName());
			ps.setString(2, tour.getFormat());
			ps.setString(3, timeStamp);
			ps.setString(4, timeStamp);
			ps.setInt(5, tour.getStatus());
			ps.setString(6, tour.getEmail());
			ps.setInt(7, tour.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
	}

	public boolean authenticateAdmin(Login loginObj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.ADMIN_LOGIN);
			ps.setString(1, loginObj.getEmail());
			ps.setString(2, loginObj.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return flag;
	}

	public ArrayList retrieveTournament() throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.VIEW_TOURNAMENT);
			rs = ps.executeQuery();
			while (rs.next()) {
				hm.add(rs.getInt(1));
				hm.add(rs.getString(2));
				hm.add(rs.getString(3));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return hm;
	}
	
	public int isUserAlreadyRegistered(String email,int tournamentid) throws SQLException{
		int flag=0;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.CHECK_TEAM);
			ps.setString(1,email);
			ps.setInt(2,tournamentid);
			rs = ps.executeQuery();
			while (rs.next()) {
				flag=1;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return flag;
	}

	public ArrayList retrieveTournament(String email) throws SQLException {
		ArrayList hm=new ArrayList();
		try {
			System.out.println("email is:"+email);
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.VIEW_TOURNAMENT_USER);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				hm.add(rs.getInt(1));
				hm.add(rs.getString(2));
				hm.add(rs.getString(3));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		//System.out.println("Arraylist in dao"+hm.toString());
		return hm;
	}

	public String getTeamName(int tourid, String email) throws SQLException {
		String team = "";
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TEAM);
			ps.setInt(1, tourid);
			ps.setString(2, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				team = rs.getString(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return team;
	}

	public int insertTeam(Team obj, int userid) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_TEAM);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getTeamName());
			ps.setInt(2, obj.getTournamentId());
			ps.setString(3, timeStamp);
			ps.setString(4, timeStamp);
			ps.setString(5, obj.getEmail());
			ps.setInt(6, userid);
			ps.executeUpdate();
			ps = myconn.prepareStatement(SqlQuery.TEAMID);
			rs = ps.executeQuery();
			while (rs.next()) {
				teamId = rs.getInt(1);
			}
			if (teamId == 0) {
				logger.info(ConsoleStrings.INVALID);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return teamId;
	}

	public void insertPlayer(ArrayList<Player> al, int userid) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			for (int i = 0; i < al.size(); i++) {
				ps = myconn.prepareStatement(SqlQuery.INSERT_PLAYER);
				timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
				ps.setString(1, al.get(i).getPlayerName());
				ps.setString(2, al.get(i).getPlayerLastName());
				ps.setString(3, al.get(i).getPlayerRole());
				ps.setInt(4, al.get(i).getTeamId());
				ps.setString(5, timeStamp);
				ps.setString(6, timeStamp);
				ps.setInt(7, userid);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public int getPlayerId(int teamid) throws SQLException {
		int playerid = 0;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_PLAYER_ID);
			ps.setInt(1, teamid);
			rs = ps.executeQuery();
			while (rs.next()) {
				playerid = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return playerid;
	}

	public void updatePlayer(ArrayList<Player> al, int userid, int playerid) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			for (int i = 0; i < al.size(); i++) {
				ps = myconn.prepareStatement(SqlQuery.UPDATE_PLAYER_DETAILS);
				timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
				ps.setString(1, al.get(i).getPlayerName());
				ps.setString(2, al.get(i).getPlayerLastName());
				ps.setString(3, al.get(i).getPlayerRole());
				ps.setString(4, timeStamp);
				ps.setInt(5, al.get(i).getTeamId());
				ps.setInt(6, userid);
				ps.setInt(7, playerid);
				playerid++;
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public void insertUser(UserDetails obj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_USER);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getfirstName());
			ps.setString(2, obj.getlastName());
			ps.setString(3, obj.getPassword());
			ps.setInt(4, obj.getAge());
			ps.setString(5, obj.getMobile());
			ps.setString(6, obj.getEmail());
			ps.setString(7, timeStamp);
			ps.setString(8, timeStamp);
			ps.setString(9, obj.getRole());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public boolean getRole(Login credentials) throws SQLException {
		boolean roleStatus = false;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_ROLE);
			ps.setString(1, credentials.getEmail());
			ps.setString(2, credentials.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals("user")) {
					roleStatus = true;
				} else {
					roleStatus = false;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return roleStatus;
	}

	public ArrayList getScore(int teamId, String match) throws SQLException {
		ArrayList scoreDetails = new ArrayList();
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_SCORE);
			ps.setInt(1, teamId);
			ps.setInt(2, Integer.parseInt(match));
			rs = ps.executeQuery();
			while (rs.next()) {
				scoreDetails.add(rs.getInt(1));
				scoreDetails.add(rs.getString(2));
				scoreDetails.add(rs.getInt(3));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return scoreDetails;
	}

	public ArrayList getplayers(int teamid) throws SQLException {
		ArrayList players = new ArrayList();
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_PLAYERS);
			ps.setInt(1, teamid);
			rs = ps.executeQuery();
			while (rs.next()) {
				players.add(rs.getString(1));
				players.add(rs.getString(2));
				players.add(rs.getString(3));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return players;
	}

	public int getteamId(String teamName) throws SQLException {
		int teamId = 0;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TEAM_ID);
			ps.setString(1, teamName);
			rs = ps.executeQuery();
			while (rs.next()) {
				teamId = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return teamId;
	}

	public void insertAdmin(AdminDetails obj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_ADMIN);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getfirstName());
			ps.setString(2, obj.getlastName());
			ps.setString(3, obj.getPassword());
			ps.setInt(4, obj.getAge());
			ps.setString(5, obj.getMobile());
			ps.setString(6, obj.getEmail());
			ps.setString(7, timeStamp);
			ps.setString(8, timeStamp);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public void insertTournament(Tournament obj, Login loginobj) throws SQLException {
		int status = 0;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_TOURNAMENT);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getTournamentName());
			ps.setString(2, obj.getFormat());
			ps.setString(3, timeStamp);
			ps.setString(4, timeStamp);
			ps.setInt(5, status);
			ps.setString(6, loginobj.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public ArrayList viewRequestStatus(Login credentials) throws SQLException {
		ArrayList approvalStatus = new ArrayList();
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.VIEW_REQUEST);
			ps.setString(1, credentials.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				approvalStatus.add(rs.getInt(1));
				approvalStatus.add(rs.getString(2));
				approvalStatus.add(rs.getInt(3));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return approvalStatus;
	}

	public void changeRequestStatus(Team team) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.UPDATE_STATUS);
			ps.setInt(1, team.getTournamentId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public void removeDeclineTournament(Team team) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.REMOVE_REQUEST);
			ps.setInt(1, team.getTournamentId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public ArrayList viewRequestStatus() throws SQLException {
		ArrayList approvalStatus = new ArrayList();
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.VIEW_REQUEST_ADMIN);
			rs = ps.executeQuery();
			while (rs.next()) {
				approvalStatus.add(rs.getInt(1));
				approvalStatus.add(rs.getString(2));
				approvalStatus.add(rs.getInt(3));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return approvalStatus;
	}

	public int getTourId(String tournamentName) throws SQLException {
		int tournamentId = 0;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.TOURNAMENT_ID);
			ps.setString(1, tournamentName);
			rs = ps.executeQuery();
			while (rs.next()) {
				tournamentId = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return tournamentId;
	}

	public boolean isValidEmail(Login credentials) throws SQLException {
		boolean validate = false;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.EXISTING_USER);
			ps.setString(1, credentials.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				validate = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return validate;
	}

	public boolean isValidCredentials(Login credentials) throws SQLException {
		boolean validate = false;
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.EXISTING_USER_CREDENTIALS);
			ps.setString(1, credentials.getEmail());
			ps.setString(2, credentials.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				validate = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return validate;
	}

	public ArrayList<String> viewTeams(Team object, Login login) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.VIEW_TEAMS);
			ps.setInt(1, object.getTournamentId());
			ps.setString(2, login.getEmail());
			rs = ps.executeQuery();
			while (rs.next()) {
				teamList.add(rs.getString(2));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return teamList;
	}

	public ArrayList<String> viewTeams(Team teamobject) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			teamList.clear();
			ps = myconn.prepareStatement(SqlQuery.USER_TEAMS);
			ps.setInt(1, teamobject.getTournamentId());
			rs = ps.executeQuery();
			while (rs.next()) {
				teamList.add(rs.getString(2));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return teamList;
	}

	public int[] getTeamId(String arr[]) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			for (int i = 0; i < arr.length; i++) {
				ps = myconn.prepareStatement(SqlQuery.VIEW_TEAMID);
				arr[i] = arr[i].trim();
				ps.setString(1, arr[i]);
				rs = ps.executeQuery();
				while (rs.next()) {
					array[i] = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return array;
	}

	public void insertSchedule(Schedule obj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_SCHEDULE);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setInt(1, obj.getTeamOneId());
			ps.setInt(2, obj.getTeamTwoId());
			ps.setString(3, obj.getMatches());
			ps.setInt(6, obj.getTournamentId());
			ps.setString(4, timeStamp);
			ps.setString(5, timeStamp);
			ps.executeUpdate();
			ps = myconn.prepareStatement(SqlQuery.MATCH_NO);
			rs = ps.executeQuery();
			int matchNo = 0;
			while (rs.next()) {
				matchNo = rs.getInt(1);
			}
			for (int i = 0; i < 2; i++) {
				ps = myconn.prepareStatement(SqlQuery.INSERT_SCORECARD);
				ps.setInt(1, matchNo);
				if (i == 0)
					ps.setInt(2, obj.getTeamOneId());
				else
					ps.setInt(2, obj.getTeamTwoId());
				ps.setInt(3, obj.getTournamentId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
	}

	public int deleteFixedMatches(Team teamobject) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.DELETE_SCHEDULE);
			ps.setInt(1, teamobject.getTournamentId());
			ps.executeUpdate();
			ps = myconn.prepareStatement(SqlQuery.DELETE_SCORECARD);
			ps.setInt(1, teamobject.getTournamentId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return 1;
	}

	public int checkTeamName(Team obj, Team object) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.TEAMNAME_VERIFICATION);
			ps.setInt(1, object.getTournamentId());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString(1).equals(obj.getTeamName())) {
					return 0;
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return 1;
	}

	public int updateTeamName(String[] teamName, Team teamobject) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.CHECK_TEAMNAME);
			ps.setString(1, teamName[0]);
			rs = ps.executeQuery();
			while (rs.next()) {
				status = true;
				ps = myconn.prepareStatement(SqlQuery.UPDATE_teamName);
				ps.setString(1, teamName[1]);
				ps.setString(2, teamName[0]);
				ps.setInt(3, teamobject.getTournamentId());
				ps.executeUpdate();
			}
			if (status == false)
				return 0;
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return 1;
	}

	public ArrayList showPlayer(Team teamName) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TEAMID);
			ps.setString(1, teamName.getTeamName());
			rs = ps.executeQuery();
			hm.clear();
			while (rs.next()) {
				int teamId = rs.getInt(1);
				ps = myconn.prepareStatement(SqlQuery.PLAYER_DETAILS);
				ps.setInt(1, teamId);
				rs = ps.executeQuery();
				while (rs.next()) {
					hm.add(rs.getInt(1));
					hm.add(rs.getString(2));
				}
				break;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return hm;
	}

	public void updatePlayer(ArrayList<String> player, Player playerobject) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TEAMID);
			ps.setString(1, player.get(0));
			rs = ps.executeQuery();
			while (rs.next()) {
				int teamId = rs.getInt(1);
				for (int i = 1; i < player.size(); i += 2) {
					ps = myconn.prepareStatement(SqlQuery.UPDATE_PLAYER);
					ps.setString(1, player.get(i));
					ps.setString(2, player.get(i + 1));
					ps.setInt(3, teamId);
					ps.setInt(4, playerobject.getPlayerId());
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public ArrayList viewSchedule(Team teamobject) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			hm.clear();
			ps = myconn.prepareStatement(SqlQuery.VIEW_SCHEDULE);
			ps.setInt(1, teamobject.getTournamentId());
			rs = ps.executeQuery();
			while (rs.next()) {
				hm.add(rs.getInt(1));
				hm.add(rs.getInt(2));
				hm.add(rs.getInt(3));
				hm.add(rs.getString(4));
				hm.add(rs.getInt(5));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return hm;
	}

	public void insertScorecard(ArrayList<Scorecard> al) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			for (int i = 0; i < al.size(); i++) {
				ps = myconn.prepareStatement(SqlQuery.INSERT_SCORECARD);
				ps.setInt(1, al.get(i).getmatchNo());
				ps.setInt(2, al.get(i).getTeamId());
				ps.setInt(3, al.get(i).getTournamentId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public void updateScore(int[] info, Score obj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.UPDATE_SCORECARD);
			String str = String.format("%.1f", obj.getOvers());
			ps.setInt(1, obj.getRuns());
			ps.setString(2, str);
			ps.setInt(3, obj.getWickets());
			ps.setInt(4, info[1]);
			ps.setInt(5, info[0]);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
	}

	public ArrayList viewScorecard(Scorecard score) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			hm.clear();
			ps = myconn.prepareStatement(SqlQuery.VIEW_SCORECARD);
			ps.setInt(1, score.getmatchNo());
			rs = ps.executeQuery();
			while (rs.next()) {
				hm.add(rs.getInt(1));
				hm.add(rs.getInt(2));
				hm.add(rs.getInt(3));
				hm.add(rs.getDouble(4));
				hm.add(rs.getInt(5));
				hm.add(rs.getInt(6));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
		return hm;
	}

	public void updateScorecard(Score stats, int matchno,int teamid) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.UPDATE_SCORE);
			ps.setInt(1, stats.getRuns());
			ps.setString(2, String.valueOf(stats.getOvers()));
			ps.setInt(3, stats.getWickets());
			ps.setInt(4, teamid);
			ps.setInt(5, matchno);
			ps.executeUpdate();
		} catch (SQLException e) {

		} finally {
			DbConnection.closeConnection(myconn, ps, rs);
		}
	}

	public int deleteTournament(Team tour) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.DELETE_TOURNAMENT);
			ps.setInt(1, tour.getTournamentId());
			delStatus = ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
		return delStatus;
	}
}
