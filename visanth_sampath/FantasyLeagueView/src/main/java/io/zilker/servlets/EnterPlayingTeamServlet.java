package io.zilker.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zilker.fantasy.delegates.AdminDelegate;

/**
 * Servlet implementation class EnterPlayingTeamServlet
 */
@WebServlet("/EnterPlayingTeamServlet")
public class EnterPlayingTeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterPlayingTeamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playingTeam = request.getParameter("playing-team");
		int matchId = Integer.parseInt(request.getParameter("match-id"));
		String [] playingTeamStringArray = playingTeam.split(",");
		ArrayList<Integer> playingTeamArray = new ArrayList<Integer>();
		for(int i=0;i<playingTeamStringArray.length;i++) {
			playingTeamArray.add(Integer.parseInt(playingTeamStringArray[i]));
		}
		new AdminDelegate().endMatch(matchId,playingTeamArray);
		//doGet(request, response);
	}

}
