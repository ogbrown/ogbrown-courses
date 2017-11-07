package com.ogbrown.devcourses.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable("courses")
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

	@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
	@OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="COURSE_ID")
	private List<CourseLabel> courseLabels;
	@OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="COURSE_ID")
	private List<CourseOffering> courseOfferings;
//	private String instructor;
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
    private List<Date> currentSchedule;
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
    

    public List<Date> getCurrentSchedule() {
        return currentSchedule;
    }


    public void setCurrentSchedule(List<Date> currentSchedule) {
        this.currentSchedule = currentSchedule;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseLabels == null) ? 0 : courseLabels.hashCode());
        result = prime * result + ((courseObjectives == null) ? 0 : courseObjectives.hashCode());
        result = prime * result + ((courseOfferings == null) ? 0 : courseOfferings.hashCode());
        result = prime * result + ((currentCourseOffering == null) ? 0 : currentCourseOffering.hashCode());
        result = prime * result + ((currentInstructor == null) ? 0 : currentInstructor.hashCode());
        result = prime * result + ((currentLabel == null) ? 0 : currentLabel.hashCode());
        result = prime * result + ((currentSchedule == null) ? 0 : currentSchedule.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lessonPlanMap == null) ? 0 : lessonPlanMap.hashCode());
        result = prime * result + numberOfSessions;
        result = prime * result + ((prerequisites == null) ? 0 : prerequisites.hashCode());
        result = prime * result + ((shortTitle == null) ? 0 : shortTitle.hashCode());
        result = prime * result + ((textBooks == null) ? 0 : textBooks.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((urlSlug == null) ? 0 : urlSlug.hashCode());
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
        Course other = (Course) obj;
        if (courseLabels == null) {
            if (other.courseLabels != null)
                return false;
        } else if (!courseLabels.equals(other.courseLabels))
            return false;
        if (courseObjectives == null) {
            if (other.courseObjectives != null)
                return false;
        } else if (!courseObjectives.equals(other.courseObjectives))
            return false;
        if (courseOfferings == null) {
            if (other.courseOfferings != null)
                return false;
        } else if (!courseOfferings.equals(other.courseOfferings))
            return false;
        if (currentCourseOffering == null) {
            if (other.currentCourseOffering != null)
                return false;
        } else if (!currentCourseOffering.equals(other.currentCourseOffering))
            return false;
        if (currentInstructor == null) {
            if (other.currentInstructor != null)
                return false;
        } else if (!currentInstructor.equals(other.currentInstructor))
            return false;
        if (currentLabel == null) {
            if (other.currentLabel != null)
                return false;
        } else if (!currentLabel.equals(other.currentLabel))
            return false;
        if (currentSchedule == null) {
            if (other.currentSchedule != null)
                return false;
        } else if (!currentSchedule.equals(other.currentSchedule))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lessonPlanMap == null) {
            if (other.lessonPlanMap != null)
                return false;
        } else if (!lessonPlanMap.equals(other.lessonPlanMap))
            return false;
        if (numberOfSessions != other.numberOfSessions)
            return false;
        if (prerequisites == null) {
            if (other.prerequisites != null)
                return false;
        } else if (!prerequisites.equals(other.prerequisites))
            return false;
        if (shortTitle == null) {
            if (other.shortTitle != null)
                return false;
        } else if (!shortTitle.equals(other.shortTitle))
            return false;
        if (textBooks == null) {
            if (other.textBooks != null)
                return false;
        } else if (!textBooks.equals(other.textBooks))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (urlSlug == null) {
            if (other.urlSlug != null)
                return false;
        } else if (!urlSlug.equals(other.urlSlug))
            return false;
        return true;
    }


    @Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", shortTitle=" + shortTitle + ", courseLabels=" + courseLabels
				+ ", courseOfferings=" + courseOfferings + ", urlSlug=" + urlSlug
				+ ", numberOfSessions=" + numberOfSessions + ", numberOfPreReqs=" + prerequisites.size() + "]";
	}

}
