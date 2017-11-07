package com.ogbrown.devcourses.service;

import java.util.List;

import com.ogbrown.devcourses.model.Page;

public interface PageService {
    Page readPage(Long pageId);

	List<Page> getPagesHavingNoParentPage();

	Page getPageForUriSlug(String string);

	List<Page> getPagesHavingNoCourseSession();
}
