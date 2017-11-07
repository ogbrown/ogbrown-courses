package com.ogbrown.devcourses.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoClassDates {
    private static List<NoClassDate> noClassDates;
    private static List<Date> dates;

    public NoClassDates() {
        noClassDates = new ArrayList<NoClassDate>();
        dates = new ArrayList<Date>();
    }
    public NoClassDates(List<NoClassDate> list) {
        this();
        noClassDates.addAll(list);
        for (NoClassDate noClassDate: noClassDates) {
            dates.add(noClassDate.getDay());
        }
        
    }
    
    public static boolean containsDate(Date date) {
        return dates.contains(date);
    }

}
