package com.zilker.delegates;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.zilker.beans.LoggedInUserData;

public class LoginDelegate {

	public LoggedInUserData isValidUser(Long registrationNumber, String password) throws SQLException {
		StringBuffer response = new StringBuffer();
		try {

			URL url = new URL("http://localhost:8000/ResultApplication/login");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "application/json");
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes("registrationNumber=" + registrationNumber.toString() + "&password=" + password);
			out.flush();
			out.close();

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		LoggedInUserData currentUser = new Gson().fromJson(response.toString(), LoggedInUserData.class);
		System.out.println(currentUser.getRole());
		return currentUser;
	}
}
