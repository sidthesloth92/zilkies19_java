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
import java.util.*;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.zilker.fantasy.bean.Match;
import io.zilker.fantasy.bean.Message;
import io.zilker.fantasy.bean.Player;
import io.zilker.fantasy.bean.ResultBoard;
import io.zilker.fantasy.bean.User;
import io.zilker.fantasy.bean.UserPickedTeam;

public class UserDelegate {
	java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserDelegate.class.getName());
	
	
	public ArrayList<Message> displayMessages() throws IOException {
		logger.info("Entering displayMessages in User Delegate");
		String url = "http://127.0.0.1:8090/FantasyLeague/User/GetChat";
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
         Type listType = new TypeToken<List<Message>>() {}.getType();
         ArrayList<Message> messages = gson.fromJson(jsonOutput, listType); 
	     return messages;
	}
	
	public ResultBoard viewLeaderBoard(User user , int matchId) throws IOException {
		String url = "http://127.0.0.1:8090/FantasyLeague/User/LeaderBoard?user-id=" + user.getUserId()+"&match-id="+matchId;
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
	     Gson gson = new Gson();
	     String jsonInString = response.toString();
	     ResultBoard resultBoard = gson.fromJson(jsonInString, ResultBoard.class);
	     return resultBoard;
	}
	
	public ArrayList<Player> viewTeam(User user ,int matchId) throws IOException {
		String url = "http://127.0.0.1:8090/FantasyLeague/User/ViewTeam?user-id=" + user.getUserId() +"&match-id=" + matchId;
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
	     return players;
	}
	
	public int getMatchCredits(int matchId) throws IOException {
		String url = "http://127.0.0.1:8090/FantasyLeague/User/GetMatchCredits?match-id="+matchId;
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
	     JSONObject jsonObj = new JSONObject(response.toString());
	     return jsonObj.getInt("credits");
	}
	
	public ArrayList<String> viewMostPicked(int matchId) throws IOException {
		String url = "http://127.0.0.1:8090/FantasyLeague/User/TopPicks?match-id=" + matchId;
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
         ArrayList<String> players = gson.fromJson(jsonOutput, listType); 
	     return players;
	}
	
	
	
	public boolean updateChat(int userId,String message) throws IOException {
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/User/UpdateChat");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		
		String parameters = "user-id=" + userId +"&" +"message="+message ;
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
        in.close();
        JSONObject jsonObj = new JSONObject(response.toString());
	    return jsonObj.getBoolean("status");
	}

	public ArrayList<Match> displayCompletedMatches() throws IOException {
		String url = "http://127.0.0.1:8090/FantasyLeague/User/CompletedMatches";
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
	     return matches;
	}

	public ArrayList<Match> displayActiveMatches() throws IOException {
		String url = "http://127.0.0.1:8090/FantasyLeague/User/ActiveMatches";
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
	     return matches;
	}

	public boolean addTeam(int userId, int matchId, ArrayList<Integer> playingTeamArray, ArrayList<Integer> creditsArray) throws IOException {
		//logger.info("Entering addTeam in Admin Delegate");
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/User/PickTeam/"+userId);
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		UserPickedTeam userTeam = new UserPickedTeam();
		userTeam.setMatchId(matchId);
		userTeam.setPlayerId(playingTeamArray);
		userTeam.setPlayerCredits(creditsArray);
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
        //logger.info("exiting endMatch in Admin Delegate");
	    return jsonObj.getBoolean("status");
		
	}

	public boolean modifyTeam(int userId, int matchId, ArrayList<Integer> playingTeamArray,
			ArrayList<Integer> creditsArray) throws IOException {
		
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/User/ModifyTeam/"+userId);
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		UserPickedTeam userTeam = new UserPickedTeam();
		userTeam.setMatchId(matchId);
		userTeam.setPlayerId(playingTeamArray);
		userTeam.setPlayerCredits(creditsArray);
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
        //logger.info("exiting endMatch in Admin Delegate");
	    return jsonObj.getBoolean("status");

		
	}

	public static Boolean isTeamTaken(int matchId, int userId) throws IOException {
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/User/Team-Status");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		
		String parameters = "user-id=" + userId +"&" +"match-id="+matchId ;
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
        in.close();
        JSONObject jsonObj = new JSONObject(response.toString());
	    return jsonObj.getBoolean("status");
	}

	//public boolean addTeam(int userId() , int matchId,ArrayList<Integer> playingTeamArray ,ArrayList<Integer> creditsArray) {
		
	//}
	
	
}
