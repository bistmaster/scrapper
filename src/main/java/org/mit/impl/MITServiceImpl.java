package org.mit.impl;

import org.json.simple.JSONArray;
import org.mit.bean.CourseDetail;
import org.mit.bean.CourseList;
import org.mit.service.MITService;
import org.mit.util.JSONParserUtil;
import org.mit.util.MITRequester;
/**
 * 
 * @author Bethoveen
 *
 */
public class MITServiceImpl extends MITRequester implements MITService{
	
	
	
	public MITServiceImpl(int _provider) {
		this.setProvider(_provider);
	}

	public CourseDetail getCourseDetail(String linkHash) {
		String details = sendRequest("courses/view/" + linkHash + "/");
		System.out.println(details);
		return null;
	}

	public void getCourseContent(String link) {
		// TODO Auto-generated method stub
		
	}

	public CourseList getCourseList() {
		String response = sendRequest("providers/" + this.getProvider() + "/courses/");
		JSONParserUtil parser = new JSONParserUtil();
		JSONArray results = (JSONArray) parser.getValue(response, "results");
		parser.getArrayValue(results);
		return null;
	}
	
	public static void main(String[] args) {
		MITServiceImpl service = new MITServiceImpl(13);
		System.out.println("Fetching... ");
		service.getCourseList();
		//service.getCourseDetail("59069fd6f629c3eefa5f8c5d6a39d96a");
	}

}
