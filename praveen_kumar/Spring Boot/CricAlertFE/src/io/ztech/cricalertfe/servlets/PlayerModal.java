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

import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.Team;
import io.ztech.cricalertfe.delegates.PlayerDelegate;
import io.ztech.cricalertfe.delegates.TeamDelegate;

/**
 * Servlet implementation class PlayerModal
 */
@WebServlet("/PlayerModal")
public class PlayerModal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerModal() {
        super();
        logger = Logger.getLogger(PlayerModal.class.getName());
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
		logger.info("Entered PlayerModal.java");
		TeamDelegate teamDelegate = new TeamDelegate();
		PlayerDelegate playerDelegate = new PlayerDelegate();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(data);
			Player player = playerDelegate.fetchPlayer(Integer.parseInt((String) jsonObj.get("playerId")));
			Team team = teamDelegate.fetchTeam(player.getTeamId());
			
			JSONObject obj = new JSONObject();
			obj.put("firstName", "" + player.getFirstName());
			obj.put("lastName", "" + player.getLastName());
			obj.put("teamName", "" + team.getTeamName());
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(obj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception caught at PlayerModal!");
			e.printStackTrace();
		}
		logger.info("Exited PlayerModal.java");
	}

}
