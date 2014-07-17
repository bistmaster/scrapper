package org.mit.service;

import java.util.List;

import org.mit.bean.CourseDetail;
import org.mit.bean.CourseList;
/**
 * 
 * @author Bethoveen
 *
 */
public interface MITService {
	public abstract List<CourseList> getCourseList();
	public abstract CourseDetail getCourseDetail(String linkHash);
	public abstract void getCourseContent(String link); 
}
