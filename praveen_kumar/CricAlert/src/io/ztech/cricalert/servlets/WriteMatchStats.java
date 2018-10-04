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

import io.ztech.cricalert.beans.MatchStats;
import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.controller.MatchController;

/**
 * Servlet implementation class WriteMatchStats
 */
@WebServlet("/WriteMatchStats")
public class WriteMatchStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteMatchStats() {
        super();
        logger = Logger.getLogger(WriteMatchStats.class.getName());
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
		logger.info("Entered WriteMatchStats");
		MatchController matchController = new MatchController();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject matchStatsJson;
		try {
			matchStatsJson = new JSONObject(data);
			MatchStats matchStats = new MatchStats();
			Team battingTeam = new Team();
			Team bowlingTeam = new Team();
			Player onstrike = new Player();
			Player offstrike = new Player();
			Player bowler = new Player();
			battingTeam.setTeamId(((JSONObject) matchStatsJson.get("battingTeam")).getInt("teamId"));
			bowlingTeam.setTeamId(((JSONObject) matchStatsJson.get("bowlingTeam")).getInt("teamId"));
			onstrike.setPlayerId(((JSONObject) matchStatsJson.get("onstrike")).getInt("playerId"));
			offstrike.setPlayerId(((JSONObject) matchStatsJson.get("offstrike")).getInt("playerId"));
			bowler.setPlayerId(((JSONObject) matchStatsJson.get("bowler")).getInt("playerId"));
			matchStats.setMatchId(matchStatsJson.getInt("matchId"));
			matchStats.setBattingTeam(battingTeam);
			matchStats.setBowlingTeam(bowlingTeam);
			matchStats.setTeamAscore(matchStatsJson.getInt("teamAscore"));
			matchStats.setTeamBscore(matchStatsJson.getInt("teamBscore"));
			matchStats.setTeamAwickets(matchStatsJson.getInt("teamAwickets"));
			matchStats.setTeamBwickets(matchStatsJson.getInt("teamBwickets"));
			matchStats.setOvers(Float.parseFloat(String.valueOf(matchStatsJson.get("overs"))));
			matchStats.setOnstrike(onstrike);
			matchStats.setOffstrike(offstrike);
			matchStats.setBowler(bowler);
			matchStats.setInning(matchStatsJson.getInt("inning"));
			matchController.updateMatchStats(matchStats);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("Exited WriteMatchStats");
	}

}
