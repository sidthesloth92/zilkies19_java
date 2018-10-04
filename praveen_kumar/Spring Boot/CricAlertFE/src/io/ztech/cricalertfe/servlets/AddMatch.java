package io.ztech.cricalertfe.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalertfe.beans.LineUp;
import io.ztech.cricalertfe.beans.Match;
import io.ztech.cricalertfe.beans.Team;
import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.delegates.MatchDelegate;
import io.ztech.cricalertfe.delegates.TeamDelegate;

/**
 * Servlet implementation class AddMatch
 */
@WebServlet("/AddMatch")
public class AddMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMatch() {
        super();
        logger = Logger.getLogger(AddMatch.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered AddMatch doGet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		TeamDelegate teamDelegate = new TeamDelegate();
		ArrayList<Team> teamList = teamDelegate.fetchTeams((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("teamList", teamList);
		
		request.getRequestDispatcher("/pages/add-match.jsp").forward(request, response);
		logger.info("Exited AddMatch doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered AddMatch doPost");
		MatchDelegate matchDelegate = new MatchDelegate();
		User user = (User) request.getSession(false).getAttribute("user");
		TeamDelegate teamDelegate = new TeamDelegate();
		Match match = new Match();
		Team teamA = new Team();
		Team teamB = new Team();
		LineUp lineUpA = new LineUp();
		LineUp lineUpB = new LineUp();
		
		int teamAid = Integer.parseInt(request.getParameter("team-a"));
		int teamBid = Integer.parseInt(request.getParameter("team-b"));
		String[] teamALineUpString = request.getParameterValues("team-a-lineup");
		String[] teamBLineUpString = request.getParameterValues("team-b-lineup");
		ArrayList<Integer> playerIdsA = new ArrayList<>();
		ArrayList<Integer> playerIdsB = new ArrayList<>();
		for (String playerId : teamALineUpString) {
			playerIdsA.add(Integer.parseInt(playerId));
		}
		for (String playerId : teamBLineUpString) {
			playerIdsB.add(Integer.parseInt(playerId));
		}
		String venue = request.getParameter("venue");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		
		String datetime = date + " " + time + ":00";
		Timestamp matchDatetime = Timestamp.valueOf(datetime);
		teamA = teamDelegate.fetchTeam(teamAid);
		teamB = teamDelegate.fetchTeam(teamBid);
		lineUpA.setPlayerId(playerIdsA);
		lineUpB.setPlayerId(playerIdsB);
		match.setUser(user);
		match.setTeamA(teamA);
		match.setTeamB(teamB);
		match.setTeamALineUp(lineUpA);
		match.setTeamBLineUp(lineUpB);
		match.setStatus("scheduled");
		match.setVenue(venue);
		match.setMatchDatetime(matchDatetime);
		user.addMatch(match);
		matchDelegate.setMatch(match);
		
		request.getRequestDispatcher("/Home").forward(request, response);
		logger.info("Exited AddMatch doPost");
	}

}
