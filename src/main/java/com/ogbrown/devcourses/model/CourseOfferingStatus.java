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

public enum CourseOfferingStatus {
    PLANNING, SCHEDULED, OPEN, IN_PROGRESS_OPEN, IN_PROGRESS_CLOSED, COMPLETED, CANCELLED, FULL, ON_HOLD, ARCHIVED;
    
    public static CourseOfferingStatus next(CourseOfferingStatus cos) {
        
        switch (cos) {
        case PLANNING:
            return SCHEDULED;
        case SCHEDULED:
            return OPEN;
        case OPEN:
            return IN_PROGRESS_OPEN;
        case IN_PROGRESS_OPEN:
            return IN_PROGRESS_CLOSED;
        case FULL:
            return IN_PROGRESS_CLOSED;
        case IN_PROGRESS_CLOSED:
            return COMPLETED;
        default:
            return cos;
        }
        
    }
}
