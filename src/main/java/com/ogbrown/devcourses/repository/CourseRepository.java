package com.ogbrown.devcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.Course;
@Transactional(readOnly = true, timeout = 1)
public interface CourseRepository extends JpaRepository<Course, Long> {

//	@Query("SELECT id FROM Course c WHERE c.courseLabel = (:courseLabel)")
//	Long findIdByCourseLabel (@Param("courseLabel") String courseLabel);
//	
//	@Query("FROM Course c WHERE c.courseLabel = (:courseLabel)")
//	Course findByCourseLabel (@Param("courseLabel") String courseLabel);

    Course findByCourseLabelsLabel (String label);
	
	@Query("FROM Course c WHERE c.urlSlug = (:urlSlug)")
	Course findByUrlSlug (@Param("urlSlug") String urlSlug);
}