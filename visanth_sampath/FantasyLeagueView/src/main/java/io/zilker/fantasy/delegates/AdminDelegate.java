package io.zilker.fantasy.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.UserPickedTeam;

public class AdminDelegate {

Logger logger = Logger.getLogger(AdminDelegate.class.getName());

	public boolean scheduleNewMatch(String teamOne, String teamTwo,String startDate,String startTime,String endTime,int credits) throws IOException {
		logger.info("Entering scheduleNewMatch in Admin Delegate");
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/Admin/AddMatch");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		
		String parameters = "team-one=" + teamOne +"&" + "team-two=" + teamTwo +"&" + "scheduled-date=" + startDate +"&" + "start-time=" + startTime +"&" +"end-time=" + endTime +"&";
		parameters += "match-credits=" + credits;
		byte[] postData       = parameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		httpCon.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		httpCon.setRequestProperty( "charset", "utf-8");
		httpCon.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		 
		OutputStreamWriter writer = new OutputStreamWriter(
		    httpCon.getOutputStream());
		writer.write(parameters);
		writer.flush();
		String inputLine;
		BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response);
        logger.info("Exiting scheduleNewMatch in Admin Delegate");
        return true;
	}
	
	
	public boolean addPlayer(String name, String team, String role,int rating) throws IOException {
		logger.info("Entering addPlayer in Admin Delegate");
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/Admin/AddPlayer");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		String parameters = "player-name=" + name +"&" + "team=" + team +"&" + "role=" + role +"&" + "rating=" + rating ;
		byte[] postData       = parameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		httpCon.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		httpCon.setRequestProperty( "charset", "utf-8");
		httpCon.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		 
		OutputStreamWriter writer = new OutputStreamWriter(
		    httpCon.getOutputStream());
		writer.write(parameters);
		writer.flush();
		String inputLine;
		BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response);
        logger.info("Exiting addPlayer in Admin Delegate");
        return true;
	}
	
	public int getCurrentRating(int playerId) throws IOException {
		logger.info("Entering getCurrentRating in Admin Delegate");
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/Admin/CurrentPlayerRating");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		String parameters = "player-id=" + playerId;
		byte[] postData       = parameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		httpCon.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		httpCon.setRequestProperty( "charset", "utf-8");
		httpCon.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		 
		OutputStreamWriter writer = new OutputStreamWriter(
		    httpCon.getOutputStream());
		writer.write(parameters);
		writer.flush();
		String inputLine;
		BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        System.out.println(response);
//        String [] RatingStringOne = response.toString().split("[{]");
        JSONObject jsonObj = new JSONObject(response.toString());
        logger.info("Exiting getCurrentRating in Admin Delegate");
	    return jsonObj.getInt("rating");
	}
	
	public boolean editPlayerRating(int playerId, int modifiedRating) throws IOException {
		logger.info("Entering editPlayerRating in Admin Delegate");
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/Admin/UpdateRating");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		
		String parameters = "player-id=" + playerId +"&" +"modified-rating="+modifiedRating ;
		byte[] postData       = parameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		httpCon.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		httpCon.setRequestProperty( "charset", "utf-8");
		httpCon.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		 
		OutputStreamWriter writer = new OutputStreamWriter(
		    httpCon.getOutputStream());
		writer.write(parameters);
		writer.flush();
		String inputLine;
		BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonObj = new JSONObject(response.toString());
        logger.info("Exiting editPlayerRating in Admin Delegate");
	    return jsonObj.getBoolean("status");
	}
	
	
	public boolean endMatch(int matchId, ArrayList<Integer> playingTeam) throws IOException {
		logger.info("Entering endMatch in Admin Delegate");
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/Admin/EndMatch");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		UserPickedTeam userTeam = new UserPickedTeam();
		userTeam.setMatchId(matchId);
		userTeam.setPlayerId(playingTeam);
		Gson gson = new Gson();
		String teamJsonArray = gson.toJson(userTeam);
		byte[] postData       = teamJsonArray.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("POST");
		httpCon.setRequestProperty( "Content-Type", "application/json"); 
		httpCon.setRequestProperty( "charset", "utf-8");
		httpCon.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		 
		OutputStreamWriter writer = new OutputStreamWriter(
		    httpCon.getOutputStream());
		writer.write(teamJsonArray);
		writer.flush();
		String inputLine;
		BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonObj = new JSONObject(response.toString());
        logger.info("exiting endMatch in Admin Delegate");
	    return jsonObj.getBoolean("status");
	}
	
	
	public ArrayList<String> getTeamNames() throws IOException{
		logger.info("Entering getTeamNames in Admin Delegate");
		String url = "http://localhost:8090/FantasyLeague/Admin/GetTeams";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     //Read JSON response and print
	     Gson gson = new Gson();
        String jsonOutput = response.toString();
        Type listType = new TypeToken<List<String>>() {}.getType();
        ArrayList<String> teams = gson.fromJson(jsonOutput, listType); 
        logger.info("Exiting getTeamNames in Admin Delegate");
	    return teams;
	}


	public ArrayList< Match > displayOngoingMatches() throws IOException {
		logger.info("Entering displayOngoingMatches in Admin Delegate");
		String url = "http://localhost:8090/FantasyLeague/Admin/GetMatches";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     //Read JSON response and print
	     Gson gson = new Gson();
       String jsonOutput = response.toString();
       Type listType = new TypeToken<List<Match>>() {}.getType();
       ArrayList<Match> matches = gson.fromJson(jsonOutput, listType); 
       logger.info("Exiting displayOngoingMatches in Admin Delegate");
	    return matches;
	}


	public ArrayList<String> getPlayerNames() throws IOException {
		logger.info("Entering getPlayerNames in Admin Delegate");
		String url = "http://localhost:8090/FantasyLeague/Admin/GetPlayerNames";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     //Read JSON response and print
	     Gson gson = new Gson();
       String jsonOutput = response.toString();
       Type listType = new TypeToken<List<String>>() {}.getType();
       ArrayList<String> playerNames = gson.fromJson(jsonOutput, listType); 
       logger.info("Exiting getPlayerNames in Admin Delegate");
	    return playerNames;
	}
	
	public ArrayList<String> getPlayerIds() throws IOException {
		logger.info("Entering getPlayerIds in Admin Delegate");
		String url = "http://localhost:8090/FantasyLeague/Admin/GetPlayerIds";
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     //Read JSON response and print
	     Gson gson = new Gson();
       String jsonOutput = response.toString();
       Type listType = new TypeToken<List<String>>() {}.getType();
       ArrayList<String> playerIds = gson.fromJson(jsonOutput, listType); 
       logger.info("Exiting getPlayerIds in Admin Delegate");
	    return playerIds;
	}


	public ArrayList<Player> getTeamOne(int matchId) throws IOException {
		logger.info("Entering getTeamOne in Admin Delegate");
		String url = "http://localhost:8090/FantasyLeague/Admin/TeamOne?match-id="+matchId;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     //Read JSON response and print
	     Gson gson = new Gson();
      String jsonOutput = response.toString();
      Type listType = new TypeToken<List<Player>>() {}.getType();
      ArrayList<Player> players = gson.fromJson(jsonOutput, listType); 
      logger.info("Entering getTeamOne in Admin Delegate");
	    return players;
	}
	
	public ArrayList<Player> getTeamTwo(int matchId) throws IOException {
		logger.info("Entering getTeamTwo in Admin Delegate");
		String url = "http://localhost:8090/FantasyLeague/Admin/TeamTwo?match-id="+matchId;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	     //Read JSON response and print
	     Gson gson = new Gson();
      String jsonOutput = response.toString();
      Type listType = new TypeToken<List<Player>>() {}.getType();
      ArrayList<Player> players = gson.fromJson(jsonOutput, listType); 
      logger.info("Exiting getTeamTwo in Admin Delegate");
	    return players;
	    
	}

}
