package io.zilker.application.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

import io.zilker.application.constants.DisplayConstants;

public class RequestAndResponseHandler {
	private static final Logger logger = Logger.getLogger(RequestAndResponseHandler.class.getName());

	public static void sendGetRequest(HttpURLConnection urlConnection) {
		logger.info("Entering sendGetRequest");
		try {
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			urlConnection.setRequestProperty("Accept", "application/json");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			logger.info(DisplayConstants.REQUEST_RESPONSE_ERR);
		} finally {
			logger.info("Leaving sendGetRequest");
		}
	}

	public static void sendPutRequest(HttpURLConnection urlConnection, String json) throws IOException {
		logger.info("Entering sendPutRequest");
		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("PUT");
		urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		urlConnection.setRequestProperty("Accept", "application/json");

		// Writing the post data to the HTTP request body
		BufferedWriter httpRequestBodyWriter = new BufferedWriter(
				new OutputStreamWriter(urlConnection.getOutputStream()));
		httpRequestBodyWriter.write(json);
		httpRequestBodyWriter.close();

		logger.info("Leaving sendPutRequest");
	}

	public static void sendPostRequest(HttpURLConnection urlConnection, String json) throws IOException {
		logger.info("Entering sendPostRequest");
		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		urlConnection.setRequestProperty("Accept", "application/json");

		// Writing the post data to the HTTP request body
		BufferedWriter httpRequestBodyWriter = new BufferedWriter(
				new OutputStreamWriter(urlConnection.getOutputStream()));
		httpRequestBodyWriter.write(json);
		httpRequestBodyWriter.close();
		logger.info("Leaving sendPostRequest");
	}

	public static void sendPostRequestAccept(HttpURLConnection urlConnection, String data) throws IOException {
		logger.info("Entering sendPostRequest");
		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		// urlConnection.setRequestProperty("Accept", "application/json");

		// Writing the post data to the HTTP request body
		BufferedWriter httpRequestBodyWriter = new BufferedWriter(
				new OutputStreamWriter(urlConnection.getOutputStream()));
		httpRequestBodyWriter.write(data);
		httpRequestBodyWriter.close();
		logger.info("Leaving sendPostRequest");
	}

	public static HttpURLConnection getUrlConnection(String urlEndPoint) {
		HttpURLConnection urlConnection = null;
		// Define the server end point to send the HTTP request to
		try {
			URL serverUrl = new URL(urlEndPoint);
			urlConnection = (HttpURLConnection) serverUrl.openConnection();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(DisplayConstants.REQUEST_RESPONSE_ERR);
		}
		return urlConnection;
	}

	public static StringBuffer readFromHttpResponse(HttpURLConnection urlConnection) throws IOException {
		String inputLine;
		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		return response;
	}
}
