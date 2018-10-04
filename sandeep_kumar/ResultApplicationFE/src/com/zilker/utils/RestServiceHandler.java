package com.zilker.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestServiceHandler {
	public String restServiceHandler(String restUrl, String method, String jsonData) {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(restUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setRequestProperty("Accept", "application/json");
			if (method.equals("POST") || method.equals("PUT")) {
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/json");
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				out.writeBytes(jsonData.toString());
				out.flush();
				out.close();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			System.out.println("Output from Server");
			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			System.out.println(response.toString());
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}
}
