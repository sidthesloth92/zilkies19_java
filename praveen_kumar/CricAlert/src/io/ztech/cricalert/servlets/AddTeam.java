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
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeam() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlayerController playerController = new PlayerController();
		TeamController teamController = new TeamController();
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
		PlayerController playerController = new PlayerController();
		TeamController teamController = new TeamController();
		Team team = new Team();
		User user = (User) request.getSession(false).getAttribute("user");
		String teamName = request.getParameter("name");
	    team.setTeamName(teamName);
	    team.setUser(user);
	    
	    String[] playerIds = request.getParameterValues("players");
	    for (String playerId : playerIds) {
	    	System.out.println("String: " + playerId + " Integer: " + Integer.parseInt(playerId));
    		Player player = playerController.fetchPlayer(user, Integer.parseInt(playerId));
    		System.out.println(player.getPlayerId());
    		player.setUser(user);
    		team.addPlayer(player);
    		System.out.println(player.getPlayerId() + " " + player.getFirstName());
    	}
	    try {
	    	System.out.println(team.getTeamId() + " " + team.getTeamName());
	    	teamController.createTeam(team, user);
	    	request.getRequestDispatcher("/Teams").forward(request, response);
	    } catch(InvalidNameException e) {
	    	System.out.println("Exception caught!");
	    	request.getRequestDispatcher("/Teams").forward(request, response);
	    }
	}

}
