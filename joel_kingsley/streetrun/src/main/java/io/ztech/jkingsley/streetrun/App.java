package io.ztech.jkingsley.streetrun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

public class App {
	public static String API_KEY = "AIzaSyBjwR3R1uOpltgiZamNTRcYwXDmyHeDFb0";
	public static String GEO_LOCATE_URL = "https://www.googleapis.com/geolocation/v1/geolocate?key=" + API_KEY;

	public static void displayGeoCoding() {
		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
			GeocodingResult[] results;
			results = GeocodingApi.geocode(context, "TN 600019").await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(results[0].addressComponents));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void printCurrentGeoLocation() {

		LookupService cl;
		String systemipaddress = "";
		URL url_name;
		try {
			url_name = new URL("http://bot.whatismyipaddress.com");
			BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));

			// reads system IPAddress
			systemipaddress = sc.readLine().trim();

			System.out.println(systemipaddress);

			cl = new LookupService(
					"/home/joelkingsley/zilkies2019_java/joel_kingsley/streetrun/src/main/java/io/ztech/jkingsley/streetrun/GeoLiteCity.dat",
					LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
			Location location = cl.getLocation(systemipaddress);
			System.out.println(location.latitude);
			System.out.println(location.longitude);
			System.out.println(location.city);
			System.out.println(location.countryName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
		displayGeoCoding();
		// displayGeoLocation();
		printCurrentGeoLocation();
	}
}
