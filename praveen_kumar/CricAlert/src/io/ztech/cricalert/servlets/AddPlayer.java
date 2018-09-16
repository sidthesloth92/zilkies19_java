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
import io.ztech.cricalert.controller.TeamController;
import io.ztech.cricalert.exceptions.InvalidNameException;

/**
 * Servlet implementation class AddPlayer
 */
@WebServlet("/AddPlayer")
public class AddPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlayer() {
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
		
		request.getRequestDispatcher("/pages/add-player.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		TeamController teamController = new TeamController();
		Player player = new Player();
		String firstName = request.getParameter("fname");
	    String lastName = request.getParameter("lname");
	    int teamId = Integer.parseInt(request.getParameter("team"));
	    player.setFirstName(firstName);
	    player.setLastName(lastName);
	    player.setTeamId(teamId);
	    player.setUser((User) request.getSession(false).getAttribute("user"));
	    try {
	    	teamController.addNewPlayer(player, (User) request.getSession(false).getAttribute("user"));
	    	request.getRequestDispatcher("/Players").forward(request, response);
	    } catch(InvalidNameException e) {
	    	System.out.println("Exception caught!");
	    	request.getRequestDispatcher("/Players").forward(request, response);
	    }
	}

}
