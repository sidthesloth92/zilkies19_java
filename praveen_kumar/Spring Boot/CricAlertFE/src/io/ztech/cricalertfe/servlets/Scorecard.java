package io.ztech.cricalertfe.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalertfe.beans.Match;
import io.ztech.cricalertfe.beans.MatchStats;
import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.PlayerStats;
import io.ztech.cricalertfe.delegates.MatchDelegate;
import io.ztech.cricalertfe.delegates.TeamDelegate;

/**
 * Servlet implementation class Scorecard
 */
@WebServlet("/Scorecard")
public class Scorecard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scorecard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MatchDelegate matchDelegate = new MatchDelegate();
		TeamDelegate teamDelegate = new TeamDelegate();
		int matchId = Integer.parseInt(request.getParameter("id"));
		Match match = matchDelegate.fetchMatch(matchId);
		MatchStats matchStats = matchDelegate.fetchMatchStats(matchId);
		ArrayList<PlayerStats> playerStatsList = matchDelegate.fetchPlayerStats(matchId);
		ArrayList<Player> teamAplayerList = teamDelegate.fetchTeamPlayers(match.getTeamA());
		ArrayList<Player> teamBplayerList = teamDelegate.fetchTeamPlayers(match.getTeamB());
		HashMap<Integer, String> playerMapA = new HashMap<>();
		HashMap<Integer, String> playerMapB = new HashMap<>();
		for (Player player : teamAplayerList) {
			playerMapA.put(player.getPlayerId(), player.getFirstName() + " " + player.getLastName());
		}
		for (Player player : teamBplayerList) {
			playerMapB.put(player.getPlayerId(), player.getFirstName() + " " + player.getLastName());
		}
		request.setAttribute("match", match);
		request.setAttribute("matchStats", matchStats);
		request.setAttribute("playerStatsList", playerStatsList);
		request.setAttribute("playerMapA", playerMapA);
		request.setAttribute("playerMapB", playerMapB);
		request.getRequestDispatcher("/pages/scorecard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
