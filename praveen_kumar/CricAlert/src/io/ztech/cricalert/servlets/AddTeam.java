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
 * Servlet implementation class AddTeam
 */
@WebServlet("/AddTeam")
public class AddTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PlayerController playerController;
	TeamController teamController;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeam() {
        super();
        playerController = new PlayerController();
        teamController = new TeamController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<Player> playerList = playerController.fetchPlayers((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("playerList", playerList);
		
		request.getRequestDispatcher("/pages/add-team.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Team team = new Team();
		User user = (User) request.getSession(false).getAttribute("user");
		String teamName = request.getParameter("name");
	    team.setTeamName(teamName);
	    team.setUser(user);
	    
	    String[] playerIds = request.getParameterValues("players");
	    for (String playerId : playerIds) {
    		Player player = playerController.fetchPlayer(user, Integer.parseInt(playerId));
    		player.setUser(user);
    		team.addPlayer(player);
    	}
	    try {
	    	teamController.createTeam(team, user);
	    	request.getRequestDispatcher("/Teams").forward(request, response);
	    } catch(InvalidNameException e) {
	    	System.out.println("Exception caught!");
	    	request.getRequestDispatcher("/Teams").forward(request, response);
	    }
	}

}
