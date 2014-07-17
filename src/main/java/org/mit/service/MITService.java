package org.mit.service;

import org.mit.bean.CourseDetail;
import org.mit.bean.CourseList;
/**
 * 
 * @author Bethoveen
 *
 */
public interface MITService {
	public abstract CourseList getCourseList();
	public abstract CourseDetail getCourseDetail(String linkHash);
	public abstract void getCourseContent(String link); 
}
