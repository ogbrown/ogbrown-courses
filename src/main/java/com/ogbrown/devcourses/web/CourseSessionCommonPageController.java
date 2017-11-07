package com.ogbrown.devcourses.web;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.Instructor;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.service.CourseService;
import com.ogbrown.devcourses.service.GenerateCommonPageService;
import com.ogbrown.devcourses.service.GeneratePageService;
import com.ogbrown.devcourses.view.CoursePageHelper;
import com.ogbrown.devcourses.view.MenuViewHelper;
import com.ogbrown.devcourses.web.dto.MenuItemDto;
import com.ogbrown.devcourses.web.dto.PageStringsDto;

@Controller
public class CourseSessionCommonPageController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private GenerateCommonPageService generateCommonPageService;
	@Autowired
	private MenuViewHelper menuViewHelper;
	@Autowired
	@Qualifier( "courseMenuViewHelper" )
	private MenuViewHelper courseMenuViewHelper;
	@Autowired
	private CoursePageHelper coursePageHelper;

	public CourseSessionCommonPageController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value={"/course/{courseUrl}/{courseUrl}-session-{courseSessionNum}/common/{courseUrl}-{pageRequest}-{pageRequest2}/index.html",
	        "/course/{courseUrl}/{courseUrl}-session-{courseSessionNum}/common/{courseUrl}-{pageRequest}/index.html"})
	public ModelAndView courseCommonPageCtrl(HttpServletRequest request, @PathVariable String courseUrl,
			@PathVariable Short courseSessionNum, @PathVariable String pageRequest, @PathVariable(name="pageRequest2", required=false) String pageRequest2) {
		ModelAndView mav = new ModelAndView();
//		String courseRequestUrl = request.getRequestURL().toString().replace("/" + pageRequest,"");
		String courseRequestUrl = request.getContextPath() + "/course";
		mav.setViewName("course-common-page");
		if (pageRequest2 != null) pageRequest+="-"+pageRequest2;
//		List<Course> courses = courseService.getCourses();
        Course course = courseService.getCourse(courseUrl);
		Page pageDataObj = generateCommonPageService.getCourseSessionCommonPage(courseUrl, courseSessionNum, courseUrl+"-"+pageRequest);
		PageStringsDto page = coursePageHelper.getPageStrings(pageDataObj, courseRequestUrl);
//		page.setContentHeader(courseUrl);
//		page.setContent(pageRequest + "\n<img src=\"" + request.getContextPath() + "/course/uploads/" + courseUrl
//				+ "/JDBCtoDB.jpg\" />");
		List<Page> menuPages = generateCommonPageService.getPagesHavingNoCourseSession();
		List<MenuItemDto> topMenu = menuViewHelper.getTopMenuItems(menuPages);
	    Instructor instructor = course.getCurrentInstructor();
		mav.addObject("topmenu", topMenu);
		mav.addObject("page", page);
		mav.addObject("course", course);
		mav.addObject("instructor", instructor);
		return mav;
	}
	

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setGenerateCommonPageService(GenerateCommonPageService generateCommonPageService) {
        this.generateCommonPageService = generateCommonPageService;
    }

    public void setMenuViewHelper(MenuViewHelper menuViewHelper) {
		this.menuViewHelper = menuViewHelper;
	}
}
