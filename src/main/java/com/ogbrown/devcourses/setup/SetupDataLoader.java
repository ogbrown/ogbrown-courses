package com.ogbrown.devcourses.setup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ogbrown.devcourses.model.Course;
import com.ogbrown.devcourses.model.CourseOffering;
import com.ogbrown.devcourses.model.CourseSession;
import com.ogbrown.devcourses.model.Page;
import com.ogbrown.devcourses.model.School;
import com.ogbrown.devcourses.repository.CourseRepository;
import com.ogbrown.devcourses.repository.CourseSessionRepository;
import com.ogbrown.devcourses.repository.PageRepository;
import com.ogbrown.utility.xml.XmlConverter;

@Component
public class SetupDataLoader { // implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(SetupDataLoader.class);
	

	private boolean alreadySetup = false;

	private static final String XML_FILE_NAME = "courses.xml";
	@Autowired
	private XmlConverter converter;
	@Autowired
	private PageRepository pageRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	CourseSessionRepository courseSessionRepository;

	//@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		final String urlSlug = "";
		String content = "";
		if (alreadySetup) {
			return;
		}
		List<Course> courses = loadCoursesInitData();
		
		CourseSession courseSession = new CourseSession(courses.get(0),courses.get(0).getNumberOfSessions(), null);
		Page page = pageRepository.findByUrlSlug(urlSlug);
		if (page != null) {
			return;
		}
		page = new Page();
		page.setTitle("SbOGB.com Courses");
		page.setUrlSlug("");
		page.setContentHeader("Courses");
		page.setMetaDescription("SbOGB.com Training Site for Collin.Edu Java Web Applications Development Courses.");
		page.setMetaKeywords(new HashSet<String>(Arrays.asList("Java", "Courses", "Spring", "Hibernate", "Webapps")));
		page.setPageOrd((short) 1);
		page.publish();

		try {
			content = readFile("initialization.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		page.setContent(content);

		page = pageRepository.save(page);

		
		page = new Page();
		page.setTitle("Terms of Use");
		page.setUrlSlug("terms-and-privacy-policy");
		page.setContentHeader("Terms of Use");
		page.setMetaDescription("SbOGB.com Terms of Use Training Site for Collin.Edu Java Web Applications Development Courses.");
		page.setMetaKeywords(new HashSet<String>(Arrays.asList("Java", "Courses", "Spring", "Terms", "Conditions")));
		page.setPageOrd((short) 99);
		page.publish();

		try {
			content = readFile("terms.html-fragment");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		page.setContent(content);

		page = pageRepository.save(page);
		
		page = new Page();
		page.setTitle("Contact Me");
		page.setUrlSlug("contact-us");
		page.setContentHeader("Contact Me");
		page.setMetaDescription("SbOGB.com Terms of Use Training Site for Collin.Edu Java Web Applications Development Courses.");
		page.setMetaKeywords(new HashSet<String>(Arrays.asList("Java", "Courses", "Spring", "Contact", "Contact Us")));
		page.setPageOrd((short) 95);
		page.publish();

		try {
			content = readFile("working-new-contact.html-fragment");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		page.setContent(content);

		page = pageRepository.save(page);
		List<Page> pages = new ArrayList<Page>();
		pages.add(page);
//		courseSession.setPages(pages);
//		courseSession = courseSessionRepository.save(courseSession);
		alreadySetup = true;
	}



	private String readFile(String fileName) throws IOException {
		Resource resource = new ClassPathResource(fileName);

		BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
	private List<Course> loadCoursesInitData() {
		

		final String XML_FILE_NAME = "courses.xml";
		School school = new School();
		try {
			school = (School) converter.convertFromXMLToObject(XML_FILE_NAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			school.setCourses(courseRepository.save(school.getCourses()));

		return school.getCourses();
	}
	public List<Course> loadCoursesInitData(InputStream is) {

		School school = new School();
		try {
			school = (School) converter.convertFromXMLToObject(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			school.setCourses(courseRepository.save(school.getCourses()));

		return school.getCourses();
	}
}