package io.zilker.fantasy.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.google.gson.Gson;

import io.zilker.fantasy.bean.User;

public class LoginDelegate {
	
	public User loginCaller(String userName , String password) throws IOException {
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/Login");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		
		String parameters = "name=" + userName +"&";
		parameters += "password=" + password;
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
        Gson gson = new Gson();
        String jsonInString = response.toString();
        User user = gson.fromJson(jsonInString, User.class);
        return user;
	}
	
	public boolean addUser(String userName , String email , String password ) throws IOException {
		URL urlObj = new URL("http://localhost:8090/FantasyLeague/SignUp");
		HttpURLConnection httpCon = (HttpURLConnection) urlObj.openConnection();
		
		String parameters = "name=" + userName +"&" +"email="+email +"&";
		parameters += "password=" + password;
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
	
}
