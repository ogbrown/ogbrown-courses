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

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemDto that = (MenuItemDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(urlSlug, that.urlSlug) &&
                Objects.equals(contentHeader, that.contentHeader) &&
                Objects.equals(menuOrder, that.menuOrder) &&
                Objects.equals(depth, that.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, urlSlug, contentHeader, menuOrder, depth);
    }

    @Override
	public String toString() {
		return "MenuItemDto [id=" + id + ", title=" + title + ", urlSlug=" + urlSlug + ", contentHeader=" + contentHeader
				+ ", menuOrder=" + menuOrder + ", depth=" + depth + "]";
	}
}
