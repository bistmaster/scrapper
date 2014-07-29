package org.mit.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NodeNotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class ScrapperUtil {
	
	private UserAgent userAgent = new UserAgent();
	
	public HashMap<String, String> getSectionAndContentByUrl(String courseUrl){
		HashMap<String, String> hashMap = new HashMap<String, String>(); 
		HashMap<String, String> content = null;
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
			content = getContentBySection(hashMap);
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NodeNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	@SuppressWarnings("rawtypes")
	private HashMap<String, String> getContentBySection(HashMap<String, String> hashMap){
		if(hashMap == null){
			throw new RuntimeException("Invalid content and section");
		}
		HashMap<String, String> contents = new HashMap<String, String>();
		Iterator<Entry<String, String>> i = hashMap.entrySet().iterator();
		while(i.hasNext()){
			Map.Entry pairs = (Map.Entry) i.next();
			String topic = (String) pairs.getKey();
			String description = getSectionContentByUrl((String) pairs.getValue());	
			contents.put(topic, description);
		}
		return contents;
	}
	
	
	private String getSectionContentByUrl(String contentUrl){
		String content = "";
		try {
			Element firstElement = userAgent.visit(contentUrl).findFirst("<div id=\"course_inner_section|course_inner_chp\">");
			content = firstElement.innerHTML();
			//System.out.println(contentUrl + ":" + content);
		} catch (NodeNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	
	public static void main(String...args){
		ScrapperUtil mitService = new ScrapperUtil();
		HashMap<String, String> m = mitService.getSectionAndContentByUrl("http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-00sc-introduction-to-computer-science-and-programming-spring-2011/");
	}
}
