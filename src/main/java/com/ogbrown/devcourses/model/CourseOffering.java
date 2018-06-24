package com.ogbrown.devcourses.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(contEdHours);
        result = prime * result + courseNumber;
        result = prime * result + ((courseOfferingStatus == null) ? 0 : courseOfferingStatus.hashCode());
        result = prime * result + ((daysOfWeek == null) ? 0 : daysOfWeek.hashCode());
        result = prime * result + Arrays.hashCode(daysOfWeekArray);
        result = prime * result + ((daysOfWeekCsv == null) ? 0 : daysOfWeekCsv.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((instructors == null) ? 0 : instructors.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((seats == null) ? 0 : seats.hashCode());
        result = prime * result + sessionCount;
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((term == null) ? 0 : term.hashCode());
        result = prime * result + termNumber;
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
        CourseOffering other = (CourseOffering) obj;
        if (Float.floatToIntBits(contEdHours) != Float.floatToIntBits(other.contEdHours))
            return false;
        if (courseNumber != other.courseNumber)
            return false;
        if (courseOfferingStatus != other.courseOfferingStatus)
            return false;
        if (daysOfWeek == null) {
            if (other.daysOfWeek != null)
                return false;
        } else if (!daysOfWeek.equals(other.daysOfWeek))
            return false;
        if (!Arrays.equals(daysOfWeekArray, other.daysOfWeekArray))
            return false;
        if (daysOfWeekCsv == null) {
            if (other.daysOfWeekCsv != null)
                return false;
        } else if (!daysOfWeekCsv.equals(other.daysOfWeekCsv))
            return false;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (instructors == null) {
            if (other.instructors != null)
                return false;
        } else if (!instructors.equals(other.instructors))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        if (seats == null) {
            if (other.seats != null)
                return false;
        } else if (!seats.equals(other.seats))
            return false;
        if (sessionCount != other.sessionCount)
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
            return false;
        if (term == null) {
            if (other.term != null)
                return false;
        } else if (!term.equals(other.term))
            return false;
        if (termNumber != other.termNumber)
            return false;
        return true;
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
