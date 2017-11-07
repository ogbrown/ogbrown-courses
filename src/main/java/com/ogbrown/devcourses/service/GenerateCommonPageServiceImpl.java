package com.ogbrown.devcourses.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.CoursePage;
import com.ogbrown.devcourses.model.CourseSession;
import com.ogbrown.devcourses.model.CourseSessionPage;
import com.ogbrown.devcourses.model.Page;

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
//        List<BigInteger> pageIdsFound = courseSessionRepository.findPageIdByCourseAndSessionNumber(CoursePage.ALL_COURSES, (short)(courseSessionNum));
//        List<Long> idsList = new ArrayList<Long>();
//        for (BigInteger bigInt : pageIdsFound) idsList.add(bigInt.longValue());
//        Sort sort = new Sort(Direction.ASC, "pageOrder");
//        Iterable<Page> pagesFound = pageRepository.findByIdIn(idsList,sort);
//        List<Page> pages = new ArrayList<Page>();
//        for (Page pageFound : pagesFound) pages.add(pageFound);

        
        Iterable<BigInteger> pageIds = courseSessionRepository.findPageIdByCourseAndSessionNumber(CoursePage.ALL_COURSES, (short)(courseSessionNum));
        logger.trace("List of Page Ids: " + pageIds);

//        for (BigInteger bigInt : pageIdsDb) idsList.add(bigInt.longValue());
//        Sort sort = new Sort(Direction.ASC, "pageOrder");
//        Iterable<Page> pagesFound = pageRepository.findByIdInOrderByPageOrdAsc(idsList);
//        List<Page> pages = new ArrayList<Page>();
//        for (Page pageFound : pagesFound) pages.add(pageFound);        
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
//            page.s(course.getUrlSlug() + "-" + page.getUrlSlug());
//            page.setUrlSlug(course.getUrlSlug() + "-" + page.getUrlSlug());
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
