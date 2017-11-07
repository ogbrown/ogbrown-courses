package com.ogbrown.devcourses.service;

import java.util.List;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.Page;

public interface GeneratePageService extends PageService {
    Page getHomePage();

	Page getCoursePage(String urlSlug);

	List<Page> getCourseSessionPages(Course course);

	Page getCourseSessionPage(String courseUrl, int courseSessionNum, String urlSlug);

	Page getCourseSessionMenuPage(String courseUrl, int courseSessionNum);

	Page getCourseAddlInfoPage(String courseUrl, String pageRequest);

//	List<Page> getCourseCommonPages(String courseUrl, int courseNumber, int sessionNum, String commonPageSubUrl);
	List<Page> getCourseCommonPages(Course course);

}
