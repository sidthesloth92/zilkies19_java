package io.ztech.cricalert.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalert.beans.LineUp;
import io.ztech.cricalert.beans.Match;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.controller.MatchController;
import io.ztech.cricalert.controller.TeamController;

/**
 * Servlet implementation class AddMatch
 */
@WebServlet("/AddMatch")
public class AddMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		TeamController teamController = new TeamController();
		
		ArrayList<Team> teamList = teamController.fetchTeams((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("teamList", teamList);
		
		request.getRequestDispatcher("/pages/add-match.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession(false).getAttribute("user");
		MatchController matchController = new MatchController();
		TeamController teamController = new TeamController();
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
		teamA = teamController.fetchTeam(teamAid);
		teamB = teamController.fetchTeam(teamBid);
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
		matchController.setMatch(match);
		
		request.getRequestDispatcher("/Home").forward(request, response);
	}

}
