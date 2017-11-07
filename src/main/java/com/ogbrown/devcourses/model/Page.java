package com.ogbrown.devcourses.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable("pages")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
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
	public Boolean getProtectedPage() {
		return protectedPage;
	}

	public void setProtectedPage(Boolean protectedPage) {
		this.protectedPage = protectedPage;
	}





    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((childPages == null) ? 0 : childPages.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((contentHeader == null) ? 0 : contentHeader.hashCode());
        result = prime * result + ((courseSessions == null) ? 0 : courseSessions.hashCode());
        result = prime * result + ((folderUrlSlug == null) ? 0 : folderUrlSlug.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((metaDescription == null) ? 0 : metaDescription.hashCode());
        result = prime * result + ((metaKeywords == null) ? 0 : metaKeywords.hashCode());
        result = prime * result + ((nextPage == null) ? 0 : nextPage.hashCode());
        result = prime * result + ((nextPageLinkOverride == null) ? 0 : nextPageLinkOverride.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((pageOrd == null) ? 0 : pageOrd.hashCode());
        result = prime * result + ((parentPages == null) ? 0 : parentPages.hashCode());
        result = prime * result + ((prevPageLinkOverride == null) ? 0 : prevPageLinkOverride.hashCode());
        result = prime * result + ((previousPage == null) ? 0 : previousPage.hashCode());
        result = prime * result + ((protectedPage == null) ? 0 : protectedPage.hashCode());
        result = prime * result + ((published == null) ? 0 : published.hashCode());
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
        Page other = (Page) obj;
        if (childPages == null) {
            if (other.childPages != null)
                return false;
        } else if (!childPages.equals(other.childPages))
            return false;
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
        if (courseSessions == null) {
            if (other.courseSessions != null)
                return false;
        } else if (!courseSessions.equals(other.courseSessions))
            return false;
        if (folderUrlSlug == null) {
            if (other.folderUrlSlug != null)
                return false;
        } else if (!folderUrlSlug.equals(other.folderUrlSlug))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        if (nextPage == null) {
            if (other.nextPage != null)
                return false;
        } else if (!nextPage.equals(other.nextPage))
            return false;
        if (nextPageLinkOverride == null) {
            if (other.nextPageLinkOverride != null)
                return false;
        } else if (!nextPageLinkOverride.equals(other.nextPageLinkOverride))
            return false;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        if (pageOrd == null) {
            if (other.pageOrd != null)
                return false;
        } else if (!pageOrd.equals(other.pageOrd))
            return false;
        if (parentPages == null) {
            if (other.parentPages != null)
                return false;
        } else if (!parentPages.equals(other.parentPages))
            return false;
        if (prevPageLinkOverride == null) {
            if (other.prevPageLinkOverride != null)
                return false;
        } else if (!prevPageLinkOverride.equals(other.prevPageLinkOverride))
            return false;
        if (previousPage == null) {
            if (other.previousPage != null)
                return false;
        } else if (!previousPage.equals(other.previousPage))
            return false;
        if (protectedPage == null) {
            if (other.protectedPage != null)
                return false;
        } else if (!protectedPage.equals(other.protectedPage))
            return false;
        if (published == null) {
            if (other.published != null)
                return false;
        } else if (!published.equals(other.published))
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
