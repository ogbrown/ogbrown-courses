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
