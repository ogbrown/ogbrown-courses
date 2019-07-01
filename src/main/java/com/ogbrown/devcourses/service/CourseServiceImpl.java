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
import com.ogbrown.devcourses.repository.InstructorRepository;
import com.ogbrown.devcourses.repository.NoClassDateRepository;
import com.ogbrown.utility.time.DaysOfWeekUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
@Service
public class CourseServiceImpl implements CourseService {
	public static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
    private CourseSessionRepository courseSessionRepository;
    @Autowired
    private InstructorRepository instructorRepository;
	@Autowired
    private NoClassDateRepository noClassDateRepository;
	@PersistenceContext
    protected EntityManager entityManager;
	
	public CourseServiceImpl() {

    }
	@PostConstruct
	public void myInit() {
	    new NoClassDates(noClassDateRepository.findAll());
	}
    @Transactional(readOnly = true)
    @Cacheable({"courses", "coursesessions", "courseofferings"})
	@Override
	public List<Course> getCourses() {
		List<Course> courses = courseRepository.findAll();
		logger.trace(courses.toString());
		Iterator<Course> courseIter = courses.iterator();
		while (courseIter.hasNext()) {
			Course course = courseIter.next();
			entityManager.detach(course);
			if (course.getId().equals(CoursePage.ALL_COURSES)) {
			    courseIter.remove();
			    continue;
			}
			sortCourseOfferings(course);			
		}
		Collections.sort(courses, (o1, o2) -> o2.getCourseOfferings().get(0).getStart().
				compareTo(o1.getCourseOfferings().get(0).getStart()));
		return courses;
	}
    private void sortCourseOfferings(Course course) {
        List<CourseOffering> courseOfferings;
        courseOfferings = course.getCourseOfferings();
        for (CourseOffering courseOffering : courseOfferings) {
            courseOffering.setDaysOfWeekArray(DaysOfWeekUtility.getDaysOfWeekArray(courseOffering.getDaysOfWeek()));
        }
        Collections.sort(courseOfferings, (o1, o2) -> o2.getStart().compareTo(o1.getStart()));
        course.setCourseOfferings(courseOfferings);
    }
	@Transactional(readOnly = true)
	@Override
	public Map<String, Course> getCoursesMap() {
		List<Course> courses = courseRepository.findAll();
		List<CourseOffering> courseOfferings;
		Map<String, Course> coursesMap = new HashMap<String,Course>();
		Iterator<Course> courseIter = courses.iterator();
		while (courseIter.hasNext()) {
			Course course = courseIter.next();
			courseOfferings = course.getCourseOfferings();
			Collections.sort(courseOfferings, (o1, o2) -> o2.getStart().compareTo(o1.getStart()));
			course.setCourseOfferings(courseOfferings);
			coursesMap.put(course.getUrlSlug(), course);
		}
		entityManager.detach(courses);
		
		return coursesMap;
	}
	
	@Transactional(readOnly = true)
	@Cacheable({"courses", "coursesessions", "courseofferings", "lessonplans"})
    @Override
	public Course getCourse(String urlSlug) {
	    Course course = courseRepository.findByUrlSlug(urlSlug);
	    sortCourseOfferings(course);
	    course.setCurrentSchedule(getCourseSchedule(course.getCurrentCourseOffering()));
	    if (course.getCurrentCourseOffering().getInstructors().size() == 0 ){
	        Instructor inst = instructorRepository.findOne(Instructor.DEFAULT_INSTRUCTOR_ID);
	        course.getCurrentCourseOffering().getInstructors().add(inst);
	    }
	    SortedMap<Short,List<LessonPlan>> lessonPlanMap = new TreeMap<Short,List<LessonPlan>>();
	    List<CourseSession> cslist = courseSessionRepository.findByCourse(course);
	    for (CourseSession cs : cslist) {
    	    List<LessonPlan> lessonPlan = cs.getLessonPlan();
    	    lessonPlanMap.put(cs.getSessionNumber(), lessonPlan);
	    }
	    course.setLessonPlanMap(lessonPlanMap);
	    
	    
	    return course;
	}
	

    @Transactional(readOnly = true)
    @Override
    public List<LocalDate> getCourseSchedule(CourseOffering courseOffering) {
	    List<LocalDate> resultArray = new ArrayList<LocalDate>();
	    courseOffering.setDaysOfWeekArray(DaysOfWeekUtility.getDaysOfWeekArray(courseOffering.getDaysOfWeek()));
	    int meetingDatesPerWeek = courseOffering.getDaysOfWeekArray().length;
	    resultArray.add(courseOffering.getStart());
	    for (int session=1; session < courseOffering.getSessionCount(); session++) {
	        for (int meetingInWeek = 0; meetingInWeek < meetingDatesPerWeek; meetingInWeek++ ) {
	            LocalDate nextDate = getNextMeetingDate(resultArray.get(session - 1), courseOffering.getDaysOfWeekArray()[meetingInWeek]);
	            resultArray.add(nextDate);
	        }
	        
	    }
	    Iterator<LocalDate> iter = resultArray.iterator();
	    List<LocalDate> additionalDates = new ArrayList<LocalDate>();
	    while (iter.hasNext()) {
	        LocalDate checkDate = iter.next();
	        if (NoClassDates.containsDate(checkDate)) {
	            LocalDate lastDate = resultArray.get(resultArray.size()-1);
	            DayOfWeek lastDayOfWeek = lastDate.getDayOfWeek();
	            if (courseOffering.getDaysOfWeekArray().length == 1) {
	                additionalDates.add(getNextMeetingDate(lastDate, lastDayOfWeek));
	            }
	        } else {
	            additionalDates.add(checkDate);
	        }
	        
	    }
	    Collections.sort(additionalDates);
	    if (! additionalDates.get(additionalDates.size()-1).equals(courseOffering.getEnd())) {
	        logger.warn(additionalDates.get(additionalDates.size()-1) + " not equal to end date of " + courseOffering.getEnd());
	    }
	    return additionalDates;
	}
	private LocalDate getNextMeetingDate(LocalDate classDate, DayOfWeek dayOfWeek) {
	    return classDate.with(TemporalAdjusters.next(dayOfWeek));
	}

}
