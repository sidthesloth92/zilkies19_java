package io.ztech.cricalert.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.controller.PlayerController;

/**
 * Servlet implementation class Players
 */
@WebServlet("/Players")
public class Players extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PlayerController playerController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Players() {
        super();
        playerController = new PlayerController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<Player> playerList = playerController.fetchPlayers((User) request.getSession(false).getAttribute("user"));
		request.setAttribute("playerList", playerList);
		
		request.getRequestDispatcher("/pages/players.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
