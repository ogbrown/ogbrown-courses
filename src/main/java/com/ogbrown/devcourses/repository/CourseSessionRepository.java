package com.ogbrown.devcourses.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.CourseSession;
import com.ogbrown.devcourses.model.CourseSessionPk;
@Transactional(readOnly = true, timeout = 1)
public interface CourseSessionRepository extends JpaRepository<CourseSession, CourseSessionPk> {

	@Override
	@Transactional(timeout = 10, readOnly = true)
	public CourseSession findOne(CourseSessionPk courseSessionPk);
	
	List<CourseSession> findByCourse(Course course);

	@Query(value="SELECT pageid FROM coursesessions_pages csp WHERE csp.courseid = (:courseId) AND csp.sessionnumber = (:sessionNumber)",
			nativeQuery=true )
	public Iterable<BigInteger> findPageIdByCourseAndSessionNumber(@Param("courseId") Long courseId, @Param("sessionNumber") Short sessionNumber); 
		
}