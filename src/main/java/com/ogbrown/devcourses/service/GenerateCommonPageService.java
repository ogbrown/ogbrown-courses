package com.ogbrown.devcourses.service;

import com.ogbrown.devcourses.model.Page;

public interface GenerateCommonPageService extends GeneratePageService {
    Page getCourseSessionMenuWithCommonItemsPage(String courseUrl, Short courseSessionNum);
    Page getCourseSessionCommonPage(String courseUrl, Short courseSessionNum, String urlSlug);

}
