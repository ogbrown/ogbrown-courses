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

import com.ogbrown.devcourses.model.CourseSessionLink;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.model.dto.MenuItemDto;
import com.ogbrown.devcourses.model.dto.PageStringsDto;
import com.ogbrown.devcourses.service.CourseService;
import com.ogbrown.devcourses.service.GeneratePageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/course/")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private GeneratePageService generatePageService;
	@Autowired
	private MenuViewHelper menuViewHelper;

	@Autowired
	private CoursePageHelper coursePageHelper;

	public CourseController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/{courseUrl}/index.html")
	public ModelAndView indexCourseCtrl(@PathVariable String courseUrl) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("course-index");
		Page pageDataObject = generatePageService.getCoursePage(courseUrl); 
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObject);
		List<Page> menuPages = generatePageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		List<CourseSessionLink> courseSessionLinks = 
				coursePageHelper.getCourseSessionLinks(pageDataObject);
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
		mav.addObject("courseSessionLinks", courseSessionLinks);
		return mav;
	}
	
	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public MenuViewHelper getMenuViewHelper() {
		return menuViewHelper;
	}

	public void setMenuViewHelper(MenuViewHelper menuViewHelper) {
		this.menuViewHelper = menuViewHelper;
	}

	public void setGeneratePageService(GeneratePageService generatePageService) {
		this.generatePageService = generatePageService;
	}



	public void setCoursePageHelper(CoursePageHelper coursePageHelper) {
		this.coursePageHelper = coursePageHelper;
	}

}
