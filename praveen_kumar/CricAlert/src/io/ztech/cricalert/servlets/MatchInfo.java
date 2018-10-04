package io.ztech.cricalert.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalert.beans.Match;
import io.ztech.cricalert.beans.User;
import jdk.nashorn.internal.codegen.types.Type;

/**
 * Servlet implementation class MatchInfo
 */
@WebServlet("/MatchInfo")
public class MatchInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		User user = (User) request.getSession(false).getAttribute("user");
		
		int matchId = Integer.parseInt(request.getParameter("id"));
		String matchType = request.getParameter("type");
		Match match = new Match();
		for (Match m : user.getMatches()) {
			if (m.getMatchId() == matchId) {
				match = m;
				break;
			}
		}
		request.setAttribute("match", match);
		
		if (matchType.equals("live")) {
			request.getRequestDispatcher("/pages/live-match.jsp").forward(request, response);
		} else if (matchType.equals("upcoming")) {
			request.getRequestDispatcher("/pages/upcoming-match.jsp").forward(request, response);
		} else if (matchType.equals("past")) {
			request.getRequestDispatcher("/pages/past-match.jsp").forward(request, response);
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
