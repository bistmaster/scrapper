package org.mit.util;

import java.util.HashMap;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NodeNotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class ScrapperUtil {
	
	private UserAgent userAgent = new UserAgent();
	
	public HashMap<String, String> getSectionAndUrl(String courseUrl){
		HashMap<String, String> hashMap = new HashMap<String, String>(); 
		try {
			Element firstElement = userAgent.visit(courseUrl).findFirst("<div id=\"course_nav\">");
			Elements elements = firstElement.findEvery("<li class>");
			for(Element elem : elements){
				String key = elem.innerText().trim();
				String url = elem.findFirst("<a href>").getAt("href").trim();
				if(key.indexOf("Download Course Materials") == -1 && url.indexOf("#") == -1){
					hashMap.put(key, url);	
				}
			}
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NodeNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashMap;
	}
	
	public static void main(String...args){
		ScrapperUtil mitService = new ScrapperUtil();
		mitService.getSectionAndUrl("http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-00sc-introduction-to-computer-science-and-programming-spring-2011/unit-1/lecture-2-core-elements-of-a-program/");
	}
}
