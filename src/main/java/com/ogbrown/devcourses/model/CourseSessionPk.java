package com.ogbrown.devcourses.model;

import java.io.Serializable;

public class CourseSessionPk implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long course;
	private Short sessionNumber;

	public CourseSessionPk() {
		
	}

	public CourseSessionPk(Long course, Short sessionNumber) {
		this.course = course;
		this.sessionNumber = sessionNumber;
	}

	public Long getCourseId() {
		return course;
	}

	public void setCourseId(Long course) {
		this.course = course;
	}

	public Short getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Short sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((sessionNumber == null) ? 0 : sessionNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CourseSessionPk other = (CourseSessionPk) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (sessionNumber == null) {
            if (other.sessionNumber != null)
                return false;
        } else if (!sessionNumber.equals(other.sessionNumber))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "CourseSessionPk [course=" + course + ", sessionNumber=" + sessionNumber + "]";
	}
}
