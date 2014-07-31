package org.mit.bean;

import java.util.List;

public class CourseContent {
	
	private String section = "";
	private String topic = "";
	private String description = "";
	private List<CourseFile> fileList = null;
	
	public CourseContent(String _section, String _topic, 
			String _description, List<CourseFile> _file) {
		this.section = _section;
		this.topic = _topic;
		this.description = _description;
		this.fileList = _file;
	}
	
	public CourseContent(){
		
	}
	
	public String getSection() {
		return section;
	}
	
	public void setSection(String section) {
		this.section = section;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<CourseFile> getFileList() {
		return fileList;
	}
	
	public void setFileList(List<CourseFile> fileList) {
		this.fileList = fileList;
	}
}
