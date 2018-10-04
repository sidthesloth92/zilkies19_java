package io.ztech.cricalertfe.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import io.ztech.cricalertfe.beans.Match;
import io.ztech.cricalertfe.beans.User;
import io.ztech.cricalertfe.constants.MatchResult;
import io.ztech.cricalertfe.delegates.MatchDelegate;

/**
 * Servlet implementation class WriteMatch
 */
@WebServlet("/WriteMatch")
public class WriteMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger logger;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteMatch() {
        super();
        logger = Logger.getLogger(WriteMatch.class.getName());
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
		// TODO Auto-generated method stub
		logger.info("Entered WriteMatch");
		MatchDelegate matchDelegate = new MatchDelegate();
		Match match = new Match();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		JSONObject matchJson;
		try {
			matchJson = new JSONObject(data);
			match.setMatchId(matchJson.getInt("matchId"));
			match.setStatus(matchJson.getString("status"));
			if (matchJson.getString("matchResult").equals("WIN")) {
				match.setMatchResult(MatchResult.WIN);
			} else if (matchJson.getString("matchResult").equals("LOSE")) {
				match.setMatchResult(MatchResult.LOSE);
			} else if (matchJson.getString("matchResult").equals("DRAW")) {
				match.setMatchResult(MatchResult.DRAW);
			} else {
				match.setMatchResult(null);
			}
			User user = (User) request.getSession(false).getAttribute("user");
			for (Match userMatch : user.getMatches()) {
				if (userMatch.getMatchId() == match.getMatchId()) {
					userMatch.setStatus(match.getStatus());
				}
			}
			matchDelegate.updateMatchStatus(match);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		logger.info("Exited WriteMatch");
	}

}
