package com.ogbrown.devcourses.view;

import java.util.List;

import com.ogbrown.devcourses.model.CourseSessionLink;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.web.dto.PageStringsDto;

public interface CoursePageHelper {

	List<CourseSessionLink> getCourseSessionLinks(Page pageDataObject);

	PageStringsDto getPageStrings(Page pageDataObject);

	PageStringsDto getPageStrings(Page courseSessionPage, String courseRequestUrl);

}
