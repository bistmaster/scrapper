package org.mit.bean;

import java.util.ArrayList;
import java.util.Date;
/**
 * 
 * @author Bethoveen
 *
 */
public class CourseDetail {
	private String linkHash = "";
	private String title = "";
	private String description = "";
	private String tags = "";
	private int provider = 0;
	private String providerName = "";
	private String language = "";
	private Date datePublished = null;
	private int id = 0;
	private String linkUrl = "";
	private String author = "";
	private ArrayList<String> categories = new ArrayList<String>();
	
	public CourseDetail() {}

	public String getLinkHash() {
		return linkHash;
	}

	public void setLinkHash(String linkHash) {
		this.linkHash = linkHash;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	
}
