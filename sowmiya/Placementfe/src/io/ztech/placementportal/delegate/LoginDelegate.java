package io.ztech.placementportal.delegate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import io.ztech.placementportal.bean.Register;

public class LoginDelegate {

	public Register checkLoginCredential(Register login) throws Exception {
        Logger log=Logger.getLogger("LoginDelegate.class");
		Gson gson = new Gson();
		String json = gson.toJson(login);
		System.out.println(json);
		String url = "http://localhost:8006/demo/login";
		URL urlObject = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter output = new OutputStreamWriter(connection.getOutputStream());
/*		System.out.println(json);
*/		output.write(json);
        output.close();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
       while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
       }
       in.close();
      System.out.println(response.toString());
      String responseString=response.toString();
      Register loginCredential=gson.fromJson(responseString,Register.class);
      return loginCredential;
	}

}
