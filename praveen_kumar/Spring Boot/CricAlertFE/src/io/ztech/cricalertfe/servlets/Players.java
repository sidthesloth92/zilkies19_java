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
import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.delegates.PlayerDelegate;

/**
 * Servlet implementation class Players
 */
@WebServlet("/Players")
public class Players extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Players() {
        super();
        logger = Logger.getLogger(Players.class.getName());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entered Players.java");
		PlayerDelegate playerDelegate = new PlayerDelegate();
		ArrayList<Player> playerList = playerDelegate.fetchPlayers((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("playerList", playerList);
		
		request.getRequestDispatcher("/pages/players.jsp").forward(request, response);
		logger.info("Exited Players.java");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
