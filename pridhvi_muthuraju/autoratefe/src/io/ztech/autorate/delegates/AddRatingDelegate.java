package io.ztech.autorate.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;

public class AddRatingDelegate {

	public boolean addRating(Specification specification, Rating rating, User user) throws Exception {
		Gson gson = new Gson();
		String ratingJson = gson.toJson(rating);
//		System.out.println(json);
		String url = "http://localhost:8081/rating/add/" + specification.getCarId() + "/" + user.getUsername();
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(ratingJson);
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
		Boolean addRatingResponse = gson.fromJson(responseString, Boolean.class);
		return addRatingResponse;
	}

	public boolean editRating(Specification specification, Rating rating, User user) throws Exception {
		Gson gson = new Gson();
		String ratingJson = gson.toJson(rating);
//		System.out.println(json);
		String url = "http://localhost:8081/rating/edit/" + specification.getCarId() + "/" + user.getUsername();
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(ratingJson);
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
		Boolean editRatingResponse = gson.fromJson(responseString, Boolean.class);
		return editRatingResponse;
	}

	public boolean isRating(Specification specification, User user) throws Exception {
		Gson gson = new Gson();
//		System.out.println(json);
		String url = "http://localhost:8081/rating/checkrating/" + specification.getCarId() + "/" + user.getUsername();
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
//		System.out.println(response.toString());
		String responseString = response.toString();
		Boolean isRatingResponse = gson.fromJson(responseString, Boolean.class);
		return isRatingResponse;
	}
}
