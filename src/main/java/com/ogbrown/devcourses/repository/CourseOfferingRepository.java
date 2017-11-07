package com.ogbrown.devcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.CourseOffering;

@Transactional( readOnly = true, timeout = 1)
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {


	
}