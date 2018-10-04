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

import io.ztech.cricalertfe.beans.Match;
import io.ztech.cricalertfe.beans.MatchStats;
import io.ztech.cricalertfe.beans.PlayerStats;

public class MatchDelegate {
	private Logger logger;

	public MatchDelegate() {
		logger = Logger.getLogger(MatchDelegate.class.getName());
	}
	
	public Match fetchMatch(int matchId) {
		logger.info("Entered fetchMatch Delegate (FE)");
		Match response = new Match();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/matches/" + matchId);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			response = new Gson().fromJson(buffer.toString(), Match.class);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited fetchMatch Delegate (FE)");
		return response;
	}
	
	public MatchStats fetchMatchStats(int matchId) {
		logger.info("Entered fetchMatchStats Delegate (FE)");
		MatchStats response = new MatchStats();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/matches/" + matchId + "/match-stats");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			response = new Gson().fromJson(buffer.toString(), MatchStats.class);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited fetchMatchStats Delegate (FE)");
		return response;
	}
	
	public ArrayList<PlayerStats> fetchPlayerStats(int matchId) {
		logger.info("Entered fetchPlayerStats Delegate (FE)");
		ArrayList<PlayerStats> response = new ArrayList<>();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/matches/" + matchId + "/player-stats");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			Type listType = new TypeToken<List<PlayerStats>>() {}.getType();
			response = new Gson().fromJson(buffer.toString(), listType);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited fetchPlayerStats Delegate (FE)");
		return response;
	}
	
	public void setMatch(Match newMatch) {
		logger.info("Entered setMatch Delegate (FE)");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/matches/");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(newMatch);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited setMatch Delegate (FE)");
	}
	
	public void updateMatchStatus(Match match) {
		logger.info("Entered updateMatchStatus Delegate (FE)");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/matches/" + match.getMatchId());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(match);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited updateMatchStatus Delegate (FE)");
	}
	
	public void updateMatchStats(MatchStats matchStats) {
		logger.info("Entered updateMatchStats Delegate (FE)");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/matches/" + matchStats.getMatchId() + "/match-stats");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(matchStats);
			System.out.println(url.toString());
			System.out.println(json);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited updateMatchStats Delegate (FE)");
	}
	
	public void updatePlayerStats(PlayerStats playerStats) {
		logger.info("Entered updatePlayerStats Delegate (FE)");
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/matches/" + playerStats.getMatchId() + "/player-stats");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(playerStats);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited updatePlayerStats Delegate (FE)");
	}
	
	/*public void updateMatchDate(Match match) {
		dao.updateMatchDate(match);
	}
	
	public void updateTeam(Match match, String team) {
		dao.updateTeam(match, team);
	}
	
	public ArrayList<Player> fetchPlayers(ArrayList<Integer> players) {
		return dao.fetchPlayers(players);
	}
	
	public void updateBallStats(BallStats ballStats) {
		dao.insertBallStats(ballStats);
	}*/
}
