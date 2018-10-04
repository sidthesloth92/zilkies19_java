package io.zilker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.delegate.UserOperations;

/**
 * Servlet implementation class GetUserTeamServlet
 */
@WebServlet("/GetUserTeamServlet")
public class GetUserTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int matchId = Integer.parseInt(request.getParameter("match-id"));
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		ArrayList<Player> playersList = new UserOperations().viewTeam(user , matchId);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		for(int index=0; index<playersList.size(); index++) {
			out.println(playersList.get(index).getPlayerId());
			out.println(playersList.get(index).getplayerRating());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
