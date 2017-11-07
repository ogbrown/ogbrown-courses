package com.ogbrown.devcourses.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.service.GenerateCommonPageService;
import com.ogbrown.devcourses.service.PageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import com.ogbrown.devcourses.web.dto.MenuItemDto;
import com.ogbrown.devcourses.web.dto.PageStringsDto;

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
//		String courseRequestUrl = request.getRequestURL().toString().replace("/"+courseUrl+"-session-"+courseSessionNum,"");
		String courseRequestUrl = request.getContextPath() + "/course";
		Page courseSessionPage = generatePageService.getCourseSessionMenuWithCommonItemsPage(courseUrl, courseSessionNum);
		
//		if (courseSessionNum == 1) {
//			mav.setViewName("session-one-index");
//		} else {
//			mav.setViewName("session-index");
//		}
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
//		String courseRequestUrl = request.getRequestURL().toString().replace("/"+courseUrl+"-addl-info","");
		String courseRequestUrl = request.getContextPath() + "/course";
//		Page courseSessionPage = generatePageService.getCourseSessionMenuWithCommonItemsPage(courseUrl, courseSessionNum);
		Page coursePage = generatePageService.getCoursePage(courseUrl);
		Page courseSessionPage = generatePageService.getCourseSessionMenuPage(courseUrl, coursePage.getChildPages().size());
		mav.setViewName("session-index");
//		courseSessionPage.setContent("<div class=\"alert alert-success\">Class materials will be posted to this site as the course progresses and a link will become active under the appropriate session by the end of the day after the session/class completes.  Please only share this information with other enrolled class members.</div>");
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
