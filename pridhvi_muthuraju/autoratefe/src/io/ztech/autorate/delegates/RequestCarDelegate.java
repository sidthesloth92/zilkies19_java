package io.ztech.autorate.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;

public class RequestCarDelegate {

	public int addCarUserRequest(User user, Specification specification) throws Exception {
		Gson gson = new Gson();
		System.out.println("lalalala:" + specification.getCarId());
//		System.out.println(json);
		String url = "http://localhost:8081/request/addcar/" + specification.getCarId() + "/" + user.getUsername();
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
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
		int addCarUserRequestResponse = gson.fromJson(responseString, Integer.class);
		return addCarUserRequestResponse;
	}

	public ArrayList<Request> getRequests(User user) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(user);
//		System.out.println(json);
		String url = "http://localhost:8081/request/getrequests";
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
		Type listType = new TypeToken<List<Request>>() {
		}.getType();
		ArrayList<Request> getRequestsResponse = gson.fromJson(responseString, listType);
		return getRequestsResponse;
	}

	public boolean approveCar(Specification specification, Request request) throws Exception {
		Gson gson = new Gson();
//		System.out.println(json);
		String url = "http://localhost:8081/request/addcar/" + specification.getCarId() + "/" + request.getRequestId();
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
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
		Boolean approveCarResponse = gson.fromJson(responseString, Boolean.class);
		return approveCarResponse;
	}
}
