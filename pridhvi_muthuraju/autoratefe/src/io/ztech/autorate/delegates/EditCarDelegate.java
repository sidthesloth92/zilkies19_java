package io.ztech.autorate.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import io.ztech.autorate.beans.Specification;

public class EditCarDelegate {

	public boolean editCar(Specification specification) throws Exception {
		Gson gson = new Gson();
		String specificationJson = gson.toJson(specification);
//		System.out.println(json);
		String url = "http://localhost:8081/car/edit";
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
		Boolean editCarResponse = gson.fromJson(responseString, Boolean.class);
		return editCarResponse;
	}
}
