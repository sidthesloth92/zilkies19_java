package io.ztech.jkingsley.hrmanagement.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.ztech.jkingsley.hrmanagement.beans.objects.Employee;
import io.ztech.jkingsley.hrmanagement.beans.objects.Profile;
import io.ztech.jkingsley.hrmanagement.beans.objects.ResponseObject;

public class RestCaller {
	
	private final static Logger LOGGER = Logger.getLogger(RestCaller.class.getName());
	
	public JsonReader doGet(String url) throws MalformedURLException {
		
		LOGGER.info("RestCaller::doGet Entering");
		
		try {
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			JsonReader reader = Json.createReader(connection.getInputStream());
			
			return reader;
			
			
		} catch (ProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			LOGGER.info("RestCaller::doGet Exiting");
		}
	}
	
}
