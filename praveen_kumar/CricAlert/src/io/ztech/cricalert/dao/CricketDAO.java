package io.ztech.cricalert.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import io.ztech.cricalert.beans.BallStats;
import io.ztech.cricalert.beans.LineUp;
import io.ztech.cricalert.beans.Match;
import io.ztech.cricalert.beans.MatchStats;
import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.PlayerStats;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.constants.MatchResult;
import io.ztech.cricalert.constants.Queries;
import io.ztech.cricalert.dbutils.Connector;
import io.ztech.cricketapp.ui.UserEntry;

public class CricketDAO {
	Connector connector;
	Logger logger;
	
	public CricketDAO() {
		connector = new Connector();
		logger = Logger.getLogger(CricketDAO.class.getName());
		System.setProperty("java.util.logging.SimpleFormatter.format","%5$s%6$s%n");
	}
	
	public void insertUser(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.INSERT_USER);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			ps.execute();
			ps = con.prepareStatement(Queries.FETCH_USER_ID);
			ps.setString(1, user.getUserName());
			rs = ps.executeQuery();
			rs.next();
			user.setUserId(rs.getInt(1));
		} catch (SQLException e) {
			logger.info("Exception caught at insertUser(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public void insertPlayer(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ArrayList<Player> playerList = user.getPlayers();
			Player player = playerList.get(playerList.size() - 1);
			ps = con.prepareStatement(Queries.INSERT_PLAYER);
			ps.setInt(1, player.getTeamId());
			ps.setString(2, player.getFirstName());
			ps.setString(3, player.getLastName());
			ps.setInt(4, player.getUser().getUserId());
			ps.execute();
			player.setPlayerId(getRecentPlayerId());
		} catch (SQLException e) {
			logger.info("Exception caught at insertPlayer(User): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	/*public void insertPlayer(Player player) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_PLAYER);
			ps.setInt(1, player.getTeamId());
			ps.setString(2, player.getFirstName());
			ps.setString(3, player.getLastName());
			ps.setInt(4, player.getUser().getUserId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at insertPlayer(Player): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}*/
	
	public void insertTeam(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ArrayList<Team> teamList = user.getTeams();
			Team team = teamList.get(teamList.size() - 1);
			ps = con.prepareStatement(Queries.INSERT_TEAM);
			ps.setString(1, team.getTeamName());
			ps.setString(2, team.getAbbreviation());
			ps.setInt(3, team.getUser().getUserId());
			ps.execute();
			team.setTeamId(getRecentTeamId());
			for (Player player : team.getPlayers()) {
				player.setTeamId(getRecentTeamId());
			}
			updateTeamPlayers(team.getPlayers());
		} catch (SQLException e) {
			logger.info("Exception caught at insertTeam(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertMatch(Match match) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_MATCH);
			ps.setTimestamp(1, match.getMatchDatetime());
			ps.setString(2, match.getVenue());
			ps.setInt(3, match.getTeamA().getTeamId());
			ps.setInt(4, match.getTeamB().getTeamId());
			ps.setString(5, match.getStatus());
			ps.setInt(6, match.getUser().getUserId());
			ps.execute();
			int recentMatchId = getRecentMatchId();
			match.getTeamALineUp().setMatchId(recentMatchId);
			match.getTeamALineUp().setTeamId(match.getTeamA().getTeamId());
			match.getTeamBLineUp().setMatchId(recentMatchId);
			match.getTeamBLineUp().setTeamId(match.getTeamB().getTeamId());
			insertLineUp(match.getTeamALineUp());
			insertLineUp(match.getTeamBLineUp());
		} catch (SQLException e) {
			logger.info("Exception caught at insertMatch(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertBallStats(BallStats ballStats) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.INSERT_BALL_STATS);
			ps.setInt(1, ballStats.getMatchId());
			ps.setInt(2, ballStats.getTeamBatting());
			ps.setInt(3, ballStats.getTeamBowling());
			ps.setInt(4, ballStats.getBowlerId());
			ps.setInt(5, ballStats.getBatmsanId());
			ps.setInt(6, ballStats.getRunsGiven());
			ps.setBoolean(7, ballStats.getWicketTaken());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at insertBallStats(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void insertLineUp(LineUp lineUp) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			for (Integer id : lineUp.getPlayerId()) {
				ps = con.prepareStatement(Queries.INSERT_LINE_UP);
				ps.setInt(1, lineUp.getMatchId());
				ps.setInt(2, lineUp.getTeamId());
				ps.setInt(3, id);
				ps.execute();
			}
		} catch (SQLException e) {
			logger.info("Exception caught at insertLineUp(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public int getRecentPlayerId() {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		int playerId = 0;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_RECENT_PLAYER_ID);
			rs = ps.executeQuery();
			rs.next();
			playerId = rs.getInt(1);
		} catch (SQLException e) {
			logger.info("Exception caught at getRecentPlayerId(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
		return playerId;
	}
	
	public int getRecentTeamId() {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		int teamId = 0;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_RECENT_TEAM_ID);
			rs = ps.executeQuery();
			rs.next();
			teamId = rs.getInt(1);
		} catch (SQLException e) {
			logger.info("Exception caught at getRecentTeamId(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
		return teamId;
	}
	
	public int getRecentMatchId() {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		int matchId = 0;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_RECENT_MATCH_ID);
			rs = ps.executeQuery();
			rs.next();
			matchId = rs.getInt(1);
		} catch (SQLException e) {
			logger.info("Exception caught at getRecentMatchId(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
		return matchId;
	}
	
	public void updateTeamName(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_TEAM_NAME);
			ps.setString(1, team.getTeamName());
			ps.setInt(2, team.getTeamId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at updateTeamName(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void updateTeamPlayers(ArrayList<Player> playerList) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			for (Player player : playerList) {
				ps = con.prepareStatement(Queries.UPDATE_PLAYER_TEAM);
				ps.setInt(1, player.getTeamId());
				ps.setInt(2, player.getPlayerId());
				ps.execute();
			}
		} catch (SQLException e) {
			logger.info("Exception caught at updateTeamPlayers(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	/*
	public void updatePlayerName(Player player, String query) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(query);
			if (query.equals(Queries.UPDATE_PLAYER_FIRST_NAME)) {
				ps.setString(1, player.getFirstName());
			} else {
				ps.setString(1, player.getLastName());
			}
			ps.setInt(2, player.getPlayerId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at updatePlayerName(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}*/
	
	public void updatePlayer(Player player) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_PLAYER);
			ps.setString(1, player.getFirstName());
			ps.setString(2, player.getLastName());
			ps.setInt(3, player.getTeamId());
			ps.setInt(4, player.getPlayerId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at updatePlayer(): " + e);
		} finally {
			connector.closeConnection(con, null, ps);
		}
	}
	
	public void updateMatchDate(Match match) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_MATCH_DATE);
			ps.setTimestamp(1, match.getMatchDatetime());
			ps.setInt(2, match.getMatchId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Exception caught at updateMatchDate(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public void updateTeam(Match match, String team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			if (team.equals("A")) {
				ps = con.prepareStatement(Queries.UPDATE_MATCH_TEAM_A);
				ps.setInt(1, match.getTeamA().getTeamId());
			} else {
				ps = con.prepareStatement(Queries.UPDATE_MATCH_TEAM_B);
				ps.setInt(1, match.getTeamB().getTeamId());
			}
			ps.setInt(2, match.getMatchId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.info("Exception caught at updateTeam(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public void updateMatchStatus(Match match) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.UPDATE_MATCH_STATUS);
			ps.setString(1, match.getStatus());
			ps.setInt(2, match.getMatchId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at updateMatchStatus(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public void updateMatchStats(MatchStats matchStats) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_MATCH_STATS);
			ps.setInt(1, matchStats.getMatchId());
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = con.prepareStatement(Queries.INSERT_MATCH_STATS);
			} else {
				ps = con.prepareStatement(Queries.UPDATE_MATCH_STATS);
			}
			ps.setInt(1, matchStats.getBattingTeam().getTeamId());
			ps.setInt(2, matchStats.getBowlingTeam().getTeamId());
			ps.setInt(3, matchStats.getTeamAscore());
			ps.setInt(4, matchStats.getTeamBscore());
			ps.setInt(5, matchStats.getTeamAwickets());
			ps.setInt(6, matchStats.getTeamBwickets());
			ps.setFloat(7, matchStats.getOvers());
			ps.setInt(8, matchStats.getOnstrike().getPlayerId());
			ps.setInt(9, matchStats.getOffstrike().getPlayerId());
			ps.setInt(10, matchStats.getBowler().getPlayerId());
			ps.setInt(11, matchStats.getInning());
			ps.setInt(12, matchStats.getMatchId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at updateMatchStats(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public void updatePlayerStats(PlayerStats playerStats) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.CHECK_PLAYER_STATS);
			ps.setInt(1, playerStats.getMatchId());
			ps.setInt(2, playerStats.getPlayerId());
			rs = ps.executeQuery();
			if (!rs.next()) {
				ps = con.prepareStatement(Queries.INSERT_PLAYER_STATS);
				ps.setInt(14, playerStats.getTeamId());
			} else {
				logger.info("EXECUTING UPDATE PLAYER STATS!");
				ps = con.prepareStatement(Queries.UPDATE_PLAYER_STATS);
			}
			ps.setInt(1, playerStats.getRunsScored());
			ps.setInt(2, playerStats.getBallsFaced());
			ps.setInt(3, playerStats.getWicketsTaken());
			ps.setInt(4, playerStats.getRunsGiven());
			ps.setFloat(5, playerStats.getOvers());
			ps.setBoolean(6, playerStats.getBatFlag());
			ps.setBoolean(7, playerStats.getBowlFlag());
			ps.setInt(8, playerStats.getFours());
			ps.setInt(9, playerStats.getSixes());
			ps.setFloat(10, playerStats.getEconomy());
			ps.setFloat(11, playerStats.getStrikeRate());
			ps.setInt(12, playerStats.getMatchId());
			ps.setInt(13, playerStats.getPlayerId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at updatePlayerStats(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public ArrayList<Player> fetchTeamPlayers(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Player> playerList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_TEAM_PLAYERS);
			ps.setInt(1, team.getTeamId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Player newPlayer = new Player();
				newPlayer.setTeamId(rs.getInt("team_id"));
				newPlayer.setPlayerId(rs.getInt("player_id"));
				newPlayer.setFirstName(rs.getString("first_name"));
				newPlayer.setLastName(rs.getString("last_name"));
				playerList.add(newPlayer);
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchTeamPlayers(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return playerList;
	}
	
	public User fetchUser(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_USER);
			ps.setString(1, user.getUserName());
			rs = ps.executeQuery();
			int flag = 0;
			while (rs.next()) {
				flag++;
				String password = rs.getString("password");
				if (user.getPassword().equals(password)) {
					user.setName(rs.getString("name"));
					user.setEmail(rs.getString("email"));
					user.setUserId(rs.getInt("user_id"));
					user.setPlayers(fetchPlayers(user));
					user.setTeams(fetchTeams(user));
					user.setMatches(fetchMatches(user));
				} else {
					user = null;
				}
			}
			if (flag == 0) {
				user = null;
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchUser(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return user;
	}

	public ArrayList<Player> fetchPlayers(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Player> playerList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_USER_PLAYERS);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Player player = new Player();
				player.setPlayerId(rs.getInt(1));
				player.setFirstName(rs.getString(2));
				player.setLastName(rs.getString(3));
				player.setTeamId(rs.getInt(4));
				playerList.add(player);
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchPlayers(User): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return playerList;
	}
	
	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Player> playerList = new ArrayList<>();
		
		try {
			for (Integer playerId : players) {
				Player player = new Player();
				ps = con.prepareStatement(Queries.FETCH_PLAYERS);
				ps.setInt(1, playerId);
				rs = ps.executeQuery();
				while (rs.next()) {
					player.setPlayerId(rs.getInt(1));
					player.setFirstName(rs.getString(2));
					player.setLastName(rs.getString(3));
					playerList.add(player);
				}
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchPlayers(ArrayList): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return playerList;
	}
	
	public Player fetchPlayer(int playerId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		Player player = new Player();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_PLAYER);
			ps.setInt(1, playerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				player.setPlayerId(playerId);
				player.setFirstName(rs.getString(1));
				player.setLastName(rs.getString(2));
				player.setTeamId(rs.getInt(3));
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchPlayer(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return player;
	}

	public Team fetchTeam(int teamId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		Team team = new Team();
		try {
			ps = con.prepareStatement(Queries.FETCH_TEAM);
			ps.setInt(1, teamId);
			rs = ps.executeQuery();
			while (rs.next()) {
				team.setTeamId(rs.getInt("team_id"));
				team.setAbbreviation(rs.getString("abbreviation"));
				team.setTeamName(rs.getString("team_name"));
				team.setPlayers(fetchTeamPlayers(team));
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchTeam(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return team;
	}
	
	public ArrayList<Team> fetchTeams(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Team> teamList = new ArrayList<>();

		try {
			ps = con.prepareStatement(Queries.FETCH_TEAMS);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Team newTeam = new Team();
				newTeam.setTeamId(rs.getInt("team_id"));
				newTeam.setAbbreviation(rs.getString("abbreviation"));
				newTeam.setTeamName(rs.getString("team_name"));
				newTeam.setPlayers(fetchTeamPlayers(newTeam));
				teamList.add(newTeam);
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchTeams(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return teamList;
	}
	
	public Match fetchMatch(int matchId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		Match match = null;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_MATCH);
			ps.setInt(1, matchId);
			rs = ps.executeQuery();
			match = new Match();
			while (rs.next()) {
				match.setMatchId(rs.getInt(1));
				match.setMatchDatetime(rs.getTimestamp(2));
				match.setVenue(rs.getString(3));
				
				Team teamA = new Team();
				teamA.setTeamId(rs.getInt(4));
				teamA = fetchTeam(teamA.getTeamId());
				match.setTeamA(teamA);
				
				Team teamB = new Team();
				teamB.setTeamId(rs.getInt(5));
				teamB = fetchTeam(teamB.getTeamId());
				match.setTeamB(teamB);
				match.setStatus(rs.getString(6));
				match.setTossWonBy(rs.getInt(7));
				if (rs.getInt(8) == 0) {
					match.setMatchResult(null);
				} else {
					match.setMatchResult(MatchResult.values()[rs.getInt(7)-1]);
				}
				match.setTeamALineUp( fetchLineUp(match.getMatchId(), match.getTeamA().getTeamId()) );
				match.setTeamBLineUp( fetchLineUp(match.getMatchId(), match.getTeamB().getTeamId()) );
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchMatch(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return match;
	}
	
	public ArrayList<Match> fetchMatches(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<Match> matchList = new ArrayList<>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_MATCHES);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Match match = new Match();
				match.setMatchId(rs.getInt(1));
				match.setMatchDatetime(rs.getTimestamp(2));
				match.setVenue(rs.getString(3));
				
				Team teamA = fetchTeam(rs.getInt(4));
				match.setTeamA(teamA);
				
				Team teamB = fetchTeam(rs.getInt(5));
				match.setTeamB(teamB);
				
				match.setStatus(rs.getString(6));
				
				match.setTossWonBy(rs.getInt(7));
				if (rs.getInt(8) == 0) {
					match.setMatchResult(null);
				} else {
					match.setMatchResult(MatchResult.values()[rs.getInt(7)-1]);
				}
				matchList.add(match);
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchMatches(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return matchList;
	}
	
	public MatchStats fetchMatchStats(int matchId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		MatchStats matchStats = new MatchStats();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_MATCH_STATS);
			ps.setInt(1, matchId);
			rs = ps.executeQuery();
			while (rs.next()) {
				matchStats.setMatchId(rs.getInt("match_id"));
				matchStats.setBattingTeam(fetchTeam(rs.getInt("batting_team")));
				matchStats.setBowlingTeam(fetchTeam(rs.getInt("bowling_team")));
				matchStats.setTeamAscore(rs.getInt("team_a_score"));
				matchStats.setTeamBscore(rs.getInt("team_b_score"));
				matchStats.setTeamAwickets(rs.getInt("team_a_wickets"));
				matchStats.setTeamBwickets(rs.getInt("team_b_wickets"));
				matchStats.setOvers(rs.getFloat("overs"));
				matchStats.setOnstrike(fetchPlayer(rs.getInt("onstrike")));
				matchStats.setOffstrike(fetchPlayer(rs.getInt("offstrike")));
				matchStats.setBowler(fetchPlayer(rs.getInt("bowler")));
				matchStats.setInning(rs.getInt("inning"));
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchMatchStats(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return matchStats;
	}
	
	public ArrayList<PlayerStats> fetchPlayerStats(int matchId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		ArrayList<PlayerStats> playerStatsList = new ArrayList<PlayerStats>();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_PLAYER_STATS);
			ps.setInt(1, matchId);
			rs = ps.executeQuery();
			while (rs.next()) {
				PlayerStats playerStats = new PlayerStats();
				playerStats.setMatchId(rs.getInt("match_id"));
				playerStats.setPlayerId(rs.getInt("player_id"));
				playerStats.setRunsScored(rs.getInt("runs_scored"));
				playerStats.setTeamId(rs.getInt("team_id"));
				playerStats.setBallsFaced(rs.getInt("balls_faced"));
				playerStats.setWicketsTaken(rs.getInt("wickets_taken"));
				playerStats.setRunsGiven(rs.getInt("runs_given"));
				playerStats.setOvers(rs.getFloat("overs"));
				playerStats.setBatFlag(rs.getBoolean("bat_flag"));
				playerStats.setBowlFlag(rs.getBoolean("bowl_flag"));
				playerStats.setFours(rs.getInt("fours"));
				playerStats.setSixes(rs.getInt("sixes"));
				playerStats.setEconomy(rs.getFloat("economy"));
				playerStats.setStrikeRate(rs.getFloat("strike_rate"));
				playerStatsList.add(playerStats);
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchPlayerStats(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return playerStatsList;
	}
	
	public LineUp fetchLineUp(int matchId, int teamId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		LineUp lineUp = new LineUp();
		
		try {
			ps = con.prepareStatement(Queries.FETCH_LINE_UP);
			ArrayList<Integer> playerId = new ArrayList<>();
			ps.setInt(1, matchId);
			ps.setInt(2, teamId);
			rs = ps.executeQuery();
			while (rs.next()) {
				playerId.add(rs.getInt(1));
			}
			lineUp.setPlayerId(playerId);
			lineUp.setMatchId(matchId);
			lineUp.setTeamId(teamId);
		} catch (SQLException e) {
			logger.info("Exception caught at fetchLineUp(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return lineUp;
	}
	
	public boolean fetchScheduledMatch(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.FETCH_SCHEDULED_MATCH);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) > 1) {
				flag = true;
			}
		} catch (SQLException e) {
			logger.info("Exception caught at fetchScheduledMatch(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}
	
	public boolean searchUser(User user) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_USER);
			ps.setString(1, user.getUserName());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			logger.info("Exception caught at searchUser(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}
	
	/*public boolean searchTeam(User user, int team_id) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_TEAM);
			ps.setInt(1, team_id);
			ps.setInt(2, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			logger.info("Exception caught at searchTeam(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}*/
	
	/*public boolean searchPlayer(User user, int player_id) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_PLAYER);
			ps.setInt(1, player_id);
			ps.setInt(2, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			logger.info("Exception caught at searchPlayer(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}*/
	
	public boolean searchMatch(User user, int matchId) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement(Queries.SEARCH_MATCH);
			ps.setInt(1, matchId);
			ps.setInt(2, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == 0) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (SQLException e) {
			logger.info("Exception caught at searchMatch(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
		return flag;
	}
	
	public void removePlayerFromTeam(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.REMOVE_PLAYER_TEAM);
			ps.setInt(1, team.getTeamId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at removePlayerFromTeam(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public void deletePlayer(Player player) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.DELETE_PLAYER);
			ps.setInt(1, player.getPlayerId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at deletePlayer(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
	
	public void deleteTeam(Team team) {
		PreparedStatement ps = null;
		Connection con = connector.openConnection();
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(Queries.DELETE_TEAM);
			ps.setInt(1, team.getTeamId());
			ps.execute();
		} catch (SQLException e) {
			logger.info("Exception caught at deleteTeam(): " + e);
		} finally {
			connector.closeConnection(con, rs, ps);
		}
	}
}
