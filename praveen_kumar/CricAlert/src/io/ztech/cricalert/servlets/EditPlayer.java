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
 * Servlet implementation class EditPlayer
 */
@WebServlet("/EditPlayer")
public class EditPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PlayerController playerController;
	TeamController teamController;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPlayer() {
        super();
        playerController = new PlayerController();
        teamController = new TeamController();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int playerId = Integer.parseInt(request.getParameter("id"));
		Player player = playerController.fetchPlayer(playerId);
		player.setPlayerId(playerId);
		request.setAttribute("player", player);
		ArrayList<Team> teamList = teamController.fetchTeams((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("teamList", teamList);
		
		request.getRequestDispatcher("/pages/edit-player.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		Player player = new Player();
		int playerId = Integer.parseInt(request.getParameter("playerId"));
		String firstName = request.getParameter("fname");
	    String lastName = request.getParameter("lname");
	    int teamId = Integer.parseInt(request.getParameter("team"));
	    
	    player.setPlayerId(playerId);
	    player.setFirstName(firstName);
	    player.setLastName(lastName);
	    player.setTeamId(teamId);
	    player.setUser((User) request.getSession(false).getAttribute("user"));
	    playerController.updatePlayer(player);
    	request.getRequestDispatcher("/Players").forward(request, response);
	}

}
