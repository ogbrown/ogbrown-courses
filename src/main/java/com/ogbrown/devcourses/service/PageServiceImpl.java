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

package com.ogbrown.devcourses.service;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
