package com.ogbrown.devcourses.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class NoClassDate implements Serializable {

    public NoClassDate() {
        // TODO Auto-generated constructor stub
    }
    private static final long serialVersionUID = 1L;
    @Id
    @Temporal(TemporalType.DATE)
    private Date day;
    private String description;
   
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        NoClassDate other = (NoClassDate) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "NoClassDate [day=" + day + ", description=" + description + "]";
    }

   
    
}
