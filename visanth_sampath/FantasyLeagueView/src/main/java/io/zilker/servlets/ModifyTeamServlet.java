package io.zilker.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.delegates.AdminDelegate;
import io.zilker.fantasy.delegates.UserDelegate;

/**
 * Servlet implementation class ModifyTeamServlet
 */
@WebServlet("/ModifyTeamServlet")
public class ModifyTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int matchId = Integer.parseInt(request.getParameter("match-id"));
		int matchCredits = new UserDelegate().getMatchCredits(matchId);
		request.setAttribute("matchCredits" , matchCredits);
		request.setAttribute("matchId", matchId);
		ArrayList< Player > teamOne = new AdminDelegate().getTeamOne(matchId);
		request.setAttribute("teamOne", teamOne);
		ArrayList< Player > teamTwo = new AdminDelegate().getTeamTwo(matchId);
		request.setAttribute("teamTwo", teamTwo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/modify-team.jsp");
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
