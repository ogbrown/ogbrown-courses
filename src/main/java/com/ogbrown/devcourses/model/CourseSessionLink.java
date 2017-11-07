package com.ogbrown.devcourses.model;

public class CourseSessionLink {
	private String title;
	private String text;
	private String urlSlug;
	
	
	public CourseSessionLink() {
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrlSlug() {
		return urlSlug;
	}
	public void setUrlSlug(String urlSlug) {
		this.urlSlug = urlSlug;
	}

	@Override
	public String toString() {
		return "CourseSessionLink [title=" + title + ", text=" + text + ", urlSlug=" + urlSlug + "]";
	}
	
	
}
