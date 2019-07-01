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

public class CourseSessionPage extends CoursePage implements Cloneable {
    public final static short ALL_SESSION_NUMBERS=Short.MAX_VALUE;
    public final static short LAST_SESSION=Short.MAX_VALUE-1;
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Short sessionNum;

	public CourseSessionPage() {
		// TODO Auto-generated constructor stub
	}
	public CourseSessionPage(CourseSessionPage p) {
		super(p);
		this.setCourseId(p.getCourseId());
		this.setSessionNum(p.getSessionNum());
	}
	public CourseSessionPage(CoursePage p) {
		super(p);
		this.setCourseId(p.getCourseId());
	}

	public CourseSessionPage(Page p) {
		super(p);
	}
	public Short getSessionNum() {
		return sessionNum;
	}

	public void setSessionNum(Short sessionNum) {
		this.sessionNum = sessionNum;
	}
	@Override
	public CourseSessionPage clone() {
		CourseSessionPage courseSessionPage = new CourseSessionPage(super.clone());
		courseSessionPage.setSessionNum(this.sessionNum);
		return courseSessionPage;
	}
	

}
