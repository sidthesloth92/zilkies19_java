package io.zilker.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ViewTeamServlet
 */
@WebServlet("/ViewTeamServlet")
public class ViewTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTeamServlet() {
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
		request.setAttribute("playersList", playersList);
		request.setAttribute("matchId", matchId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/view-team.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
