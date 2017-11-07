package com.ogbrown.devcourses.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.CoursePage;
import com.ogbrown.devcourses.model.CourseSession;
import com.ogbrown.devcourses.model.CourseSessionPage;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.repository.CourseRepository;
import com.ogbrown.devcourses.repository.CourseSessionRepository;

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
//		Page homePage = getHomePage();
		
//		entityManager.detach(coursePage);
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
//		Page nextPage = null;
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
			courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
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
		courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
		courseSessionPage.setCourseSessions(courseSessions);
		courseSessionPage.setParentPages(parentPages);
		courseSessionPages.add(courseSessionPage);
		
		return courseSessionPages;
	}
	@Override
	public List<Page> getCourseCommonPages(Course course) {
		CourseSessionPage courseSessionPage = null;
		Page previousPage = null;
//		Page nextPage = null;
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
			courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
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
		courseSessions.add(new CourseSession(course,courseSessionPage.getSessionNum(), courseSessionPage.getPageOrd()));
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
        Sort sort = new Sort(Direction.ASC, "pageOrder");
        
        
		CoursePage coursePage = getCoursePage(courseUrl);
		CourseSessionPage courseSessionMenuPage = (CourseSessionPage) coursePage.getChildPages().get(courseSessionNum-1);
		Iterable<BigInteger> pageIds = courseSessionRepository.findPageIdByCourseAndSessionNumber(coursePage.getCourseId(), (short)(courseSessionNum));
		if (pageIds.iterator().hasNext() == true) {
		    for (BigInteger bigInt : pageIds) idsList.add(bigInt.longValue());
	    
		    Iterable<Page> pagesFound = pageRepository.findAll(idsList);

		    for (Page pageFound : pagesFound) pages.add(pageFound);
		}
//		List<Page> pages = new ArrayList<Page>();
//		pages = pageRepository.findAll(idsList);
//		if (pages.size() > 1) {
//			Collections.sort(pages, new Comparator<Page>() {
//
//				@Override
//				public int compare(Page o1, Page o2) {
//					return o1.getPageOrder() - o2.getPageOrder();
//				}
//			});
//		}
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
	public Page getCourseAddlInfoPage(String courseUrl, String pageRequest) {
		int lastPage = 9999; // arbitrary number to represent last sessionNumber
		return getCourseSessionPage(courseUrl, lastPage, pageRequest);
	}
	
}
