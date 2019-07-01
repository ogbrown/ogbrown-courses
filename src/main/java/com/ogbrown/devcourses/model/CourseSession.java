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
import java.util.List;
import java.util.Objects;

@Cacheable(value="deviceCache")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSession that = (CourseSession) o;
        return Objects.equals(course, that.course) &&
                Objects.equals(sessionNumber, that.sessionNumber) &&
                Objects.equals(pages, that.pages) &&
                Objects.equals(lessonPlan, that.lessonPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, sessionNumber, pages, lessonPlan);
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
