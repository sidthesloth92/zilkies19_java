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
import io.ztech.cricalertfe.beans.User;

public class PlayerDelegate {
	private Logger logger;
	
	public PlayerDelegate() {
		logger = Logger.getLogger(PlayerDelegate.class.getName());
	}
	
	public Player fetchPlayer(int playerId) {
		logger.info("Entered fetchPlayer");
		Player response = new Player();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/players/" + playerId);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			response = new Gson().fromJson(buffer.toString(), Player.class);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited fetchPlayer");
		return response;
	}
	
	public ArrayList<Player> fetchPlayers(User user) {
		logger.info("Entered fetchPlayers");
		ArrayList<Player> response = new ArrayList<>();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/players/user/" + user.getUserId());
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
		logger.info("Exited fetchPlayers");
		return response;
	}
	
	public void addNewPlayer(Player player) {
		logger.info("Entered addNewPlayer");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/players/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(player);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited addNewPlayer");
	}
	
	public void updatePlayer(Player player) {
		logger.info("Entered updatePlayer");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/players/" + player.getTeamId());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(player);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited updatePlayer");
	}
	
	public void removePlayer(Player player) {
		logger.info("Entered removePlayer");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/players/" + player.getTeamId());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(player);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited removePlayer");
	}
}
