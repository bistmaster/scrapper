package org.mit.impl;

import org.mit.bean.CourseDetail;
import org.mit.bean.CourseList;
import org.mit.service.MITRequester;
import org.mit.service.MITService;
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
		// TODO Auto-generated method stub
		return null;
	}

	public void getCourseContent(String link) {
		// TODO Auto-generated method stub
		
	}

	public CourseList getCourseList() {
		// TODO Auto-generated method stub
		System.out.println(sendRequest("providers/" + this.getProvider() + "/courses/"));
		return null;
	}
	
	public static void main(String[] args) {
		MITServiceImpl service = new MITServiceImpl(13);
		System.out.println("Fetching... ");
		service.getCourseList();
	}

}
