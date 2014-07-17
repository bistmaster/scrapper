package org.mit.bean;

/**	
 * 
 * @author Bethoveen
 * @description List of all courses		
 */
public class CourseList {
	private String title = "";
	private String hash = "";
	private String language = "";
	private int id = 0;
	private String author = "";
	
	public CourseList() {}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

}
