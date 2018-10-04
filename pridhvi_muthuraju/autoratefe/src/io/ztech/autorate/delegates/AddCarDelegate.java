package io.ztech.autorate.delegates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Specification;

public class AddCarDelegate {

	public int addCar(CarType carType, Make make, Specification specification) throws Exception {
		Gson gson = new Gson();
		String specificationJson = gson.toJson(specification);
//		System.out.println(json);
		String typeData = "";
		String makeData = "";
		if (carType.getCarTypeId() == 0)
			typeData = carType.getCarTypeName();
		else
			typeData = Integer.toString(carType.getCarTypeId());
		System.out.println(Integer.toString(carType.getCarTypeId()));
		if (make.getMakeId() == 0)
			makeData = make.getMakeName();
		else
			makeData = Integer.toString(make.getMakeId());
		String url = "http://localhost:8081/car/add/" + typeData + "/" + makeData;
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
		int addCarResponse = gson.fromJson(responseString, Integer.class);
		return addCarResponse;
	}
}
