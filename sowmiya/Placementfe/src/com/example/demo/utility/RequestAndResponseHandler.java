package com.example.demo.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class RequestAndResponseHandler {
	private Logger log = Logger.getLogger("RequestAndResponseHandler.class");

	public String getRequestHandler(String url, String method) {
		log.info("In getrequest() of RequestAndResponseHandler ");
		try {
			HttpURLConnection connection = getConnection(url);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			return getResponse(connection);

		} catch (Exception e) {
			log.info(e.toString());
		}
		return null;

	}

	public HttpURLConnection getConnection(String url) {
		try {
			URL urlObject = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
			return connection;
		} catch (IOException e) {
			log.warning(e.toString());
		}
		return null;
	}

	public String getResponse(HttpURLConnection connection) {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		} catch (IOException e) {
			log.info(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	public String postRequestHandler(String url, String json, String method) {
		log.info("Entering postrequest() of RequestAndResponseHandler ");
		try {
			HttpURLConnection connection = getConnection(url);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
			output.write(json);
			output.close();
			return getResponse(connection);
		} catch (Exception e) {
			log.warning(e.toString());
		}
		log.info("Exiting postrequest() of RequestAndResponseHandler ");
		return null;

	}
}
