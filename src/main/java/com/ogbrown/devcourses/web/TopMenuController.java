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

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.model.dto.MenuItemDto;
import com.ogbrown.devcourses.model.dto.PageStringsDto;
import com.ogbrown.devcourses.service.CourseService;
import com.ogbrown.devcourses.service.GeneratePageService;
import com.ogbrown.devcourses.service.PageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TopMenuController {
	private static final String TOP_MENU = "topmenu";

	@Autowired
	private CourseService courseService;
	@Autowired
	private PageService pageService;
	@Autowired
	private GeneratePageService generatePageService;
	@Autowired
	private CoursePageHelper coursePageHelper;
	@Autowired
	private MenuViewHelper menuViewHelper;

	public TopMenuController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/index.html")
	public ModelAndView indexCtrl() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		List<Course> courses = courseService.getCourses();
		Page page = generatePageService.getHomePage();
		List<Page> menuPages = pageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject(TOP_MENU, topMenu);
		mav.addObject("page", page);
		mav.addObject("courses", courses);
		return mav;
	}
	@RequestMapping("/about-us/index.html")
	public ModelAndView aboutus(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("page");
		String courseRequestUrl = request.getRequestURL().toString().replace("/about-us","");
		Page pageDataObj = pageService.getPageForUriSlug("about-us");
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
		page.setTitle(page.getTitle());
		List<Page> menuPages = pageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject(TOP_MENU, topMenu);
		mav.addObject("page", page);
		return mav;
	}
	@RequestMapping("/contact-us/index.html")
	public ModelAndView contact(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("page");
		String courseRequestUrl = request.getRequestURL().toString().replace("/contact-us","");

		Page pageDataObj = pageService.getPageForUriSlug("contact-us");
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
		page.setTitle(page.getTitle());
		List<Page> menuPages = pageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject(TOP_MENU, topMenu);
		mav.addObject("page", page);
		return mav;
	}
	@RequestMapping("/terms-and-privacy-policy/index.html")
	public ModelAndView termsAndPolicy(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String courseRequestUrl = request.getRequestURL().toString().replace("/terms-and-privacy-policy","");
		mav.setViewName("page");
		Page pageDataObj = pageService.getPageForUriSlug("terms-and-privacy-policy");
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
		page.setTitle(page.getTitle());
		List<Page> menuPages = pageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject(TOP_MENU, topMenu);
		mav.addObject("page", page);
		return mav;
	}

}
