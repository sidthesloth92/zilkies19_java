package com.zilker.delegates;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.zilker.beans.AdminDetails;
import com.zilker.beans.Login;
import com.zilker.beans.Player;
import com.zilker.beans.Schedule;
import com.zilker.beans.Scorecard;
import com.zilker.beans.Team;
import com.zilker.beans.Tournament;
import com.zilker.beans.Score;
import com.zilker.beans.UserDetails;
import java.util.*;

public class DelegatesCrudOperations {
	boolean flag;
	ArrayList hm = new ArrayList();
	ArrayList<String> al = new ArrayList<String>();
	int teamid, status;
	int teamId[] = new int[2];

	public DelegatesCrudOperations() {
		flag = false;
		teamid = 0;
		status = 0;
	}

	public boolean getUserLoginInfo(Login credentials) throws SQLException, IOException {
		boolean validate = false;
		String url = "http://localhost:9000/login/user-info?email=" + credentials.getEmail() + "&password="
				+ credentials.getPassword();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println("response is:" + response.toString());
		// print in String
		Gson gson = new Gson();
		Login result = gson.fromJson(response.toString(), Login.class);
		if (result.getLoginstatus()) {
			System.out.println("delegate true");
			return true;
		} else {
			System.out.println("delegate false");
			return false;
		}
	}

	public ArrayList getScore(int teamid, String match) throws SQLException, IOException {
		boolean validate = false;
		String url = "http://localhost:9000/user/score?teamid=" + teamid + "&match=" + match;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println("response is:" + response.toString());
		// print in String
		Gson gson = new Gson();
		String jsonOutput = response.toString();
		Type listType = new TypeToken<Collection>() {
		}.getType();
		ArrayList tournamentlist = gson.fromJson(jsonOutput, listType);
		return tournamentlist;
	}

