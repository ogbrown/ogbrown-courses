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
import com.ogbrown.devcourses.repository.CourseRepository;
import com.ogbrown.devcourses.repository.CourseSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.*;

@Service( "generatePageService" )
public class GeneratePageServiceImpl extends PageServiceImpl implements GeneratePageService {
	public static Logger logger = LoggerFactory.getLogger(GeneratePageServiceImpl.class);
	@Autowired
	protected CourseRepository courseRepository;
	@Autowired
	protected CourseSessionRepository courseSessionRepository;
	@PersistenceContext
    protected EntityManager entityManager;
	
	public GeneratePageServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly = true)
	@Override
	public Page getHomePage() {
		return readPage(1L);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CoursePage getCoursePage(String urlSlug) {


		Course course = courseRepository.findByUrlSlug(urlSlug);

		CoursePage coursePage = new CoursePage();

		coursePage.setCourseId(course.getId());
		coursePage.setTitle(course.getTitle());
		coursePage.setContentHeader(course.getTitle());
		coursePage.setContent("");
		coursePage.setUrlSlug(urlSlug);
		coursePage.setChildPages(getCourseSessionPages(course));		
		logger.trace(coursePage.toString().replace(",", ",\n"));
		return coursePage;
	}


	@Override
	public List<Page> getCourseSessionPages(Course course) {
		CourseSessionPage courseSessionPage = null;
		Page previousPage = null;
		Set<CourseSession> courseSessions = null;
		List<Page> courseSessionPages = new ArrayList<Page>();
		
		CoursePage coursePage = new CoursePage();
		coursePage.setCourseId(course.getId());
		coursePage.setTitle(course.getTitle());
		coursePage.setContentHeader(course.getTitle());
		coursePage.setContent("");
		coursePage.setUrlSlug(course.getUrlSlug());
		List<Page> parentPages = new ArrayList<Page>();
		parentPages.add(coursePage);

		for (int i = 0; i < course.getNumberOfSessions(); i++) {
			courseSessionPage = new CourseSessionPage();
			courseSessions = new HashSet<CourseSession>();
			courseSessionPage.setCourseId(course.getId());
			courseSessionPage.setSessionNum((short)(i+1));
			courseSessionPage.setTitle(course.getShortTitle() + ": Session " + (i+1));
			courseSessionPage.setContentHeader(course.getShortTitle() + ": Session " + (i+1) );
			courseSessionPage.setUrlSlug(course.getUrlSlug() + "-session-" + (i+1));
			courseSessionPage.setPageOrd((short)(i+1));
			courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum()));
			courseSessionPage.setCourseSessions(courseSessions);
			if ( i > 0 ) {
				courseSessionPage.setPreviousPage(previousPage);
				courseSessionPages.get(i-1).setNextPage(courseSessionPage);
			}
			courseSessionPage.setParentPages(parentPages);
			courseSessionPages.add(courseSessionPage);
			previousPage = courseSessionPage;

		}
		courseSessionPage = new CourseSessionPage();
		courseSessions = new HashSet<CourseSession>();
		courseSessionPage.setCourseId(course.getId());
		courseSessionPage.setSessionNum((short)(course.getNumberOfSessions()+1));
		courseSessionPage.setTitle(course.getShortTitle() + ": Additional Information");
		courseSessionPage.setContentHeader(course.getShortTitle() + ": Addl Info");
		courseSessionPage.setUrlSlug(course.getUrlSlug() + "-addl-info");
		courseSessionPage.setPageOrd((short)(course.getNumberOfSessions()+1));
		if ( course.getNumberOfSessions() > 0 ) {
			courseSessionPage.setPreviousPage(previousPage);
			courseSessionPages.get(courseSessionPages.size()-1).setNextPage(courseSessionPage);
		}
		courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum()));
		courseSessionPage.setCourseSessions(courseSessions);
		courseSessionPage.setParentPages(parentPages);
		courseSessionPages.add(courseSessionPage);
		
		return courseSessionPages;
	}
	@Override
	public List<Page> getCourseCommonPages(Course course) {
		CourseSessionPage courseSessionPage = null;
		Page previousPage = null;
		Set<CourseSession> courseSessions = null;
		List<Page> courseSessionPages = new ArrayList<Page>();
		
		CoursePage coursePage = new CoursePage();
		coursePage.setCourseId(course.getId());
		coursePage.setTitle(course.getTitle());
		coursePage.setContentHeader(course.getTitle());
		coursePage.setContent("");
		coursePage.setUrlSlug(course.getUrlSlug());
		List<Page> parentPages = new ArrayList<Page>();
		parentPages.add(coursePage);

		for (int i = 0; i < course.getNumberOfSessions(); i++) {
			courseSessionPage = new CourseSessionPage();
			courseSessions = new HashSet<CourseSession>();
			courseSessionPage.setCourseId(course.getId());
			courseSessionPage.setSessionNum((short)(i+1));
			courseSessionPage.setTitle(course.getShortTitle() + ": Session " + (i+1));
			courseSessionPage.setContentHeader(course.getShortTitle() + ": Session " + (i+1) );
			courseSessionPage.setUrlSlug(course.getUrlSlug() + "-session-" + (i+1));
			courseSessionPage.setPageOrd((short)(i+1));
			courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum()));
			courseSessionPage.setCourseSessions(courseSessions);
			if ( i > 0 ) {
				courseSessionPage.setPreviousPage(previousPage);
				courseSessionPages.get(i-1).setNextPage(courseSessionPage);
			}
			courseSessionPage.setParentPages(parentPages);
			courseSessionPages.add(courseSessionPage);
			previousPage = courseSessionPage;

		}
		courseSessionPage = new CourseSessionPage();
		courseSessions = new HashSet<CourseSession>();
		courseSessionPage.setCourseId(course.getId());
		courseSessionPage.setSessionNum((short)(course.getNumberOfSessions()+1));
		courseSessionPage.setTitle(course.getShortTitle() + ": Additional Information");
		courseSessionPage.setContentHeader(course.getShortTitle() + ": Addl Info");
		courseSessionPage.setUrlSlug(course.getUrlSlug() + "-addl-info");
		courseSessionPage.setPageOrd((short)(course.getNumberOfSessions()+1));
		if ( course.getNumberOfSessions() > 0 ) {
			courseSessionPage.setPreviousPage(previousPage);
			courseSessionPages.get(courseSessionPages.size()-1).setNextPage(courseSessionPage);
		}
		courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum()));
		courseSessionPage.setCourseSessions(courseSessions);
		courseSessionPage.setParentPages(parentPages);
		courseSessionPages.add(courseSessionPage);
		
		return courseSessionPages;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page getCourseSessionMenuPage(String courseUrl, int courseSessionNum) {

	    List<Long> idsList = new ArrayList<Long>();
        List<Page> pages = new ArrayList<Page>();

		CoursePage coursePage = getCoursePage(courseUrl);
		CourseSessionPage courseSessionMenuPage = (CourseSessionPage) coursePage.getChildPages().get(courseSessionNum-1);
		Iterable<BigInteger> pageIds = courseSessionRepository.findPageIdByCourseAndSessionNumber(coursePage.getCourseId(), (short)(courseSessionNum));
		if (pageIds.iterator().hasNext() == true) {
		    for (BigInteger bigInt : pageIds) idsList.add(bigInt.longValue());
	    
		    Iterable<Page> pagesFound = pageRepository.findAll(idsList);

		    for (Page pageFound : pagesFound) pages.add(pageFound);
		}
		for (int i = 0; i < pages.size(); i++) {
			entityManager.detach(pages.get(i));
			if (null == pages.get(i).getParentPages() ) {
				List<Page> parentPages = new ArrayList<Page>();
				parentPages.add(courseSessionMenuPage);
				pages.get(i).setParentPages(parentPages);
			} else if (pages.get(i).getParentPages().size() == 0) {
				Page p = courseSessionMenuPage.clone();
				p.setCourseSessions(null);
				pages.get(i).getParentPages().add(p);
			}
			if (i < pages.size()-1) {
				pages.get(i).setNextPage(pages.get(i+1));
			}
			if (i > 1) {
				pages.get(i).setPreviousPage(pages.get(i-1));
			}
		}
		courseSessionMenuPage.setChildPages(pages);
		return courseSessionMenuPage;
	}
	
		
	@Override
	@Transactional(readOnly = true)
	public Page getCourseSessionPage(String courseUrl, int courseSessionNum, String urlSlug) {
		CourseSessionPage courseSessionMenuPage = (CourseSessionPage) getCourseSessionMenuPage(courseUrl, courseSessionNum);
		Map<String, Page> urlSlugToPageMap = new HashMap<String,Page>();
		for (Page p : courseSessionMenuPage.getChildPages()) {
			urlSlugToPageMap.put(p.getUrlSlug(), p);
		}
		Page page = urlSlugToPageMap.get(urlSlug);
		return page;
	}

	@Override
	@Transactional(readOnly = true)
	public Page getCourseAddlInfoPage(String courseUrl, String pageRequest) {
		int lastPage = 9999; // arbitrary number to represent last sessionNumber
		return getCourseSessionPage(courseUrl, lastPage, pageRequest);
	}
	
}
