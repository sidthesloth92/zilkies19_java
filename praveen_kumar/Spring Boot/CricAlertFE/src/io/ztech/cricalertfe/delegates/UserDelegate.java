package io.ztech.cricalertfe.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.google.gson.Gson;

import io.ztech.cricalertfe.beans.User;

public class UserDelegate {
	
	private Logger logger;
	
	public UserDelegate() {
		logger = Logger.getLogger(UserDelegate.class.getName());
	}
	
	public User verifyUser(User user) {
		logger.info("Entered verifyUser");
		User response = new User();
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/users/verify");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(user);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
			
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			response = new Gson().fromJson(buffer.toString(), User.class);
		} catch (Exception e) {
			logger.info(e.toString());
		}
		logger.info("Exited verifyUser");
		return response;
	}
	
	public boolean createUser(User newUser) {
		boolean response = false;
		try {
			URL url = new URL("http://localhost:8090/CricAlertBE/users/create");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			//con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			JSONObject json = new JSONObject(newUser);
			OutputStream os = con.getOutputStream();
			byte[] jsonBytes = json.toString().getBytes();
			os.write(jsonBytes);
			os.close();
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			response = Boolean.parseBoolean(buffer.toString());
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
}
