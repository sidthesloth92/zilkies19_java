package io.zilker.fantasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.bean.UserPickedTeam;
import io.zilker.fantasy.constants.SqlConstants;

public class UserDAO {
	public Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	DbConfig dbConfig ;
	//UserService userService;
	UserPickedTeam userPickedTeam;
	ArrayList<Integer> players,credits;
	ResultBoard resultBoard ;
	public UserDAO() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		dbConfig = new DbConfig();
		//userService = new UserService();
		userPickedTeam = new UserPickedTeam();
		players = new ArrayList<Integer>();
		credits = new ArrayList<Integer>();
		resultBoard = new ResultBoard();
	}

	public String getRole(int playerId) {
		String role = null;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_ROLE);
			preparedStatement.setInt(1, playerId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				role = resultSet.getString("type");
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return role;
	}

	public int getPlayerCredits(int playerId) {
		int rating = 0;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_ROLE);
			preparedStatement.setInt(1, playerId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				rating = resultSet.getInt("rating");
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}

		return rating;
	}

	// display active matches
	public void displayActiveMatches() {
		ArrayList<Match> matches = new ArrayList<Match>();
		try {
			AdminDAO adminDAO = new AdminDAO();
			matches = adminDAO.listActiveMatches();
			//new AdminService().displayMatchList(matches);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	// selects from match table and set the match bean
	public Match setMatch(int matchId) {
		// TODO Auto-generated method stub
		Match match = new Match();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_MATCH);
			preparedStatement.setInt(1, matchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				match.setMatchTable(resultSet.getString("team1"), resultSet.getString("team2"),
						resultSet.getString("scheduled_date"), resultSet.getString("start_time"),
						resultSet.getString("end_time"), resultSet.getInt("match_credits"));
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return match;
	}

	public ArrayList<Player> displayTeam(String teamName) {
		ArrayList<Player> playersList = new ArrayList<Player>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_TEAM);
			preparedStatement.setString(1, teamName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setPlayer(resultSet.getString("player_name"), "team", resultSet.getString("type"),
						resultSet.getInt("rating"));
				player.setPlayerId(resultSet.getInt("player_id"));
				playersList.add(player);
				/*
				 * userService.printPlayers(resultSet.getInt("player_id"),
				 * resultSet.getString("player_name"), resultSet.getString("type"),
				 * resultSet.getInt("rating"));
				 */

			}
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return playersList;
	}

	// add the team one by one
	public void addPlayerToTeam(int userId, int matchId, int playerId, int credits) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.PLAYERS_PICKED_INSERT);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			preparedStatement.setInt(3, playerId);
			preparedStatement.setInt(4, credits);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
	}

	//
	public boolean addChat(int userId, String message) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.CHAT_INSERT);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, message);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return true;
	}

	public ArrayList<Message> displayMessages() {
		// TODO Auto-generated method stub
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.CHAT_MESSAGES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Message message = new Message();
				message.setMessage(resultSet.getString("users.user_name"), resultSet.getString("message"),
						resultSet.getString("inserted_time"));
				/*
				 * userService.printMessages(resultSet.getString("users.user_name"),
				 * resultSet.getString("message"), resultSet.getString("inserted_time"));
				 */
				messages.add(message);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return messages;
	}

	public ArrayList<Player> displaySelectedTeam(int matchId, int userId) {
		// TODO Auto-generated method stub
		ArrayList<Player> playersList = new ArrayList<Player>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.PLAYER_SELECTED_TEAM);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Player player = new Player();
				player.setPlayer(resultSet.getString("player_name"), "team", resultSet.getString("type"),
						resultSet.getInt("rating"));
				player.setPlayerId(resultSet.getInt("player_id"));
				playersList.add(player);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return playersList;

	}

	// display completed Matches
