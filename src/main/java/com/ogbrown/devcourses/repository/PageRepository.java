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

package com.ogbrown.devcourses.repository;

import com.ogbrown.devcourses.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
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
