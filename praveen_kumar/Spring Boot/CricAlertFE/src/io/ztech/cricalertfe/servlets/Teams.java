package io.ztech.cricalertfe.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalertfe.beans.Team;
import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.delegates.TeamDelegate;

/**
 * Servlet implementation class Teams
 */
@WebServlet("/Teams")
public class Teams extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teams() {
        super();
        logger = Logger.getLogger(Teams.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered Teams.java");
		TeamDelegate teamDelegate = new TeamDelegate();
		ArrayList<Team> teamList = teamDelegate.fetchTeams((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("teamList", teamList);
		
		request.getRequestDispatcher("/pages/teams.jsp").forward(request, response);
		logger.info("Exited Teams.java");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
