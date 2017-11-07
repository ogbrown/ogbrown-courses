package com.ogbrown.utility.time;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaysOfWeekUtility {
    private static Logger logger = LoggerFactory.getLogger(DaysOfWeekUtility.class);

    public DaysOfWeekUtility() {
        // TODO Auto-generated constructor stub
    }
    public static DayOfWeek getDayOfWeek(String dayOfWeek) {
        dayOfWeek = dayOfWeek.toLowerCase().substring(0,2);
        switch (dayOfWeek) {
        case "su":
            return DayOfWeek.SUNDAY;
        case "mo":
            return DayOfWeek.MONDAY;
        case "tu":
            return DayOfWeek.TUESDAY;
        case "we":
            return DayOfWeek.WEDNESDAY;
        case "th":
            return DayOfWeek.THURSDAY;            
        case "fr":
            return DayOfWeek.FRIDAY;            
        case "sa":
            return DayOfWeek.SATURDAY;            
            
        }
        logger.warn("Input must be at least a 2 character representation of a day of the week.");
        return null;
    }
    
    public static String getDaysOfWeekCsv(TextStyle textStyle, String daysOfWeek) {
        String output = "";
        String[] daysOfWeeksArray = daysOfWeek.split(",");
        for (String day: daysOfWeeksArray) {
            day = day.toLowerCase();
            switch (day) {
            case "m":
                day="mo";
                break;
            case "t":
                day="tu";
                break;
            case "w":
                day="we";
                break;
            case "f":
                day="fr";
                break;
            case "s":
                day="sa";
                break;
            }
            if (output.length() > 0 ) output+=", ";
            output += getDayOfWeek(day).getDisplayName(textStyle, Locale.US);
        }
        return output;
    }
    public static DayOfWeek [] getDaysOfWeekArray(String daysOfWeek) {
        ArrayList<DayOfWeek> daysOfWeekArray = new ArrayList<>();;
        String[] daysOfWeeksArray = daysOfWeek.split(",");
        for (String day: daysOfWeeksArray) {
            day = day.toLowerCase();
            switch (day) {
            case "m":
                day="mo";
                break;
            case "t":
                day="tu";
                break;
            case "w":
                day="we";
                break;
            case "f":
                day="fr";
                break;
            case "s":
                day="sa";
                break;
            }
            daysOfWeekArray.add(getDayOfWeek(day));
        }
        return daysOfWeekArray.toArray(new DayOfWeek[daysOfWeekArray.size()]);
    }
}