	public int getteamId(String teamName) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(teamName);
		System.out.println(json);
		String url = "http://localhost:9000/user/teamdetails/"+teamName;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(teamName);
		//output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String responseString = response.toString();
		return Integer.parseInt(responseString);
	}

	public ArrayList getplayers(int teamid) throws SQLException, IOException {
		Gson gson = new Gson();
//		String json = gson.toJson(teamid);
//		System.out.println(json);
		String url = "http://localhost:9000/user/players/"+teamid;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
//		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
//		output.write(teamid);
//		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String jsonOutput = response.toString();
		Type listType = new TypeToken<Collection>() {
		}.getType();
		ArrayList tournamentlist = gson.fromJson(jsonOutput, listType);
		return tournamentlist;
	}

	public String getUserName(Login credentials) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(credentials,Login.class);
		System.out.println(json);
		String url = "http://localhost:9000/login/username";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String username = response.toString();
		return username;
	}

	public int getUserId(Login credentials) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(credentials,Login.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/id";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String responseString = response.toString();
		return Integer.parseInt(responseString);
	}

	/*public boolean getAdminLoginInfo(Login loginObj) throws SQLException {
		try {
			flag = daoObj.authenticateAdmin(loginObj);
			return flag;
		} catch (Exception e) {
			throw e;
		}

	}*/

	public String getMobile(int userid) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(userid);
		System.out.println(json);
		String url = "http://localhost:9000/user/get-mobile/"+userid;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(userid);
		//output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String mobile = response.toString();
		return mobile;
	}

	public void updateStatus(String tour) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(tour);
		System.out.println(json);
		String url = "http://localhost:9000/user/update-tournament/"+tour;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(tour);
		//output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		//output.close();
	}

	public void rejectTour(String tour) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(tour);
		System.out.println(json);
		String url = "http://localhost:9000/user/reject-tournament/"+tour;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(tour);
		//output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		//output.close();
	}

	public ArrayList getTournament() throws SQLException, IOException {
		String url = "http://localhost:9000/user/get-tour";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Gson gson = new Gson();
		String jsonOutput = response.toString();
		Type listType = new TypeToken<Collection>() {
		}.getType();
		ArrayList tournamentlist = gson.fromJson(jsonOutput, listType);
		return tournamentlist;
	}

	public ArrayList getTournament(String email) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(email);
		System.out.println(json);
		String url = "http://localhost:9000/user/get-tournament/"+email;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(email);
		//output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String jsonOutput = response.toString();
		Type listType = new TypeToken<Collection>() {
		}.getType();
		ArrayList tournamentlist = gson.fromJson(jsonOutput, listType);
		return tournamentlist;
	}

	public void insertTournament(Tournament tour) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(tour,Tournament.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/insert-tournament";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		output.close();
	}

	public boolean isValidEmail(Login credentials) throws SQLException, IOException {
		boolean validate = false;
		String url = "http://localhost:9000/login/email?email=" + credentials.getEmail();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println("response is:" + response.toString());
		// print in String
		Gson gson = new Gson();
		Login result = gson.fromJson(response.toString(), Login.class);
		if (result.getLoginstatus() == true)
			return true;
		else
			return false;
	}

	public boolean isValidCredentials(Login credentials) throws IOException {
		Gson gson = new Gson();
		String json = gson.toJson(credentials,Login.class);
		System.out.println(json);
		String url = "http://localhost:9000/login/password";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String responseString = response.toString();
		Login result = gson.fromJson(response.toString(), Login.class);
		if (result.getLoginstatus() == true)
			return true;
		else
			return false;

	}

	public void insertUserDetails(UserDetails user) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(user, UserDetails.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/user-info";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json; charset=utf-8");

		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		output.close();
	}

	public int getTourId(String tournamentName) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(tournamentName);
		System.out.println(json);
		String url = "http://localhost:9000/user/tour-id/"+tournamentName;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(tournamentName);
		//output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String responseString = response.toString();
		return Integer.parseInt(responseString);
	}

	/*public void insertAdminDetails(AdminDetails adminObj) throws SQLException {
		try {
			daoObj.insertAdmin(adminObj);
		} catch (Exception e) {
			throw e;
		}
	}*/

	public ArrayList showTournament() throws SQLException, IOException {
		String url = "http://localhost:9000/user/show-tournament";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		Gson gson = new Gson();
		System.out.println("Response String:" + response.toString());
		String jsonOutput = response.toString();
		Type listType = new TypeToken<Collection>() {
		}.getType();
		ArrayList tournamentlist = gson.fromJson(jsonOutput, listType);
		System.out.println("tournament is:" + tournamentlist);
		return tournamentlist;
	}

	public ArrayList showTournament(String email) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(email);
		System.out.println(json);
		String url = "http://localhost:9000/user/user-tournament/"+email;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(email);
		//output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String jsonOutput = response.toString();
		System.out.println("response is:"+response.toString());
		Type listType = new TypeToken<Collection>() {
		}.getType();
		ArrayList tournamentlist = gson.fromJson(jsonOutput, listType);
		System.out.println("tournament list is:"+tournamentlist);
		return tournamentlist;
	}

	public int isUserAlreadyRegistered(String email, int tournamentid) throws SQLException, IOException {
		String url = "http://localhost:9000/user/existinguser?tournamentid=" + tournamentid + "&email=" + email;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String teamname = response.toString();
		return Integer.parseInt(teamname);
	}

	public String getTeamName(int tourid, String email) throws SQLException, IOException {
		String url = "http://localhost:9000/user/team?tourid=" + tourid + "&email=" + email;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String teamname = response.toString();
		return teamname;
	}

	public int addTeam(Team team, int userid) throws SQLException, IOException {
		String url = "http://localhost:9000/user/add-team/"+userid;
		Gson gson = new Gson();
		String json = gson.toJson(team,Team.class);
		System.out.println(json);
		//String url = "http://localhost:9000/user/update-scorecard/"+matchno+"/"+teamid;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String teamid = response.toString();
		return Integer.parseInt(teamid);
	}

	public void addPlayer(ArrayList<Player> player, int userid) throws SQLException, IOException {
		String url = "http://localhost:9000/user/add-player?player=" + player + "&userid=" + userid;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
	}

	public int getPlayerId(int teamid) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(teamid);
		System.out.println(json);
		String url = "http://localhost:9000/user/playerid/"+teamid;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		//OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		//output.write(teamid);
		//output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String playerid = response.toString();
		return Integer.parseInt(playerid);
	}

	public void updatePlayer(ArrayList<Player> player, int userid, int playerid) throws SQLException, IOException {
		//String url = "http://localhost:9000/user/update-player?player=" + player + "&userid=" + userid + "&playerid="
		//		+ playerid;
		Gson gson = new Gson();
		String json = gson.toJson(player);
		System.out.println(json);
		String url = "http://localhost:9000/user/update-player/"+userid+"/"+playerid;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		output.close();
	}

	/*public void addTournament(Tournament tourobj, Login loginObj) throws SQLException {
		try {
			daoObj.insertTournament(tourobj, loginObj);
		} catch (SQLException e) {
			throw e;
		}
	}*/

	/*public ArrayList viewRequestStatus(Login credentials) throws SQLException {
		ArrayList approvalStatus = new ArrayList();
		try {
			approvalStatus = daoObj.viewRequestStatus(credentials);
		} catch (SQLException e) {
			throw e;
		}
		return approvalStatus;
	}*/

	/*public ArrayList viewRequestStatus() throws SQLException {
		ArrayList approvalStatus = new ArrayList();
		try {
			approvalStatus = daoObj.viewRequestStatus();
		} catch (SQLException e) {
			throw e;
		}
		return approvalStatus;
	}*/

	/*public void changeRequestStatus(Team team) throws SQLException {
		try {
			daoObj.changeRequestStatus(team);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void removeDeclineTournament(Team team) throws SQLException {
		try {
			daoObj.removeDeclineTournament(team);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList<String> viewTeams(Team object, Login login) throws SQLException {
		try {
			al = daoObj.viewTeams(object, login);
			return al;
		} catch (SQLException e) {
			throw e;
		}
	}*/

	public ArrayList<String> viewTeams(Team tournamentId) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(tournamentId,Team.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/view-teams";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String jsonOutput = response.toString();
		Type listType = new TypeToken<Collection<String>>() {
		}.getType();
		ArrayList<String> teams = gson.fromJson(jsonOutput, listType);
		return teams;
	}

	public String getTeamId(String[] match) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(match);
		System.out.println(json);
		String url = "http://localhost:9000/user/team-id";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String jsonOutput = response.toString();
		System.out.println("Response string of int array:"+response.toString());
		Type listType = new TypeToken<Collection<Integer>>() {
		}.getType();
		ArrayList<Integer> teams = gson.fromJson(jsonOutput, listType);
		int[] team = teams.stream().mapToInt(Integer::intValue).toArray();
		System.out.println("Response string of int array:"+team[0]);
		System.out.println("Response string of int array:"+team[1]);
		String s=team[0]+","+team[1];
		return s;
		
	}

	public int deleteFixtures(Team tournamentId) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(tournamentId,Team.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/delete-fixture";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String fixtureId = response.toString();
		return Integer.parseInt(fixtureId);
	}

	public void fixtures(Schedule obj) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(obj,Schedule.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/generate-fixtures";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		output.close();
	}

	/*public int checkTeamName(Team obj, Team object) throws SQLException {
		try {
			return daoObj.checkTeamName(obj, object);
		} catch (SQLException e) {
			throw e;
		}
	}*/

	public ArrayList<String> schedule(ArrayList<String> teamList) throws SQLException {
		com.zilker.delegates.Schedule scheduleObj = new com.zilker.delegates.Schedule();
		try {
			return scheduleObj.scheduleMatches(teamList);
		} catch (Exception e) {

			throw e;
		}
	}

	/*public int updateTeamName(String[] teamName, Team teamobject) throws SQLException {
		try {
			return daoObj.updateTeamName(teamName, teamobject);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void updatePlayer(ArrayList<String> al, Player playerId) throws SQLException {
		try {
			daoObj.updatePlayer(al, playerId);
		} catch (SQLException e) {
			throw e;
		}
	}*/

	public ArrayList viewSchedule(Team tournamentId) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(tournamentId,Team.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/view-schedule";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		gson = new Gson();
		String jsonOutput = response.toString();
		Type listType = new TypeToken<Collection>() {
		}.getType();
		ArrayList tournamentlist = gson.fromJson(jsonOutput, listType);
		return tournamentlist;
	}

	/*public ArrayList showPlayer(Team teamName) throws SQLException {
		try {
			return daoObj.showPlayer(teamName);
		} catch (SQLException e) {
			throw e;
		}

	}*/

	public void updateScorecard(Score stats, int matchno,int teamid) throws SQLException, IOException {
		//String url = "http://localhost:9000/user/update-scorecard?stats=" + stats + "&matchinfo=" + matchinfo;
		Gson gson = new Gson();
		String json = gson.toJson(stats,Score.class);
		System.out.println(json);
		String url = "http://localhost:9000/user/update-scorecard/"+matchno+"/"+teamid;
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.flush();
		int code = connection.getResponseCode();
		System.out.println("code:"+code);
		output.close();
	}

	/*public void insertScorecard(ArrayList<Scorecard> al) throws SQLException {
		try {
			daoObj.insertScorecard(al);
		} catch (SQLException e) {
			throw e;
		}
	}

	public void updateScore(int[] info, Score obj) throws SQLException {
		try {
			daoObj.updateScore(info, obj);
		} catch (SQLException e) {
			throw e;
		}
	}

	public ArrayList viewScorecard(Scorecard matchNo) throws SQLException {
		try {
			return daoObj.viewScorecard(matchNo);
		} catch (SQLException e) {
			throw e;
		}
	}

	public int deleteTournament(Team tournamentId) throws SQLException {
		try {
			return daoObj.deleteTournament(tournamentId);
		} catch (SQLException e) {
			throw e;
		}
	}*/

	public boolean getRole(Login credentials) throws SQLException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(credentials,Login.class);
		System.out.println(json);
		String url = "http://localhost:9000/login/role";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(json);
		output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String responseString = response.toString();
		return Boolean.parseBoolean(responseString);
	}
}
