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

import com.ogbrown.devcourses.model.CourseSessionLink;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.model.dto.PageStringsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
