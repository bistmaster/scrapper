package org.mit.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mit.bean.CourseBean;

public interface CourseScrapperService {
	public abstract HashMap<String, ArrayList<CourseBean>> getAllCourseContent(String courseUrl);
}
