package org.mit.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.mit.bean.CourseBean;
import org.mit.service.CourseScrapperService;
import org.mit.util.ProviderRequester;

import com.jaunt.Elements;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class CourseScrapperServiceMIT extends ProviderRequester implements CourseScrapperService {
	
	private UserAgent userAgent = null;
	
	public HashMap<String, ArrayList<CourseBean>> getAllCourseContent(String courseUrl) {
		if(courseUrl == ""){
			throw new RuntimeException("Invalid Url " + courseUrl + " , please provide university URL");
		}
		String request = sendRequest(courseUrl, false);
		try {
			userAgent.openContent(request);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(request);

		
		return null;
	}


	
	public static void main(String...args){
		CourseScrapperServiceMIT mitService = new CourseScrapperServiceMIT();
		mitService.setProvider(13);
		mitService.getAllCourseContent("http://ocw.mit.edu/courses/mechanical-engineering/2-60-fundamentals-of-advanced-energy-conversion-spring-2004/");
	}
}
