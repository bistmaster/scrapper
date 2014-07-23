package org.mit.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mit.bean.CourseDetail;
import org.mit.impl.ProviderServiceImpl;


/**
 * Unit test for simple App.
 */
public class ProvideServiceImpleTest {
	
	private ProviderServiceImpl service = null;
	private CourseDetail courseDetail = null;
	
	@Before
	public void start(){
		service = new ProviderServiceImpl(13);
		courseDetail = service.getCourseDetail("2f4a43eb63513a3ecb353c85396527d7");
		
	}
	
	@Test
	public void testProviderServiceImplProvider() {
		assertEquals(service.getProvider(), 13);
	}
	
	@Test
	public void testCourseDetails() {
		assertEquals(courseDetail.getProviderName(), "Massachusetts Institute of Technology");
	}
	
	@Test
	public void testCourseCount() {
		assertEquals(service.getCourseCount(), 2412);
	}
	
	@Test
	public void testCoursePages() {
		assertEquals(service.getCoursePages(), 96);
	}

	@Test
	public void testCourseList() {
		assertEquals(service.getCourseList(1).size(), 25);
	}
	
}
