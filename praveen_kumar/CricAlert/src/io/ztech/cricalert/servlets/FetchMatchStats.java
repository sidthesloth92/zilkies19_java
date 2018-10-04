package io.ztech.cricalert.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import io.ztech.cricalert.beans.MatchStats;
import io.ztech.cricalert.controller.MatchController;

/**
 * Servlet implementation class FetchMatchStats
 */
@WebServlet("/FetchMatchStats")
public class FetchMatchStats extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchMatchStats() {
        super();
        logger = Logger.getLogger(FetchMatchStats.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered FetchMatchStats");
		MatchController matchController = new MatchController();
		int matchId = Integer.parseInt(request.getParameter("id"));
		MatchStats matchStats = matchController.fetchMatchStats(matchId);
		JSONObject matchStatsJson = new JSONObject(matchStats);
		response.getWriter().write(matchStatsJson.toString());
		logger.info("Exited FetchMatchStats");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
