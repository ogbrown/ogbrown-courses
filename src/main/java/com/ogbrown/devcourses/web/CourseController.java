package com.ogbrown.devcourses.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ogbrown.devcourses.model.CourseSessionLink;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.service.CourseService;
import com.ogbrown.devcourses.service.GeneratePageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import com.ogbrown.devcourses.web.dto.MenuItemDto;
import com.ogbrown.devcourses.web.dto.PageStringsDto;

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
