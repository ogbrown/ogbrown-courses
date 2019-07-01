/*
 * Copyright (c) 2017 - 2019 Oswald G. Brown, III
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ogbrown.devcourses.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Cacheable(value="deviceCache")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String shortTitle;
	
	@OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="COURSE_ID")
	private List<CourseLabel> courseLabels;
	@OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="COURSE_ID")
	private List<CourseOffering> courseOfferings;

	@Column(unique=true)
	private String urlSlug;
	@Column(columnDefinition="SMALLINT")
	private short numberOfSessions;
	@ManyToMany(targetEntity = Course.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "course_prereqs", joinColumns = @JoinColumn( name="id"),
            inverseJoinColumns = @JoinColumn( name="prereq_id"))
	private Set<Course> prerequisites = new HashSet<Course>();
	@ManyToMany(targetEntity = TextBook.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "course_textbooks", joinColumns = @JoinColumn( name="id"),
            inverseJoinColumns = @JoinColumn( name="textbook_id"))
    private Set<TextBook> textBooks = new HashSet<TextBook>();
	@ManyToMany(targetEntity = Objective.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "course_objectives", joinColumns = @JoinColumn( name="course_id"),
            inverseJoinColumns = @JoinColumn( name="objective_id"))
	private List<Objective> courseObjectives; 
	@Transient
	private String currentLabel;
	@Transient
	private Instructor currentInstructor;
	@Transient
	private CourseOffering currentCourseOffering;
	//use CourseService to update currentSchedule
	@Transient
    private List<LocalDate> currentSchedule;
	@Transient
	private SortedMap<Short,List<LessonPlan>> lessonPlanMap;
	@Transient
	private String prerequisitesShortNames;

	public Course() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}


	public String getCourseLabel() {
		return courseLabels.get(courseLabels.size()-1).getLabel();
	}
	public void setCourseLabel(CourseLabel courseLabel) {
		this.courseLabels.add(courseLabel);
	}


	public List<CourseLabel> getCourseLabels() {
        return courseLabels;
    }


    public void setCourseLabels(List<CourseLabel> courseLabels) {
        this.courseLabels = courseLabels;
    }


    public List<CourseOffering> getCourseOfferings() {
		return courseOfferings;
	}
	public void setCourseOfferings(List<CourseOffering> courseOfferings) {
		this.courseOfferings = courseOfferings;
	}


	public String getUrlSlug() {
		return urlSlug;
	}
	public void setUrlSlug(String urlSlug) {
		this.urlSlug = urlSlug;
	}


	
	public short getNumberOfSessions() {
		return numberOfSessions;
	}
	public void setNumberOfSessions(short numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}


	public Set<Course> getPrerequisites() {
        return prerequisites;
    }


    public void setPrerequisites(Set<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public Set<TextBook> getTextBooks() {
        return textBooks;
    }


    public void setTextBooks(Set<TextBook> textBooks) {
        this.textBooks = textBooks;
    }


    public List<Objective> getCourseObjectives() {
        return courseObjectives;
    }


    public void setCourseObjectives(List<Objective> courseObjectives) {
        this.courseObjectives = courseObjectives;
    }


    public String getCurrentLabel() {
        return this.courseLabels.get(courseLabels.size() - 1).getLabel();
    }
    public CourseOffering getCurrentCourseOffering() {
        return this.courseOfferings.get(0);
    }
    public Instructor getCurrentInstructor() {
        return getCurrentCourseOffering().getInstructors().get(getCurrentCourseOffering().getInstructors().size() - 1);
    }
    

    public List<LocalDate> getCurrentSchedule() {
        return currentSchedule;
    }


    public void setCurrentSchedule(List<LocalDate> list) {
        this.currentSchedule = list;
    } 


    public SortedMap<Short, List<LessonPlan>> getLessonPlanMap() {
        return lessonPlanMap;
    }


    public void setLessonPlanMap(SortedMap<Short, List<LessonPlan>> lessonPlanMap) {
        this.lessonPlanMap = lessonPlanMap;
    }
    
    public String getPrerequisitesShortNames() {
        if (prerequisitesShortNames == null) {
            if (prerequisites != null && prerequisites.size() > 0) {
                for (Course c : prerequisites) {
                    if (prerequisitesShortNames == null) {
                        prerequisitesShortNames = "";
                    } else {
                        prerequisitesShortNames += ", ";
                    }
                    prerequisitesShortNames += c.getShortTitle();
                }
            } else {
                prerequisitesShortNames = "";
            }
        }
        return prerequisitesShortNames;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return numberOfSessions == course.numberOfSessions &&
                Objects.equals(id, course.id) &&
                Objects.equals(title, course.title) &&
                Objects.equals(shortTitle, course.shortTitle) &&
                Objects.equals(courseLabels, course.courseLabels) &&
                Objects.equals(courseOfferings, course.courseOfferings) &&
                Objects.equals(urlSlug, course.urlSlug) &&
                Objects.equals(prerequisites, course.prerequisites) &&
                Objects.equals(textBooks, course.textBooks) &&
                Objects.equals(courseObjectives, course.courseObjectives) &&
                Objects.equals(currentLabel, course.currentLabel) &&
                Objects.equals(currentInstructor, course.currentInstructor) &&
                Objects.equals(currentCourseOffering, course.currentCourseOffering) &&
                Objects.equals(currentSchedule, course.currentSchedule) &&
                Objects.equals(lessonPlanMap, course.lessonPlanMap) &&
                Objects.equals(prerequisitesShortNames, course.prerequisitesShortNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortTitle, courseLabels, courseOfferings, urlSlug, numberOfSessions, prerequisites, textBooks, courseObjectives, currentLabel, currentInstructor, currentCourseOffering, currentSchedule, lessonPlanMap, prerequisitesShortNames);
    }

    @Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", shortTitle=" + shortTitle + ", courseLabels=" + courseLabels
				+ ", courseOfferings=" + courseOfferings + ", urlSlug=" + urlSlug
				+ ", numberOfSessions=" + numberOfSessions + ", numberOfPreReqs=" + prerequisites.size() + "]";
	}

}
