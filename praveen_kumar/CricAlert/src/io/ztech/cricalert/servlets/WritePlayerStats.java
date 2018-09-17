package io.ztech.cricalert.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import io.ztech.cricalert.beans.PlayerStats;
import io.ztech.cricalert.controller.MatchController;

/**
 * Servlet implementation class WritePlayerStats
 */
@WebServlet("/WritePlayerStats")
public class WritePlayerStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WritePlayerStats() {
        super();
        logger = Logger.getLogger(WritePlayerStats.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered WritePlayerStats");
		MatchController matchController = new MatchController();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject playerStatsMapJson;
		try {
			playerStatsMapJson = new JSONObject(data);
			for(int i = 0; i < playerStatsMapJson.names().length(); i++){
				int playerId = playerStatsMapJson.names().getInt(i);
				JSONObject playerStatsJson = (JSONObject) playerStatsMapJson.get(playerStatsMapJson.names().getString(i));
				PlayerStats playerStats = new PlayerStats();
			    playerStats.setMatchId(playerStatsJson.getInt("matchId"));
			    playerStats.setPlayerId(playerId);
			    playerStats.setRunsScored(playerStatsJson.getInt("runsScored"));
			    playerStats.setTeamId(playerStatsJson.getInt("teamId"));
			    playerStats.setBallsFaced(playerStatsJson.getInt("ballsFaced"));
			    playerStats.setWicketsTaken(playerStatsJson.getInt("wicketsTaken"));
			    playerStats.setRunsGiven(playerStatsJson.getInt("runsGiven"));
			    playerStats.setOvers(Float.parseFloat(playerStatsJson.getString("overs")));
			    playerStats.setBatFlag(playerStatsJson.getBoolean("batFlag"));
			    playerStats.setBowlFlag(playerStatsJson.getBoolean("bowlFlag"));
			    playerStats.setFours(playerStatsJson.getInt("fours"));
			    playerStats.setSixes(playerStatsJson.getInt("sixes"));
			    playerStats.setEconomy(Float.parseFloat(playerStatsJson.getString("economy")));
			    playerStats.setStrikeRate(Float.parseFloat(playerStatsJson.getString("strikeRate")));
			    matchController.updatePlayerStats(playerStats);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("Exited WritePlayerStats");
	}

}
