package com.ogbrown.devcourses.model;

public class CourseSessionPage extends CoursePage implements Cloneable {
    public final static short ALL_SESSION_NUMBERS=Short.MAX_VALUE;
    public final static short LAST_SESSION=Short.MAX_VALUE-1;
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Short sessionNum;

	public CourseSessionPage() {
		// TODO Auto-generated constructor stub
	}
	public CourseSessionPage(CourseSessionPage p) {
		super(p);
		this.setCourseId(p.getCourseId());
		this.setSessionNum(p.getSessionNum());
	}
	public CourseSessionPage(CoursePage p) {
		super(p);
		this.setCourseId(p.getCourseId());
	}

	public CourseSessionPage(Page p) {
		super(p);
	}
	public Short getSessionNum() {
		return sessionNum;
	}

	public void setSessionNum(Short sessionNum) {
		this.sessionNum = sessionNum;
	}
	@Override
	public CourseSessionPage clone() {
		CourseSessionPage courseSessionPage = new CourseSessionPage(super.clone());
		courseSessionPage.setSessionNum(this.sessionNum);
		return courseSessionPage;
	}
	

}
