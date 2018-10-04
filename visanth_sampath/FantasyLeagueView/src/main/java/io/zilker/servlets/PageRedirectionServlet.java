package io.zilker.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.delegates.AdminDelegate;
import io.zilker.fantasy.delegates.UserDelegate;

/**
 * Servlet implementation class PageRedirectionServlet
 */
@WebServlet("/PageRedirectionServlet")
public class PageRedirectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageRedirectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = (String) request.getParameter("page-name");
		if(page.equals("user-home")) {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			int userId = user.getUserId();
			ArrayList<Match> matches = new UserDelegate().displayActiveMatches();
            request.setAttribute("matchList", matches);
            ArrayList<Boolean> pickedStatus = new ArrayList<Boolean> ();
            for(int i=0; i< matches.size();i++) {
            	pickedStatus.add(UserDelegate.isTeamTaken( matches.get(i).getMatchId() ,userId));
            }
            request.setAttribute("pickedStatus", pickedStatus);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/user-home.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("available-matches")) {
			ArrayList<Match> matches = new UserDelegate().displayActiveMatches();
            request.setAttribute("matchList", matches);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/available-matches.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("matches-upcoming")) {
			ArrayList<Match> matches = new UserDelegate().displayActiveMatches();
            request.setAttribute("matchList", matches);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/matches-upcoming.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("leaderboard")) {
			ArrayList<Match> matchList = new UserDelegate().displayCompletedMatches();
            request.setAttribute("matchList", matchList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/leaderboard.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("top-picks")) {
			ArrayList<Match> matchList = new UserDelegate().displayCompletedMatches();
            request.setAttribute("matchList", matchList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/top-picks.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("add-match")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/add-match.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("add-player")) {
			ArrayList<String> teamNames = new AdminDelegate().getTeamNames();
            request.setAttribute("teamNames", teamNames);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/add-player.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("edit-rating")) {
			ArrayList<String> playerNames = new AdminDelegate().getPlayerNames();
			request.setAttribute("playerNames", playerNames);
			ArrayList<String> playerIds = new AdminDelegate().getPlayerIds();
			request.setAttribute("playerIds", playerIds);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/edit-rating.jsp");
			dispatcher.forward(request, response);
		}
		else if(page.equals("end-match")) {
			ArrayList<Match> matchList = new AdminDelegate().displayOngoingMatches();
			request.setAttribute("matchList", matchList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/end-match.jsp");
			dispatcher.forward(request, response);
		} 
		//RequestDispatcher dispatcher = request.getRequestDispatcher("");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
