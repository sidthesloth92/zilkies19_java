package io.ztech.autorate.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.google.gson.Gson;

import io.ztech.autorate.beans.User;

public class LoginDelegate {
	Logger log = Logger.getLogger("LoginDelegate.class");

	public boolean login(User user) throws Exception {
//		Gson gson = new Gson();
//		String json = gson.toJson(user);
//		System.out.println(json);
//		String url = "http://localhost:8081/autorate/login";
//		URL urlObject = new URL(url);
//		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
//		connection.setRequestMethod("POST");
//		connection.setDoOutput(true);
//		connection.setRequestProperty("Accept", "application/json");
//		connection.setRequestProperty("Content-Type", "application/json");
//		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
//		output.write(json);
//		output.close();
		return true;
	}

	public boolean logout(User user) {
//		return loginDelegate.logoutLogin(false,user);
		return true;
	}

	public boolean signup(User user) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(user);
//		System.out.println(json);
		String url = "http://localhost:8081/login/signup";
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
//		System.out.println(response.toString());
		String responseString = response.toString();
		Boolean signupResponse = gson.fromJson(responseString, Boolean.class);
		return signupResponse;
	}

	public boolean isUser(User user) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(user);
//		System.out.println(json);
		String url = "http://localhost:8081/login/checkuser";
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
//		System.out.println(response.toString());
		String responseString = response.toString();
		Boolean isUserResponse = gson.fromJson(responseString, Boolean.class);
		return isUserResponse;
	}

	public boolean isAdmin(User user) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(user);
//		System.out.println(json);
		String url = "http://localhost:8081/login/checkadmin";
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
//		System.out.println(response.toString());
		String responseString = response.toString();
		Boolean isAdminResponse = gson.fromJson(responseString, Boolean.class);
		return isAdminResponse;
	}
}
