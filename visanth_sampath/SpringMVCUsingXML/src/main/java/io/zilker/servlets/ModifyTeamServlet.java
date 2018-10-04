package io.zilker.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.fantasy.delegate.UserOperations;

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
		int matchCredits = new UserOperations().getMatchCredits(matchId);
		request.setAttribute("matchCredits" , matchCredits);
		request.setAttribute("matchId", matchId);
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
