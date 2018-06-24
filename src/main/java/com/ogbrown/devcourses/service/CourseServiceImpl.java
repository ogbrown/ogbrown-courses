package com.ogbrown.devcourses.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.CourseOffering;
import com.ogbrown.devcourses.model.CoursePage;
import com.ogbrown.devcourses.model.CourseSession;
import com.ogbrown.devcourses.model.Instructor;
import com.ogbrown.devcourses.model.LessonPlan;
import com.ogbrown.devcourses.model.NoClassDates;
import com.ogbrown.devcourses.repository.CourseRepository;
import com.ogbrown.devcourses.repository.CourseSessionRepository;
import com.ogbrown.devcourses.repository.InstructorRepository;
import com.ogbrown.devcourses.repository.NoClassDateRepository;
import com.ogbrown.utility.time.DaysOfWeekUtility;
@Service
public class CourseServiceImpl implements CourseService {
	public static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	@Autowired
	private CourseRepository courseRepository;
//	@Autowired
//    private CourseOfferingRepository courseOfferingRepository;
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
//	    List<CourseOffering> courseOfferings = courseOfferingRepository.findAll();
//	    for (CourseOffering co: courseOfferings) co.setCourseOfferingStatus(CourseOfferingStatus.COMPLETED);
//	    courseOfferingRepository.save(courseOfferings);
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
		Collections.sort(courses, new Comparator<Course>() {

			@Override
			public int compare(Course o1, Course o2) {
				return o2.getCourseOfferings().get(0).getStart().
						compareTo(o1.getCourseOfferings().get(0).getStart());
			}


		});
//		entityManager.detach(courses);
		return courses;
	}
    private void sortCourseOfferings(Course course) {
        List<CourseOffering> courseOfferings;
        courseOfferings = course.getCourseOfferings();
        for (CourseOffering courseOffering : courseOfferings) {
            courseOffering.setDaysOfWeekArray(DaysOfWeekUtility.getDaysOfWeekArray(courseOffering.getDaysOfWeek()));
//            courseOffering.setCourseOfferingStatus(CourseOfferingStatus.COMPLETED);
        }
        Collections.sort(courseOfferings, new Comparator<CourseOffering>() {

            @Override
            public int compare(CourseOffering o1, CourseOffering o2) {
                return o2.getStart().compareTo(o1.getStart());
            }


        });
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
			Collections.sort(courseOfferings, new Comparator<CourseOffering>() {

				@Override
				public int compare(CourseOffering o1, CourseOffering o2) {
					return o2.getStart().compareTo(o1.getStart());
				}


			});
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
//	    CourseSession cs = courseSessionRepository.findOne(new CourseSessionPk(course.getId(),(short)1));
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
//	    LocalDate input = new java.sql.Date(courseOffering.getStart().getTime()).toLocalDate();
	    for (int session=1; session < courseOffering.getSessionCount(); ) {
	        for (int meetingInWeek = 0; meetingInWeek < meetingDatesPerWeek; meetingInWeek++ ) {
	            LocalDate nextDate = getNextMeetingDate(resultArray.get(session - 1), courseOffering.getDaysOfWeekArray()[meetingInWeek]);
	            resultArray.add(nextDate);
	            session++;
	        }
	        
	    }
	    Iterator<LocalDate> iter = resultArray.iterator();
	    List<LocalDate> additionalDates = new ArrayList<LocalDate>();
	    int deleteDatesCount = 0;
	    while (iter.hasNext()) {
	        LocalDate checkDate = iter.next();
	        if (NoClassDates.containsDate(checkDate)) {
	            LocalDate lastDate = resultArray.get(resultArray.size()-1);
	            DayOfWeek lastDayOfWeek = lastDate.getDayOfWeek();
	            if (courseOffering.getDaysOfWeekArray().length == 1) {
	                additionalDates.add(getNextMeetingDate(lastDate, lastDayOfWeek));
	            }
	            deleteDatesCount++;
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
