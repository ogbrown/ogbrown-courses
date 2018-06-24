package com.ogbrown.devcourses.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoClassDates {
    private static List<NoClassDate> noClassDates;
    private static List<LocalDate> dates;

    public NoClassDates() {
        noClassDates = new ArrayList<NoClassDate>();
        dates = new ArrayList<LocalDate>();
    }
    public NoClassDates(List<NoClassDate> list) {
        this();
        noClassDates.addAll(list);
        for (NoClassDate noClassDate: noClassDates) {
            dates.add(noClassDate.getDay());
        }
        
    }
    
    public static boolean containsDate(LocalDate date) {
        return dates.contains(date);
    }

}
