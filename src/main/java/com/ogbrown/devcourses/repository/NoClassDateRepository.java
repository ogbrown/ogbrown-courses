package com.ogbrown.devcourses.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.NoClassDate;
@Transactional(readOnly = true, timeout = 1)
public interface NoClassDateRepository extends JpaRepository<NoClassDate, Date>{

}
