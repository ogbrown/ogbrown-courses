package com.ogbrown.devcourses.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.CourseOffering;

public interface CourseService {
	List<Course> getCourses();

	Map<String, Course> getCoursesMap();

    Course getCourse(String urlSlug);

    List<Date> getCourseSchedule(CourseOffering courseOffering);
}
