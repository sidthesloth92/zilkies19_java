package io.ztech.autorate.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Rating;
import io.ztech.autorate.beans.Request;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.beans.User;

public class FetchDetailsDelegate {

	public ArrayList<Make> displayMakes(CarType carType) throws Exception {
		Gson gson = new Gson();
		String carTypeJson = gson.toJson(carType);
//		System.out.println(json);
		String url = "http://localhost:8081/fetch/makes";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(carTypeJson);
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
		Type listType = new TypeToken<List<Make>>() {
		}.getType();
		ArrayList<Make> makes = gson.fromJson(responseString, listType);
		return makes;
	}

	public ArrayList<Specification> getCars(Make make, CarType carType) throws Exception {
		Gson gson = new Gson();
		String url = "http://localhost:8081/fetch/cars/" + make.getMakeId() + "/" + carType.getCarTypeId();
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
		Type listType = new TypeToken<List<Specification>>() {
		}.getType();
		ArrayList<Specification> cars = gson.fromJson(responseString, listType);
		return cars;
	}

	public ArrayList<CarType> displayCarTypes(Make make) throws Exception {
		Gson gson = new Gson();
		String makeJson = gson.toJson(make);
//		System.out.println(json);
		String url = "http://localhost:8081/fetch/cartypes";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(makeJson);
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
		Type listType = new TypeToken<List<CarType>>() {
		}.getType();
		ArrayList<CarType> carTypes = gson.fromJson(responseString, listType);
		return carTypes;
	}

	public HashMap<String, Rating> displayRating(Specification specification) throws Exception {
		Gson gson = new Gson();
		String specificationJson = gson.toJson(specification);
//		System.out.println(json);
		String url = "http://localhost:8081/fetch/ratings";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(specificationJson);
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
		Type listType = new TypeToken<HashMap<String, Rating>>() {
		}.getType();
		HashMap<String, Rating> ratings = gson.fromJson(responseString, listType);
		return ratings;
	}

	public Rating getRating(Specification specification, User user) throws Exception {
		Gson gson = new Gson();
//		System.out.println(json);
		String url = "http://localhost:8081/fetch/rating/" + specification.getCarId() + "/" + user.getUsername();
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
		Rating getRatingResponse = gson.fromJson(responseString, Rating.class);
		return getRatingResponse;
	}

	public Specification getCar(Request request) throws Exception {
		Gson gson = new Gson();
		String requestJson = gson.toJson(request);
//		System.out.println(json);
		String url = "http://localhost:8081/fetch/car";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
		output.write(requestJson);
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
		Specification getCarResponse = gson.fromJson(responseString, Specification.class);
		return getCarResponse;
	}
}
