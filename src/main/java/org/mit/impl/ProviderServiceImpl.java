package org.mit.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.mit.bean.CourseDetail;
import org.mit.bean.CourseList;
import org.mit.service.ProviderService;
import org.mit.util.JSONParserUtil;
import org.mit.util.ProviderRequester;
/**
 * 
 * @author Bethoveen
 *
 */
public class ProviderServiceImpl extends ProviderRequester implements ProviderService{
	
	private final JSONParserUtil parser = new JSONParserUtil();
	
	/**
	 * @param _provider Uniquie ID Identifier of the Provider from the OEC
	 */
	public ProviderServiceImpl(int _provider) {
		this.setProvider(_provider);
	}

	/**
	 * @param linkHash A unique hash value of the course which represent as a unique identifier
	 * @return CourseDetail bean class 
	 */
	public CourseDetail getCourseDetail(String linkHash) {
		String details = sendRequest("courses/view/" + linkHash + "/", true);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse((String) parser.getValue(details, "date_published"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
		CourseDetail cDetails = new CourseDetail();
        cDetails.setAuthor((String) parser.getValue(details, "author"));
        cDetails.setLinkHash((String) parser.getValue(details, "linkhash"));
        cDetails.setDatePublished(dateString);
        cDetails.setDescription((String) parser.getValue(details, "description"));
        cDetails.setId((Long) parser.getValue(details, "id"));
        cDetails.setLanguage((String)parser.getValue(details, "language"));
        cDetails.setLinkUrl((String)parser.getValue(details, "linkurl"));
        cDetails.setProvider((Long) parser.getValue(details, "provider"));
        cDetails.setProviderName((String)parser.getValue(details, "provider_name"));
        cDetails.setTags((String)parser.getValue(details, "tags"));
        cDetails.setTitle((String)parser.getValue(details, "title"));
		return cDetails;
	}
	
	/**
	 * Work-in-progress
	 * Get the Course Details from the Institution
	 */
	@SuppressWarnings("unused")
	public void getCourseContent(String link) {
		String[] sections = { "Syllabus", "calendar", "readings", "assignments", "download-course-materials", "videos-class-notes", };
		String html = sendRequest(link, false);
		System.out.println(html);
	}
	
	/**
	 *  Get all the courses based on the provider id supplied in the constructor
	 *  @return List
	 */
	public List<CourseList> getCourseList() {
		String response = sendRequest("providers/" + this.getProvider() + "/courses/", true);
		System.out.println(response);
		JSONArray results = (JSONArray) parser.getValue(response, "results");
		return parser.getCoursesOnJson(results);
	}
	
	public static void main(String[] args) {
		System.out.println("Fetching... ");
		ProviderServiceImpl service = new ProviderServiceImpl(13);
		service.getCourseList();
		service.getCourseDetail("59069fd6f629c3eefa5f8c5d6a39d96a");
		service.getCourseContent("http://ocw.mit.edu/courses/nuclear-engineering/22-033-nuclear-systems-design-project-fall-2011");
	}

}
