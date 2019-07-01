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

package com.ogbrown.devcourses.web;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.model.dto.MenuItemDto;
import com.ogbrown.devcourses.model.dto.PageStringsDto;
import com.ogbrown.devcourses.service.GenerateCommonPageService;
import com.ogbrown.devcourses.service.PageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseSessionController {
	private static Logger logger = LoggerFactory.getLogger(CourseSessionController.class);
	@Autowired
	private PageService pageService;
	@Autowired
	private GenerateCommonPageService generatePageService;
	@Autowired
	private MenuViewHelper menuViewHelper;
	@Autowired
	private CoursePageHelper coursePageHelper;


	public CourseSessionController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/{courseUrl}/{courseUrl}-session-{courseSessionNum}/index.html")
	public ModelAndView courseSessionCtrl(HttpServletRequest request, @PathVariable String courseUrl,
			@PathVariable Short courseSessionNum) {
		ModelAndView mav = new ModelAndView();
		String courseRequestUrl = request.getContextPath() + "/course";
		Page courseSessionPage = generatePageService.getCourseSessionMenuWithCommonItemsPage(courseUrl, courseSessionNum);
		mav.setViewName("session-index");
		PageStringsDto page = coursePageHelper.getPageStrings(courseSessionPage, courseRequestUrl);
		List<Page> menuPages = pageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		List<MenuItemDto> contentMenu = menuViewHelper.getChildPagesMenuItems(courseSessionPage);
		logger.trace(contentMenu.toString());
		mav.addObject("topmenu", topMenu);
		mav.addObject("contentMenu", contentMenu);
		mav.addObject("page", page);
		return mav;
	}
	@RequestMapping("/{courseUrl}/{courseUrl}-addl-info/index.html")
	public ModelAndView courseAddlInfoCtrl(HttpServletRequest request, @PathVariable String courseUrl) {
		ModelAndView mav = new ModelAndView();
		String courseRequestUrl = request.getContextPath() + "/course";
		Page coursePage = generatePageService.getCoursePage(courseUrl);
		Page courseSessionPage = generatePageService.getCourseSessionMenuPage(courseUrl, coursePage.getChildPages().size());
		mav.setViewName("session-index");
		PageStringsDto page = coursePageHelper.getPageStrings(courseSessionPage, courseRequestUrl);
		List<Page> menuPages = pageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		List<MenuItemDto> contentMenu = menuViewHelper.getChildPagesMenuItems(courseSessionPage);
		mav.addObject("topmenu", topMenu);
		mav.addObject("contentMenu", contentMenu);
		mav.addObject("page", page);
		return mav;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}
	public void setMenuViewHelper(MenuViewHelper menuViewHelper) {
		this.menuViewHelper = menuViewHelper;
	}
	public void setGeneratePageService(GenerateCommonPageService generatePageService) {
		this.generatePageService = generatePageService;
	}
	public void setCoursePageHelper(CoursePageHelper coursePageHelper) {
		this.coursePageHelper = coursePageHelper;
	}
}
