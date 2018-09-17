package io.ztech.cricalert.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.beans.User;
import io.ztech.cricalert.controller.PlayerController;
import io.ztech.cricalert.controller.TeamController;

/**
 * Servlet implementation class PlayerModal
 */
@WebServlet("/PlayerModal")
public class PlayerModal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlayerController playerController;
	TeamController teamController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerModal() {
        super();
        playerController = new PlayerController();
        teamController = new TeamController();
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
			
			User user = (User) request.getSession(false).getAttribute("user");
			
			Player player = playerController.fetchPlayer(Integer.parseInt((String) jsonObj.get("playerId")));
			Team team = teamController.fetchTeam(player.getTeamId());
			
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
	}

}
