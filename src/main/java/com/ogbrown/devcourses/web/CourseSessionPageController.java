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
import com.ogbrown.devcourses.service.CourseService;
import com.ogbrown.devcourses.service.GenerateCommonPageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseSessionPageController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private GenerateCommonPageService generatePageService;
	@Autowired
	private MenuViewHelper menuViewHelper;
	@Autowired
	@Qualifier( "courseMenuViewHelper" )
	private MenuViewHelper courseMenuViewHelper;
	@Autowired
	private CoursePageHelper coursePageHelper;

	public CourseSessionPageController() {
		// TODO Auto-generated constructor stub
	}


	@RequestMapping("/course/{courseUrl}/{courseUrl}-session-{courseSessionNum}/{pageRequest}/index.html")
	public ModelAndView coursePageCtrl(HttpServletRequest request, @PathVariable String courseUrl,
			@PathVariable int courseSessionNum, @PathVariable String pageRequest) {
		ModelAndView mav = new ModelAndView();
		String courseRequestUrl = request.getContextPath() + "/course";
		mav.setViewName("page");

		Page pageDataObj = generatePageService.getCourseSessionPage(courseUrl, courseSessionNum, pageRequest);
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
		List<Page> menuPages = generatePageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
		return mav;
	}
	@RequestMapping("/course/{courseUrl}/{courseUrl}-addl-info/{pageRequest}/index.html")
	public ModelAndView courseAddlInfoPageCtrl(HttpServletRequest request, @PathVariable String courseUrl,
			@PathVariable String pageRequest) {
		ModelAndView mav = new ModelAndView();
		String courseRequestUrl = request.getRequestURL().toString().replace("/" + pageRequest,"");
		mav.setViewName("page");
		Page coursePage = generatePageService.getCoursePage(courseUrl);
		Page pageDataObj = generatePageService.getCourseSessionPage(courseUrl,coursePage.getChildPages().size(), pageRequest);
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
		List<Page> menuPages = generatePageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
		return mav;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	public void setGeneratePageService(GenerateCommonPageService generatePageService) {
		this.generatePageService = generatePageService;
	}
	public void setMenuViewHelper(MenuViewHelper menuViewHelper) {
		this.menuViewHelper = menuViewHelper;
	}
}
