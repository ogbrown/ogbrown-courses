package com.ogbrown.devcourses.web.dto;

import com.ogbrown.utility.text.CsvTextUtility;

public class PageStringsDto {
	private Long id;
	private String title;
	private String urlSlug;
	private String metaDescription;
	private String metaKeywords;
	private String contentHeader;
	private String content;
	private String notes;
	private Boolean published = false;
	private MenuItemDto parentLink;
	private MenuItemDto leftSiblingLink;
	private MenuItemDto rightSiblingLink;
	private String courseShortTitle;
	private String courseUrlSlug;

	public PageStringsDto() {
		this.title = "";
		this.urlSlug = "";
		this.metaDescription = "";
		this.metaKeywords = "";
		this.contentHeader = "";
		this.content = "";
		this.notes = "";
		this.parentLink = null;
		this.leftSiblingLink = null;
		this.rightSiblingLink = null;
		this.courseShortTitle = "";
		this.courseUrlSlug = "";
	}
	
	public PageStringsDto(com.ogbrown.devcourses.model.Page p) {
		this.id = p.getId();
		this.title = p.getTitle();
		this.urlSlug = p.getUrlSlug();
		this.metaDescription = p.getMetaDescription();
		this.metaKeywords = CsvTextUtility.commaSeparateString(p.getMetaKeywords());
		this.contentHeader = p.getContentHeader();
		this.content = p.getContent();
		this.published = p.hasPublished();
		this.notes = p.getNotes();
		if (p.getCourseSessions() != null && p.getCourseSessions().size() > 0) { 
			this.courseShortTitle = p.getCourseSessions().iterator().next().getCourse().getShortTitle();
			this.courseUrlSlug = p.getCourseSessions().iterator().next().getCourse().getUrlSlug();
		}
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUrlSlug() {
		return urlSlug;
	}


	public void setUrlSlug(String urlSlug) {
		this.urlSlug = urlSlug;
	}


	public String getMetaDescription() {
		return metaDescription;
	}


	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}


	public String getMetaKeywords() {
		return metaKeywords;
	}


	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}


	public String getContentHeader() {
		return contentHeader;
	}

	public void setContentHeader(String contentHeader) {
		this.contentHeader = contentHeader;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Boolean getPublished() {
		return published;
	}


	public void setPublished(Boolean published) {
		this.published = published;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public MenuItemDto getParentLink() {
		return parentLink;
	}

	public void setParentLink(MenuItemDto parentLink) {
		this.parentLink = parentLink;
	}

	public MenuItemDto getLeftSiblingLink() {
		return leftSiblingLink;
	}

	public void setLeftSiblingLink(MenuItemDto leftSiblingLink) {
		this.leftSiblingLink = leftSiblingLink;
	}

	public MenuItemDto getRightSiblingLink() {
		return rightSiblingLink;
	}

	public void setRightSiblingLink(MenuItemDto rightSiblingLink) {
		this.rightSiblingLink = rightSiblingLink;
	}

	public String getCourseShortTitle() {
		return courseShortTitle;
	}

	public void setCourseShortTitle(String courseShortTitle) {
		this.courseShortTitle = courseShortTitle;
	}

	public String getCourseUrlSlug() {
		return courseUrlSlug;
	}

	public void setCourseUrlSlug(String courseUrlSlug) {
		this.courseUrlSlug = courseUrlSlug;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((contentHeader == null) ? 0 : contentHeader.hashCode());
		result = prime * result + ((courseShortTitle == null) ? 0 : courseShortTitle.hashCode());
		result = prime * result + ((courseUrlSlug == null) ? 0 : courseUrlSlug.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((leftSiblingLink == null) ? 0 : leftSiblingLink.hashCode());
		result = prime * result + ((metaDescription == null) ? 0 : metaDescription.hashCode());
		result = prime * result + ((metaKeywords == null) ? 0 : metaKeywords.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((parentLink == null) ? 0 : parentLink.hashCode());
		result = prime * result + ((published == null) ? 0 : published.hashCode());
		result = prime * result + ((rightSiblingLink == null) ? 0 : rightSiblingLink.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((urlSlug == null) ? 0 : urlSlug.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageStringsDto other = (PageStringsDto) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (contentHeader == null) {
			if (other.contentHeader != null)
				return false;
		} else if (!contentHeader.equals(other.contentHeader))
			return false;
		if (courseShortTitle == null) {
			if (other.courseShortTitle != null)
				return false;
		} else if (!courseShortTitle.equals(other.courseShortTitle))
			return false;
		if (courseUrlSlug == null) {
			if (other.courseUrlSlug != null)
				return false;
		} else if (!courseUrlSlug.equals(other.courseUrlSlug))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (leftSiblingLink == null) {
			if (other.leftSiblingLink != null)
				return false;
		} else if (!leftSiblingLink.equals(other.leftSiblingLink))
			return false;
		if (metaDescription == null) {
			if (other.metaDescription != null)
				return false;
		} else if (!metaDescription.equals(other.metaDescription))
			return false;
		if (metaKeywords == null) {
			if (other.metaKeywords != null)
				return false;
		} else if (!metaKeywords.equals(other.metaKeywords))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (parentLink == null) {
			if (other.parentLink != null)
				return false;
		} else if (!parentLink.equals(other.parentLink))
			return false;
		if (published == null) {
			if (other.published != null)
				return false;
		} else if (!published.equals(other.published))
			return false;
		if (rightSiblingLink == null) {
			if (other.rightSiblingLink != null)
				return false;
		} else if (!rightSiblingLink.equals(other.rightSiblingLink))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (urlSlug == null) {
			if (other.urlSlug != null)
				return false;
		} else if (!urlSlug.equals(other.urlSlug))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PageStringsDto [id=" + id + ", title=" + title + ", urlSlug=" + urlSlug + ", metaDescription="
				+ metaDescription + ", metaKeywords=" + metaKeywords + ", contentHeader=" + contentHeader + ", content="
				+ content + ", notes=" + notes + ", published=" + published + ", parentLink=" + parentLink
				+ ", leftSiblingLink=" + leftSiblingLink + ", rightSiblingLink=" + rightSiblingLink
				+ ", courseShortTitle=" + courseShortTitle + ", courseUrlSlug=" + courseUrlSlug + "]";
	}



}
