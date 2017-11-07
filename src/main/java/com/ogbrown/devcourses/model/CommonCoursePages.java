package com.ogbrown.devcourses.model;

import java.util.Arrays;
import java.util.HashSet;

public class CommonCoursePages {
	CoursePage coursePage = new CoursePage();
	
	public CommonCoursePages() {
		coursePage.setTitle("Welcome");
		coursePage.setContentHeader("Welcome");
		coursePage.setMetaDescription("Welcome");
		coursePage.setUrlSlug("welcome");
		coursePage.setMetaKeywords(new HashSet<String>(Arrays.asList(new String[]{"Welcome"})));
//		coursePage
	}

}
