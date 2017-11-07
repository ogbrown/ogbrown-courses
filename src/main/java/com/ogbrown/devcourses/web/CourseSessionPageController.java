package com.ogbrown.devcourses.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.service.CourseService;
import com.ogbrown.devcourses.service.GenerateCommonPageService;
import com.ogbrown.devcourses.service.GeneratePageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import com.ogbrown.devcourses.web.dto.MenuItemDto;
import com.ogbrown.devcourses.web.dto.PageStringsDto;

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
//		String courseRequestUrl = request.getRequestURL().toString().replace("/" + pageRequest,"");
		String courseRequestUrl = request.getContextPath() + "/course";
		mav.setViewName("page");
//		List<Course> courses = courseService.getCourses();

		Page pageDataObj = generatePageService.getCourseSessionPage(courseUrl, courseSessionNum, pageRequest);
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
//		page.setContentHeader(courseUrl);
//		page.setContent(pageRequest + "\n<img src=\"" + request.getContextPath() + "/course/uploads/" + courseUrl
//				+ "/JDBCtoDB.jpg\" />");
		List<Page> menuPages = generatePageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
//		mav.addObject("courses", courses);
		return mav;
	}
	@RequestMapping("/course/{courseUrl}/{courseUrl}-addl-info/{pageRequest}/index.html")
	public ModelAndView courseAddlInfoPageCtrl(HttpServletRequest request, @PathVariable String courseUrl,
			@PathVariable String pageRequest) {
		ModelAndView mav = new ModelAndView();
		String courseRequestUrl = request.getRequestURL().toString().replace("/" + pageRequest,"");
		mav.setViewName("page");
//		List<Course> courses = courseService.getCourses();
		Page coursePage = generatePageService.getCoursePage(courseUrl);
		Page courseSessionPage = generatePageService.getCourseSessionMenuPage(courseUrl, coursePage.getChildPages().size());
		Page pageDataObj = generatePageService.getCourseSessionPage(courseUrl,coursePage.getChildPages().size(), pageRequest);
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
//		page.setContentHeader(courseUrl);
//		page.setContent(pageRequest + "\n<img src=\"" + request.getContextPath() + "/course/uploads/" + courseUrl
//				+ "/JDBCtoDB.jpg\" />");
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
