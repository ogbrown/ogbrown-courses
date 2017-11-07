package com.ogbrown.devcourses.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@IdClass(CourseSessionPk.class)
public class CourseSession {
    public final static short LAST_SESSION=(short)9999;
	@Id
	@ManyToOne
	@JoinColumn(name = "courseid", referencedColumnName = "id")
	private Course course;
	@Id
	@Column(name = "sessionnumber", columnDefinition="SMALLINT")
	private Short sessionNumber;
	@OneToMany(targetEntity = Page.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "coursesessions_pages", joinColumns = { @JoinColumn(name = "courseid"), @JoinColumn(name = "sessionnumber") }, inverseJoinColumns = { @JoinColumn(name = "pageid") })
	private List<Page> pages;
	@ManyToMany(targetEntity = LessonPlan.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "coursesession_lessonplan",joinColumns = { @JoinColumn(name = "courseid"), @JoinColumn(name = "sessionnumber") }, inverseJoinColumns = { @JoinColumn(name = "lessonplanid") })
    private List<LessonPlan> lessonPlan; 
	
	public CourseSession() {

	}
	
	
	public CourseSession(Course course, Short sessionNumber, List<Page> pages) {
		this.course = course;
		this.sessionNumber = sessionNumber;
		this.pages = pages;
	}


	public CourseSession(Course course, Short sessionNumber) {
		this.course = course;
		this.sessionNumber = sessionNumber;
	}


	public CourseSession(Course course, short sessionNumber, short pageOrder) {
		this.course = course;
		this.sessionNumber = sessionNumber;
	}


	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public short getSessionNumber() {
		return sessionNumber;
	}
	public void setSessionNumber(short sessionNumber) {
		this.sessionNumber = sessionNumber;
	}
	
	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public List<LessonPlan> getLessonPlan() {
        return lessonPlan;
    }


    public void setLessonPlan(List<LessonPlan> lessonPlan) {
        this.lessonPlan = lessonPlan;
    }


  
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((lessonPlan == null) ? 0 : lessonPlan.hashCode());
        result = prime * result + ((pages == null) ? 0 : pages.hashCode());
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
        CourseSession other = (CourseSession) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (lessonPlan == null) {
            if (other.lessonPlan != null)
                return false;
        } else if (!lessonPlan.equals(other.lessonPlan))
            return false;
        if (pages == null) {
            if (other.pages != null)
                return false;
        } else if (!pages.equals(other.pages))
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
		try {
			return "CourseSession [course=" + (null==course?"Null":course) + ", sessionNumber=" + sessionNumber + ", pages=" + (pages!=null?pages.size():"none") + ", lessonPlan=" + (lessonPlan!=null?lessonPlan.size():"none") + "]";
		} catch(NullPointerException npe) {
			return "CourseSession [course=" + (null==course?"Null":course) + ", sessionNumber=" + sessionNumber + " pages=n/a]";
		}
	}

}
