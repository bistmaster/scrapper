package org.mit.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	/**
	 *  Parser
	 */
	private final JSONParserUtil parser = new JSONParserUtil();
	
	/**
	 * Courses being fetch per page
	 */
	private final static int COURSE_PAGE = 25;
	
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
	@SuppressWarnings("rawtypes")
	public List<CourseList> getCourseList(int page) {
		if(page == 0){
			page = 1;
		} else {
			int courseLength = getCoursePages();
			if(page > courseLength) {
				throw new RuntimeException("Invalid page exception, Unable to fetch page number.");
			} 
		} 
		List<CourseList> listCourse = new ArrayList<CourseList>();
		String response = sendRequest("providers/" + this.getProvider() + "/courses/?page=" + page, true);
		JSONArray results = (JSONArray) parser.getValue(response, "results");
		Iterator i = parser.getCoursesOnJson(results);
		while(i.hasNext()){
			JSONObject json = (JSONObject) i.next();
			String desc = getCourseDescription((String) json.get("linkhash"));
			CourseList cl = new CourseList();
			cl.setAuthor((String) json.get("author"));
			cl.setDescription(desc);
			cl.setHash((String) json.get("linkhash"));
			cl.setId((Long) json.get("id"));
			cl.setLanguage((String) json.get("language"));
			cl.setTitle((String) json.get("title"));
			listCourse.add(cl);			
		}
		return listCourse;
	}
	
	/**
	 * Gets the total count of courses
	 * @return long a number of course return
	 */
	public long getCourseCount() {
		String results = sendRequest("providers/" + this.getProvider() + "/courses/", true);
		return (Long) parser.getValue(results, "count");
	}
	
	/**
	 * Gets a number of pages of the courses offered by the provider
	 * @return int number of pages
	 */
	public int getCoursePages() {
		return Math.round(getCourseCount() / COURSE_PAGE);		
	}
	
	/**
	 * Gets the course description of the course
	 * @param linkHash unique hash value of the course that represents OECD
	 * @return String course description
	 */
	public String getCourseDescription(String linkHash){
		String description = sendRequest("courses/view/" + linkHash + "/", true);
		return (String) parser.getValue(description, "description");
	}
	
	public static void main(String[] args) {
		System.out.println("Fetching... ");
		ProviderServiceImpl service = new ProviderServiceImpl(13);
		service.getCourseList(9);
		service.getCourseDetail("59069fd6f629c3eefa5f8c5d6a39d96a");
		//System.out.println(service.getCourseDescription("59069fd6f629c3eefa5f8c5d6a39d96a"));
		//service.getCourseContent("http://ocw.mit.edu/courses/nuclear-engineering/22-033-nuclear-systems-design-project-fall-2011");
	}

}
