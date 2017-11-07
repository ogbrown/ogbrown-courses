package com.ogbrown.devcourses.view;

import java.util.List;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.web.dto.MenuItemDto;


public interface MenuViewHelper {

	public List<MenuItemDto> getMenuItems(List<Page> pages); 
	public List<MenuItemDto> getTopMenuItems(List<Page> pages);
	public MenuItemDto getParentLink(String courseUrl, Page pagePersistedObj);
	public MenuItemDto getPreviousLink(String courseUrl, Page pagePersistedObj);
	public MenuItemDto getNextLink(String courseUrl, Page pagePersistedObj);
	public List<MenuItemDto> getChildPagesMenuItems(Page page);
}
