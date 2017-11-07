package com.ogbrown.devcourses.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.repository.PageRepository;

@Service( "pageService" )
public class PageServiceImpl implements PageService {

	@Autowired
	protected PageRepository pageRepository;

	public PageServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Page readPage(Long pageId) {
		return pageRepository.findOne(pageId);
	}

	@Override
	public List<Page> getPagesHavingNoParentPage() {
		return pageRepository.findByTopParentPages();
	}
	
	@Override
	public Page getPageForUriSlug(String urlSlug) {
		Page page = pageRepository.findByUrlSlug(urlSlug);
		return page;
	}
	
	public void setPageRepository(PageRepository pageRepository) {
		this.pageRepository = pageRepository;
	}

	@Override
	public List<Page> getPagesHavingNoCourseSession() {
		return pageRepository.findByCourseSessionsIsNull();
	}



}
