package org.mit.impl;

import java.util.Date;
import java.util.List;

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
	
	private final JSONParserUtil parser = new JSONParserUtil();
	
	public MITServiceImpl(int _provider) {
		this.setProvider(_provider);
	}

	public CourseDetail getCourseDetail(String linkHash) {
		String details = sendRequest("courses/view/" + linkHash + "/");
                CourseDetail cDetails = new CourseDetail();
                cDetails.setAuthor((String) parser.getValue(details, "author"));
                cDetails.setLinkHash((String) parser.getValue(details, "linkhash"));
                cDetails.setDatePublished((Date) parser.getValue(details, "date_published"));
                cDetails.setDescription((String) parser.getValue(details, "description"));
                cDetails.setId(Integer.parseInt((String) parser.getValue(details, "id")));
                cDetails.setLanguage((String)parser.getValue(details, "language"));
                cDetails.setLinkUrl((String)parser.getValue(details, "linkurl"));
                cDetails.setProvider(Integer.parseInt((String)parser.getValue(details, "provider")));
                cDetails.setProviderName((String)parser.getValue(details, "provider_name"));
                cDetails.setTags((String)parser.getValue(details, "tags"));
                cDetails.setTitle((String)parser.getValue(details, "title"));
		return cDetails;
	}

	public void getCourseContent(String link) {
		// TODO Auto-generated method stub
		
	}

	public List<CourseList> getCourseList() {
		String response = sendRequest("providers/" + this.getProvider() + "/courses/");
		JSONArray results = (JSONArray) parser.getValue(response, "results");
		return parser.getArrayValues(results);
	}
	
	public static void main(String[] args) {
		MITServiceImpl service = new MITServiceImpl(13);
		System.out.println("Fetching... ");
		service.getCourseList();
		service.getCourseDetail("59069fd6f629c3eefa5f8c5d6a39d96a");
	}

}
