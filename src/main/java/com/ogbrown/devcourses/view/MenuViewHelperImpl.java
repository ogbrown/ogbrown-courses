package com.ogbrown.devcourses.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.web.dto.MenuItemDto;

@Component("menuViewHelper")
public class MenuViewHelperImpl implements MenuViewHelper {
	final static Logger logger = LoggerFactory.getLogger(MenuViewHelperImpl.class);
	public final String DEFAULT_LINK_TITLE = "SbOGB.com Courses";
	public final String DEFAULT_LINK_CONTENT_HEADER = "All Courses";
	public final String DEFAULT_LINK_URL_SLUG = "";
	public final short DEFAULT_LINK_MENU_ORDER = (short) 0;
	public final Long DEFAULT_LINK_ID = 0L;

	@Override
	public List<MenuItemDto> getMenuItems(List<Page> pages) {
		List<MenuItemDto> menuItemDtos = new ArrayList<MenuItemDto>();
		// MenuItemDto menuItem = new MenuItemDto(pages.get(0));
		for (Page p : pages) {
			menuItemDtos.add(new MenuItemDto(p));
		}
		Collections.sort(menuItemDtos, new Comparator<MenuItemDto>() {

			@Override
			public int compare(MenuItemDto o1, MenuItemDto o2) {
				return o1.getMenuOrder() - o2.getMenuOrder();
			}
		});
		return menuItemDtos;
	}

	@Override
	public List<MenuItemDto> getTopMenuItems(List<Page> pages) {
		List<MenuItemDto> menuItemDtos = getMenuItems(pages);
		Iterator<MenuItemDto> menuItemIterator = menuItemDtos.iterator();
		while (menuItemIterator.hasNext()) {
			MenuItemDto m = menuItemIterator.next();
			if (m.getUrlSlug().isEmpty()) {
				menuItemIterator.remove();
				break;
			}
		}
		return menuItemDtos;
	}

	@Override
	public MenuItemDto getParentLink(String courseUrl, Page pagePersistedObj) {
		MenuItemDto parentLink = null;
		if (null == pagePersistedObj.getParentPages() || pagePersistedObj.getParentPages().size() == 0) {
			parentLink = new MenuItemDto();
			logger.trace(pagePersistedObj.getTitle() + " has " + pagePersistedObj.getParentPages().size()
					+ " parent links.");
			parentLink.setTitle(DEFAULT_LINK_TITLE);
			parentLink.setContentHeader(DEFAULT_LINK_CONTENT_HEADER);
			parentLink.setUrlSlug(DEFAULT_LINK_URL_SLUG);
			parentLink.setMenuOrder(DEFAULT_LINK_MENU_ORDER);
			parentLink.setId(DEFAULT_LINK_ID);
		} else {
			parentLink = new MenuItemDto(pagePersistedObj.getParentPages().get(0));
//			if (courseUrl.contains("/" + parentLink.getUrlSlug() + "/")) {
//				parentLink.setUrlSlug(courseUrl.substring(0, courseUrl.length() - 1));
//			} else {
//				
//				parentLink.setUrlSlug(courseUrl + "/" + parentLink.getUrlSlug());
//			}
			List<Page> tmpPages = pagePersistedObj.getParentPages();
			MenuItemDto tmpParentLink = parentLink;
			String linkRelativeToParent = "";
			while (tmpPages != null) {
				linkRelativeToParent = "/" + tmpParentLink.getUrlSlug() + linkRelativeToParent;
				tmpPages = tmpPages.get(0).getParentPages();
				if (tmpPages != null) {
					tmpParentLink = new MenuItemDto(tmpPages.get(0));
				}
			}
			parentLink.setUrlSlug(courseUrl + linkRelativeToParent + "/");
		}
		return parentLink;
	}

	@Override
	public MenuItemDto getPreviousLink(String courseUrl, Page pageDataObj) {
		MenuItemDto leftLink = null;
		if (null == pageDataObj.getPreviousPage()) {
			return null;
		} else {
			leftLink = new MenuItemDto(pageDataObj.getPreviousPage());
		}
		if (null != courseUrl) {
			leftLink.setUrlSlug(getParentLink(courseUrl, pageDataObj).getUrlSlug() + leftLink.getUrlSlug() + "/");
		}
		return leftLink;
	}

	@Override
	public MenuItemDto getNextLink(String courseUrl, Page pageDataObj) {
		MenuItemDto rightLink = null;
		if (null == pageDataObj.getNextPage()) {
			return null;
		} else {
			rightLink = new MenuItemDto(pageDataObj.getNextPage());
		}
		if (null != courseUrl) {
			rightLink.setUrlSlug(
					getParentLink(courseUrl, pageDataObj).getUrlSlug() + rightLink.getUrlSlug() + "/");
		}
		return rightLink;
	}

	@Override
	public List<MenuItemDto> getChildPagesMenuItems(Page page) {
		List<MenuItemDto> menuItemDtos = new ArrayList<MenuItemDto>();
		// MenuItemDto menuItem = new MenuItemDto(pages.get(0));
		for (Page p : page.getChildPages()) {
			menuItemDtos.add(new MenuItemDto(p));
		}
		if (menuItemDtos.size() > 1) {
			Collections.sort(menuItemDtos, new Comparator<MenuItemDto>() {

				@Override
				public int compare(MenuItemDto o1, MenuItemDto o2) {
					return o1.getMenuOrder() - o2.getMenuOrder();
				}
			});
		}
		return menuItemDtos;
	}
}
