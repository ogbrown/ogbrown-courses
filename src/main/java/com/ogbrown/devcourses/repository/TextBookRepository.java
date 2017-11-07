package com.ogbrown.devcourses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.TextBook;
@Transactional(readOnly = true, timeout = 1)
public interface TextBookRepository extends JpaRepository<TextBook, Long>{

}
