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
 * Servlet implementation class AddPlayer
 */
@WebServlet("/AddPlayer")
public class AddPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlayer() {
        super();
        logger = Logger.getLogger(AddPlayer.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered AddPlayer doGet");
		TeamDelegate teamDelegate = new TeamDelegate();
		
		ArrayList<Team> teamList = teamDelegate.fetchTeams((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("teamList", teamList);
		
		request.getRequestDispatcher("/pages/add-player.jsp").forward(request, response);
		logger.info("Exited AddPlayer doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered AddPlayer doGet");
		Validator validator = new Validator();
		User user = (User) request.getSession(false).getAttribute("user");
		PlayerDelegate playerDelegate = new PlayerDelegate();
		Player player = new Player();
		String firstName = request.getParameter("fname");
	    String lastName = request.getParameter("lname");
	    int teamId = Integer.parseInt(request.getParameter("team"));
	    player.setFirstName(firstName);
	    player.setLastName(lastName);
	    player.setTeamId(teamId);
	    player.setUser(user);
	    try {
	    	if (!(validator.validateInput(Regex.nameRegex, player.getFirstName(), UserMessages.INVALID_FIRST_NAME)
					&& validator.validateInput(Regex.nameRegex, player.getLastName(), UserMessages.INVALID_LAST_NAME))) {
				throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
			}
			user.addPlayer(player);
			playerDelegate.addNewPlayer(player);
	    	request.getRequestDispatcher("/Players").forward(request, response);
	    } catch(InvalidNameException e) {
	    	System.out.println("Exception caught!");
	    	request.getRequestDispatcher("/Players").forward(request, response);
	    }
	    logger.info("Exited AddPlayer doGet");
	}

}
