package com.ogbrown.devcourses.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable(value="deviceCache")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Table(name="CourseLabels")
public class CourseLabel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
	@ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;
	private String label;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Date modified;

	
	public CourseLabel() {
		// TODO Auto-generated constructor stub
	}


	public CourseLabel(String label) {
        this.label = label;
    }


    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}


    public Course getCourse() {
        return course;
    }


    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getModified() {
        return modified;
    }


    public void setModified(Date modified) {
        this.modified = modified;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((modified == null) ? 0 : modified.hashCode());
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
        CourseLabel other = (CourseLabel) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (modified == null) {
            if (other.modified != null)
                return false;
        } else if (!modified.equals(other.modified))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "CourseLabel [id=" + id + ", course=" + course.getId() + ", label=" + label + ", modified="
                + modified + "]";
    }




}
