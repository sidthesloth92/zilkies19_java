package io.ztech.cricalert.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.controller.PlayerController;
import io.ztech.cricalert.controller.TeamController;
import io.ztech.cricalert.exceptions.InvalidNameException;

/**
 * Servlet implementation class EditTeam
 */
@WebServlet("/EditTeam")
public class EditTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PlayerController playerController;
	TeamController teamController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTeam() {
        super();
        // TODO Auto-generated constructor stub
        playerController = new PlayerController();
        teamController = new TeamController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int teamId = Integer.parseInt(request.getParameter("id"));
		Team team = teamController.fetchTeam(teamId);
		ArrayList<Player> teamPlayers = teamController.fetchTeamPlayers(team);
		ArrayList<Integer> teamPlayersId = new ArrayList<>();
		for (Player player : teamPlayers) {
			teamPlayersId.add(player.getPlayerId());
		}
		ArrayList<Player> playerList = playerController.fetchPlayers((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("team", team);
		request.setAttribute("teamPlayersId", teamPlayersId);
		request.setAttribute("playerList", playerList);
		
		request.getRequestDispatcher("/pages/edit-team.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Team team = new Team();
		
		int teamId = Integer.parseInt(request.getParameter("teamId"));
		String teamName = request.getParameter("name");
		String[] playerIds = request.getParameterValues("players");
		ArrayList<Player> playerList = new ArrayList<>();
		for (String playerId : playerIds) {
			Player player = new Player();
			player.setPlayerId(Integer.parseInt(playerId));
			player.setTeamId(teamId);
			playerList.add(player);
		}
		team.setTeamId(teamId);
		team.setTeamName(teamName);
		try {
			teamController.updateTeamName(team);
			teamController.updateTeamPlayers(playerList, team);
		} catch (InvalidNameException e) {
			System.out.println("Exception caught!");
		} finally {
			request.getRequestDispatcher("/Teams").forward(request, response);
		}
	}

}
