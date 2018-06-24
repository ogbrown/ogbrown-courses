package com.ogbrown.devcourses.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Cacheable(value="deviceCache")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class LessonPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lesson;
    private Integer defaultSession;
    private Integer lessonOrd;
    
    public LessonPlan() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getDefaultSession() {
        return defaultSession;
    }

    public void setDefaultSession(int defaultSession) {
        this.defaultSession = defaultSession;
    }

    public int getLessonOrd() {
        return lessonOrd;
    }

    public void setLessonOrd(int lessonOrder) {
        this.lessonOrd = lessonOrder;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + defaultSession;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lesson == null) ? 0 : lesson.hashCode());
        result = prime * result + lessonOrd;
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
        LessonPlan other = (LessonPlan) obj;
        if (defaultSession != other.defaultSession)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lesson == null) {
            if (other.lesson != null)
                return false;
        } else if (!lesson.equals(other.lesson))
            return false;
        if (lessonOrd != other.lessonOrd)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LessonPlan [id=" + id + ", lesson=" + lesson + ", defaultSession=" + defaultSession + ", lessonOrd="
                + lessonOrd + "]";
    }
    
}