/*	public void displayCompletedMatches() {
		ArrayList<Integer> matchId = new ArrayList<Integer>();
		ArrayList<String> teamOne = new ArrayList<String>();
		ArrayList<String> teamTwo = new ArrayList<String>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.DISPLAY_COMPLETED_MATCHES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				matchId.add(resultSet.getInt("match_id"));
				teamOne.add(resultSet.getString("team1"));
				teamTwo.add(resultSet.getString("team2"));
			}
			userService.displayMatches(matchId, teamOne, teamTwo);
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
	}*/

	public UserPickedTeam setUserTeam(int userId, int matchId) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_FROM_PLAYERS_PICKED);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				players.add(resultSet.getInt("player_id"));
				credits.add(resultSet.getInt("points"));
			}
			userPickedTeam.setMatchId(matchId);
			userPickedTeam.setPlayerCredits(credits);
			userPickedTeam.setPlayerId(players);

		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return userPickedTeam;
	}

	// delete the existing team for a particular match
	public void deleteOldTeam(int matchId, int userId) {
		// TODO Auto-generated method stub
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.DELETE_EXISTING);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}

	}

	public int calculateMatchScore(int matchId, int userId) {
		// TODO Auto-generated method stub
		int total = 0;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SCORE_CALCULATE);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				total += resultSet.getInt("points");
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return total;
	}

	public int checkResultTable(int userId, int matchId) {
		// TODO Auto-generated method stub
		int total = 0;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.CHECK_RESULT_TABLE);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				total = 0;
			} else {
				total = resultSet.getInt("match_points");
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return total;
	}

	public ResultBoard setResultBoard(int matchId) {
		// TODO Auto-generated method stub
		ArrayList<Integer> users = new ArrayList<Integer>();
		ArrayList<Integer> matchPoints = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_RESULT_TABLE);
			preparedStatement.setInt(1, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(resultSet.getInt("user_id"));
				names.add(resultSet.getString("user_name"));
				matchPoints.add(resultSet.getInt("match_points"));
			}
			resultBoard.setResultBoard(users, names, matchPoints);

		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return resultBoard;
	}

	public ArrayList<String> getPlayersName(int matchId) {
		// TODO Auto-generated method stub
		ArrayList<String> names = new ArrayList<String>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_MOST_PICKED);
			preparedStatement.setInt(1, matchId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				names.add(resultSet.getString("player_name"));
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return names;
	}

	public ResultBoard getPreviousResult(int userId) {
		// TODO Auto-generated method stub
		ArrayList<Integer> users = new ArrayList<Integer>();
		ArrayList<Integer> matchPoints = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		ResultBoard resultBoard = new ResultBoard();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_PARTICULAR_RESULT_TABLE);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(resultSet.getInt("user_id"));
				names.add(resultSet.getString("user_name"));
				matchPoints.add(resultSet.getInt("match_points"));
			}
			resultBoard.setResultBoard(users, names, matchPoints);
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}

		return resultBoard;
	}

	public ArrayList<Match> getUpcomingMatches() {
		// TODO Auto-generated method stub
		ArrayList<Match> matchList = new ArrayList<Match>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_UPCOMING_MATCHES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Match match = new Match();
				match.setMatchTable(resultSet.getString("team1"), resultSet.getString("team2"), "scheduledDate",
						"startTime", "endTime", 0);
				match.setStatusTable(resultSet.getInt("match_id"), 1, 1);
				matchList.add(match);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return matchList;
	}
	
	
	//get completed matches
	public ArrayList<Match> displayCompletedMatches() {
		// TODO Auto-generated method stub
		ArrayList<Match> matchList = new ArrayList<Match>();
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_COMPLETED_MATCHES);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Match match = new Match();
				match.setMatchTable(resultSet.getString("team1"), resultSet.getString("team2"), "scheduledDate",
						"startTime", "endTime", 0);
				/*
				 * adminService.printMatches(resultSet.getInt("match_id"),
				 * resultSet.getString("team1"), resultSet.getString("team2"));
				 */
				match.setStatusTable(resultSet.getInt("match_id"), 0, 1);
				matchList.add(match);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return matchList;

	}

	// return true if team is selcted by a user for a particukar match
	public boolean isTeamTaken(int matchId, int userId) {
		boolean isPresent = false;
		try {
			connection = dbConfig.getConnection();
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_FROM_PLAYERS_PICKED);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, matchId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isPresent = true;
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbConfig.closeConnection(connection, preparedStatement, resultSet);
		}
		return isPresent;
	}


}



