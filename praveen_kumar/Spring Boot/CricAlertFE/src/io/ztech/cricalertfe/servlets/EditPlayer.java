package io.ztech.cricalertfe.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.Team;
import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.delegates.PlayerDelegate;
import io.ztech.cricalertfe.delegates.TeamDelegate;

/**
 * Servlet implementation class EditPlayer
 */
@WebServlet("/EditPlayer")
public class EditPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPlayer() {
        super();
        logger = Logger.getLogger(EditPlayer.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered Players.java doGet");
		TeamDelegate teamDelegate = new TeamDelegate();
		PlayerDelegate playerDelegate = new PlayerDelegate();
		int playerId = Integer.parseInt(request.getParameter("id"));
		Player player = playerDelegate.fetchPlayer(playerId);
		player.setPlayerId(playerId);
		request.setAttribute("player", player);
		ArrayList<Team> teamList = teamDelegate.fetchTeams((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("teamList", teamList);
		
		request.getRequestDispatcher("/pages/edit-player.jsp").forward(request, response);
		logger.info("Exited Players.java doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered Players.java doPost");
		PlayerDelegate playerDelegate = new PlayerDelegate();
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
	    playerDelegate.updatePlayer(player);
    	request.getRequestDispatcher("/Players").forward(request, response);
    	logger.info("Exited Players.java doPost");
	}

}
