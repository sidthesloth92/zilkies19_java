package io.ztech.cricalert.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import io.ztech.cricalert.beans.Player;
import io.ztech.cricalert.beans.Team;
import io.ztech.cricalert.controller.TeamController;

/**
 * Servlet implementation class TeamPlayers
 */
@WebServlet("/TeamPlayers")
public class TeamPlayers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TeamController teamController;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeamPlayers() {
        super();
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
		Team team = new Team();
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
		
			team.setTeamId(Integer.parseInt((String) jsonObj.get("teamId")));
			ArrayList<Player> playerList = teamController.fetchTeamPlayers(team);
			String json = new Gson().toJson(playerList);
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
