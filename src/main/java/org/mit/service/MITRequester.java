package org.mit.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MITRequester {
	
	private static String API = "http://data.oeconsortium.org/api/";
	private static String API_VERSION = "v1";
	private static String API_URL = API + API_VERSION + "/";
	private int provider = 0;
	
	public String sendRequest(String page) {
		String output = "";	
		
		if(provider == 0) {
			throw new RuntimeException("Provider id was not set");
		}
		
		try {
			URL url = new URL(API_URL + page);
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
					output = readline;
			}
		} catch(MalformedURLException e){
			System.out.println(e.getMessage());
		} catch(IOException io){
			System.out.println(io.getMessage());
		}
		return output;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}
}
