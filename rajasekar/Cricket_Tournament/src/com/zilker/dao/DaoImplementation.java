package com.zilker.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Schedule;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.UserDetails;
import com.zilker.constant.ConsoleStrings;
import com.zilker.constant.SqlQuery;
import com.zilker.dbutils.DbConnectivity;
import com.zilker.ui.FetchAndDisplay;

public class DaoImplementation {
	ResultSet rs = null;
	PreparedStatement ps = null;
	boolean flag = false;
	Connection myconn = null;
	int tournament_id = 0, increment_variable = 0, team_id = 0, total_teams = 0;
	int array[] = new int[2];
	char ch = ' ';
	String timeStamp = "";
	ArrayList<String> team_list = new ArrayList<String>();
	ArrayList hm = new ArrayList();
	boolean status=false;

	public boolean authenticateUser(Login loginObj) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.LOGIN);
			ps.setString(1, loginObj.getEmail());
			ps.setString(2, loginObj.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return flag;
	}

	public boolean authenticateAdmin(Login loginObj) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.ADMIN_LOGIN);
			ps.setString(1, loginObj.getEmail());
			ps.setString(2, loginObj.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return flag;
	}

	public ArrayList retrieveTournament() {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.VIEW_TOURNAMENT);
			rs = ps.executeQuery();
			while (rs.next()) {
				hm.add(rs.getInt(1));
				hm.add(rs.getString(2));
				hm.add(rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return hm;
	}

	public int insertTeam(Team obj) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_TEAM);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getTeamName());
			ps.setInt(2, obj.getTournamentId());
			ps.setString(3, timeStamp);
			ps.setString(4, timeStamp);
			ps.setString(5, obj.getEmail());
			ps.executeUpdate();
			ps = myconn.prepareStatement(SqlQuery.TEAM_ID);
			rs = ps.executeQuery();
			while (rs.next()) {
				team_id = rs.getInt(1);
			}
			if (team_id == 0) {
				System.out.println(ConsoleStrings.INVALID);
			}
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return team_id;
	}

	public void insertPlayer(ArrayList<Player> al) {
		try {
			myconn = DbConnectivity.getConnection();
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
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
	}

	public void insertUser(UserDetails obj) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_USER);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getFirst_name());
			ps.setString(2, obj.getLast_name());
			ps.setString(3, obj.getPassword());
			ps.setInt(4, obj.getAge());
			ps.setString(5, obj.getMobile());
			ps.setString(6, obj.getEmail());
			ps.setString(7, timeStamp);
			ps.setString(8, timeStamp);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
	}

	public void insertAdmin(AdminDetails obj) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_ADMIN);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getFirst_name());
			ps.setString(2, obj.getLast_name());
			ps.setString(3, obj.getPassword());
			ps.setInt(4, obj.getAge());
			ps.setString(5, obj.getMobile());
			ps.setString(6, obj.getEmail());
			ps.setString(7, timeStamp);
			ps.setString(8, timeStamp);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
	}

	public void insertTournament(Tournament obj) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_TOURNAMENT);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setString(1, obj.getTournamentName());
			ps.setString(2, obj.getFormat());
			ps.setString(3, timeStamp);
			ps.setString(4, timeStamp);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
	}

	public ArrayList<String> viewTeams(int tournament_id,String email) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.VIEW_TEAMS);
			ps.setInt(1, tournament_id);
			ps.setString(2,email);
			rs = ps.executeQuery();
			while (rs.next()) {
				team_list.add(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return team_list;
	}
	public ArrayList<String> viewTeams(int tournament_id) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.USER_TEAMS);
			ps.setInt(1, tournament_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				team_list.add(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return team_list;
	}
	public ArrayList<String> viewTeams(String email) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.TEAMS);
			ps.setString(1,email);
			rs = ps.executeQuery();
			while (rs.next()) {
				team_list.add(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return team_list;
	}

	public int[] getTeamId(String arr[]) {
		try {
			myconn = DbConnectivity.getConnection();
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
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return array;
	}

	public void insertSchedule(Schedule obj) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.INSERT_SCHEDULE);
			timeStamp = new SimpleDateFormat("yyyy/MM/dd,HH:mm:ss").format(new java.util.Date());
			ps.setInt(1, obj.getTeam1Id());
			ps.setInt(2, obj.getTeam2Id());
			ps.setString(3, obj.getMatches());
			ps.setInt(6, obj.getTournamentId());
			ps.setString(4, timeStamp);
			ps.setString(5, timeStamp);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
	}
	public int deleteFixedMatches(int tournament_id) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.DELETE_SCHEDULE);
			ps.setInt(1, tournament_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		} finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
		return 1;
	}
	public int checkTeamName(Team obj,int tournament_id) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.TEAM_NAME_VERIFICATION);
			ps.setInt(1, tournament_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals(obj.getTeamName())) {
					return 0;
				}
			}
		}
		catch(SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		}
		finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return 1;
	}
	public int updateTeamName(String[] team_name,int tournament_id) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.CHECK_TEAM_NAME);
			ps.setString(1,team_name[0]);
			rs=ps.executeQuery();
			while(rs.next()) {
				status=true;
				ps = myconn.prepareStatement(SqlQuery.UPDATE_TEAM_NAME);
				ps.setString(1,team_name[1]);
				ps.setString(2,team_name[0]);
				ps.setInt(3,tournament_id);
				ps.executeUpdate();
			}
			if(status==false)
				return 0;
		}
		catch(SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		}
		finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return 1;
	}
	public ArrayList showPlayer(String team_name) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TEAM_ID);
			ps.setString(1,team_name);
			rs=ps.executeQuery();
			hm.clear();
			while(rs.next()) {
				int team_id=rs.getInt(1);
				ps = myconn.prepareStatement(SqlQuery.PLAYER_DETAILS);
				ps.setInt(1,team_id);
				rs=ps.executeQuery();
				while(rs.next()) {
					hm.add(rs.getInt(1));
					hm.add(rs.getString(2));
				}
				break;
			}
		}
		catch(SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		}
		finally {
			DbConnectivity.closeConnection(myconn, ps, rs);
		}
		return hm;
	}
	public void updatePlayer(ArrayList<String> player,int player_id) {
		try {
			myconn = DbConnectivity.getConnection();
			ps = myconn.prepareStatement(SqlQuery.GET_TEAM_ID);
			ps.setString(1,player.get(0));
			rs=ps.executeQuery();
			while(rs.next()) {
				int team_id=rs.getInt(1);
				for(int i=1;i<player.size();i+=2) {
					ps = myconn.prepareStatement(SqlQuery.UPDATE_PLAYER);
					ps.setString(1,player.get(i));
					ps.setString(2,player.get(i+1));
					ps.setInt(3,team_id);
					ps.setInt(4,player_id);
					ps.executeUpdate();
				}
			}
		}
		catch(SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		}
		finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
	}
	public ArrayList viewSchedule(int tournament_id) {
		try {
			myconn = DbConnectivity.getConnection();
			hm.clear();
			ps = myconn.prepareStatement(SqlQuery.VIEW_SCHEDULE);
			ps.setInt(1,tournament_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				hm.add(rs.getInt(1));
				hm.add(rs.getInt(2));
				hm.add(rs.getInt(3));
				hm.add(rs.getString(4));
				hm.add(rs.getInt(5));
			}
		}
		catch(SQLException e) {
			System.out.println(ConsoleStrings.DB_ERROR);
		}
		finally {
			DbConnectivity.closeConnection(myconn, ps, null);
		}
		return hm;
	}
}
