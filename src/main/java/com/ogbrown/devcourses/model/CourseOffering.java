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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable(value="deviceCache")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class CourseOffering implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int courseNumber;
	private int termNumber;
	private String term;
	private String location;
	private String room;
//	@Temporal(TemporalType.DATE)
	private LocalDate start;
//	@Temporal(TemporalType.TIME)
	private LocalDateTime startTime;
//	@Temporal(TemporalType.TIME)
	private LocalDateTime endTime;
//	@Temporal(TemporalType.DATE)
	private LocalDate end;
	private String daysOfWeek; 
	private int sessionCount;
	private float contEdHours;
	@ManyToMany(targetEntity = Instructor.class, cascade = { CascadeType.REFRESH })
	@JoinTable(name = "courseoffering_instructors")
	@JoinColumns({
        @JoinColumn(name="COURSEOFFERING_ID", referencedColumnName="ID"),
        @JoinColumn(name="INSTRUCTOR_ID", referencedColumnName="ID")
    })
	private List<Instructor> instructors;
	private Integer seats = 16;
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private CourseOfferingStatus courseOfferingStatus;
	//Optional: Few use cases... one is for the schedule...
	@Transient
	private DayOfWeek [] daysOfWeekArray;
	//Optional: Few use cases... one is for the schedule...
    @Transient
    private String daysOfWeekCsv="";	
	
	public CourseOffering() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public int getTermNumber() {
		return termNumber;
	}

	public void setTermNumber(int termNumber) {
		this.termNumber = termNumber;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public int getSessionCount() {
		return sessionCount;
	}

	public void setSessionCount(int sessionCount) {
		this.sessionCount = sessionCount;
	}

	public float getContEdHours() {
		return contEdHours;
	}

	public void setContEdHours(float contEdHours) {
		this.contEdHours = contEdHours;
	}

	public String getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public DayOfWeek[] getDaysOfWeekArray() {
        return daysOfWeekArray;
    }

    public void setDaysOfWeekArray(DayOfWeek[] daysOfWeekArray) {
        this.daysOfWeekArray = daysOfWeekArray;
    }

    public String getDaysOfWeekCsv() {
        return daysOfWeekCsv;
    }

    public void setDaysOfWeekCsv(String daysOfWeekCsv) {
        this.daysOfWeekCsv = daysOfWeekCsv;
    }

    public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	
	public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public CourseOfferingStatus getCourseOfferingStatus() {
        return courseOfferingStatus;
    }

    public void setCourseOfferingStatus(CourseOfferingStatus courseOfferingStatus) {
        this.courseOfferingStatus = courseOfferingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseOffering that = (CourseOffering) o;
        return courseNumber == that.courseNumber &&
                termNumber == that.termNumber &&
                sessionCount == that.sessionCount &&
                Float.compare(that.contEdHours, contEdHours) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(term, that.term) &&
                Objects.equals(location, that.location) &&
                Objects.equals(room, that.room) &&
                Objects.equals(start, that.start) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(end, that.end) &&
                Objects.equals(daysOfWeek, that.daysOfWeek) &&
                Objects.equals(instructors, that.instructors) &&
                Objects.equals(seats, that.seats) &&
                courseOfferingStatus == that.courseOfferingStatus &&
                Arrays.equals(daysOfWeekArray, that.daysOfWeekArray) &&
                Objects.equals(daysOfWeekCsv, that.daysOfWeekCsv);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, courseNumber, termNumber, term, location, room, start, startTime, endTime, end, daysOfWeek, sessionCount, contEdHours, instructors, seats, courseOfferingStatus, daysOfWeekCsv);
        result = 31 * result + Arrays.hashCode(daysOfWeekArray);
        return result;
    }

    @Override
	public String toString() {
		return "CourseOffering [id=" + id + ", courseNumber=" + courseNumber + ", termNumber=" + termNumber + ", term="
				+ term + ", location=" + location + ", room=" + room + ", start=" + start + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", end=" + end + ", daysOfWeek=" + daysOfWeek + ", sessionCount="
				+ sessionCount + ", contEdHours=" + contEdHours + ", instructors=" + instructors + ", seats="
				+ seats + ", status=" + courseOfferingStatus + "]";
	}



	

}
