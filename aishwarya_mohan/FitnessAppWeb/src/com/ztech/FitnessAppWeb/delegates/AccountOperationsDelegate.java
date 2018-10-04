package com.ztech.FitnessAppWeb.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ztech.FitnessAppWeb.beans.User;

public class AccountOperationsDelegate {
	Logger logger;

	public AccountOperationsDelegate() {
		logger = Logger.getLogger(AccountOperationsDelegate.class.getName());
	}

	public boolean login(User user) {
		logger.info("enter login(user) @ AccountOperationsDelegate");
		String url = "http://localhost:8051/login";

		Gson gson = new Gson();

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("userName", user.getUserName());
		jsonObj.addProperty("password", user.getPassword());

		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			wr.write(jsonObj.toString());
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//System.out.println("RESPONSE" + response.toString());

			User userReturned = gson.fromJson(new String(response), User.class);

			//System.out.println("returned" + userReturned.getUserName());

			if (user.getUserName().equals(userReturned.getUserName())) {
				logger.info("exit login(user) @ AccountOperationsDelegate with true status");
				return true;
			} else {
				logger.info("exit login(user) @ AccountOperationsDelegate with false status");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
