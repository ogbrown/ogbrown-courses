package com.ogbrown.devcourses.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.Page;
@Transactional(readOnly = true, timeout = 1)
public interface PageRepository extends JpaRepository<Page, Long> {

	@Query("SELECT id FROM Page pg WHERE pg.urlSlug = (:urlSlug)")
	Long findIdByUrlSlug (@Param("urlSlug") String urlSlug);
	
	@Query("FROM Page pg WHERE pg.urlSlug = (:urlSlug)")
	Page findByUrlSlug (@Param("urlSlug") String urlSlug);
	
	@Query("FROM Page pg WHERE pg.contentHeader = (:contentHeader)")
	Page findByContentHeader (@Param("contentHeader") String contentHeader);
	
	@Query(value="SELECT * FROM Page p LEFT JOIN parents_children_pages pc ON p.id = pc.childid WHERE pc.childid IS NULL",
			nativeQuery=true )
	List<Page> findByTopParentPages();
	
	List<Page> findByCourseSessionsIsNull();
	
	List<Page> findByUrlSlugContaining(String partialUrlSlug);
	
	Iterable<Page> findByIdInOrderByPageOrdAsc(Collection<Long> ids);
}