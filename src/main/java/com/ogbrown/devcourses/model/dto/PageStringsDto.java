/*
 * Copyright (c) 2017 - 2019 Oswald G. Brown, III
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ogbrown.devcourses.model.dto;

import com.ogbrown.utility.text.CsvTextUtility;

import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PageStringsDto that = (PageStringsDto) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(title, that.title) &&
				Objects.equals(urlSlug, that.urlSlug) &&
				Objects.equals(metaDescription, that.metaDescription) &&
				Objects.equals(metaKeywords, that.metaKeywords) &&
				Objects.equals(contentHeader, that.contentHeader) &&
				Objects.equals(content, that.content) &&
				Objects.equals(notes, that.notes) &&
				Objects.equals(published, that.published) &&
				Objects.equals(parentLink, that.parentLink) &&
				Objects.equals(leftSiblingLink, that.leftSiblingLink) &&
				Objects.equals(rightSiblingLink, that.rightSiblingLink) &&
				Objects.equals(courseShortTitle, that.courseShortTitle) &&
				Objects.equals(courseUrlSlug, that.courseUrlSlug);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, urlSlug, metaDescription, metaKeywords, contentHeader, content, notes, published, parentLink, leftSiblingLink, rightSiblingLink, courseShortTitle, courseUrlSlug);
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
