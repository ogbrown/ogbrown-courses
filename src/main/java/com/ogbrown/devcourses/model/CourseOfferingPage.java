package com.ogbrown.devcourses.model;

public class CourseOfferingPage extends CoursePage implements Cloneable {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long courseOfferingId;

	public CourseOfferingPage() {
		// TODO Auto-generated constructor stub
	}
	public CourseOfferingPage(CourseOfferingPage p) {
		super(p);
		this.courseOfferingId = p.courseOfferingId;
	}

	public CourseOfferingPage(CoursePage p) {
		super(p);
	}
	
	public CourseOfferingPage(Page p) {
		super(p);
	}
	public Long getCourseOfferingId() {
		return courseOfferingId;
	}

	public void setCourseOfferingId(Long courseOfferingId) {
		this.courseOfferingId = courseOfferingId;
	}

	@Override
	public CourseOfferingPage clone() {
		CourseOfferingPage courseOfferingPage = new CourseOfferingPage(super.clone());
		courseOfferingPage.setCourseOfferingId(this.courseOfferingId);
		return courseOfferingPage;
	}
	
	
	

}
