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

package com.ogbrown.devcourses.service;

import com.ogbrown.devcourses.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;

@Service( "generateCommonPageService" )
public class GenerateCommonPageServiceImpl extends GeneratePageServiceImpl implements GenerateCommonPageService {

	public GenerateCommonPageServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
    @Transactional(readOnly = true)
    public Page getCourseSessionMenuWithCommonItemsPage(String courseUrl, Short courseSessionNum) {
        List<Long> idsList = new ArrayList<Long>();
        List<Page> pages = new ArrayList<Page>();
        
	    Course course = courseRepository.findByUrlSlug(courseUrl);
        CoursePage coursePage = getCoursePage(courseUrl);
        CourseSessionPage courseSessionWithCommonMenuItemsPage = (CourseSessionPage) coursePage.getChildPages().get(courseSessionNum-1);

        Iterable<BigInteger> pageIds = courseSessionRepository.findPageIdByCourseAndSessionNumber(CoursePage.ALL_COURSES, (short)(courseSessionNum));
        logger.trace("List of Page Ids: {}", pageIds);

        if (pageIds.iterator().hasNext() == true) {
            for (BigInteger bigInt : pageIds) idsList.add(bigInt.longValue());
        
            Iterable<Page> pagesFound = pageRepository.findByIdInOrderByPageOrdAsc(idsList);

            for (Page pageFound : pagesFound) pages.add(pageFound);
        }
        
        for (Page page : pages) {
            entityManager.detach(page);
            Set<CourseSession> courseSessions = page.getCourseSessions();
            for (CourseSession cs : courseSessions) {
                cs.setCourse(course);
            }
            page.setUrlSlug(page.getUrlSlug().replace("common-", ""));
            page.setContentHeader(course.getShortTitle() + ": " + page.getContentHeader());
            page.setTitle(course.getShortTitle() + " " + page.getTitle());
        }
        for (int i = 0; i < pages.size(); i++) {
            Page page = pages.get(i);
            
            page.setUrlSlug(course.getUrlSlug() + "-" + page.getUrlSlug());
            if (null == page.getParentPages() ) {
                List<Page> parentPages = new ArrayList<Page>();
                parentPages.add(courseSessionWithCommonMenuItemsPage);
                page.setParentPages(parentPages);
            } else if (page.getParentPages().size() == 0) {
                Page p = courseSessionWithCommonMenuItemsPage.clone();
                p.setCourseSessions(null);
                page.getParentPages().add(p);
            }
            if (i < pages.size()-1) {
                page.setNextPage(pages.get(i+1));
            }
            if (i > 1) {
                page.setPreviousPage(pages.get(i-1));
            }
        }
        CourseSessionPage courseSessionMenuPage = (CourseSessionPage) super.getCourseSessionMenuPage(courseUrl, courseSessionNum);
        if (courseSessionMenuPage != null) {
            List<Page> regularChildPages = courseSessionMenuPage.getChildPages();
            pages.addAll(regularChildPages);
        }
        courseSessionWithCommonMenuItemsPage.setChildPages(pages);
        if (pages.size() == 0) {
            courseSessionWithCommonMenuItemsPage.setContentHeader("Protected Content");
        }
        
        return courseSessionWithCommonMenuItemsPage;
    }
	@Override
    @Transactional(readOnly = true)
    public Page getCourseSessionCommonPage(String courseUrl, Short courseSessionNum, String urlSlug) {
        CourseSessionPage courseSessionMenuPage = (CourseSessionPage) getCourseSessionMenuWithCommonItemsPage(courseUrl, courseSessionNum);
        Map<String, Page> urlSlugToPageMap = new HashMap<String,Page>();
        for (Page p : courseSessionMenuPage.getChildPages()) {
            urlSlugToPageMap.put(p.getUrlSlug(), p);
        }
        Page page = urlSlugToPageMap.get(urlSlug);
        return page;
    }
}
