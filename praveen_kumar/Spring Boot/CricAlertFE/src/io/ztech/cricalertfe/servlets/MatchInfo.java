package io.ztech.cricalertfe.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.ztech.cricalertfe.beans.Match;
import io.ztech.cricalertfe.beans.User;

/**
 * Servlet implementation class MatchInfo
 */
@WebServlet("/MatchInfo")
public class MatchInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchInfo() {
        super();
        logger = Logger.getLogger(MatchInfo.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered MatchInfo.java");
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
		logger.info("Exited MatchInfo.java");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
