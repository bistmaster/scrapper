package org.mit.bean;

public class CourseFile {
	
	private String id  = "";
	private String fileType = "";
	private String title = "";
	private String author = "";
	private String url = "";
	
	public CourseFile(String _id, String _file,
			String _title, String _author, String _url) {
		this.id = _id;
		this.fileType = _file;
		this.title = _title;
		this.author = _author;
		this.url = _url;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFileType() {
		return fileType;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
