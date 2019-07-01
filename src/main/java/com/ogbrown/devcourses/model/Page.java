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

package com.ogbrown.devcourses.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Cacheable(value="deviceCache")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Page implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	@Column(unique = true)
	private String urlSlug;
	private String folderUrlSlug;
	private String metaDescription;
	private String contentHeader;
	@ElementCollection
	private Set<String> metaKeywords;
	@Column(columnDefinition="TEXT")
	private String content;
	private Boolean published = false;
	private Boolean protectedPage = false;
	@Column(columnDefinition="SMALLINT")
	private Short pageOrd;
	@ManyToMany(targetEntity = Page.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "children_parents_pages", joinColumns = { @JoinColumn(name = "childid") }, inverseJoinColumns = { @JoinColumn(name = "parentid") })
	private List<Page> parentPages;
	@ManyToMany(targetEntity = Page.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "parents_children_pages", joinColumns = { @JoinColumn(name = "childid") }, inverseJoinColumns = { @JoinColumn(name = "parentid") })
	private List<Page> childPages;
	private String prevPageLinkOverride;
	@Transient
	private Page previousPage;
	private String nextPageLinkOverride;
	@Transient
	private Page nextPage;
	@ManyToMany(targetEntity = CourseSession.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "coursesessions_pages", joinColumns = { @JoinColumn(name = "pageid") }, inverseJoinColumns = { @JoinColumn(name = "courseid"), @JoinColumn(name = "sessionnumber")})
	private Set<CourseSession> courseSessions;
	private String notes;
	
	public Page() {
	    this.folderUrlSlug = null;
	}

	public Page(Page p) {
		this.id = p.id;
		this.title = p.title;
		this.urlSlug = p.urlSlug;
		this.folderUrlSlug = p.folderUrlSlug;
		this.metaDescription = p.metaDescription;
		this.contentHeader = p.contentHeader;
		this.metaKeywords = p.metaKeywords;
		this.content = p.content;
		this.published = p.published;
		this.protectedPage = p.protectedPage;
		this.pageOrd = p.pageOrd;
		this.parentPages = p.parentPages;
		this.childPages = p.childPages;
		this.prevPageLinkOverride = p.prevPageLinkOverride;
		this.previousPage = p.previousPage;
		this.nextPageLinkOverride = p.nextPageLinkOverride;
		this.nextPage = p.nextPage;
		this.courseSessions = p.courseSessions;
		this.notes = p.notes;
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

	public String getFolderUrlSlug() {
        return folderUrlSlug;
    }

    public void setFolderUrlSlug(String folderUrlSlug) {
        this.folderUrlSlug = folderUrlSlug;
    }

    public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public Set<String> getMetaKeywords() {
		return metaKeywords;
	}
	public void setMetaKeywords(Set<String> metaKeywords) {
		this.metaKeywords = metaKeywords;
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
	public Boolean hasPublished() {
		return published;
	}
	public void publish() {
		published = true;
	}
	public void draft() {
		published = false;
	}
	public void setPublished(Boolean published) {
		this.published = published;
	}

	public String getContentHeader() {
		return contentHeader;
	}
	public void setContentHeader(String contentHeader) {
		this.contentHeader = contentHeader;
	}

	public Short getPageOrd() {
		return pageOrd;
	}
	public void setPageOrd(Short ord) {
		this.pageOrd = ord;
	}

	public String getPrevPageLinkOverride() {
		return prevPageLinkOverride;
	}
	public void setPrevPageLinkOverride(String prevPageLinkOverride) {
		this.prevPageLinkOverride = prevPageLinkOverride;
	}

	public String getNextPageLinkOverride() {
		return nextPageLinkOverride;
	}
	public void setNextPageLinkOverride(String nextPageLinkOverride) {
		this.nextPageLinkOverride = nextPageLinkOverride;
	}

	public Page getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(Page previousPage) {
		this.previousPage = previousPage;
	}

	public Page getNextPage() {
		return nextPage;
	}

	public void setNextPage(Page nextPage) {
		this.nextPage = nextPage;
	}

	public List<Page> getParentPages() {
		return parentPages;
	}

	public void setParentPages(List<Page> parentPages) {
		this.parentPages = parentPages;
	}

	public List<Page> getChildPages() {
		return childPages;
	}

	public void setChildPages(List<Page> childPages) {
		this.childPages = childPages;
	}

	public Set<CourseSession> getCourseSessions() {
		return courseSessions;
	}

	public void setCourseSessions(Set<CourseSession> courseSessions) {
		this.courseSessions = courseSessions;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Boolean isProtectedPage() {
		return protectedPage;
	}

	public void setProtectedPage(Boolean protectedPage) {
		this.protectedPage = protectedPage;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Page page = (Page) o;
		return Objects.equals(id, page.id) &&
				Objects.equals(title, page.title) &&
				Objects.equals(urlSlug, page.urlSlug) &&
				Objects.equals(folderUrlSlug, page.folderUrlSlug) &&
				Objects.equals(metaDescription, page.metaDescription) &&
				Objects.equals(contentHeader, page.contentHeader) &&
				Objects.equals(metaKeywords, page.metaKeywords) &&
				Objects.equals(content, page.content) &&
				Objects.equals(published, page.published) &&
				Objects.equals(protectedPage, page.protectedPage) &&
				Objects.equals(pageOrd, page.pageOrd) &&
				Objects.equals(parentPages, page.parentPages) &&
				Objects.equals(childPages, page.childPages) &&
				Objects.equals(prevPageLinkOverride, page.prevPageLinkOverride) &&
				Objects.equals(previousPage, page.previousPage) &&
				Objects.equals(nextPageLinkOverride, page.nextPageLinkOverride) &&
				Objects.equals(nextPage, page.nextPage) &&
				Objects.equals(courseSessions, page.courseSessions) &&
				Objects.equals(notes, page.notes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, urlSlug, folderUrlSlug, metaDescription, contentHeader, metaKeywords, content, published, protectedPage, pageOrd, parentPages, childPages, prevPageLinkOverride, previousPage, nextPageLinkOverride, nextPage, courseSessions, notes);
	}

	@Override
	public String toString() {
		String startOfString = "Page [id=" + id + ", title=" + title + ", urlSlug=" + urlSlug + ", folderUrlSlug=" + folderUrlSlug +
		        ", metaDescription=" + metaDescription 
				+ ", contentHeader=" + contentHeader + ", metaKeywords=" + metaKeywords + ", content=" + content
				+ ", protectedPage=" + protectedPage + ", published=" + published + ", pageOrd=" + pageOrd + ", parentPages=" + parentPages
				+ ", childPages=" + childPages + ", prevPageLinkOverride=" + prevPageLinkOverride + ", previousPage=";
		if (this.previousPage == null) {
			startOfString += "null";
		} else {
			startOfString += this.previousPage.getContentHeader();
		};
		String middleOfString =  ", nextPageLinkOverride=" + nextPageLinkOverride + ", nextPage=";
		if (this.nextPage == null) {
			middleOfString += "null";
		} else {
			middleOfString += this.nextPage.getContentHeader();
		};
		String endOfString = ", courseSessions=" + courseSessions + ", notes=" + notes + "]";
		return startOfString + middleOfString + endOfString;
	}
	@Override
	public Page clone() {
		Page pageClone = new Page();
		pageClone.title = this.title;
		pageClone.urlSlug = this.urlSlug;
		pageClone.folderUrlSlug = this.folderUrlSlug;
		pageClone.metaDescription = this.metaDescription;
		pageClone.contentHeader = this.contentHeader;
		if (null == this.metaKeywords) {
			pageClone.metaKeywords = null;
		} else {
			pageClone.metaKeywords = new HashSet<String>(this.metaKeywords);
		}
		pageClone.content = this.content;
		pageClone.published = this.published;
		pageClone.protectedPage = this.protectedPage;
		pageClone.pageOrd = this.pageOrd;
		if (null == this.parentPages) {
			pageClone.parentPages = null;
		} else {
			pageClone.parentPages = new ArrayList<Page>(this.parentPages);
		}
		if (null == this.childPages) {
			pageClone.childPages = null;
		} else {
			pageClone.childPages = new ArrayList<Page>(this.childPages);
		}
		pageClone.prevPageLinkOverride = this.prevPageLinkOverride;
		pageClone.previousPage = this.previousPage;
		pageClone.nextPageLinkOverride = this.nextPageLinkOverride;
		pageClone.nextPage = this.nextPage;
		if (null == this.courseSessions) {
			pageClone.courseSessions = null;
		} else {
			pageClone.courseSessions = new HashSet<CourseSession>(this.courseSessions);
		}
		pageClone.notes = this.notes;
		return pageClone;	
	}
	
}
