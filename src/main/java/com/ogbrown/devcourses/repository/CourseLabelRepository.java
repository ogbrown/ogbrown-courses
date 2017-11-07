package com.ogbrown.devcourses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.CourseLabel;
@Transactional(readOnly = true, timeout = 1)
public interface CourseLabelRepository extends JpaRepository<CourseLabel, Long> {

    List<CourseLabel> findByLabel(String label);


	
}