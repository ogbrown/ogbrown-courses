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

import java.io.Serializable;

public class CourseSessionPk implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long course;
	private Short sessionNumber;

	public CourseSessionPk() {
		
	}

	public CourseSessionPk(Long course, Short sessionNumber) {
		this.course = course;
		this.sessionNumber = sessionNumber;
	}

	public Long getCourseId() {
		return course;
	}

	public void setCourseId(Long course) {
		this.course = course;
	}

	public Short getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Short sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((sessionNumber == null) ? 0 : sessionNumber.hashCode());
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
        CourseSessionPk other = (CourseSessionPk) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (sessionNumber == null) {
            if (other.sessionNumber != null)
                return false;
        } else if (!sessionNumber.equals(other.sessionNumber))
            return false;
        return true;
    }

    @Override
	public String toString() {
		return "CourseSessionPk [course=" + course + ", sessionNumber=" + sessionNumber + "]";
	}
}
