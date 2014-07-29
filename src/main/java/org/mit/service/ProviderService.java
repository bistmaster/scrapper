package org.mit.service;

import java.util.HashMap;
import java.util.List;

import org.mit.bean.CourseDetail;
import org.mit.bean.CourseList;
/**
 * Interface that provides a methods to get the Courses, CourseDetails, and Getting the Resources
 * @author Bethoveen
 *
 */
public interface ProviderService {
	public abstract List<CourseList> getCourseList(int page);
	public abstract CourseDetail getCourseDetail(String linkHash);
	public abstract HashMap<String, String> getAllCourseContent(String link); 
}
