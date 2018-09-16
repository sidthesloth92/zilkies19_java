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
import com.zilker.ui.FetchAndDisplay;

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

	public int insertTeam(Team obj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_TEAM);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getTeamName());
			ps.setInt(2, obj.getTournamentId());
			ps.setString(3, timeStamp);
			ps.setString(4, timeStamp);
			ps.setString(5, obj.getEmail());
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

	public void insertPlayer(ArrayList<Player> al) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			for (int i = 0; i < al.size(); i++) {
				ps = myconn.prepareStatement(SqlQuery.INSERT_PLAYER);
				timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
				ps.setString(1, al.get(i).getPlayerName());
				ps.setString(2, al.get(i).getPlayerRole());
				ps.setInt(3, al.get(i).getTeamId());
				ps.setString(4, timeStamp);
				ps.setString(5, timeStamp);
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
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
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

	public void insertTournament(Tournament obj) throws SQLException {
		try {
			myconn = DbConnection.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_TOURNAMENT);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getTournamentName());
			ps.setString(2, obj.getFormat());
			ps.setString(3, timeStamp);
			ps.setString(4, timeStamp);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(myconn, ps, null);
		}
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
			rs=ps.executeQuery();
			int matchNo=0;
			while(rs.next()) {
				matchNo=rs.getInt(1);
			}
			for(int i=0;i<2;i++) {
			ps = myconn.prepareStatement(SqlQuery.INSERT_SCORECARD);
			ps.setInt(1, matchNo);
			if(i==0)
			ps.setInt(2,obj.getTeamOneId());
			else
			ps.setInt(2,obj.getTeamTwoId());
			ps.setInt(3,obj.getTournamentId());
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
