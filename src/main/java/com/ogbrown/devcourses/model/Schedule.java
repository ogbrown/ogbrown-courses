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

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Schedule {
	private List<SessionTime> sessionTimes;

    public Schedule() {

    }

    public List<SessionTime> getSessionTimes() {
        return sessionTimes;
    }

    public void setSessionTimes(List<SessionTime> sessionTimes) {
        this.sessionTimes = sessionTimes;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Schedule schedule = (Schedule) o;
		return Objects.equals(sessionTimes, schedule.sessionTimes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionTimes);
	}

	@Override
    public String toString() {
        return "Schedule [sessionTimes=" + sessionTimes + "]";
    }
	

}
class SessionTime {
	private Date startDate;
	private Date startTime;
	private Date endDate;
	private Date endTime;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SessionTime that = (SessionTime) o;
		return Objects.equals(startDate, that.startDate) &&
				Objects.equals(startTime, that.startTime) &&
				Objects.equals(endDate, that.endDate) &&
				Objects.equals(endTime, that.endTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(startDate, startTime, endDate, endTime);
	}

	@Override
	public String toString() {
		return "SessionTime [startDate=" + startDate + ", startTime=" + startTime + ", endDate=" + endDate
				+ ", endTime=" + endTime + "]";
	}
	
	
}
