package com.ogbrown.devcourses.web.dto;

public class MenuItemDto {
	private Long id;
	private String title;
	private String urlSlug;
	private String contentHeader;
	private Short menuOrder;
	private Short depth;

	

	public MenuItemDto() {
		this.title = "";
		this.urlSlug = "";
		this.contentHeader = "";
	}
	
	public MenuItemDto(com.ogbrown.devcourses.model.Page p) {
		if ( null != p.getId()) {
			this.id = p.getId();
		}
		this.title = p.getTitle();
		this.urlSlug = ((p.getFolderUrlSlug()!=null?(p.getFolderUrlSlug()+"/"):"") + p.getUrlSlug());
		this.contentHeader = p.getContentHeader();
		this.menuOrder = p.getPageOrd();
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


	public String getContentHeader() {
		return contentHeader;
	}

	public void setContentHeader(String contentHeader) {
		this.contentHeader = contentHeader;
	}

	public Short getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Short menuOrder) {
		this.menuOrder = menuOrder;
	}


	public Short getDepth() {
        return depth;
    }

    public void setDepth(Short depth) {
        this.depth = depth;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contentHeader == null) ? 0 : contentHeader.hashCode());
        result = prime * result + ((depth == null) ? 0 : depth.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((menuOrder == null) ? 0 : menuOrder.hashCode());
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
        MenuItemDto other = (MenuItemDto) obj;
        if (contentHeader == null) {
            if (other.contentHeader != null)
                return false;
        } else if (!contentHeader.equals(other.contentHeader))
            return false;
        if (depth == null) {
            if (other.depth != null)
                return false;
        } else if (!depth.equals(other.depth))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (menuOrder == null) {
            if (other.menuOrder != null)
                return false;
        } else if (!menuOrder.equals(other.menuOrder))
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
		return "MenuItemDto [id=" + id + ", title=" + title + ", urlSlug=" + urlSlug + ", contentHeader=" + contentHeader
				+ ", menuOrder=" + menuOrder + ", depth=" + depth + "]";
	}
}
