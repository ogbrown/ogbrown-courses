package com.ogbrown.devcourses.model;

public class CoursePage extends Page implements Cloneable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public final static Long ALL_COURSES=999999L;
	private Long courseId;

	public CoursePage() {
		// TODO Auto-generated constructor stub
	}
	public CoursePage(CoursePage p) {
		super(p);
		this.courseId = p.courseId;
	}

	public CoursePage(Page p) {
		super(p);
	}
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public CoursePage clone() {
		CoursePage coursePage = new CoursePage(super.clone());
		coursePage.setCourseId(this.courseId);
		return coursePage;
	}
	
	
	

}
