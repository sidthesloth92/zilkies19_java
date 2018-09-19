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
import io.ztech.cricalertfe.delegates.Validator;
import io.ztech.cricalertfe.delegates.PlayerDelegate;
import io.ztech.cricalertfe.delegates.TeamDelegate;
import io.ztech.cricalertfe.exceptions.InvalidNameException;

/**
 * Servlet implementation class EditTeam
 */
@WebServlet("/EditTeam")
public class EditTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTeam() {
        super();
        // TODO Auto-generated constructor stub
        logger = Logger.getLogger(EditTeam.class.getName());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered EditTeam.java doGet");
		TeamDelegate teamDelegate = new TeamDelegate();
		PlayerDelegate playerDelegate = new PlayerDelegate();
		int teamId = Integer.parseInt(request.getParameter("id"));
		Team team = teamDelegate.fetchTeam(teamId);
		ArrayList<Player> teamPlayers = teamDelegate.fetchTeamPlayers(team);
		ArrayList<Integer> teamPlayersId = new ArrayList<>();
		for (Player player : teamPlayers) {
			teamPlayersId.add(player.getPlayerId());
		}
		ArrayList<Player> playerList = playerDelegate.fetchPlayers((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("team", team);
		request.setAttribute("teamPlayersId", teamPlayersId);
		request.setAttribute("playerList", playerList);
		
		request.getRequestDispatcher("/pages/edit-team.jsp").forward(request, response);
		logger.info("Exited EditTeam.java doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered EditTeam.java doPost");
		Validator validator = new Validator();
		Team team = new Team();
		TeamDelegate teamDelegate = new TeamDelegate();
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
			if (!(validator.validateInput(Regex.nameRegex, team.getTeamName(), UserMessages.INVALID_NAME))) {
				throw new InvalidNameException(UserMessages.INVALID_NAME_EXCEPTION);
			}
			teamDelegate.updateTeamName(team);
			teamDelegate.updateTeamPlayers(playerList, team);
		} catch (InvalidNameException e) {
			logger.info("Exception caught!");
		} finally {
			request.getRequestDispatcher("/Teams").forward(request, response);
		}
		logger.info("Exited EditTeam.java doPost");
	}

}
