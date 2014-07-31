package org.mit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.mit.bean.CourseContent;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NodeNotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class ScrapperUtil {
	
	private static final String EXCLUDE_DOWNLOAD = "Download Course Materials";
	private static final String COURSE_CONTAINER = "<div id=\"course_nav\">";
	private static final String UNITS_CONTAINER = "<div class=\"mO|mC\">";
	
	private UserAgent userAgent = new UserAgent();
	
	public List<CourseContent> getSectionAndContentByUrl(String courseUrl){
		ArrayList<CourseContent> contents = new ArrayList<CourseContent>();
		try {
			Element firstElement = userAgent.visit(courseUrl).findFirst(COURSE_CONTAINER).getElement(1);
			List<Element> elements = firstElement.getChildElements();
			for(Element elem : elements){
				String key = elem.innerText().trim();
				if(key.indexOf(EXCLUDE_DOWNLOAD) == -1){
					CourseContent course = new CourseContent();
					if(elem.getElement(0).size() == 1){
						key = elem.innerText().trim();
						elem.getElement(0).getAt("href").trim();
						course.setSection(key);	
						course.setTopic("");
					} else {
						if(hasUnits(elem)){
							key = getUnitText(elem);
							course.setSection(key);								
						}
					}
					contents.add(course);
				}				
			}
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NodeNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contents;
	}
	
	public List<CourseContent> getSectionAndContent(Element element){

		return null;
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
	
	private boolean hasUnits(Element element){
		Elements elems = element.findEvery(UNITS_CONTAINER);
		return (elems.size() > 0) ? true : false;
	}
	
	private String getUnitText(Element element) throws NodeNotFound {
		String unitName = ""; 
		Elements elems = element.findEvery(UNITS_CONTAINER);
		for(Element unit: elems){
			unitName = unit.getElement(1).getText().trim();
		}
		return unitName;
	}

	private String getSubUnitText(Element element) throws NodeNotFound {
		String unitName = ""; 
		Elements elems = element.findEvery(UNITS_CONTAINER);
		for(Element unit: elems){
			unitName = unit.getElement(1).getText().trim();
		}
		return unitName;
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
		List<CourseContent> m = mitService.getSectionAndContentByUrl("http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-00sc-introduction-to-computer-science-and-programming-spring-2011/");
		for(CourseContent course: m){
			System.out.println(course.getSection());
		}
	}
}
