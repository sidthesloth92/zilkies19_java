package io.ztech.cricalertfe.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import io.ztech.cricalertfe.beans.Match;
import io.ztech.cricalertfe.delegates.MatchDelegate;

/**
 * Servlet implementation class Play
 */
@WebServlet("/Play")
public class Play extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Play() {
        super();
        logger = Logger.getLogger(FetchPlayerStats.class.getName());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entered Play.java");
		MatchDelegate matchDelegate = new MatchDelegate();
		int matchId = Integer.parseInt(request.getParameter("id"));
		Match match = matchDelegate.fetchMatch(matchId);
		
		JSONObject json = new JSONObject(match);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	    logger.info("Exited Play.java");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
