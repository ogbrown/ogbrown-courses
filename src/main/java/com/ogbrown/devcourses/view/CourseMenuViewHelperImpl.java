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

package com.ogbrown.devcourses.view;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.model.dto.MenuItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component( "courseMenuViewHelper" )
public class CourseMenuViewHelperImpl extends MenuViewHelperImpl {
	private static final Logger logger = LoggerFactory.getLogger(CourseMenuViewHelperImpl.class);
	@Override
	public MenuItemDto getParentLink(String courseUrl, Page pageDataObj) {
		MenuItemDto parentLink = null;
		if (null == pageDataObj.getParentPages()) {
			parentLink = new MenuItemDto();
			logger.trace(pageDataObj.getTitle() + " has Null parent links." );
			parentLink.setTitle(DEFAULT_LINK_TITLE);
			parentLink.setContentHeader(DEFAULT_LINK_CONTENT_HEADER);
			parentLink.setUrlSlug(DEFAULT_LINK_URL_SLUG);
			parentLink.setMenuOrder(DEFAULT_LINK_MENU_ORDER);
			parentLink.setId(DEFAULT_LINK_ID);
		} else if (pageDataObj.getParentPages().size() == 1) {
			parentLink = new MenuItemDto(pageDataObj.getParentPages().get(0));
			if (courseUrl.contains("/"+parentLink.getUrlSlug()+"/")) {
				parentLink.setUrlSlug(courseUrl.substring(0, courseUrl.length()-1));
			} else {
				parentLink.setUrlSlug(courseUrl + "/" + parentLink.getUrlSlug());
			}
		}
		return parentLink;
	}
	@Override
	public MenuItemDto getPreviousLink(String courseUrl, Page pageDataObj) {
		MenuItemDto leftLink  = null;
		if (null == pageDataObj.getPreviousPage()) {
			return null;
		} else {
			leftLink = new MenuItemDto(pageDataObj.getPreviousPage());
		}
		if (null != courseUrl) {
			leftLink.setUrlSlug(courseUrl + "/" + leftLink.getUrlSlug() + "/");
		}
		return leftLink;
	}
	@Override
	public MenuItemDto getNextLink(String courseUrl, Page pageDataObj) {
		MenuItemDto rightLink  = null;
		if (null == pageDataObj.getNextPage()) {
			return null;
		} else {
			rightLink = new MenuItemDto(pageDataObj.getNextPage());
		}
		if (null != courseUrl) {
			rightLink.setUrlSlug(courseUrl + "/" + rightLink.getUrlSlug() + "/");
		}
		return rightLink;
	}
}
