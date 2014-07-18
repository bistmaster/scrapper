package org.mit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * This class act as the service to communicate to OECD API
 * @author Bethoveen
 * 
 */
public class ProviderRequester {
	/**
	 * End-point of the OEC developer API
	 */
	private static String API = "http://data.oeconsortium.org/api/";
	
	/**
	 * API Version
	 */
	private static String API_VERSION = "v1";
	
	/**
	 * Complete Url of the API concatenated with API and API_VERSION
	 */
	private static String API_URL = API + API_VERSION + "/";
	
	/**
	 * Represent as provider id
	 */
	private int provider = 0;
	
	/**
	 * 
	 * @param page Represent as the query string or complete url of the API
	 * @return String
	 */
	public String sendRequest(String page, boolean isAPI) {
		String output = "";	
		URL url = null;
		/**
		 * Throw RuntimeException if Provider Id was not set
		 */
		if(provider == 0) {
			throw new RuntimeException("Error: Provider ID was not set");
		}
		
		/**
		 * Act as the communicator to the API end-pont
		 */
		try {
			if (isAPI == Boolean.TRUE) {
				url = new URL(API_URL + page);
			} else {
				url = new URL(page);
			}

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200){
				throw new RuntimeException("Failed, HTTP error code");
			}
			
			InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(inputStream);
			String readline = "";
			while((readline = br.readLine()) != null) {
					output += readline;
			}
		} catch(MalformedURLException e){
			System.out.println(e.getMessage());
		} catch(IOException io){
			System.out.println(io.getMessage());
		}
		return output;
	}
	
	/**
	 * Gets the provider ID
	 * @return int provider
	 */
	public int getProvider() {
		return provider;
	}
	
	/**
	 * 
	 * @param provider Sets the unique provider id
	 */
	public void setProvider(int provider) {
		this.provider = provider;
	}
}
