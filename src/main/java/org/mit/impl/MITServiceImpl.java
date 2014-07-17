package org.mit.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.mit.bean.CourseDetail;
import org.mit.bean.CourseList;
import org.mit.service.MITService;
/**
 * 
 * @author Bethoveen
 *
 */
public class MITServiceImpl implements MITService{
	
	private static String API = "http://data.oeconsortium.org/api/";
	private static String API_VERSION = "v1";
	private static String API_URL = API + API_VERSION + "/";
	private int provider = 0;
	
	public MITServiceImpl(int _provider) {
		this.provider = _provider;
	}
	
	public void getConnection() {
		try {
			URL url = new URL(API_URL + "providers/" + provider + "/courses");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200){
				throw new RuntimeException("Failed, HTTP error code ");
			}
			
			InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(inputStream);
			String output = "";
			while((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch(MalformedURLException e){
			
		} catch(IOException e){
			
		}
	}

	public CourseDetail getCourseDetail(String linkHash) {
		// TODO Auto-generated method stub
		return null;
	}

	public void getCourseContent(String link) {
		// TODO Auto-generated method stub
		
	}

	public CourseList getCourseList(int provider) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		MITServiceImpl service = new MITServiceImpl(13);
		System.out.println("Fetching... ");
		service.getConnection();
	}
		
}
