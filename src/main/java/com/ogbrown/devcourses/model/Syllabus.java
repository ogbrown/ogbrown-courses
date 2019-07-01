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

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Syllabus syllabus = (Syllabus) o;
        return Objects.equals(course, syllabus.course) &&
                Objects.equals(schedule, syllabus.schedule) &&
                Objects.equals(description, syllabus.description) &&
                Objects.equals(objectives, syllabus.objectives) &&
                Objects.equals(lessonPlan, syllabus.lessonPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, schedule, description, objectives, lessonPlan);
    }

    @Override
    public String toString() {
        return "Syllabus [course=" + course + ", schedule=" + schedule + ", description=" + description
                + ", objectives=" + objectives + ", lessonPlan=" + lessonPlan + "]";
    }

}
