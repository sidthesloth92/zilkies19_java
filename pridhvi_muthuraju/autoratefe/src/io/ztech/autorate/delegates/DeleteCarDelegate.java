package io.ztech.autorate.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import io.ztech.autorate.beans.Specification;

public class DeleteCarDelegate {

	public boolean deleteCar(Specification specification) throws Exception {
		Gson gson = new Gson();
//		System.out.println(json);
		String url = "http://localhost:8081/car/delete/" + specification.getCarId();
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("DELETE");
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
		Boolean deleteCarResponse = gson.fromJson(responseString, Boolean.class);
		return deleteCarResponse;
	}
}
