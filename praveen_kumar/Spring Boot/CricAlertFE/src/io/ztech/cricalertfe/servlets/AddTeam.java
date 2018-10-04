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
import io.ztech.cricalertfe.constants.Regex;
import io.ztech.cricalertfe.constants.UserMessages;
import io.ztech.cricalertfe.delegates.PlayerDelegate;
import io.ztech.cricalertfe.delegates.TeamDelegate;
import io.ztech.cricalertfe.delegates.Validator;
import io.ztech.cricalertfe.exceptions.InvalidNameException;

/**
 * Servlet implementation class AddTeam
 */
@WebServlet("/AddTeam")
public class AddTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeam() {
        super();
        logger = Logger.getLogger(AddTeam.class.getName());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered AddTeam.java");
		PlayerDelegate playerDelegate = new PlayerDelegate();
		ArrayList<Player> playerList = playerDelegate.fetchPlayers((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("playerList", playerList);
		
		request.getRequestDispatcher("/pages/add-team.jsp").forward(request, response);
		logger.info("Exited AddTeam.java");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered AddTeam.java");
		Validator validator = new Validator();
		PlayerDelegate playerDelegate = new PlayerDelegate();
		TeamDelegate teamDelegate = new TeamDelegate();
		Team team = new Team();
		User user = (User) request.getSession(false).getAttribute("user");
		String teamName = request.getParameter("name");
	    team.setTeamName(teamName);
	    team.setUser(user);
	    
	    String[] playerIds = request.getParameterValues("players");
	    for (String playerId : playerIds) {
    		Player player = playerDelegate.fetchPlayer(Integer.parseInt(playerId));
    		player.setUser(user);
    		team.addPlayer(player);
    	}
	    try {
	    	System.out.println(team.getTeamId() + " " + team.getTeamName());
	    	for (Player player : team.getPlayers()) {
				if (!(validator.validateInput(Regex.nameRegex, player.getFirstName(), UserMessages.INVALID_FIRST_NAME)
						&& validator.validateInput(Regex.nameRegex, player.getLastName(), UserMessages.INVALID_LAST_NAME))) {
					throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
				}
			}
			user.addTeam(team);
			user.addPlayer(team.getPlayers());
			teamDelegate.createTeam(team);
	    	request.getRequestDispatcher("/Teams").forward(request, response);
	    } catch(InvalidNameException e) {
	    	logger.info("Exception caught!");
	    	request.getRequestDispatcher("/Teams").forward(request, response);
	    }
	    logger.info("Exited AddTeam.java");
	}

}
