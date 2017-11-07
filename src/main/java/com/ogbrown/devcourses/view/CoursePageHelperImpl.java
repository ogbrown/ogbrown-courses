package com.ogbrown.devcourses.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ogbrown.devcourses.model.CourseSessionLink;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.web.dto.PageStringsDto;

@Component( "coursePageHelper" )
public class CoursePageHelperImpl implements CoursePageHelper {
	@Autowired
	@Qualifier( "courseMenuViewHelper" )
	private MenuViewHelper courseMenuViewHelper;
	@Autowired
	@Qualifier( "menuViewHelper" )
	private MenuViewHelper menuViewHelper;
	public CoursePageHelperImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List<CourseSessionLink> getCourseSessionLinks(Page coursePage) {
		CourseSessionLink courseSessionLink = null;
		List<CourseSessionLink> courseSessionLinks = new ArrayList<CourseSessionLink>();
		for (Page p : coursePage.getChildPages()) {
			courseSessionLink = new CourseSessionLink();
			courseSessionLink.setTitle(p.getTitle());
			courseSessionLink.setText(p.getContentHeader());
			courseSessionLink.setUrlSlug(p.getUrlSlug());
			courseSessionLinks.add(courseSessionLink);
		}
		return courseSessionLinks;
	}
	@Override
	public PageStringsDto getPageStrings(Page pageDataObject) {
		String courseUrl = "Not Using";
		PageStringsDto page = new PageStringsDto(pageDataObject);
		page.setParentLink(courseMenuViewHelper.getParentLink(courseUrl, pageDataObject));
		page.setLeftSiblingLink(courseMenuViewHelper.getPreviousLink(courseUrl, pageDataObject));
		page.setRightSiblingLink(courseMenuViewHelper.getNextLink(courseUrl, pageDataObject));
		return page;
	}
	public void setCourseMenuViewHelper(MenuViewHelper courseMenuViewHelper) {
		this.courseMenuViewHelper = courseMenuViewHelper;
	}
	@Override
	public PageStringsDto getPageStrings(Page courseSessionPage, String courseRequestUrl) {
		PageStringsDto page = new PageStringsDto(courseSessionPage);
		if (courseSessionPage.getParentPages() == null) {
		    page.setParentLink(courseMenuViewHelper.getParentLink(courseRequestUrl, courseSessionPage));
		    page.setLeftSiblingLink(courseMenuViewHelper.getPreviousLink(courseRequestUrl, courseSessionPage));
		    page.setRightSiblingLink(courseMenuViewHelper.getNextLink(courseRequestUrl, courseSessionPage));
		} else {
			page.setParentLink(menuViewHelper.getParentLink(courseRequestUrl, courseSessionPage));
		    page.setLeftSiblingLink(menuViewHelper.getPreviousLink(courseRequestUrl, courseSessionPage));
		    page.setRightSiblingLink(menuViewHelper.getNextLink(courseRequestUrl, courseSessionPage));
		}
		return page;
	}
}
