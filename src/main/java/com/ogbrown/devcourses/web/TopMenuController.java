package com.ogbrown.devcourses.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.service.CourseService;
import com.ogbrown.devcourses.service.GeneratePageService;
import com.ogbrown.devcourses.service.PageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import com.ogbrown.devcourses.web.dto.MenuItemDto;
import com.ogbrown.devcourses.web.dto.PageStringsDto;

@Controller
public class TopMenuController {
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
	@Autowired
	@Qualifier( "courseMenuViewHelper" )
	private MenuViewHelper courseMenuViewHelper;

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
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
		mav.addObject("courses", courses);
		return mav;
	}
	@RequestMapping("/about-us/index.html")
	public ModelAndView aboutus(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("page");
//		List<Course> courses = courseService.getCourses();
		String courseRequestUrl = request.getRequestURL().toString().replace("/about-us","");
		Page pageDataObj = pageService.getPageForUriSlug("about-us");
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
		page.setTitle(page.getTitle());
		List<Page> menuPages = pageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
//		mav.addObject("courses", courses);
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
		mav.addObject("topmenu", topMenu);
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
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
		return mav;
	}



	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public MenuViewHelper getMenuViewHelper() {
		return menuViewHelper;
	}

	public void setMenuViewHelper(MenuViewHelper menuViewHelper) {
		this.menuViewHelper = menuViewHelper;
	}

}
