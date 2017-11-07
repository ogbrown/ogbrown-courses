package com.ogbrown.devcourses.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.web.dto.MenuItemDto;

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
//			parentLink = new MenuItemDto(pageDataObj.getParentPages().get(0));
//			logger.trace(pageDataObj.getTitle() + " has 1 parent links." );
//			parentLink.setUrlSlug(courseUrl + "/");
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
