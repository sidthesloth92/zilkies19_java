package io.ztech.cricalertfe.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.ztech.cricalertfe.beans.Player;
import io.ztech.cricalertfe.beans.Team;
import io.ztech.cricalertfe.beans.User;

public class TeamDelegate {
	Logger logger;
	
	public TeamDelegate() {
		logger = Logger.getLogger(TeamDelegate.class.getName());
	}
	
	public Team fetchTeam(int teamId) {
		logger.info("Entered fetchTeam");
		Team response = new Team();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/teams/" + teamId);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			response = new Gson().fromJson(buffer.toString(), Team.class);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited fetchTeam");
		return response;
	}
	
	public ArrayList<Team> fetchTeams(User user) {
		logger.info("Entered fetchTeams");
		ArrayList<Team> response = new ArrayList<>();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/teams/user/" + user.getUserId());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			Type listType = new TypeToken<List<Team>>() {}.getType();
			response = new Gson().fromJson(buffer.toString(), listType);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited fetchTeams");
		return response;
	}
	
	public ArrayList<Player> fetchTeamPlayers(Team team) {
		logger.info("Entered fetchTeamPlayers");
		ArrayList<Player> response = new ArrayList<>();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/teams/" + team.getTeamId() + "/players");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			Type listType = new TypeToken<List<Player>>() {}.getType();
			response = new Gson().fromJson(buffer.toString(), listType);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited fetchTeamPlayers");
		return response;
	}
	
	public void createTeam(Team team) {
		logger.info("Entered createTeam");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/teams/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(team);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited createTeam");
	}
	
	public void updateTeam(Team team) {
		logger.info("Entered updateTeam");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/teams/" + team.getTeamId());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(team);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited updateTeam");
	}
	
	/*public void updateTeamName(Team team) {
		dao.updateTeamName(team);
	}
	
	public void updateTeamPlayers(ArrayList<Player> playerList, Team team) {
		dao.removePlayerFromTeam(team);
		dao.updateTeamPlayers(playerList);
	}*/
	
	public void removeTeam(Team team) {
		logger.info("Entered removeTeam");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/teams/" + team.getTeamId());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(team);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited removeTeam");
	}
}
