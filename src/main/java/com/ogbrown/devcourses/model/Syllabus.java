package com.ogbrown.devcourses.model;

public class Syllabus {
	private Course course;
	private Schedule schedule;
	private String description;
	private String objectives;
	private String lessonPlan;
    public Syllabus() {
        // TODO Auto-generated constructor stub
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public Schedule getSchedule() {
        return schedule;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getObjectives() {
        return objectives;
    }
    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }
    public String getLessonPlan() {
        return lessonPlan;
    }
    public void setLessonPlan(String lessonPlan) {
        this.lessonPlan = lessonPlan;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((lessonPlan == null) ? 0 : lessonPlan.hashCode());
        result = prime * result + ((objectives == null) ? 0 : objectives.hashCode());
        result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
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
        Syllabus other = (Syllabus) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (lessonPlan == null) {
            if (other.lessonPlan != null)
                return false;
        } else if (!lessonPlan.equals(other.lessonPlan))
            return false;
        if (objectives == null) {
            if (other.objectives != null)
                return false;
        } else if (!objectives.equals(other.objectives))
            return false;
        if (schedule == null) {
            if (other.schedule != null)
                return false;
        } else if (!schedule.equals(other.schedule))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Syllabus [course=" + course + ", schedule=" + schedule + ", description=" + description
                + ", objectives=" + objectives + ", lessonPlan=" + lessonPlan + "]";
    }

}
