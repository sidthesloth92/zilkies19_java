package io.ztech.cricalert.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import io.ztech.cricalert.beans.PlayerStats;
import io.ztech.cricalert.controller.MatchController;

/**
 * Servlet implementation class FetchPlayerStats
 */
@WebServlet("/FetchPlayerStats")
public class FetchPlayerStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchPlayerStats() {
        super();
        logger = Logger.getLogger(FetchPlayerStats.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered FetchPlayerStats");
		MatchController matchController = new MatchController();
		int matchId = Integer.parseInt(request.getParameter("id"));
		ArrayList<PlayerStats> playerStatsList = matchController.fetchPlayerStats(matchId);
		
		Map<Integer, JSONObject> playerStatsMap = new HashMap<>();
		for (PlayerStats playerStats : playerStatsList) {
			JSONObject playerStatsJson = new JSONObject(playerStats);
			playerStatsMap.put(playerStats.getPlayerId(), playerStatsJson);
		}
		JSONObject playerStatsMapJson = new JSONObject(playerStatsMap);
		response.getWriter().write(playerStatsMapJson.toString());
		logger.info("Exited FetchPlayerStats");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
